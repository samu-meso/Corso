package com.me.nicegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.logging.FileHandler;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameClass extends ApplicationAdapter {
//    private SpriteBatch batch;
//    private Texture image;
//
//    @Override
//    public void create() {
//        batch = new SpriteBatch();
//        image = new Texture("libgdx.png");
//    }
//
//    @Override
//    public void render() {
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        batch.begin();
//        batch.draw(image, 140, 210);
//        batch.end();
//    }
//
//    @Override
//    public void dispose() {
//        batch.dispose();
//        image.dispose();
//    }
private SpriteBatch batch;
    private Texture image;

    @Override
    public void create() {
        batch = new SpriteBatch();
        FileHandle iconFile = Gdx.files.internal("badlogic.jpg");
        image = new Texture(iconFile);
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(image, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
