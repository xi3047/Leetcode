package round1_misc.OOD.CrossSection;

import java.util.Queue;

public interface Road {
    void add(Car car);

    Car passCar();

    boolean isEmpty();

    Queue<Car> getCars();

    int getCarNum();
}
