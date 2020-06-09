package OOD.Elevator;

public enum Direction {
    // This enum class represents 3 status of an elevator
    UP("UP", 1),
    STOP("STOP", 0),
    DOWN("DOWN", -1);

    private final String direction;
    private int value;

    /**
     * Enum 只有一份copy，默认private
     */
    Direction(final String direction, int value) {
        this.direction = direction;
        this.value = value;
    }

    public String toString() {
        return direction;
    }
    public int getValue(){
        return value;
    }


}
