package ru.samsung.gamestudio.screens;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.characters.Bird;
import ru.samsung.gamestudio.characters.Tube;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.components.PointCounter;


public class ScreenGame implements Screen {

    MyGdxGame myGdxGame;
    Bird bird;
    PointCounter pointCounter;
    MovingBackground movingBackground;

    int tubeCount = 3;
    int gamePoints;
    final int pointCounterMarginTop = 60;
    final int pointCounterMarginRight = 400;
    Tube[] tubes;
    boolean isGameOver;

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        bird = new Bird(0, 0, 10, 250, 200);
        tubes = new Tube[tubeCount];
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
        movingBackground = new MovingBackground();

        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }


    @Override
    public void show() {
        gamePoints = 0;
        isGameOver = false;
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {
            bird.onClick();
        }

        movingBackground.move();
        bird.update();
        bird.fly();

        if (!bird.isInField()) {
            System.out.println("not in field");
            isGameOver = true;
        }

        for (Tube tube : tubes) {
            tube.move();
            if (tube.isHit(bird)) {
                System.out.println("hit");
                isGameOver = true;
            } else if (tube.needAddPoint(bird)) {
                gamePoints += 1;
                tube.setPointReceived();
                System.out.println(gamePoints);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        movingBackground.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, gamePoints);

        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bird.dispose();
    }
}
