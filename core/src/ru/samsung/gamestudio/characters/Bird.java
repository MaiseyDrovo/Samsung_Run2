package ru.samsung.gamestudio.characters;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {

    int x, y;
    int width, height;

    int speed;
    int jumpHeight;
    float g = 0.5f;
    float ySpeed;
    final int maxHeightOfJump = 100;
    boolean jump;

    int frameCounter;
    Texture[] framesArray;

    public Bird(int x, int y, int speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        frameCounter = 0;

        framesArray = new Texture[]{
                new Texture("birdTiles/bird0.png"),
                new Texture("birdTiles/bird1.png"),
                new Texture("birdTiles/bird2.png"),
                new Texture("birdTiles/bird1.png"),
        };
    }

    public boolean isInField() {
        if (y + height < 0) return false;
        if (y > SCR_HEIGHT) return false;
        return true;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }

    public void update() {
        ySpeed += g;
    }

    public void fly() {
        if (y >= jumpHeight) {
            jump = false;
        }

        if (jump) {
            y += 10;
            ySpeed = 0;
        } else {
            y -= ySpeed;
        }
    }

    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
    }

}
