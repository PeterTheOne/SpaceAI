package mygame;

import com.jme3.math.Vector3f;

/**
 *
 * @author Luciph3r
 * @author PeterTheOne
 */
public class ShipAI {

    private Spaceship ship;

    public ShipAI(Spaceship ship) {
        this.ship = ship;
    }

    public void think() {
        this.ship.moveDirection(Vector3f.UNIT_Z);
    }
}
