package surveillance;

public class Street {
    private String name;
    private double length;
    private Intersection end1;
    private Intersection end2;

    Street(String name, double length, Intersection end1, Intersection end2) {
        this.name = name;
        this.length = length;
        this.end1 = end1;
        this.end2 = end2;
    }

}
