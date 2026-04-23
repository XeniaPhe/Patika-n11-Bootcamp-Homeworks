import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    private final int ElevatorCount = 5;
    private final int FloorCount = 12;
    private final int ElevatorCapacity = 6;

    private final float ElevatorSpeed = 1.0f; // elevator goes up or down a floor every second
    private final float TimeStep = 0.1f;  // simulation runs at 10 TPS
    private final float AvgArrivalTime = 0.25f; // a new person spawns 4 times a second

    private float simulationTime = 0.0f;
    private float nextArrivalTime = -0.1f;

    @SuppressWarnings("unchecked")
    private List<Person>[] floors = new List[FloorCount];
    private Elevator[] elevators = new Elevator[ElevatorCount];

    private Random rng = new Random();

    public Simulation() {
        for (int i = 0; i < ElevatorCount; i++) {
            elevators[i] = new Elevator(ElevatorCapacity, ElevatorSpeed);
        }

        for (int i = 0; i < FloorCount; ++i) {
            floors[i] = new ArrayList<>();
        }
    }

    public void run() {
        while (true) {
            if (nextArrivalTime > simulationTime) {
                int fromFloor = rng.nextInt(0, FloorCount);
                int toFloor = fromFloor;

                while (fromFloor == toFloor) {
                    toFloor = rng.nextInt(0, FloorCount);
                }
                
                floors[fromFloor].add(new Person(toFloor));
                nextArrivalTime = calculateNextArrivalTime();
            }

            step();
        }
    }

    private void step() {
        try {
            Thread.sleep((long)(TimeStep * 1000));
            simulationTime += TimeStep;
        } catch (Exception e) {
            // Since the simulation is single-threaded, this shouldn't be the case
            System.exit(1);
        }
    }

    private float calculateNextArrivalTime() {
        double u = rng.nextDouble();
        return (float)-Math.log(1 - u) * AvgArrivalTime;
    }
}
