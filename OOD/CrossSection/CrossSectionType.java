package OOD.CrossSection;

public enum CrossSectionType {
    ThreeWay("ThreeWay", 3),
    FourWay("Fourway", 4);

    private String type;
    private int roadNum;

    CrossSectionType(String type, int roadNum) {
        this.type = type;
        this.roadNum = roadNum;
    }

    public String toString() {
        return type;
    }

    public int getRoadNum(){
        return roadNum;
    }
}
