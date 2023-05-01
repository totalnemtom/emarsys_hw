import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripUnitTest {
    private Trip trip;
    private ArrayList<Destination> destinations;

    @BeforeEach
    public void steUp(TestInfo info) {
        if (info.getDisplayName().equals("It should return correct order of the trip with dependencies")
                || info.getDisplayName().equals("It should return correct size of the trip with dependencies")) {
            return;
        }
        trip = new Trip();
        destinations = new ArrayList<>();

        Destination a = new Destination("Paris");
        Destination b = new Destination("Nice");
        Destination c = new Destination("Bordeaux");
        Destination d = new Destination("Marseille");

        destinations.add(a);
        destinations.add(b);
        destinations.add(c);
        destinations.add(d);
    }

    @Test
    @DisplayName("It should return correct size of the trip without dependencies")
    public void testLengthWithoutDependencies() throws Exception {

        assertEquals(4, trip.calculateCorrectOrder(destinations).size());
    }

    @Test
    @DisplayName("It should return correct size of the trip with dependencies")
    public void testLengthWithDependencies() throws Exception {
        Trip trip = new Trip();
        ArrayList<Destination> destinations = new ArrayList<Destination>();

        Destination a = new Destination("Paris", "Bordeaux");
        Destination b = new Destination("Nice", "Marseille");
        Destination c = new Destination("Bordeaux", "Nice");
        Destination d = new Destination("Marseille");

        destinations.add(a);
        destinations.add(b);
        destinations.add(c);
        destinations.add(d);

        assertEquals(4, trip.calculateCorrectOrder(destinations).size());
    }

    @Test
    @DisplayName("It should return correct order of the trip without dependencies")
    public void testOrderWithoutDependencies() throws Exception {
        List<String> expected = Arrays.asList("Paris", "Nice", "Bordeaux", "Marseille");

        assertEquals(expected, trip.calculateCorrectOrder(destinations));
    }

    @Test
    @DisplayName("It should return correct order of the trip with dependencies")
    public void testOrderWithDependencies() throws Exception {
        Trip trip = new Trip();
        List<String> expected = Arrays.asList("Marseille", "Nice", "Bordeaux", "Paris");
        ArrayList<Destination> destinations = new ArrayList<Destination>();

        Destination a = new Destination("Paris", "Bordeaux");
        Destination b = new Destination("Nice", "Marseille");
        Destination c = new Destination("Bordeaux", "Nice");
        Destination d = new Destination("Marseille");

        destinations.add(a);
        destinations.add(b);
        destinations.add(c);
        destinations.add(d);

        assertEquals(expected, trip.calculateCorrectOrder(destinations));
    }

    @Test
    @DisplayName("It should throw exception with message: Dependency not found on the trip list")
    public void exceptionTestingDependencyNotFound() throws Exception {
        Destination c = new Destination("Lyon", "Nice2");
        destinations.add(c);

        Throwable exception = assertThrows(Exception.class, () -> trip.calculateCorrectOrder(destinations));
        assertEquals("Dependency not found on the trip list", exception.getMessage());
    }

    @Test
    @DisplayName("It should throw exception with message: You have been already here: + dest.name")
    public void exceptionTestingDuplicateDestination() throws Exception {
        Destination c = new Destination("Paris");
        destinations.add(c);

        Throwable exception = assertThrows(Exception.class, () -> trip.calculateCorrectOrder(destinations));
        assertEquals("You have been already here: "+c.getDestination(), exception.getMessage());
    }
}
