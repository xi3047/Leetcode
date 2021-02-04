package round2.system_database_concurrency.gsonExample;

import com.google.gson.Gson;
import org.joda.time.Days;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import org.joda.time.DateTime;
/**
 * @author Xi Zhang
 * @date 2/2/21 11:31 下午
 * @topic round2.system_database_concurrency.gsonExample
 * @link
 * @description
 */
public class PartnerList {
    List<Partner> partners;

    public PartnerList(List<Partner> partners) {
        this.partners = partners;
    }

    static class Partner {
        String firstName;
        String lastName;
        String email;
        String country;
        List<String> availableDates;
    }

    public static void main(String[] args) {

        Gson gson = new Gson();
        String response = "{\n" +
                "    \"partners\": [\n" +
                "        {\n" +
                "            \"firstName\": \"Darin\",\n" +
                "            \"lastName\": \"Daignault\",\n" +
                "            \"email\": \"ddaignault@hubspotpartners.com\",\n" +
                "            \"country\": \"United States\",\n" +
                "            \"availableDates\": [\n" +
                "                \"2017-05-03\",\n" +
                "                \"2017-05-06\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Crystal\",\n" +
                "            \"lastName\": \"Brenna\",\n" +
                "            \"email\": \"cbrenna@hubspotpartners.com\",\n" +
                "            \"country\": \"Ireland\",\n" +
                "            \"availableDates\": [\n" +
                "                \"2017-04-27\",\n" +
                "                \"2017-04-29\",\n" +
                "                \"2017-04-30\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Janyce\",\n" +
                "            \"lastName\": \"Gustison\",\n" +
                "            \"email\": \"jgustison@hubspotpartners.com\",\n" +
                "            \"country\": \"Spain\",\n" +
                "            \"availableDates\": [\n" +
                "                \"2017-04-29\",\n" +
                "                \"2017-04-30\",\n" +
                "                \"2017-05-01\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Tifany\",\n" +
                "            \"lastName\": \"Mozie\",\n" +
                "            \"email\": \"tmozie@hubspotpartners.com\",\n" +
                "            \"country\": \"Spain\",\n" +
                "            \"availableDates\": [\n" +
                "                \"2017-04-28\",\n" +
                "                \"2017-04-29\",\n" +
                "                \"2017-05-01\",\n" +
                "                \"2017-05-04\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Temple\",\n" +
                "            \"lastName\": \"Affelt\",\n" +
                "            \"email\": \"taffelt@hubspotpartners.com\",\n" +
                "            \"country\": \"Spain\",\n" +
                "            \"availableDates\": [\n" +
                "                \"2017-04-28\",\n" +
                "                \"2017-04-29\",\n" +
                "                \"2017-05-02\",\n" +
                "                \"2017-05-04\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Robyn\",\n" +
                "            \"lastName\": \"Yarwood\",\n" +
                "            \"email\": \"ryarwood@hubspotpartners.com\",\n" +
                "            \"country\": \"Spain\",\n" +
                "            \"availableDates\": [\n" +
                "                \"2017-04-29\",\n" +
                "                \"2017-04-30\",\n" +
                "                \"2017-05-02\",\n" +
                "                \"2017-05-03\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Shirlene\",\n" +
                "            \"lastName\": \"Filipponi\",\n" +
                "            \"email\": \"sfilipponi@hubspotpartners.com\",\n" +
                "            \"country\": \"Spain\",\n" +
                "            \"availableDates\": [\n" +
                "                \"2017-04-30\",\n" +
                "                \"2017-05-01\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Oliver\",\n" +
                "            \"lastName\": \"Majica\",\n" +
                "            \"email\": \"omajica@hubspotpartners.com\",\n" +
                "            \"country\": \"Spain\",\n" +
                "            \"availableDates\": [\n" +
                "                \"2017-04-28\",\n" +
                "                \"2017-04-29\",\n" +
                "                \"2017-05-01\",\n" +
                "                \"2017-05-03\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Wilber\",\n" +
                "            \"lastName\": \"Zartman\",\n" +
                "            \"email\": \"wzartman@hubspotpartners.com\",\n" +
                "            \"country\": \"Spain\",\n" +
                "            \"availableDates\": [\n" +
                "                \"2017-04-29\",\n" +
                "                \"2017-04-30\",\n" +
                "                \"2017-05-02\",\n" +
                "                \"2017-05-03\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Eugena\",\n" +
                "            \"lastName\": \"Auther\",\n" +
                "            \"email\": \"eauther@hubspotpartners.com\",\n" +
                "            \"country\": \"United States\",\n" +
                "            \"availableDates\": [\n" +
                "                \"2017-05-04\",\n" +
                "                \"2017-05-09\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        PartnerList partnerList = gson.fromJson(response, PartnerList.class);
        Output output = partnerList.processData(partnerList);
        sendPOST(output);
    }

    public static void sendPOST(Output output) {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        Gson gson = new Gson();
        String json = gson.toJson(output);
        HttpRequest postRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("https://reqbin.com/echo/post/json"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(response.statusCode());
        }

    }

    public Output processData(PartnerList partnerList) {
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
                    startDate = date;
                }
            }

            List<String> emailList = dateToEmails.getOrDefault(startDate, new ArrayList<>());
            CountryInfo countryInfo = new CountryInfo(emailList.size(), emailList, country, startDate);
            countryInfoList.add(countryInfo);
        }
        Output output = new Output(countryInfoList);
        return output;
    }


    class Output {
        List<CountryInfo> countries;

        public Output(List<CountryInfo> countries) {
            this.countries = countries;
        }
    }

    class CountryInfo {
        int attendeeCount;
        List<String> attendees;
        String name;
        String startDate;

        public CountryInfo(int attendeeCount, List<String> attendees, String name, String startDate) {
            this.attendeeCount = attendeeCount;
            this.attendees = attendees;
            this.name = name;
            this.startDate = startDate;
        }
    }


    private boolean isConsecutiveDays(String firstDate, String secondDate) {
        DateTime start = new DateTime(firstDate);
        DateTime end = new DateTime(secondDate);
        int daysBetween = Days.daysBetween(start, end).getDays();
        return daysBetween < 2;
    }

}
