package spaceAI;

import spaceAI.entities.Spaceship;
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
        
        if(this.ship.getVelocity().equals(Vector3f.ZERO)){
            Vector3f randomvector = new Vector3f ((float) Math.random(),(float) Math.random(),(float) Math.random());
            this.ship.moveDirection(randomvector);
        }
        if (this.ship.seeShipFriend()) {
            this.ship.moveTo(this.ship.getSeenShipsFriend().get(0));
        }
           if (this.ship.seeShipEnemy()) {
            int random = (int) (Math.random() * this.ship.getSeenShipsEnemy().size());
            this.ship.attack(this.ship.getSeenShipsEnemy().get(random));
        }
     
        
    }
    
}
