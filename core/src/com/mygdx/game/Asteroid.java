package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    Vector2 position;
    Vector2 velocity;
    Vector2 acceleration;
    static Texture texture;

    public Asteroid() {
        if (texture==null){

            texture = new Texture("asteroid.png");
        }
        position =new Vector2((float) Math.random()* Gdx.graphics.getWidth(),(float) Math.random()* Gdx.graphics.getHeight());
        velocity = new Vector2(((float) Math.random()-0.5f)*200,((float) Math.random()-0.5f)*200);
        acceleration= new Vector2(0,0);

    }

    public void render (SpriteBatch batch){
        batch.draw(texture, position.x- texture.getWidth()/2, position.y- texture.getHeight()/2);
    }

    public void update (float dt){
        position.add(velocity.cpy().scl(dt));

    //       position.x+=velocity.x*dt;
    //       position.y+=velocity.y*dt;

        velocity.add(acceleration.cpy().scl(dt));
//       velocity.x+=acceleration.x*dt;
//       velocity.y+=acceleration.y*dt;

       if(position.y<50){
           position.y=50;
           velocity.y*=-0.8;
       }
       if(position.x< - texture.getWidth()/2){
           position.x=Gdx.graphics.getWidth()+texture.getWidth()/2;
       }

        if(position.x> Gdx.graphics.getWidth()+texture.getWidth()/2){
            position.x= - texture.getWidth()/2;
       }

        if(position.y< -texture.getHeight()/2){
            position.y=Gdx.graphics.getHeight()+texture.getHeight()/2;
        }

        if(position.y> Gdx.graphics.getHeight()+texture.getHeight()/2){
            position.y=- texture.getHeight()/2;
        }
    }
}
