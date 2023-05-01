import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Destination a = new Destination("Paris", "Bordeaux");
        Destination b = new Destination("Lyon", "Marseille");
        Destination c = new Destination("Bordeaux", "Lyon");
        Destination d = new Destination("Marseille");

        ArrayList<Destination> destinations = new ArrayList<Destination>();
        destinations.add(a);
        destinations.add(b);
        destinations.add(c);
        destinations.add(d);

        Trip trip = new Trip();

        System.out.println(trip.calculateCorrectOrder(destinations));
    }
}