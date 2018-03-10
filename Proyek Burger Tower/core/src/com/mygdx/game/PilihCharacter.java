
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Karakter.Character;
import com.mygdx.game.Karakter.girl;
import com.mygdx.game.Karakter.ninja;
import com.mygdx.game.Karakter.robot;
import com.mygdx.game.Karakter.santa;
import javax.swing.JOptionPane;

public class PilihCharacter implements Screen, InputProcessor{
    final Drop game;
    OrthographicCamera camera;
    private Texture karakter1,karakter2,background,judul,karakter3,karakter4;
    private Texture skill1,skill2,skill3,skill4,select;
    int xmouse=0,ymouse=0;
    int tinggi = 600;
    santa mesanta = new santa();
    girl megirl = new girl();
    ninja meninja = new ninja();
    robot merobot = new robot();
    float deltaTime;
    TextureRegion currentFrame;
    
    public PilihCharacter(final Drop game){
        this.game = game;
        currentFrame = new TextureRegion();
        meninja.create();
        mesanta.create();
        merobot.create();
        megirl.create();
        karakter1 = new Texture(Gdx.files.internal("santadiem.png"));
        karakter2 = new Texture(Gdx.files.internal("girldiem.png"));
        karakter3 = new Texture(Gdx.files.internal("ninjadiem.png"));
        karakter4 = new Texture(Gdx.files.internal("robotdiem.png"));
        background = new Texture(Gdx.files.internal("bgpilih.jpeg"));
        
        skill1 = new Texture(Gdx.files.internal("skill1.png"));
        skill2 = new Texture(Gdx.files.internal("skill2.png"));
        skill3 = new Texture(Gdx.files.internal("skill3.png"));
        skill4 = new Texture(Gdx.files.internal("skill4.png"));
        select = new Texture(Gdx.files.internal("select.png"));
        judul = new Texture(Gdx.files.internal("judul.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,600);
        
        
    }
    int xm_karakter1 = 100+40, ym_karakter1 = 180, wm_karakter1 = 40, hm_karakter1 = 64;
    int xm_karakter2 = 250+40, ym_karakter2 = 180, wm_karakter2 = 40, hm_karakter2 = 64;
    int xm_karakter3 = 400+40, ym_karakter3 = 180, wm_karakter3 = 40, hm_karakter3 = 64;
    int xm_karakter4 = 550+40, ym_karakter4 = 180, wm_karakter4 = 40, hm_karakter4 = 64;
    
    int xm_skill = 10, ym_skill = 20, wm_skill = 750, hm_skill = 100;
    
    @Override
    public void show() {
        
    }

    public int cek_karakter1()
    {
        if (xmouse>xm_karakter1 && xmouse<xm_karakter1+wm_karakter1 && 
            ymouse>ym_karakter1 && ymouse<ym_karakter1+hm_karakter1){ return 1;}
        else{return 0;}
    }
    public int cek_karakter2()
    {
        if (xmouse>xm_karakter2 && xmouse<xm_karakter2+wm_karakter2 && 
            ymouse>ym_karakter2 && ymouse<ym_karakter2+hm_karakter2){ return 1;}
        else{return 0;}
    }
    public int cek_karakter3()
    {
        if (xmouse>xm_karakter3 && xmouse<xm_karakter3+wm_karakter3 && 
            ymouse>ym_karakter3 && ymouse<ym_karakter3+hm_karakter3){ return 1;}
        else{return 0;}
    }
    public int cek_karakter4()
    {
        if (xmouse>xm_karakter4 && xmouse<xm_karakter4+wm_karakter4 && 
            ymouse>ym_karakter4 && ymouse<ym_karakter4+hm_karakter4){ return 1;}
        else{return 0;}
    }
    
    
    @Override
    public void render(float delta) {
        deltaTime+= Gdx.graphics.getDeltaTime() + 0.3;
        Gdx.gl.glClearColor(0,0,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        
        game.batch.draw(background, 0, 0,800,600);
        game.batch.draw(judul, 250,300,300,200);
        if (cek_karakter1()==0){
            game.batch.draw(karakter1, xm_karakter1, ym_karakter1,wm_karakter1,hm_karakter1);
        }
        else{
            currentFrame = mesanta.getKeyFrame(deltaTime);
            game.batch.draw(currentFrame, xm_karakter1, ym_karakter1,wm_karakter1,hm_karakter1);
        }
        if (cek_karakter2()==0){
            game.batch.draw(karakter2, xm_karakter2, ym_karakter2,wm_karakter2,hm_karakter2);
        }
        else{
            currentFrame = megirl.getKeyFrame(deltaTime);
            game.batch.draw(currentFrame, xm_karakter2, ym_karakter2,wm_karakter2,hm_karakter2);
        }
        if (cek_karakter3()==0){
            game.batch.draw(karakter3, xm_karakter3, ym_karakter3,wm_karakter3,hm_karakter3);
        }
        else{
            currentFrame = meninja.getKeyFrame(deltaTime);
            game.batch.draw(currentFrame, xm_karakter3, ym_karakter3,wm_karakter3,hm_karakter3);
        }
        if (cek_karakter4()==0){
            game.batch.draw(karakter4, xm_karakter4, ym_karakter4,wm_karakter4,hm_karakter4);
        }
        else{
            currentFrame = merobot.getKeyFrame(deltaTime);
            game.batch.draw(currentFrame, xm_karakter4, ym_karakter4,wm_karakter4,hm_karakter4);
        }
        
        if (cek_karakter1()==1){
            game.batch.draw(skill1, xm_skill, ym_skill,wm_skill,hm_skill);
        }
        else if (cek_karakter2()==1){
            game.batch.draw(skill2, xm_skill, ym_skill,wm_skill,hm_skill);
        }
        else if (cek_karakter3()==1){
            game.batch.draw(skill3, xm_skill, ym_skill,wm_skill,hm_skill);
        }
        else if (cek_karakter4()==1){
            game.batch.draw(skill4, xm_skill, ym_skill,wm_skill,hm_skill);
        }
        else{
            game.batch.draw(select, xm_skill, ym_skill,wm_skill,hm_skill);
        }
        
        
        game.font.setColor(Color.RED);
        //game.font.draw(game.batch,"Welcome to Drop !!!",  200, 400);
        //game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.font.draw(game.batch, xmouse + "-" + ymouse, 10, 100);
        game.batch.end();
        /*if (Gdx.input.isTouched()){
            game.setScreen(new ScreenMenuLevel1(game));
            dispose();
        }*/
        
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        if (cek_karakter1()==1)
        {
            System.out.println("masuk left");
            game.setScreen(new ScreenMenuLevel1(game));
            //game.setScreen(new MyGdxGame(game));
            dispose();
        }//else if (cek_menu_exit()==1)
        //{  
           // Gdx.app.exit();
           // return true;
       // }      
      return false;
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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        xmouse=screenX;
        ymouse=tinggi-screenY;
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
