package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import event.EventManager;
import java.util.ArrayList;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.math.ColorRGBA;
import com.jme3.material.Material;

/**
 *
 * @author Luciph3r
 * @author PeterTheOne
 */
public class Game extends SimpleApplication {

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
    
    private ArrayList<Spaceship> ships = new ArrayList<Spaceship>();
    private ArrayList<Spaceship> shipsToRemove = new ArrayList<Spaceship>();
    private ArrayList<Laser> lasers = new ArrayList<Laser>();
    private ArrayList<Laser> lasersToRemove = new ArrayList<Laser>();
    private EventManager evtManager;
    private View view;
    private Environment env;

    @Override
    public void simpleInitApp() {
        this.evtManager = new EventManager(false);
        this.view = new View(this);
        
        // Set camera speed
        flyCam.setMoveSpeed(50f);

        // Create spaceships
        for (int i = 0; i < 10; i++) {
            Vector3f randomStartPosition = new Vector3f((float) Math.random() * 100,
                    (float) Math.random() * 100, (float) Math.random() * 100);
            Spaceship ship = new Spaceship(this, randomStartPosition, i % 2);
            ships.add(ship);
        }
        
        env = new Environment(this);
       
        
        // Display a line of text with a default font
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("Hello World");
        helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
        guiNode.attachChild(helloText);

        // You must add a light to make the model visible
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);

        // Load Town
        assetManager.registerLocator("town.zip", ZipLocator.class.getName());
        Spatial gameLevel = assetManager.loadModel("main.scene");
        gameLevel.setLocalTranslation(0, -5.2f, 0);
        gameLevel.setLocalScale(2);
        rootNode.attachChild(gameLevel);
        
           
 
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        this.evtManager.update();
        
        for (Spaceship ship : shipsToRemove) {
            ships.remove(ship);
        }
        for (Laser laser : lasersToRemove) {
            lasers.remove(laser);
        }
        
        for (Spaceship ship : ships) {
            ship.clearSeenShips();
        }
        for (int i = 0; i < ships.size(); i++) {
            Spaceship ship = ships.get(i);
            for (int j = i + 1; j < ships.size(); j++) {
                Spaceship ship2 = ships.get(j);
                if (ship2.getPos().subtract(ship.getPos()).length() < 100) {
                    ship.addSeenShip(ship2);
                    ship2.addSeenShip(ship);
                }
            }
        }
        for (Spaceship ship : ships) {
            ship.update(tpf);
        }
        for (Laser laser : lasers) {
            laser.update(tpf);
        }
    }
    
    public void addLaser(Laser laser) {
        lasers.add(laser);
    }

    public EventManager getEventManager() {
        return this.evtManager;
    }

    void removeSpaceship(Spaceship ship) {
        shipsToRemove.add(ship);
    }
    
    void removeLaser(Laser laser) {
        lasersToRemove.add(laser);
    }
}
