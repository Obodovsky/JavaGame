package com.mygdx.game;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ParticleEmitter {
    class Particle {
        Vector2 position;
        Vector2 velocity;
        float time;

        public Particle(float x, float y, float vx, float vy) {
            position = new Vector2(x, y);
            velocity = new Vector2(vx, vy);
            time = 0.0f;
        }

        public void update(float dt) {
            if (time > 0.05f) {
                position.add(velocity.cpy().scl(dt));
                time += dt;
                if (time > 2.0f) {
                    time = 0.0f;
                }
            }
        }

        public void setup(float x, float y) {
            time = 0.1f;
            position.set(x, y);
            velocity.set((float) (Math.random() - 0.5f) * 40, (float) (Math.random() - 0.5f) * 40);
        }
    }

    Texture texture;
    Particle[] particles;

    public ParticleEmitter() {
        texture = new Texture("222.tga");
        particles = new Particle[500];
        for (int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(0, 0, 0, 0);
        }
    }

    public void render(SpriteBatch batch) {
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        for (Particle o : particles) {
            if (o.time > 0.05f) {
                batch.setColor(1.0f, o.time / 2, 0.0f, 1.0f - o.time / 2);
                //batch.setColor(o.time / 2, o.time / 2, 1.0f, 1.0f - o.time / 2);
                batch.draw(texture, o.position.x - 16, o.position.y - 16, 16, 16, 32, 32, 1.0f - o.time / 2, 1.0f - o.time / 2, 0, 0, 0, 32, 32, false, false);
            }
        }
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void add(float x, float y) {
        for (int i = 0; i < particles.length; i++) {
            if (particles[i].time < 0.05f) {
                particles[i].setup(x, y);
                break;
            }
        }
    }

    public void update(float dt) {
        for (Particle o : particles) {
            o.update(dt);
        }
    }
}
