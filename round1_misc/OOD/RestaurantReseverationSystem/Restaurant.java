package OOD.RestaurantReseverationSystem;

import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * Created by FLK on 2019-02-16.
 * The class to represent a Restaurant
 */
public class Restaurant implements ITableEventListener {

    //The id of restaurant id
    private final String id;

    /*
     *  The map that is used to map the table id this table object
     *  Key: table ID
     *  Value: table Object
     */
    private final Map<String,BaseTable> tableMap;

    /*
     *  The Map to track reservations and tables
     *  Key: reservation ID
     *  Value: List table ID
     */
    private final Map<String,List<String>> reservationMap;

    /*
     *  The Map to track serverID and the actual Server object
     *  Key: server ID
     *  Value: server object
     */
    private final Map<String,Server> serverMap;

    //The list of empty table
    private final List<String> emptyTables;

    //The list of tables which have reservations
    private final List<String> reversedTables;

    //The list of tables that is currently being used
    private final List<String> occupiedTables;

    //The list of small tables
    private final List<String> smallTables;

    //The list of medium tables
    private final List<String> mediumTables;

    //The list of large tables
    private final List<String> largeTables;

    //The handler to handler all the filtering of tables
    private final ReservationHandler mReservationHandler;

    //The splitter to handle split the reservation
    private final ReservationSplitter mReservationSplitter;

    /*
     * Constructor
     * @param id    The table id
     */
    public Restaurant(@NotNull final String id){
        this.id = id;
        tableMap = new HashMap<>();
        serverMap = new HashMap<>();
        reservationMap = new HashMap<>();

        emptyTables = new ArrayList<>();
        reversedTables = new ArrayList<>();
        occupiedTables = new ArrayList<>();

        smallTables = new ArrayList<>();
        mediumTables = new ArrayList<>();
        largeTables = new ArrayList<>();

        mReservationHandler = new ReservationHandler();
        mReservationSplitter = new ReservationSplitter();
    }

    /*
     * The method to check a reservation can be made or not
     * @return  boolean           Return whether this reservation can be taken or not
     * @param   reservation       The reservation object
     */
    public boolean canReserve(@NotNull final Reservation reservation){
        final int partySize = reservation.getSize();

        //Need to be split
        if (mReservationSplitter.needSplit(reservation)){
            return !mReservationSplitter.splitReservation(reservation).isEmpty();
        }

        final long[] timeStamp = reservation.getReservationPeriod();

        //Get the list of tables that can be used for this reservation
        final List<String> availableTables = mReservationHandler.filteredTables(partySize,timeStamp);

        return !availableTables.isEmpty();
    }

    /*
     * The method to make a reservation for a table
     * @return  boolean           Return whether this reservation is successful or not
     * @param   reservation       The reservation object
     */
    public boolean takeReservation(final Reservation reservation){
        if(!canReserve(reservation)) return false;

        final int partySize = reservation.getSize();
        final long[] timeStamp = reservation.getReservationPeriod();
        final String reservationId = reservation.getId();

        if (mReservationSplitter.needSplit(reservation)) {
            final List<String> tableList = mReservationSplitter.splitReservation(reservation);

            for(String s : tableList) {
                final BaseTable table = tableMap.get(s);
                reserveSingleTable(table,reservationId,timeStamp);
            }

        } else {
            //Get the list of tables that can be used for this reservation
            final List<String> availableTables = mReservationHandler.filteredTables(partySize,timeStamp);

            //Always get the first available table
            final BaseTable table = tableMap.get(availableTables.get(0));

            reserveSingleTable(table,reservationId,timeStamp);
        }

        return true;
    }

    private void reserveSingleTable(@NotNull final BaseTable table,final @NotNull String reservationId, @NotNull long[] timeStamp){
        //Reserve this table
        table.reserve(reservationId,timeStamp);

        //Update the reservation map
        final List<String> tableIds = reservationMap.getOrDefault(reservationId,new ArrayList<String>());

        tableIds.add(table.getTableId());
    }

    /*
     * The method to cancel a reservation
     * @return  boolean           Return whether this reservation can be cancelled or not
     * @param   reservation       The reservation object
     */
    public boolean cancelReservation(@NotNull final Reservation reservation){
        final String reservationId = reservation.getId();

        if(!reservationMap.containsKey(reservationId)) return false;

        //Find the table for this reservation
        final List<String> tableIds = reservationMap.get(reservationId);

        for(String ids : tableIds) {
            final BaseTable table = tableMap.get(ids);
            //Cancel the reservation
            table.cancel(reservationId);
        }

        //Remove the reservation from reservation map
        reservationMap.remove(reservationId);

        return true;
    }

    //Adders
    public void addSmallTable(final SmallTable smallTable){
        smallTables.add(smallTable.getTableId());
        tableMap.put(smallTable.getTableId(),smallTable);
    }

    public void addMediumTable(final MediumTable mediumTable){
        mediumTables.add(mediumTable.getTableId());
        tableMap.put(mediumTable.getTableId(),mediumTable);
    }

    public void addLargeTable(final LargeTable largeTable){
        largeTables.add(largeTable.getTableId());
        tableMap.put(largeTable.getTableId(),largeTable);
    }


    @Override
    public void onTableStateChanged(final String tableId,final TableState fromState,final TableState toState) {
        if(tableId == null || fromState == toState) return;

        final List<String> fromStateTableList = mReservationHandler.filteredTables(fromState);
        final List<String> toStateTableList = mReservationHandler.filteredTables(toState);

        fromStateTableList.remove(tableId);
        toStateTableList.add(tableId);
    }

    /*
     * The class to handle splite all tables
     */
    public class ReservationSplitter {

        //The method to check the reservation needs to be split or not
        public boolean needSplit(@NotNull final Reservation reservation){
           return mReservationHandler.filteredTables(reservation.getSize()).isEmpty();
        }

        //The method to get list of tables that can satisfied the reservation
        public List<String> splitReservation(@NotNull final Reservation reservation){
            final List<String> tableList = new ArrayList<>();
            final long[] timePeriod = reservation.getReservationPeriod();
            int originalSize = reservation.getSize();
            final Set<String> set = new HashSet<>();

            while(originalSize > 0) {
                if(mReservationHandler.filteredTables(timePeriod).isEmpty()) return new ArrayList<>();
                if(originalSize > TableType.MEDIUM.getCapacity()) {
                    final List<String> tempList = mReservationHandler.filteredTables(TableType.LARGE,timePeriod);
                    if(!tempList.isEmpty()) {
                        for(String tempId : tempList){
                            if(!set.contains(id)){
                                tableList.add(tempId);
                                originalSize -= TableType.LARGE.getCapacity();
                                set.add(tempId);
                                break;
                            }
                        }
                        continue;

                    }
                }

                if(originalSize > TableType.SMALL.getCapacity()) {
                    final List<String> tempList = mReservationHandler.filteredTables(TableType.MEDIUM,timePeriod);
                    if(!tempList.isEmpty()) {
                        for(String tempId : tempList){
                            if(!set.contains(id)){
                                tableList.add(tempId);
                                originalSize -= TableType.MEDIUM.getCapacity();
                                set.add(tempId);
                                break;
                            }
                        }
                        continue;
                    }
                }

                final List<String> tempList = mReservationHandler.filteredTables(TableType.SMALL,timePeriod);
                if(!tempList.isEmpty()) {
                    for(String tempId : tempList){
                        if(!set.contains(id)){
                            tableList.add(tempId);
                            originalSize -= TableType.MEDIUM.getCapacity();
                            set.add(tempId);
                        }
                    }
                }
            }

            return tableList;
        }
    }

    /*
     * The class to handle all the filtering of tables
     * Mainly responsible for filtering the tables with certain conditions
     */
    public class ReservationHandler {

        /*
         * The method to filter table by given state
         * @return  List<String>      Return list of ids of table with given state
         * @param   state             The TableState
         */
        public List<String> filteredTables(@NotNull final TableState state) {
            switch (state) {
                case Empty: return emptyTables;
                case Occupy: return occupiedTables;
                case Reserved: return reversedTables;
                default: return new ArrayList<>();
            }
        }

        /*
         * The method to filter table by given table type
         * @return  List<String>      Return list of ids of table with table type
         * @param   tableType         The table type
         */
        public List<String> filteredTables(@NotNull final TableType tableType){
            switch (tableType) {
                case SMALL: return smallTables;
                case MEDIUM: return mediumTables;
                case LARGE: return largeTables;
                default: return new ArrayList<>();
            }
        }

        /*
         * The method to filter table by given party size
         * @return  List<String>      Return list of ids of table with party size
         * @param   partySize         The int of party size
         */
        public List<String> filteredTables(@NotNull final int partySize){
            if( partySize <= TableType.SMALL.getCapacity()) {
                return filteredTables(TableType.SMALL);
            } else if (partySize <= TableType.MEDIUM.getCapacity()) {
                return filteredTables(TableType.MEDIUM);
            } else if (partySize <= TableType.LARGE.getCapacity()) {
                return filteredTables(TableType.LARGE);
            } else {
                return new ArrayList<>();
            }
        }

        /*
         * The method to filter table by given timeStamp
         * @return  List<String>      Return list of ids of table with timeStamp
         * @param   timeStamp         The long[] of start and end time
         */
        public List<String> filteredTables(@NotNull final long[] timeStamp){
            final List<String> result = new ArrayList<>(emptyTables);

            for (String tableId : reversedTables){
                if (tableMap.get(tableId).isAvailable(timeStamp)){
                    result.add(tableId);
                }
            }

            return result;
        }

        //The combination of filtering by table type and table state
        public List<String> filteredTables(@NotNull final TableType tableType,@NotNull final TableState state){
            final List<String> result = new ArrayList<>();

            final List<String> tableTypeList = filteredTables(tableType);
            final List<String> stateList = filteredTables(state);

            for(String id : tableTypeList){
                if(stateList.contains(id)) {
                    result.add(id);
                }
            }

            return result;
        }

        //The combination of filtering by table type and table state
        public List<String> filteredTables(@NotNull final TableType tableType,@NotNull final long[] timeStamp){
            final List<String> result = new ArrayList<>();

            final List<String> tableTypeList = filteredTables(tableType);
            final List<String> stateList = filteredTables(timeStamp);

            for(String id : tableTypeList){
                if(stateList.contains(id)) {
                    result.add(id);
                }
            }

            return result;
        }

        //The combination of filtering by partySize and timeStamp
        public List<String> filteredTables(final int partySize,@NotNull final long[] timeStamp) {
            final List<String> result = new ArrayList<>();

            final List<String> sizeList = filteredTables(partySize);
            final List<String> timeList = filteredTables(timeStamp);

            for (String id : sizeList){
                if (timeList.contains(id)) {
                    result.add(id);
                }
            }

            return result;
        }
    }
}
