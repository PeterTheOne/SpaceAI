/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import java.util.ArrayList;

/**
 *
 * @author Luciph3r
 */
public class Environment {
    private ArrayList<Planet> Planets;
    Game game;
    private int size = 300;
    public Environment(Game game){
        this.game = game;
        Planets = new ArrayList<Planet>();
        for (int i =0;i<10;i++){
            Vector3f randompos = new Vector3f((float) Math.random()*size,(float) Math.random()*size,(float) Math.random()*size);
            Planets.add(new Planet(game,randompos));
        }
       
        Planet plan = new Planet (game, new Vector3f((float) Math.random()*size,(float) Math.random()*size,(float) Math.random()*size));
        
        
    }
}
