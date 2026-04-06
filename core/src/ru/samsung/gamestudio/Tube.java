package ru.samsung.gamestudio;



import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Tube {
    Random random;
    Texture textureUpperTube;
    Texture textureDownTube;

    int x, gapY, distanceBetweenTubes;
    int speed = 10;
    final int width = 200;
    final int height = 700;
    int gapHeight = 400;
    int padding = 100;
    boolean isPointReceived;

    public Tube(int tubeCount, int tubeIdx) {
        random = new Random();
        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");


        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        distanceBetweenTubes = (SCR_WIDTH + width) / (tubeCount - 1);
        x = distanceBetweenTubes * tubeIdx + SCR_WIDTH;
        isPointReceived = false;
    }


    public boolean isHit(Bird bird) {
        if (bird.y <= gapY - gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x)
            return true;
        if (bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x)
            return true;
        return false;
    }

    public boolean needAddPoint(Bird bird) {
        return bird.x > x + width && !isPointReceived;
    }

    public void setPointReceived() {
        isPointReceived = true;
    }

    void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
    }

    void move() {
        x -= speed;
        if (x < -width) {
            isPointReceived = false;
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }

    void dispose() {
        textureDownTube.dispose();
        textureUpperTube.dispose();
    }

}
