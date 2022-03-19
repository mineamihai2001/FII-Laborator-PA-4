package surveillance;

import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class City {
    private List<Street> streetList;
    private Set<Intersection> intersectionSet;
    private Graph<Intersection, Street> network = new SimpleWeightedGraph<Intersection, Street>(Street.class);

    City() {
        streetList = new LinkedList<>();
        intersectionSet = new HashSet<>();
    }

    public void createListOfStreets(Street... streets) {
        for (Street s : streets) {
            streetList.add(s);
        }
    }

    public boolean createSetOfIntersections(Intersection... intersections) {
        for (Intersection i : intersections) {
            for (Intersection j : intersections) {
                if (i.equals(j) && i.getName() != j.getName()) {
                    System.out.println(i.getName() + "===" + j.getName());
                    return false;
                }
            }
        }

        for (Intersection i : intersections) {
            intersectionSet.add(i);
        }
        return true;
    }

    public List<Intersection> filterIntersection() {
        return intersectionSet.stream().filter(i -> i.countStreets() > 3).collect(Collectors.toList());
    }

    public void createStreets() {
        Faker faker = new Faker();

        for (int i = 0; i < 14; ++i) {
            String streetAddress = faker.address().streetAddress();
            Street s = new Street(streetAddress, (int) (Math.random() * 3 + 1));
            streetList.add(s);
        }
    }

    public void createIntersections() {
        Faker faker = new Faker();

        int counter = 0;
        while (counter < 9) {
            String intersectionName = faker.color().name();
            int numberOfStreets = (int) (Math.random() * streetList.size());
            List<Street> streets = new ArrayList<>();
            int j = 0;
            while (j < numberOfStreets) {
                Street s = streetList.get((int) (Math.random() * streetList.size()));
//                System.out.println("STREET-----> " + s.getName());
                if (!streets.contains(s)) {
                    streets.add(s);
                    ++j;
                }
            }
            Intersection inter = new Intersection(intersectionName, streets);
            if (!intersectionSet.contains(inter.getName())) {
                intersectionSet.add(inter);
                ++counter;
            }
        }
    }

    public void createNetwork() {
        for (Intersection i : intersectionSet) {
            network.addVertex(i);
        }
        for(Street s : streetList) {
            int index1 = (int)(Math.random() * intersectionSet.size());
            int index2 = (int)(Math.random() * intersectionSet.size());;
            while(index1 == index2) {
                index2 = (int)(Math.random() * intersectionSet.size());
            }
            int index = 0;
            Intersection end1 = new Intersection();
            Intersection end2 = new Intersection();
            for(Intersection i : intersectionSet) {
                if(index1 == index) {
                    end1 = i;
                }
                if(index2 == index) {
                    end2 = i;
                }
                ++index;
            }
            network.addEdge(end1, end2, s);
        }

    }

    public void sortStreets() {
        streetList.sort((x, y) -> x.getLength() - y.getLength());
        for (
                Street s : streetList) {
            s.printDetails();
        }
    }

    public List<Street> MST() {
        List<Street> connectedStreets = new ArrayList<>();
        Intersection start = network.vertexSet().stream().findAny().get();
        System.out.println("The start node is: " + start.getName());
        Iterator<Intersection> iterator = new DepthFirstIterator<>(network, start);
        while(iterator.hasNext()) {
            Intersection current = iterator.next();
            System.out.println("CURRENT NODE: " + current.getName());
//            connectedStreets.add()
        }
        return connectedStreets;
    }

    public void generateCity() {
        createStreets();
        createIntersections();

        createNetwork();
        sortStreets();


//        for (Intersection i : intersectionSet) {
//            i.printIntersectionDetails();
//        }
//        List<Street> fs = new ArrayList<>();
//        for (Intersection i : intersectionSet) {
//            fs = i.filterIntersection(3);
//            for (Street s : fs) {
//                System.out.println(s.getName() + "[" + s.getLength() + "]");
//            }
//        }
    }

}
