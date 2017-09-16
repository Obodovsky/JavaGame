package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Asteroid[] asteroids;
	Ship hero;
	static ParticleEmitter pem;
	boolean gameOver;
	boolean isNonHit=true;

	@Override
	public void create() {
		batch = new SpriteBatch();
		hero = new Ship();
		pem = new ParticleEmitter();
		asteroids = new Asteroid[50];
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i] = new Asteroid();
		}

	}

	@Override
	public void render() {
		if(!gameOver){
			float dt = Gdx.graphics.getDeltaTime();
			update(dt);
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			for (Asteroid asteroid : asteroids) {
				asteroid.render(batch);
			}
			hero.render(batch,isNonHit);
			pem.render(batch);
			batch.end();
		}
		else{
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			Texture gameOverTexture =new Texture("gameover.png");
			batch.draw(gameOverTexture, Gdx.graphics.getWidth()/2- gameOverTexture.getWidth()/2, Gdx.graphics.getHeight()/2-gameOverTexture.getHeight()/2);
			batch.end();
		}

	}

	public void update(float dt) {

		hero.update(dt);
		isNonHit=true;
		pem.update(dt);
		for (Asteroid asteroid : asteroids) {
			if(hero.position.cpy().sub(asteroid.position).len()<32){
				asteroid.velocity=hero.position.cpy().sub(asteroid.position).nor().scl(-150);
				hero.health--;
				isNonHit=false;
				if(hero.health<=0){
					gameOver=true;
					break;
				}

			}
			asteroid.update(dt);
		}
	}

	@Override
	public void dispose() {

		batch.dispose();
	}
}
