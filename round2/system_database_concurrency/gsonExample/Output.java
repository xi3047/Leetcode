package round2.system_database_concurrency.gsonExample;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 2/3/21 12:07 上午
 * @topic round2.system_database_concurrency.gsonExample
 * @link
 * @description
 */
public class Output {
    List<CountryInfo> countries;

    public Output(List<CountryInfo> countries) {
        this.countries = countries;
    }


    public static void main(String[] args) {
        List<String> emailList1 = Arrays.asList("a.gmail", "b.gmail");
        CountryInfo countryInfo1 = new CountryInfo(2, emailList1, "Spain", "2017=04-28");
        List<String> emailList2 = new ArrayList<>();
        CountryInfo countryInfo2 = new CountryInfo(0, emailList2, "US", "null");
        List<CountryInfo> countryInfoList = new ArrayList<>();
        countryInfoList.add(countryInfo1);
        countryInfoList.add(countryInfo2);
        Output jsonOutput = new Output(countryInfoList);
        Gson gson = new Gson();
        String json = gson.toJson(jsonOutput);

    }

//    static class CountryInfo {
//        int attendeeCount;
//        List<String> attendees;
//        String name;
//        String startDate;
//
//        public CountryInfo(int attendeeCount, List<String> attendees, String name, String startDate) {
//            this.attendeeCount = attendeeCount;
//            this.attendees = attendees;
//            this.name = name;
//            this.startDate = startDate;
//        }
//    }
}



