package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import javafx.scene.text.Text;

public class Ship {
    Texture texture;
    Vector2 position;
    Vector2 velocity;
    Vector2 acceleration;
    float enginePower;
    float angle;
    float rotationSpeed;
    int health;

    public Ship() {
        texture = new Texture("spaceship.png");
        position = new Vector2(640, 360);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(1, 0);
        angle = 0.0f;
        rotationSpeed = 3.14f;
        enginePower = 500.0f;
        health=10;
    }

    public void render(SpriteBatch batch, boolean isNonHit) {
        if(!isNonHit){
            batch.setColor(1, 0, 0, 0.6f);
        }
        batch.draw(texture, position.x - 32, position.y - 32, 32, 32, 64, 64, 1.0f, 1.0f, (float) Math.toDegrees(angle), 0, 0, 64, 64, false, false);

    }

    public void update(float dt) {

        acceleration.set(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            acceleration.set(enginePower * (float) Math.cos(angle), enginePower * (float) Math.sin(angle));
            MyGdxGame.pem.add(position.x + 32 * (float) Math.cos(angle - 1.57f), position.y + 32 * (float) Math.sin(angle - 1.57f));
            MyGdxGame.pem.add(position.x + 32 * (float) Math.cos(angle - 1.57f), position.y + 32 * (float) Math.sin(angle - 1.57f));
            MyGdxGame.pem.add(position.x + 32 * (float) Math.cos(angle + 1.57f), position.y + 32 * (float) Math.sin(angle + 1.57f));
            MyGdxGame.pem.add(position.x + 32 * (float) Math.cos(angle + 1.57f), position.y + 32 * (float) Math.sin(angle + 1.57f));
        }
        velocity.add(acceleration.cpy().scl(dt));
        velocity.scl(0.98f);
        position.add(velocity.cpy().scl(dt));
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += rotationSpeed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= rotationSpeed * dt;
        }
        if (position.x < 0) position.x = 1280;
        if (position.x > 1280) position.x = 0;
        if (position.y < 0) position.y = 720;
        if (position.y > 720) position.y = 0;
    }


}

