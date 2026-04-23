public class Elevator {
    private float speed;
    private float floorUpdateTime;

    private int currentFloor;
    private int targetFloor;

    private Person[] passengers;

    public Elevator(int capacity, float speed) {
        this.currentFloor = 0;
        this.targetFloor = 0;
        this.floorUpdateTime = speed;
        this.speed = speed;
        this.passengers = new Person[capacity];
    }

    public void update(float simTime, float deltaTime) {

    }
}
