package OOD.RestaurantReseverationSystem;

/**
 * Created by FLK on 2019-02-16.
 * The possible table type
 */
public enum TableType {
    //Small table with 4 people max
    SMALL("SMALL",4),
    //Medium table with 8 people max
    MEDIUM("MEDIUM",8),
    //Large table with 12 people max
    LARGE("LARGE",12);

    //The string of the type
    private final String type;

    //The int of the capacity
    private final int capacity;

    //Constructor
    TableType(final String type, final int capacity){
        this.type = type;
        this.capacity = capacity;
    }

    //Getter of the type name
    public String toString(){
        return type;
    }

    //Getter of the capacity
    public int getCapacity(){
        return capacity;
    }
}
