package round2.system_database_concurrency.hubspot;

import com.google.gson.Gson;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

/**
 * @author Xi Zhang
 * @date 2/3/21 3:26 下午
 * @topic round2.system_database_concurrency.hubspot
 * @link
 * @description
 *
 * Get API endpoint:
 * https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=7b012dedf8283646f91dc65fcc30
 *
 * POST API endpoint:
 * https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=7b012dedf8283646f91dc65fcc30
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=7b012dedf8283646f91dc65fcc30")).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        PartnerList partnerList = deserialize(response.body());
        Output output = processData(partnerList);
        String json = serialize(output);

        HttpRequest postRequest = HttpRequest.newBuilder()
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .uri(URI.create("https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=7b012dedf8283646f91dc65fcc30"))
        .build();
        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.statusCode());

    }
    public static PartnerList deserialize(String responseBody) {
        Gson gson = new Gson();
        PartnerList res = gson.fromJson(responseBody, PartnerList.class);
        return res;
    }

    public static String serialize(Output output) {
        Gson gson = new Gson();
        return gson.toJson(output);
    }

    public static Output processData(PartnerList partnerList) {
        Map<String, Map<String, List<String>>> countriesToEmail = new HashMap<>();

        List<Partner> partners = partnerList.partners;
        for (Partner partner : partners) {
            String country = partner.country;
            String email = partner.email;
            List<String> dates = partner.availableDates;
            countriesToEmail.putIfAbsent(country, new HashMap<>());
            Map<String, List<String>> dateToEmails = countriesToEmail.get(country);

            for (int i = 1; i < dates.size(); i++) {
                if (isConsecutiveDays(dates.get(i - 1), dates.get(i))) {
                    String startDate = dates.get(i - 1);
                    dateToEmails.putIfAbsent(startDate, new ArrayList<>());
                    dateToEmails.get(startDate).add(email);
                }
            }
        }
        List<CountryInfo> countryInfoList = new ArrayList<>();
        for (String country : countriesToEmail.keySet()) {
            Map<String, List<String>> dateToEmails = countriesToEmail.get(country);
            int max = 0;
            String startDate = "";
            for (String date : dateToEmails.keySet()) {
                if (dateToEmails.get(date).size() > max) {
                    max = dateToEmails.get(date).size();
                }
            }
            List<String> datesWithSameMax = new ArrayList<>();
            for (String date : dateToEmails.keySet()) {
                if (dateToEmails.get(date).size() == max) {
                    datesWithSameMax.add(date);
                }
            }
            Collections.sort(datesWithSameMax);
            startDate = datesWithSameMax.get(0);
            // if there are multiple dates, we should pick the earliest of them

            List<String> emailList = dateToEmails.getOrDefault(startDate, new ArrayList<>());
            CountryInfo countryInfo = new CountryInfo(emailList.size(), emailList, country, startDate);
            countryInfoList.add(countryInfo);
        }
        return new Output(countryInfoList);
    }

    private static boolean isConsecutiveDays(String firstDate, String secondDate) {
        DateTime start = new DateTime(firstDate);
        DateTime end = new DateTime(secondDate);
        int daysBetween = Days.daysBetween(start, end).getDays();
        return daysBetween < 2;
    }
}
