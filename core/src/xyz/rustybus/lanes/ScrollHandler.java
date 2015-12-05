package xyz.rustybus.lanes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

/**
 * Created by kienme on 5/12/15.
 *
 */

public class ScrollHandler {
    static int SCROLL_SPEED=-20;
    static float w, h;

    Vehicle vehicles[];
    Asphalt asphalt1, asphalt2, asphalt3;
    Random random;

    ScrollHandler() {
        w=Lanes.VIEWPORT_WIDTH;
        h=Lanes.VIEWPORT_WIDTH* Gdx.graphics.getHeight()/Gdx.graphics.getWidth();

        Texture asphaltTexture=new Texture("asphalt.png");
        asphalt1=new Asphalt(0, 0, asphaltTexture);
        asphalt2=new Asphalt(0, asphalt1.getY()+h, asphaltTexture);
        asphalt3=new Asphalt(0, asphalt2.getY()+h, asphaltTexture);
        random=new Random();

        Texture vehiclesTexture=new Texture("vehicles.png");
        vehicles=new Vehicle[4];
        for(int i=0; i<vehicles.length; ++i) {
            Side s=random.nextInt(2)==0?Side.LEFT:Side.RIGHT;
            vehicles[i] = new Vehicle(s, new TextureRegion(vehiclesTexture, 100*i, 0, 100, 200));
        }

    }

    public void update() {
        //Handle positions of vehicles
        asphalt1.moveY(SCROLL_SPEED);
        asphalt2.moveY(SCROLL_SPEED);
        asphalt3.moveY(SCROLL_SPEED);
    }

    public void render(SpriteBatch batch) {
        batch.draw(asphalt1.getImage(), asphalt1.getX(), asphalt1.getY(), w, h);
        batch.draw(asphalt2.getImage(), asphalt2.getX(), asphalt2.getY(), w, h);
        batch.draw(asphalt3.getImage(), asphalt3.getX(), asphalt3.getY(), w, h);

        for(int i=0; i<vehicles.length; ++i)
            batch.draw(vehicles[i].getVehicle(), vehicles[i].getX(), vehicles[i].getY());
    }

    public void dispose() {
        asphalt1.getImage().getTexture().dispose();
        asphalt2.getImage().getTexture().dispose();
        asphalt3.getImage().getTexture().dispose();

        for(int i=0; i<vehicles.length; ++i) {
            vehicles[i].getVehicle().getTexture().dispose();
        }
    }
}