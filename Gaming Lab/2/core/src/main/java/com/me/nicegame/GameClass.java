package com.me.nicegame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.ScreenUtils;


import com.badlogic.gdx.math.Rectangle;
import java.util.logging.FileHandler;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameClass extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture turtleTexture;
    private float turtleX;
    private float turtleY;
    private Rectangle turtleRectangle;
    private Texture starfishTexture;
    private float starfishX;
    private float starfishY;
    private Rectangle starfishRectangle;
    private Texture oceanTexture;
    private float oceanX;
    private float oceanY;

    private Texture winMessageTexture;
    private float winMessageX;
    private float winMessageY;
    private boolean win;

    private Stage mainStage;
    private Button buttonRight;
    private Button buttonLeft;
    private Button buttonTop;
    private Button buttonBottom;
    private TextButton.TextButtonStyle textButtonStyle;


    public void create()
    {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        mainStage = new Stage();
        Gdx.input.setInputProcessor(mainStage);

        batch = new SpriteBatch();

        turtleTexture = new Texture(Gdx.files.internal("turtle-1.png"));
        turtleX = 20;
        turtleY = 20;
        turtleRectangle = new Rectangle(turtleX, turtleY, turtleTexture.getWidth(), turtleTexture.getHeight());

        starfishTexture = new Texture(Gdx.files.internal("starfish.png"));
        starfishX = 300;
        starfishY = 300;
        starfishRectangle = new Rectangle(starfishX, starfishY, starfishTexture.getWidth(), starfishTexture.getHeight());


        oceanTexture = new Texture(Gdx.files.internal("water.jpg"));
        oceanX = 0;
        oceanY = 0;

        winMessageTexture = new Texture(Gdx.files.internal("you-win.png"));
        winMessageX = 0;
        winMessageY = 0;

        win = false;

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 48;
        fontParameters.color = Color.WHITE;
        fontParameters.borderWidth = 2;
        fontParameters.borderColor = Color.BLACK;
        fontParameters.borderStraight = true;
        fontParameters.minFilter = Texture.TextureFilter.Linear;
        fontParameters.magFilter = Texture.TextureFilter.Linear;

        BitmapFont customFont = fontGenerator.generateFont(fontParameters);

        textButtonStyle = new TextButton.TextButtonStyle();
        Texture buttonTex = new Texture( Gdx.files.internal("badlogic.jpg") );
        NinePatch buttonPatch = new NinePatch(buttonTex, 24, 24, 24, 24);
        textButtonStyle.up = new NinePatchDrawable( buttonPatch );
        textButtonStyle.font = customFont;
        textButtonStyle.fontColor = Color.GRAY;

        buttonRight =  new TextButton( "RIGHT", textButtonStyle );
        buttonRight.setSize(80,50);
        buttonRight.setPosition(70,Gdx.graphics.getHeight()-203);
        buttonRight.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("#INFO", "Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("#INFO", "Pressed Text Button");
                turtleX+=5;
                return true;
            }
        });

        buttonLeft =  new TextButton( "LEFT", textButtonStyle );
        buttonLeft.setSize(80,50);
        buttonLeft.setPosition(70,Gdx.graphics.getHeight()-503);
        buttonLeft.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("#INFO", "Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("#INFO", "Pressed Text Button");
                turtleX-=5;
                return true;
            }
        });

        buttonTop =  new TextButton( "TOP", textButtonStyle );
        buttonTop.setSize(80,50);
        buttonTop.setPosition(70,Gdx.graphics.getHeight()-803);
        buttonTop.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("#INFO", "Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("#INFO", "Pressed Text Button");
                turtleY+=5;
                return true;
            }
        });

        buttonBottom =  new TextButton( "BOTTOM", textButtonStyle );
        buttonBottom.setSize(80,50);
        buttonBottom.setPosition(70,Gdx.graphics.getHeight()-1103);
        buttonBottom.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("#INFO", "Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("#INFO", "Pressed Text Button");
                turtleY-=5;
                return true;
            }
        });
    }
}
