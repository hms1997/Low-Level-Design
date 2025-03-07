public class Request {
    private int sourceFloor;
    private int destinationFloor;

    public Request(int src, int dest) {
        sourceFloor = src;
        destinationFloor = dest;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
