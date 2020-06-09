package OOD.RestaurantReseverationSystem;

/**
 * Created by FLK on 2019-02-16.
 * The possible state of the table
 */
public enum TableState {
    //Stands for the table is empty
    Empty,
    //Stands for the table has reservation
    Reserved,
    //Stands for the table is currently being used
    Occupy;
}
