package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import java.util.ArrayList;
import java.math.*;

/** Sample 3 - how to load an OBJ model, and OgreXML model,
 * a material/texture, or text. */
public class Game extends SimpleApplication {

    public static void main(String[] args) {
        Game app = new Game();

        app.start();

    }
    private ArrayList<Spaceship> Ships = new ArrayList<Spaceship>();
    
    public Spaceship newShip(){
         Spatial spaceshipmodel = assetManager.loadModel("Models/shipA_OBJ/shipA_OBJ.j3o");
       Spaceship ship = new Spaceship(spaceshipmodel);
       
       
        spaceshipmodel.scale(0.1f);
        rootNode.attachChild(spaceshipmodel);
        return ship;
    }
    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(50f);
        for(int i=1;i<2;i++){
            Spaceship ship = newShip();
            Vector3f vec = new Vector3f(1,2,3);
            ship.moveDirection(vec);
            Ships.add(ship);
        }


        // Create a wall with a simple texture from test_data
       

        // Display a line of text with a default font
       
//
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

        assetManager.registerLocator("town.zip", ZipLocator.class.getName());
        Spatial gameLevel = assetManager.loadModel("main.scene");
        gameLevel.setLocalTranslation(0, -5.2f, 0);
        gameLevel.setLocalScale(2);
        rootNode.attachChild(gameLevel);

    }
    
     public void simpleUpdate(float tpf) {
             for(Spaceship ship : Ships){
             ship.clearSeenShips();
             }
         
       for(int i = 0;i<Ships.size();i++){
           
         Spaceship ship = Ships.get(i);
           for(int j = i+1;j<Ships.size();j++){
               Spaceship ship2 = Ships.get(j);
               if(ship2.getPos().subtract(ship.getPos()).length()<5){
                   ship.addSeenShip(ship2);
                   ship2.addSeenShip(ship);
               }
           }
       ship.update(tpf);
    }
     }
}