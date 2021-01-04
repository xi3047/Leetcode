package round2.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Xi Zhang
 * @date 1/1/2021 1:08 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/web-crawler-multithreaded/
 * @description
 */
public class L1242_WebCrawler {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
       // find hostname
        int index = startUrl.indexOf('/', 7);
        String hostname = (index != -1) ? startUrl.substring(0, index) : startUrl;

        // multi-thread
        Crawler crawler = new Crawler(startUrl, hostname, htmlParser);
        Crawler.map = new ConcurrentHashMap<>();
        Crawler.result = ConcurrentHashMap.newKeySet();
        Thread thread = new Thread(crawler);
        thread.start();

        Crawler.joinThread(thread);
        return new ArrayList<>(Crawler.result);

    }
}

class Crawler implements Runnable {
    String startUrl;
    String hostname;
    HtmlParser htmlParser;
    public static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    public static Set<String> result = ConcurrentHashMap.newKeySet();

    public Crawler(String startUrl, String hostname, HtmlParser htmlParser) {
        this.startUrl = startUrl;
        this.hostname = hostname;
        this.htmlParser = htmlParser;
    }

    @Override
    public void run() {
        if (this.startUrl.startsWith(hostname) && !result.contains(this.startUrl)) {
            result.add(this.startUrl);
            List<Thread> threads = new ArrayList<>();
            for (String s : htmlParser.getUrls(startUrl)) {
                if (result.contains(s)) continue;
                Crawler crawler = new Crawler(s, hostname, htmlParser);
                Thread thread = new Thread(crawler);
                thread.start();
                threads.add(thread);
            }
            for (Thread thread : threads) {
                joinThread(thread); // wait for all threads to complete
            }

        }
    }
    public static void joinThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}

interface HtmlParser {
      public List<String> getUrls(String url);
}