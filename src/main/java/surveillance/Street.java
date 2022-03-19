package surveillance;

public class Street {
    private String name;
    private int length;
    private Intersection end1;
    private Intersection end2;

    Street(String name, int length) {
        this.name = name;
        this.length = length;
        this.end1 = end1;
        this.end2 = end2;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public void printDetails() {
        System.out.println("Street [" + name + "], length: " + length);
    }
}
