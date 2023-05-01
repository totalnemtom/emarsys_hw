import java.util.ArrayList;
import java.util.List;

public class Trip {
    public List<String> calculateCorrectOrder(ArrayList<Destination> destinations) throws Exception {
        List<String> correctOrder = new ArrayList<>();
        List<Destination> visitedPlaces = new ArrayList<>();
        int index = 0;
        int tripSize = destinations.size();

        checkForIncorrectDependency(destinations);


        while (index <= tripSize) {
            for (Destination destination : destinations) {
                Destination nextDestination = findDestinationRoot(destination, destinations);
                checkForAlreadyVisitedPlaces(nextDestination,correctOrder);
                correctOrder.add(nextDestination.getDestination());
                visitedPlaces.add(nextDestination);
                break;
            }
            destinations.removeAll(visitedPlaces);
            index++;
        }

        return correctOrder;
    }

    private Destination findDestinationRoot(Destination destinationToLookRootFor, ArrayList<Destination> destinations) {
        if (destinationToLookRootFor.getDependency() != null) {
            for (Destination destination : destinations) {
                if (destination.getDestination().equals(destinationToLookRootFor.getDependency())) {
                    return findDestinationRoot(destination, destinations);
                }
            }
        }
        return destinationToLookRootFor;
    }

    private void checkForAlreadyVisitedPlaces(Destination upcomingDest,List<String> visitedPlaces) throws Exception {
        if(visitedPlaces.contains(upcomingDest.getDestination())){
            throw new Exception("You have been already here: " + upcomingDest.getDestination());
        }
    }

    private void checkForIncorrectDependency(ArrayList<Destination> destinations) throws Exception {
        List<String> placesToVisit = new ArrayList<>();
        List<String> dependencies = new ArrayList<>();
        destinations.forEach(destination -> placesToVisit.add(destination.getDestination()));
        destinations.forEach(destination -> dependencies.add(destination.getDependency()));
        for (String dependency : dependencies){
            if(dependency != null && !placesToVisit.contains(dependency)){
                throw new Exception("Dependency not found on the trip list");
            }
        }
    }

}
