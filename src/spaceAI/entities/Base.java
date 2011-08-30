package spaceAI.entities;

import com.jme3.math.Vector3f;
import spaceAI.Entity;
import spaceAI.Game;

/**
 *
 * @author PeterTheOne
 */
public class Base extends Entity {

    static int num = 0;

    public Base(Game game, Vector3f pos) {
        super(game, "Base", "Base#" + num++, pos);
    }
}
