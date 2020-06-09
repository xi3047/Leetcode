package OOD.CrossSection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CrossSection {
    private CrossSectionType crossSectionType;

    private List<Road> roadImps;

    private PriorityQueue<Car> interSection;

    private String id;

    public CrossSection(String id, CrossSectionType crossSectionType) {
        this.id = id;
        this.crossSectionType = crossSectionType;

        roadImps = new ArrayList<Road>(crossSectionType.getRoadNum());

        interSection = new PriorityQueue<Car>(crossSectionType.getRoadNum(), new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                if (o1.getArriveTime() == o2.getArriveTime()) return 0;
                return o1.getArriveTime() > o2.getArriveTime()? 1 : -1;
            }
        });
        initRoad();
    }

    private void initRoad() {
        int roadNum = crossSectionType.getRoadNum();
        for (int i = 0; i < roadNum; i++) {
            roadImps.add(new NormalRoad("" + i));
        }
    }

    public Car passCar(){
        for (Road roadImp : roadImps) {
            if (roadImp.getCars().peek() != null) interSection.offer(roadImp.passCar());
        }
        Car car = interSection.poll();

        car.setRoad(null);
        return car;
    }

    public boolean isEmpty() {
        for (Road roadImp : roadImps) {
            if (!roadImp.isEmpty()) return false;
        }
        return interSection.isEmpty();
    }

    public List<Road> getRoadImps() {
        return roadImps;
    }

    public Road getRoad(int index) {
        if (index >= roadImps.size()) return null;
        return roadImps.get(index);
    }

    public static void main(String[] args) {
        CrossSection crossSection = new CrossSection(CrossSectionType.FourWay.toString(), CrossSectionType.FourWay);
        for (int i = 0; i < 10; i++) {
            Car car = new Car("Car" + i, i + System.currentTimeMillis());
            Road curRoad = crossSection.getRoad(i % 4);
            car.setRoad(curRoad);
            curRoad.add(car);
        }
        while (!crossSection.isEmpty()) {
            Car car = crossSection.passCar();
            if (car != null) {
                System.out.println(car.getVIN() + " is passed.");
            }
        }
    }



}
