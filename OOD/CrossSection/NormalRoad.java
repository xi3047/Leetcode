package OOD.CrossSection;

import java.util.LinkedList;
import java.util.Queue;

public class NormalRoad implements Road {
    private Queue<Car> cars;

    private String name;

    public NormalRoad(String name) {
        this.name = name;
        this.cars = new LinkedList<Car>();
    }

    @Override
    public int getCarNum() {
        return cars.size();
    }

    @Override
    public Queue<Car> getCars(){
        return cars;
    }

    @Override
    public Car passCar() {
        return cars.poll();
    }

    @Override
    public void add(Car car) {
        cars.offer(car);
    }

    @Override
    public boolean isEmpty(){
        return cars.isEmpty();
    }

}
