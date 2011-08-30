package spaceAI.entities;

import com.jme3.math.Vector3f;
import spaceAI.Entity;
import spaceAI.Game;

/**
 *
 * @author Luciph3r
 */
public class Planet extends Entity {

    static int num = 0;

    public Planet(Game game, Vector3f pos) {
        super(game, "Planet", "Planet#" + num++, pos);
    }
}
