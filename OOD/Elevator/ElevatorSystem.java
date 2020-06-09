package OOD.Elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    // the maximum floor
    public static final int MAX_FLOOR = 20;
    public static final int ELEVATOR_CAPACITY = 15;
    public static final int ELEVATOR_NUM = 4;
    public static final String ELEVATOR_SYSTEM_NAME = "ElevatorSystem";

    private final List<ExternalButton> externalButtons;
    private final List<Elevator> elevators;

    private boolean isNormal = true;

    public ElevatorSystem() {
        externalButtons = new ArrayList<>(MAX_FLOOR);
        elevators = new ArrayList<>(ELEVATOR_NUM);

        // Generate new external buttons and elevators
        initExternalButton();
        initElevator();
    }

    private void initExternalButton() {
        for (int i = 1; i <= MAX_FLOOR; i++) {
            externalButtons.add(new ExternalButton(ELEVATOR_SYSTEM_NAME + "-" + i, i, this));
        }
    }

    private void initElevator() {
        for (int i = 1; i <= ELEVATOR_NUM; i++) {
            elevators.add(new Elevator(ELEVATOR_SYSTEM_NAME + "-" + i, ELEVATOR_CAPACITY));
        }
    }

    public void handleRequest(final ExternalRequest request) {
        for (Elevator elevator : elevators) {
            elevator.handleExternalRequest(request);
        }
    }
}
