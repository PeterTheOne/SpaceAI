package mygame;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.math.Quaternion;
import java.util.ArrayList;

/**
 *
 * @author Luciph3r
 * @author PeterTheOne
 */
public class Spaceship {
    
    private enum MoveState {
        STOP, DIRECTION, TARGET
    };

    private Spatial shipModel;
    private MoveState moveState;
    private Vector3f velo;
    private Vector3f dest;
    private ShipAI computer;
    private ArrayList<Spaceship> seenShips;
    
    private final int MAXSPEED = 3;

    public Spaceship(Spatial shipModel) {
        this(shipModel, new Vector3f());
    }
    
    public Spaceship(Spatial shipModel, Vector3f pos) {
        this.shipModel = shipModel;
        this.shipModel.setLocalTranslation(pos);
        this.velo = new Vector3f();
        this.dest = new Vector3f();
        this.moveState = MoveState.STOP;
        this.computer = new ShipAI(this);
        this.seenShips = new ArrayList<Spaceship>();
    }

    public void update(float tpf) {
        if (moveState == MoveState.TARGET) {
            if (this.getPos().subtract(dest).length() > 0.01f) {
                this.velo = dest.subtract(getPos()).normalize().mult(MAXSPEED);
            } else {
                this.stop();
            }
        }
        
        this.computer.think();
        
        this.shipModel.move(velo.mult(tpf));
    }

    public void stop() {
        moveState = MoveState.STOP;
        velo = Vector3f.ZERO;
    }

    public void moveDirection(Vector3f direction) {
        if (direction.equals(Vector3f.ZERO)) {
            this.stop();
            return;
        }
        this.moveState = MoveState.DIRECTION;
        this.velo = direction.normalize().mult(MAXSPEED);
        this.focusDirection(direction);
    }

    public void moveTo(Vector3f dest) {
        if (getPos().equals(dest)) {
            this.stop();
            return;
        }
        this.focusTarget(dest);
        this.moveState = MoveState.TARGET;
        this.dest = dest;
        
    }
    public void moveTo(Spaceship ship){
        Vector3f coordinates = ship.getPos();
        moveTo(coordinates);
    }
    public void focusTarget(Vector3f dest){
        this.shipModel.lookAt(this.getPos().subtract(dest).add(this.getPos()), Vector3f.UNIT_Y);
    }
    public void focusDirection(Vector3f direction){
        this.shipModel.lookAt(this.getPos().add(direction.negate()), Vector3f.UNIT_Y);
    }

    public Vector3f getPos() {
        return this.shipModel.getLocalTranslation();
    }

    public Quaternion getOrientation() {
        return this.shipModel.getLocalRotation();
    }

    public void addSeenShip(Spaceship ship) {
        this.seenShips.add(ship);
    }

    public void clearSeenShips() {
        this.seenShips.clear();
    }

    public boolean seeShip() {
        return !this.seenShips.isEmpty();
    }

    public ArrayList<Spaceship> getSeenShips() {
        return this.seenShips;
    }
    
}
