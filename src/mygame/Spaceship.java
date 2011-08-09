package mygame;

import com.jme3.math.Vector3f;
import java.util.ArrayList;
import event.EventManager;

/**
 *
 * @author Luciph3r
 * @author PeterTheOne
 */
public class Spaceship {

    private enum MoveState {

        STOP, DIRECTION, TARGET
    };
    private Game game;
    
    private String name;
    private static int count = 0;
    private int number;
    
    private MoveState moveState;
    private Vector3f pos;
    private Vector3f velo;
    private Vector3f dest;
    private ShipAI computer;
    private ArrayList<Spaceship> seenShips;
    private ArrayList<Spaceship> seenShipsFriend;
    private ArrayList<Spaceship> seenShipsEnemy;
    private int team;
    private int health;
    private int attackBuffer;
    
    private final int MAXSPEED = 3;
    private final int ATTACKBUFFERSIZE = 300;

    public Spaceship(Game game, Vector3f pos, int team) {
        this.game = game;
        this.pos = pos;
        this.velo = new Vector3f();
        this.dest = new Vector3f();
        this.moveState = MoveState.STOP;
        this.computer = new ShipAI(this);
        this.seenShips = new ArrayList<Spaceship>();
        this.seenShipsFriend = new ArrayList<Spaceship>();
        this.seenShipsEnemy = new ArrayList<Spaceship>();
        this.team = team;
        this.health = 100;
        this.attackBuffer = 0;
        this.number = count++;
        this.name = "Spaceship#" + number;
        EventManager evtManager = this.game.getEventManager();
        evtManager.enqueueEvent(new SpaceshipCreatedEvent(this.name));
        evtManager.enqueueEvent(new SpaceshipMovedEvent(this.name, this.pos));
    }

    public void update(float tpf) {
        //TODO: implement better fix for issue: shooting after being killed
        if (this.health <= 0) {
            return;
        }

        this.computer.think();
        
        if (moveState == MoveState.TARGET) {
            if (this.getPos().subtract(dest).length() > 0.01f) {
                this.velo = dest.subtract(getPos()).normalize().mult(MAXSPEED);
            } else {
                this.stop();
            }
        }
        if (attackBuffer > 0) {
            this.attackBuffer -= tpf;
            //TODO: remove
            /*if (attackBuffer < ATTACKBUFFERSIZE * 0.9f && rootNode.hasChild(laser)) {
                rootNode.detachChild(laser);
            }*/
        }

        this.pos = this.pos.add(this.velo.mult(tpf));
        if (!this.velo.equals(Vector3f.ZERO)) {
            EventManager evtManager = this.game.getEventManager();
            evtManager.enqueueEvent(new SpaceshipMovedEvent(this.name, this.pos));
        }
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
    }

    public void moveTo(Vector3f dest) {
        if (getPos().equals(dest)) {
            this.stop();
            return;
        }
        this.moveState = MoveState.TARGET;
        this.dest = dest;

    }

    public void moveTo(Spaceship ship) {
        Vector3f coordinates = ship.getPos();
        if (ship.getPos().subtract(this.getPos()).length() > 6) {
            moveTo(coordinates);
        } else {
            this.stop();
        }
    }

    public Vector3f getPos() {
        return this.pos;
    }

    public int getTeam() {
        return this.team;
    }

    public void addSeenShip(Spaceship ship) {
        this.seenShips.add(ship);
        if (ship.getTeam() == this.team) {
            this.seenShipsFriend.add(ship);
        } else {
            this.seenShipsEnemy.add(ship);
        }
    }

    public void clearSeenShips() {
        this.seenShips.clear();
        this.seenShipsFriend.clear();
        this.seenShipsEnemy.clear();
    }

    public boolean seeShip() {
        return !this.seenShips.isEmpty();
    }

    public boolean seeShipFriend() {
        return !this.seenShipsFriend.isEmpty();
    }

    public boolean seeShipEnemy() {
        return !this.seenShipsEnemy.isEmpty();
    }

    public ArrayList<Spaceship> getSeenShips() {
        return this.seenShips;
    }

    public ArrayList<Spaceship> getSeenShipsFriend() {
        return this.seenShipsFriend;
    }

    public ArrayList<Spaceship> getSeenShipsEnemy() {
        return this.seenShipsEnemy;
    }

    public void attack(Spaceship target) {
        if (this.attackBuffer <= 0) {
            this.attackBuffer = ATTACKBUFFERSIZE;
            //TODO: remove
            /*float length = target.getPos().subtract(this.getPos()).length();
            Vector3f center = target.getPos().subtract(this.getPos()).mult(0.5f).add(this.getPos());
            if (!rootNode.hasChild(laser)) {
                rootNode.attachChild(laser);
            }
            laser.setLocalScale(1, 1, length);
            laser.setLocalTranslation(center);
            laser.lookAt(target.getPos(), Vector3f.UNIT_Y);*/
            target.hit();
        }
    }

    public void hit() {
        this.health -= 3;
        if (this.health <= 0) {
            EventManager evtManager = this.game.getEventManager();
            this.game.removeSpaceship(this);
            evtManager.enqueueEvent(new SpaceshipDestroyedEvent(this.name));
        }
    }

    public int getHealth() {
        return health;
    }
    
    public String getName() {
        return this.name;
    }
}
