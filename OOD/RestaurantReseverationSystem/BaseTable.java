package OOD.RestaurantReseverationSystem;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by FLK on 2019-02-16.
 *
 * The base class that handle the reserve/cancel logic.
 */
public abstract class BaseTable {

    //The id of the table
    private final String tableId;

    //The type of the table
    private final TableType tableType;

    //The max number of customer this table can take
    private final int capacity;

    /*
     *  The Map to track the extra info
     *  Key is the name of the field as a string
     *  Value if the actual object
     */
    private final Map<String,Object> extra;

    /*
     *  The Map to track reservation with the time period
     *  Key is the reservation id
     *  Value is the corresponding time period
     */
    private final Map<String,long[]> reservations;

    //The list to save the listener of this class
    private final List<ITableEventListener> tableEventListeners;

    //The current state of this table
    private TableState state;

    /*
     * Constructor
     * @param id            The id of this table
     * @param tableType     The type of this table
     * We must have a table ID and a table type.
     */
    public BaseTable(@NotNull final String id,@NotNull final TableType tableType){
        this.tableId = id;
        this.tableType = tableType;
        this.capacity = tableType.getCapacity();

        tableEventListeners = new ArrayList<>();
        reservations = new HashMap<>();
        extra = new HashMap<>();
        state = TableState.Empty;
    }

    /*
     * The method to track if the table is available with the given time period
     * @param id            The id of this table
     * @param tableType     The type of this table
     */
    public boolean isAvailable(@NotNull final long[] timeStamp){
        if(timeStamp == null) return false;
        if(state == TableState.Empty) return true;

        final long currentTime = System.currentTimeMillis();

        long start = timeStamp[0];
        long end = timeStamp[1];

        if(currentTime >= start || currentTime <= end) return false;

        for(long[] period : reservations.values()){
            if(period[0] >= start || period[0] <= end) return false;
            if(period[1] >= start || period[1] <= end) return false;
        }

        return true;
    }

    //Clear all the references
    public void clear(){
        reservations.clear();
        extra.clear();
        tableEventListeners.clear();
        state = TableState.Empty;
    }

    public void setState(@NotNull final TableState state){
        this.state = state;
    }

    /*
     * The method to reserve this table
     * @return boolean          Return the reservation is successful or not
     * @param reservationId     The id of the reservation
     * @param timeStamp         The time period of the reservation
     */
    public boolean reserve(@NotNull final String reservationId,@NotNull final long[] timeStamp){
        if(!isAvailable(timeStamp)) return false;

        reservations.put(reservationId,timeStamp);

        if (state == TableState.Empty) {
            onTableReserved();
            state = TableState.Reserved;
        }

        return true;
    }

    /*
     * The method to reserve this table
     * @return boolean          Return the cancellation is successful or not
     * @param reservationId     The id of the reservation
     */
    public boolean cancel(@NotNull final String reservationId){
        if(!reservations.containsKey(reservationId)) return false;

        reservations.remove(reservationId);

        if(reservations.size() == 0){
            if (state != TableState.Occupy){
                onTableCleared();
                state = TableState.Empty;
            }
        }

        return true;
    }

    /*
     * The method to occupy the table
     */
    public boolean occupyTable(){
        if (state != TableState.Occupy){
            onTableOccupied();
            state = TableState.Occupy;
            return true;
        }

        return false;
    }

    /*
     * The moment that the table is occupied
     */
    public void onTableOccupied(){
        notifyListener(tableId,state,TableState.Occupy);
    }

    /*
     * The moment that the table is Reserved
     */
    public void onTableReserved(){
        notifyListener(tableId, state, TableState.Reserved);
    }

    /*
     * The moment that the customers leave the table
     */
    public void onTableCleared(){
        notifyListener(tableId,state,TableState.Empty);
    }

    /*
     * The method to register listener
     */
    public void registerListener(final ITableEventListener tableEventListener){
        tableEventListeners.add(tableEventListener);
    }

    /*
     * The method to deregister listener
     */
    public void deregisterListener(final ITableEventListener tableEventListener){
        tableEventListeners.remove(tableEventListener);
    }

    /*
     * The method to notify listeners
     * @param tableId       The id of the table
     * @param fromState     The initial state of the table
     * @param toState       The current state if the table
     */
    private void notifyListener(final String tableId, final TableState fromState, final TableState toTableState){
        for(ITableEventListener tableEventListener : tableEventListeners){
            tableEventListener.onTableStateChanged(tableId,fromState,toTableState);
        }
    }

    //Getters
    public String getTableId(){
        return tableId;
    }

    public TableType getTableType(){
        return tableType;
    }

    public TableState getState(){
        return state;
    }

    public int getCapacity(){
        return capacity;
    }

    public Map<String,Object> getExtra(){
        return extra;
    }

}
