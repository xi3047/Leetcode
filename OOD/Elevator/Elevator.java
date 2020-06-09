package OOD.Elevator;

import com.oracle.tools.packager.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static OOD.Elevator.ElevatorSystem.MAX_FLOOR;

public class Elevator {
    // id of the elevator
    private final String id;

    // capacity of the elevator
    private final int capacity;

    // The task manager for up and down tasks
    private final PriorityQueue<Integer> upTasks; // minHeap, 找往上走的最低的那层

    private final PriorityQueue<Integer> downTasks; // maxHeap,  找往下走的最高的那层

    // The current running direction
    private Direction currentDirection;

    // The current floor level of the elevator
    private int currentLevel;



    // The observer design pattern
    private final List<ElevatorEventListener> elevatorEventListenerList;

    // Internal buttons
    private final List<InternalButton> internalButtons;

    public Elevator(final String id, final int capacity) {
        this.id = id;
        this.capacity = capacity;
        currentDirection = Direction.STOP;
        currentLevel = 1;
        elevatorEventListenerList = new ArrayList<>();
        internalButtons = new ArrayList<>();

        // Initialize Internal Buttons
        initInternalButtons();

        upTasks = new PriorityQueue<>(MAX_FLOOR, (i1, i2) -> {
            if (i1.equals(i2)) return 0;
            return i1 > i2 ? 1 : -1;
        });

        downTasks = new PriorityQueue<>(MAX_FLOOR, (i1, i2) -> i2 - i1);
    }

    // Initialize internal buttons
    private void initInternalButtons() {
        for (int i = 1; i <= MAX_FLOOR; i++) {
            internalButtons.add(new InternalButton(id + "-" + i, i, this));
        }
    }

    // Getters
    public String getId() {
        return id;
    }


    // Register listeners
    public void registerListeners(final ElevatorEventListener elevatorEventListener) {
        elevatorEventListenerList.add(elevatorEventListener);
    }

    // Remove all listeners
    public void removeAllListeners() {
        elevatorEventListenerList.clear();
    }

    // Remove specific listner
    public void deregisterListeners(final ElevatorEventListener elevatorEventListener) {
        elevatorEventListenerList.remove(elevatorEventListener);
    }

    // the method to accept the external of an Elevator
    public boolean handleExternalRequest(final ExternalRequest externalRequest) {
        if (!isValid(externalRequest)) {
            return false;
        }
        final int requestLevel = externalRequest.getLevel();

        if (externalRequest.getDirection() == Direction.UP && currentDirection == Direction.UP) {
            if (requestLevel > currentLevel) {
                upTasks.offer(requestLevel);
            } else {
                return false;
            }
        } else if (externalRequest.getDirection() == Direction.DOWN && currentDirection == Direction.DOWN) {
            if (requestLevel < currentLevel) {
                downTasks.offer(requestLevel);
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    // The method to handle internal request
    public boolean handleInternalRequest(final Request request) {
        if (request instanceof  ExternalRequest) return false;
        if (!isValid(request)) return false;

        final int requestLevel = request.getLevel();
        // Handle the task according to the current level
        if (requestLevel > currentLevel) {
            upTasks.offer(requestLevel);
        } else {
            downTasks.offer(requestLevel);
        }
        return true;
    }

    private boolean isValid(final Request request) {
        if (request == null) {
            return false;
        }
        final int requestLevel = request.getLevel();
        if (requestLevel < 1 || requestLevel > MAX_FLOOR) {
            Log.info("Elevator: This request level is out of range");
            return false;
        } else if (requestLevel == currentLevel || upTasks.contains(requestLevel) || downTasks.contains(requestLevel)) {
            Log.info("Elevator: This request is duplicated");
            return false;
        }
        return true;
    }

    // Elevator Move Up
    public void moveUP() {
        currentDirection = Direction.UP;

        while (!upTasks.isEmpty()) {
            currentLevel = upTasks.poll();
        }

        currentDirection = Direction.STOP;
    }

    // Elevator Move down
    public void moveDown() {
        currentDirection = Direction.DOWN;

        while (!downTasks.isEmpty()) {
            currentLevel = downTasks.poll();
        }

        currentDirection = Direction.STOP;
    }

}
