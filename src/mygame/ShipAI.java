/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.math.Quaternion;
import java.util.ArrayList;


/**
 *
 * @author Luciph3r
 */
public class ShipAI {
    private Spaceship ship;
    public ShipAI(Spaceship ship){
        this.ship = ship;
    }

   
    
    public void think(){
        this.ship.moveDirection(Vector3f.UNIT_Z);
    }
}
