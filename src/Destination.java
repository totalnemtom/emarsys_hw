public class Destination {
    private String destination;
    private String dependency;

    public Destination(String destination) {
        this.destination = destination;
    }

    public Destination(String destination, String dependency) {
        this.destination = destination;
        this.dependency = dependency;
    }

    public String getDestination() {
        return destination;
    }

    public String getDependency() {
        return dependency;
    }
}
