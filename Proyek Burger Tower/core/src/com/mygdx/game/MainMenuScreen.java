
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import javax.swing.JOptionPane;

public class MainMenuScreen implements Screen, InputProcessor{
    final Drop game;
    OrthographicCamera camera;
    private Texture play,exit,background,judul,aexit,aplay;
    private Texture pilihchar,apilihchar,pilihlevel,apilihlevel;
    private Texture highscore,ahighscore;
    int xmouse=0,ymouse=0;
    int tinggi = 600;
    
    public MainMenuScreen(final Drop game){
        this.game = game;
        pilihchar = new Texture(Gdx.files.internal("character_button_inactive.png"));
        apilihchar = new Texture(Gdx.files.internal("character_button_active.png"));
        pilihlevel = new Texture(Gdx.files.internal("levels_button_inactive.png"));
        apilihlevel = new Texture(Gdx.files.internal("levels_button_active.png"));
        highscore = new Texture(Gdx.files.internal("hs_button_inactive.png"));
        ahighscore = new Texture(Gdx.files.internal("hs_button_active.png"));
        exit = new Texture(Gdx.files.internal("exit_button_inactive.png"));
        aexit = new Texture(Gdx.files.internal("exit_button_active.png"));
        play = new Texture(Gdx.files.internal("play_button_inactive.png"));
        aplay = new Texture(Gdx.files.internal("play_button_active.png"));
        background = new Texture(Gdx.files.internal("bgawal.jpg"));
        judul = new Texture(Gdx.files.internal("judul.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,600);
        
    }
    
    int xm_play = 550, ym_play = 360, wm_play = 150, hm_play = 50;
    int xm_karakter = 550, ym_karakter = 260, wm_karakter = 150, hm_karakter = 50;
    int xm_level = 550, ym_level = 180, wm_level = 150, hm_level = 50;
    int xm_hs = 550, ym_hs = 100, wm_hs = 150, hm_hs = 50;
    int xm_exit = 550, ym_exit = 20, wm_exit = 150, hm_exit = 50;
    @Override
    public void show() {
        
    }

    public int cek_menu_play()
    {
        if (xmouse>xm_play && xmouse<xm_play+wm_play && 
            ymouse>ym_play && ymouse<ym_play+hm_play){ return 1;}
        else{return 0;}
    }
    
    public int cek_menu_exit()
    {
        if (xmouse>xm_exit && xmouse<xm_exit + wm_exit && 
            ymouse>ym_exit && ymouse<ym_exit + hm_exit){ return 1;}
        else{return 0;}
    }
    public int cek_menu_karakter()
    {
        if (xmouse>xm_karakter && xmouse<xm_karakter + wm_karakter && 
            ymouse>ym_karakter && ymouse<ym_karakter + hm_karakter){ return 1;}
        else{return 0;}
    }
    public int cek_menu_level()
    {
        if (xmouse>xm_level && xmouse<xm_level + wm_level && 
            ymouse>ym_level && ymouse<ym_level + hm_level){ return 1;}
        else{return 0;}
    }
    public int cek_menu_hs()
    {
        if (xmouse>xm_hs && xmouse<xm_hs + wm_hs && 
            ymouse>ym_hs && ymouse<ym_hs + hm_hs){ return 1;}
        else{return 0;}
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        
        game.batch.draw(background, 0, 0,800,600);
        game.batch.draw(judul, 250,300,300,300);
        if (cek_menu_play()==1){
            game.batch.draw(aplay, xm_play, ym_play,wm_play,hm_play);
        }
        else{
            game.batch.draw(play, xm_play, ym_play,wm_play,hm_play);
        }
        if (cek_menu_exit()==1){
            game.batch.draw(aexit, xm_exit, ym_exit,wm_exit,hm_exit);
        }
        else{
            game.batch.draw(exit, xm_exit, ym_exit,wm_exit,hm_exit);
        }
        if (cek_menu_karakter()==1){
            game.batch.draw(apilihchar, xm_karakter, ym_karakter,wm_karakter,hm_karakter);
        }
        else{
            game.batch.draw(pilihchar, xm_karakter, ym_karakter,wm_karakter,hm_karakter);
        }
        if (cek_menu_level()==1){
            game.batch.draw(apilihlevel, xm_level, ym_level,wm_level,hm_level);
        }
        else{
            game.batch.draw(pilihlevel, xm_level, ym_level,wm_level,hm_level);
        }
        if (cek_menu_hs()==1){
            game.batch.draw(ahighscore, xm_hs, ym_hs,wm_hs,hm_hs);
        }
        else{
            game.batch.draw(highscore, xm_hs, ym_hs,wm_hs,hm_hs);
        }
        
        game.font.setColor(Color.RED);
        //game.font.draw(game.batch,"Welcome to Drop !!!",  200, 400);
        //game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.font.draw(game.batch, xmouse + "-" + ymouse, 10, 100);
        game.batch.end();
        /*if (Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }*/
        
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        if (cek_menu_karakter()==1)
        {
            PilihCharacter mms = new PilihCharacter(game);
            Gdx.input.setInputProcessor(mms);
            game.setScreen(mms);
            //game.setScreen(new MyGdxGame(game));
            dispose();
        }else if (cek_menu_exit()==1)
        {  
            Gdx.app.exit();
            return true;
        }   
        else if (cek_menu_play()==1)
        {  
            Gdx.app.exit();
            return true;
        } 
        else if (cek_menu_level()==1)
        {  
            Gdx.app.exit();
            return true;
        } 
        else if (cek_menu_hs()==1)
        {  
            Gdx.app.exit();
            return true;
        } 
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
        xmouse=screenX;ymouse=tinggi-screenY;
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
