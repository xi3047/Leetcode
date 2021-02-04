package round2.system_database_concurrency.hubspot;

import java.util.List;

/**
 * @author Xi Zhang
 * @date 2/3/21 3:40 下午
 * @topic round2.system_database_concurrency.hubspot
 * @link
 * @description
 */
public class CountryInfo {
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
