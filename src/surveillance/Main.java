package surveillance;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        Intersection i1 = new Intersection();
        Intersection i2 = new Intersection();
        Intersection i3 = new Intersection();

        Street s1 = new Street("street 1", 10, i1, i2);
        Street s2 = new Street("street 2", 20, i1, i2);

        Stream<Street> intersection1 = Stream.of(s1, s2);
    }
}
