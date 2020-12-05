package round1_misc.OOD.Elevator;

public class ExternalButton extends Button implements ElevatorEventListener{
    private ElevatorSystem elevatorSystem;

    public ExternalButton(String id, int level, ElevatorSystem elevatorSystem) {
        super(id, level);
        this.elevatorSystem = elevatorSystem;
    }

    public void sendUpRequest() {
        elevatorSystem.handleRequest(new ExternalRequest(level, Direction.UP));
    }

    public void sendDownRequest() {
        elevatorSystem.handleRequest(new ExternalRequest(level, Direction.DOWN));
    }

    public ElevatorSystem getElevatorSystem() {
        return elevatorSystem;
    }

    @Override
    public void onElevetorStop() {
        System.out.println("Allowed to add user when elevator stopped");
    }
}
