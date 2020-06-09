package OOD.RestaurantReseverationSystem;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FLK on 2019-02-16.
 * The class of reservation object that is used by Restaurant
 */
public class Reservation {

    //The id of the reservation
    private final String id;

    //The size of the party
    private final int size;

    //The start time of this reservation
    private final long startTime;

    //The end time of the reservation
    private final long endTime;

    //The time period
    private final long[] reservationPeriod;

    //The data structure to save extra information
    private final Map<String,Object> extra;

    /*
     * Constructor
     * The builder design pattern is used here to build the reservation
     * @param builder    The object to build the reservation
     */
    private Reservation(@NotNull final ReservationBuilder builder){
        this.id = builder.id;
        this.size = builder.size;
        this.startTime = builder.startTime;
        this.endTime =builder.endTime;
        reservationPeriod = new long[]{startTime,endTime};
        this.extra = new HashMap<>();
    }

    //Getters
    public String getId(){
        return id;
    }

    public int getSize(){
        return size;
    }

    public long getStartTime(){
        return startTime;
    }

    public long getEndTime(){
        return endTime;
    }

    public long[] getReservationPeriod() throws IllegalArgumentException {
        if (startTime < endTime) {
            return reservationPeriod;
        } else {
           throw new IllegalArgumentException();
        }


    }

    public Map<String,Object> getExtra(){
        return extra;
    }


    /*
     * The class of Reservation build that can build a reservation object
     * It uses the builder design pattern here.
     */
    public static class ReservationBuilder{

        //The id of the reservation
        private final String id;

        //The size of the party
        private final int size;

        //The start time of this reservation
        private long startTime;

        //The end time of the reservation
        private long endTime;

        /*
         * Constructor
         * @param id    The id of this reservation
         * @param size  The size of the party
         */
        public ReservationBuilder(@NotNull final String id,final int size){
            this.id = id;
            this.size = size;
        }

        public ReservationBuilder withStartTime(final long startTime){
            this.startTime = startTime;
            return this;
        }

        public ReservationBuilder withEndTime(final long endTime){
            this.endTime = endTime;
            return this;
        }

        public Reservation build(){
            return new Reservation(this);
        }
    }
}
