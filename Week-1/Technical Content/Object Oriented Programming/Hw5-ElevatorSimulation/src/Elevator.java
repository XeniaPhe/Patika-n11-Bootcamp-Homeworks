import java.util.*;

public class Elevator {
    public enum State { IDLE, MOVING_UP, MOVING_DOWN, LOADING }

    private int id;
    private float y = 0.0f; 
    private float speed = 2.0f; 
    private float doorTimer = 0.0f;
    private final float DOOR_OPEN_DURATION = 3.0f;

    private State state = State.IDLE;
    private int capacity;
    private List<Person> passengers = new ArrayList<>();
    private TreeSet<Integer> stops = new TreeSet<>();

    public Elevator(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public float elevatorBoardScoreAt(int targetFloor) {
        float targetY = targetFloor * 3.0f;
        float distance = Math.abs(y - targetY);
        if (state == State.IDLE) return distance;

        boolean headingTowards = (state == State.MOVING_UP && targetY > y) || 
                                 (state == State.MOVING_DOWN && targetY < y);

        return headingTowards ? distance : distance + 100.0f;
    }

    public void addStop(int floor) {
        stops.add(floor);
    }

    public void update(float dt, float simTime, List<Person>[] floors) {
        int currentFloor = getCurrentFloor();

        switch (state) {
            case IDLE -> {
                if (!stops.isEmpty()) {
                    state = (stops.first() * 3.0f > y) ? State.MOVING_UP : State.MOVING_DOWN;
                    System.out.printf("[TIME: %.1fs] Elevator %d: State changed to %s (Target: Floor %d)\n", 
                                      simTime, id, state, stops.first());
                }
            }
            case MOVING_UP, MOVING_DOWN -> {
                // Determine next stop in current direction
                Integer targetFloor = (state == State.MOVING_UP) ? stops.ceiling(currentFloor) 
                                                                 : stops.floor(currentFloor);
                
                if (targetFloor == null) targetFloor = stops.first(); // Fallback if direction empty
                float targetY = targetFloor * 3.0f;
                
                float step = speed * dt;
                if (Math.abs(y - targetY) <= step) {
                    y = targetY;
                    state = State.LOADING;
                    doorTimer = DOOR_OPEN_DURATION;
                    System.out.printf("[TIME: %.1fs] Elevator %d: Arrived at Floor %d\n", simTime, id, targetFloor);
                } else {
                    y += (state == State.MOVING_UP ? 1 : -1) * step;
                }
            }
            case LOADING -> {
                doorTimer -= dt;
                
                // Offload
                int beforeOffload = passengers.size();
                passengers.removeIf(p -> p.targetFloor() == currentFloor);
                stops.remove(currentFloor);
                if (beforeOffload > passengers.size()) {
                    System.out.printf("[TIME: %.1fs] Elevator %d: %d passenger(s) exited at Floor %d\n", 
                                      simTime, id, (beforeOffload - passengers.size()), currentFloor);
                }

                // Boarding passengers
                List<Person> queue = floors[currentFloor];
                Iterator<Person> it = queue.iterator();
                while (it.hasNext() && passengers.size() < capacity) {
                    Person p = it.next();
                    
                    if (stops.contains(p.targetFloor())) {
                        passengers.add(p);
                        it.remove();
                        System.out.printf("[TIME: %.1fs] Elevator %d: Boarded passenger for Floor %d (Cap: %d/%d)\n", 
                                          simTime, id, p.targetFloor(), passengers.size(), capacity);
                    }
                }

                if (doorTimer <= 0) {
                    if (stops.isEmpty()) {
                        state = State.IDLE;
                    } else {
                        state = (stops.first() * 3.0f > y) ? State.MOVING_UP : State.MOVING_DOWN;
                    }
                    System.out.printf("[TIME: %.1fs] Elevator %d: Doors closed. Next state: %s\n", simTime, id, state);
                }
            }
        }
    }

    public int getCurrentFloor() { return Math.round(y / 3.0f); }
}