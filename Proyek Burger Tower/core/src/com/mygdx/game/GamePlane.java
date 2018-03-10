
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import java.util.ArrayList;

public class GamePlane implements Screen, InputProcessor {
    final Drop game;
    SpriteBatch batch;
    Texture moveSheet,img,imgpeluru;
    public Animation<TextureRegion> standbyAnimation;
    TextureRegion[] texture_goleft = new TextureRegion[5];
    float deltaTime;
    Sprite sprite;
    boolean udah=false;
    ArrayList<CPeluru> peluru = new ArrayList<CPeluru>();
    
    CPlane plane = new CPlane();

    public GamePlane (final Drop game){
        this.game = game;
        plane.setX(100); plane.setY(30);
        batch = new SpriteBatch();
        img   = new Texture("back.png");
        moveSheet = new Texture(Gdx.files.internal("plane.png"));
        imgpeluru = new Texture(Gdx.files.internal("bolatembak.png"));
        TextureRegion[][] textureRegion = TextureRegion.split(moveSheet, 80, 80);
        for ( int i = 0 ; i < 5 ; i++ ){
            texture_goleft[i] = textureRegion[0][i];
        }
        standbyAnimation = new Animation<TextureRegion>(1f, texture_goleft);
        standbyAnimation.setPlayMode(Animation.PlayMode.LOOP);
        Timer.schedule(new Task() {           
            @Override
            public void run() {
               //changeScreen();
            }

         }, 15);
    }
    @Override
    public void show() {
        
    }
   
    @Override
    public void render(float delta) {
        deltaTime+= Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        TextureRegion currentFrame = standbyAnimation.getKeyFrame(deltaTime);
        sprite = new Sprite(texture_goleft[0]);
        float pergerakan=Gdx.graphics.getDeltaTime()*10.0f;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            plane.setX((int)(plane.getX()-Gdx.graphics.getDeltaTime()*pergerakan));
            //System.out.println("pos plan x " + plane.getX() + " pergeakan = " + pergerakan);
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
           // System.out.println("kanan ditekan");
            plane.setX((int)(plane.getX()+(1+ Gdx.graphics.getDeltaTime()*pergerakan)));
            //System.out.println("pos plan x " + plane.getX() + " pergeakan = " + pergerakan);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            
            System.out.println("space ditekan");
            if (udah==false)
            {    peluru.add(new CPeluru(plane.getX()+30,plane.getY()+20));
            }
            //System.out.println(peluru.size() + " pos plan x " + peluru.get(peluru.size()-1).getY() + " pergerakan = " + pergerakan);
            udah = true;
        }
        for (int i = 0; i < peluru.size(); i++) {
            peluru.get(i).setY((int)(peluru.get(i).getY()+3)); //(Gdx.graphics.getDeltaTime()*pergerakan))
            batch.draw(imgpeluru, peluru.get(i).getX(), peluru.get(i).getY(),20,20);
        }
       //sprite.draw(batch);
        batch.draw(currentFrame, plane.getX(), plane.getY());
        batch.end();
        udah=false;
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
        batch.dispose();
        moveSheet.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Sedang touchdown");
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
