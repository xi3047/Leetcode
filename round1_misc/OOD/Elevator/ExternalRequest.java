package round1_misc.OOD.Elevator;

public class ExternalRequest extends Request {

    private Direction direction;

    public ExternalRequest(int level, Direction direction) {
        super(level);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }


}
