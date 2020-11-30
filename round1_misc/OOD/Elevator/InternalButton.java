package round1_misc.OOD.Elevator;

public class InternalButton extends Button {

    private Elevator elevator;

    public InternalButton(String id, int level, Elevator elevator) {
        super(id, level);
        this.elevator = elevator;
    }

    public void sendRequest() {
        elevator.handleInternalRequest(new Request(level));
    }
}
