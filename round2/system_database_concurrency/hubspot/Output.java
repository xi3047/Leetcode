package round2.system_database_concurrency.hubspot;


import java.util.List;

/**
 * @author Xi Zhang
 * @date 2/3/21 3:41 下午
 * @topic round2.system_database_concurrency.hubspot
 * @link
 * @description
 */
public class Output {
    List<CountryInfo> countries;

    public Output(List<CountryInfo> countries) {
        this.countries = countries;
    }
}
