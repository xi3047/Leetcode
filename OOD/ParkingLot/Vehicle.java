package OOD.ParkingLot;


import java.util.List;

enum VehicleSize {
    Motorcycle, Compact, Large;
}
abstract class Vehicle {
    /* basic vehicle info */
    protected List<ParkingLot> parkingSpots;
    private VehicleSize vehicleSize;
    private int spotsNeeded;
    private Level parkingLotLevel;

    public Vehicle(VehicleSize vehicleSize, int spotsNeeded) {
    }

}
