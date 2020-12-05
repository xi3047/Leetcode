package round1_misc.OOD.CrossSection;

public class Car {
    private String VIN;
    private long arriveTime;

    private Road road;

    public Car(String VIN, long arriveTime) {
        this.VIN = VIN;
        this.arriveTime = arriveTime;
    }


    public String getVIN() {
        return VIN;
    }

    public long getArriveTime() {
        return arriveTime;
    }


    public void setRoad(Road road) {
        this.road = road;
    }

    public Road getRoad() {
        return road;
    }
}
