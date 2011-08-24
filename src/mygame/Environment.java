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
    private int size = 500;
    public Environment(Game game){
        this.game = game;
        Planets = new ArrayList<Planet>();
        for (int i =0;i<10;i++){
            Vector3f randompos = new Vector3f((0.5f - (float) Math.random())*size,(0.5f-(float) Math.random())*size,(0.5f-(float) Math.random())*size);
            Planets.add(new Planet(game,randompos));
        }
        
    }
}
