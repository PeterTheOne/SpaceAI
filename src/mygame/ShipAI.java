package mygame;

import com.jme3.math.Vector3f;

/**
 *
 * @author Luciph3r
 * @author PeterTheOne
 */
public class ShipAI {

    private Spaceship ship;
    Vector3f coordinates = new Vector3f();

    public ShipAI(Spaceship ship) {
        this.ship = ship;
    }

    public void think() {
        if (this.ship.seeShipFriend()) {
            this.ship.moveTo(this.ship.getSeenShipsFriend().get(0));
        }
    }
}
