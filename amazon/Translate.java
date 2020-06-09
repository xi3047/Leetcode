package amazon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;

public class Translate {
    /**
     * Xi Zhang
     * Amazon Onsite 4/3/2019 Problem
     * @param word 需要被翻译的单词
     * @param from 单词的原语言
     * @param to 要翻译成的语言
     * @return 翻译后的单词
     */

    public static void main(String[] args) throws IOException {
        String text = "Hello World!";
        System.out.println("Translated text: " + translate(text, "en", "es"));
    }

    private static String translate(String text, String langFrom, String langTo) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycby3eN8TftNjBQ0p2fXQqZWfWZoNf08CvL0lnG2zTsL1foQGoAY/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}
