package surveillance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Intersection {
    private String name;
    private List<Street> streetList;
    private Stream<Street> intersection;

    Intersection() {
        this.name = "";
        intersection = Stream.empty();
        streetList = new ArrayList<>();
    }

    Intersection(String name, Street... streets) {
        this.name = name;
        intersection = Stream.empty();
        streetList = new ArrayList<>();
        for (Street s : streets) {
            streetList.add(s);
        }
    }

    Intersection(String name, List<Street> streets) {
        this.name = name;
        streetList = new ArrayList<>(streets);
    }

    public String getName() {
        return name;
    }

    public void getStream() {
        intersection = streetList.stream();
    }

    public int countStreets() {
        return streetList.size();
    }

    public List<Street> filterIntersection(int length) {
        getStream();
        try {
            List<Street> filteredStreets = intersection.filter(s -> s.getLength() >= length).collect(Collectors.toList());
            return filteredStreets;
        }catch (Exception e) {
            throw e;
        }
    }

    public void printIntersectionDetails() {
        System.out.print("Intersection [" + name + "] (");
        for (Street s : streetList) {
            System.out.print(s.getName() + "[" + s.getLength() +"]" + " || ");
        }
        System.out.printf(")\n");
    }
}
