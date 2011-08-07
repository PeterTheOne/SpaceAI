package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import com.jme3.effect.ParticleMesh;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.Node;

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

    public Spaceship newShip(Vector3f pos, int team) {
        Cylinder c = new Cylinder(12, 12, 0.1f, 1f, true);

        Geometry laser = new Geometry("Cylinder", c);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);

        laser.setMaterial(mat);


        Spatial spaceshipmodel = assetManager.loadModel("Models/shipA_OBJ/shipA_OBJ.j3o");
        Spaceship ship = new Spaceship(spaceshipmodel, laser, rootNode, pos, team);
        spaceshipmodel.scale(0.1f);
        rootNode.attachChild(spaceshipmodel);
        rootNode.attachChild(laser);
        return ship;
    }

    @Override
    public void simpleInitApp() {
        // Set camera speed
        flyCam.setMoveSpeed(50f);

        // Create spaceships
        for (int i = 0; i < 10; i++) {
            Vector3f randomStartPosition = new Vector3f((float) Math.random() * 100,
                    (float) Math.random() * 100, (float) Math.random() * 100);
            Spaceship ship = newShip(randomStartPosition, i % 2);
            ships.add(ship);
        }




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

    public void simpleUpdate(float tpf) {
        for (Spaceship ship : ships) {
            ship.clearSeenShips();
        }
        ArrayList<Spaceship> spaceshipsToRemove = new ArrayList<Spaceship>();
        for (Spaceship ship : ships) {
            if (ship.getHealth() <= 0) {
                spaceshipsToRemove.add(ship);
            }
        }
        for (Spaceship ship : spaceshipsToRemove) {
            ships.remove(ship);
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
    }
}
