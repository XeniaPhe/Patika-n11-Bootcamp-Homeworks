import java.util.*;

public class Simulation {
    private final int FLOOR_COUNT = 12;
    private final int ELEVATOR_COUNT = 5;
    
    private float simTime = 0.0f;
    private final float TIME_STEP = 0.1f; 
    private final float AVG_ARRIVAL_TIME = 2.0f; 
    private float nextArrivalTime = 0.0f;

    private List<Person>[] floors = new List[FLOOR_COUNT];
    private List<Elevator> elevators = new ArrayList<>();
    private Random rng = new Random();

    public Simulation() {
        for (int i = 0; i < FLOOR_COUNT; i++) floors[i] = new ArrayList<>();
        for (int i = 0; i < ELEVATOR_COUNT; i++) elevators.add(new Elevator(i, 6));
        nextArrivalTime = getNextArrival();
    }

    private float getNextArrival() {
        return simTime + (float)(-Math.log(1 - rng.nextDouble()) * AVG_ARRIVAL_TIME);
    }

    public void run(float duration) {
        float lastLogTime = 0.0f;
        float LOG_INTERVAL = 5.0f; // Log floor status every 5 seconds
        
        while (simTime < duration) {
            simTime += TIME_STEP;
        
            // Spawn people
            if (simTime >= nextArrivalTime) {
                spawnPerson();
                nextArrivalTime = getNextArrival();
            }
        
            // Update Elevators
            for (Elevator e : elevators) {
                e.update(TIME_STEP, simTime, floors);
            }
        
            // Periodic Floor Status Logging
            if (simTime - lastLogTime >= LOG_INTERVAL) {
                logFloorStatus();
                lastLogTime = simTime;
            }
            
            // Slow down execution slightly for real-time viewing if needed
            // try { Thread.sleep(50); } catch (InterruptedException e) {}
        }
    }

    private void logFloorStatus() {
        System.out.println("\n--- FLOOR WAITING STATUS [Time: " + String.format("%.1f", simTime) + "s] ---");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < FLOOR_COUNT; i++) {
            sb.append(String.format("F%02d: %d | ", i, floors[i].size()));
            if ((i + 1) % 4 == 0) sb.append("\n"); // Break line every 4 floors for readability
        }
        System.out.println(sb.toString());
        System.out.println("-----------------------------------------------\n");
    }

    private void spawnPerson() {
        int from = rng.nextInt(FLOOR_COUNT);
        int to = from;
        while (to == from) to = rng.nextInt(FLOOR_COUNT);
        
        floors[from].add(new Person(from, to));
        
        // Scheduling logic using elevatorBoardScoreAt
        Elevator best = null;
        float bestScore = Float.MAX_VALUE;

        for (Elevator e : elevators) {
            float score = e.elevatorBoardScoreAt(from);
            if (score < bestScore) {
                bestScore = score;
                best = e;
            }
        }

        if (best != null) {
            best.addStop(from);
            System.out.printf("[%.1fs] Person @ Floor %d -> %d. Assigned Elevator ID: %s\n", simTime, from, to, elevators.indexOf(best));
        }
    }
}