
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

public class ScreenMenuLevel2 implements Screen, InputProcessor{
    final Drop game;
    OrthographicCamera camera;
    private Texture newgame1,newgame2,background,judul,anewgame1,anewgame2;
    int xmouse=0,ymouse=0;
    int tinggi = 600;
    
    public ScreenMenuLevel2(final Drop game){
        this.game = game;
        newgame1 = new Texture(Gdx.files.internal("play_button_inactive.png"));
        newgame2 = new Texture(Gdx.files.internal("exit_button_inactive.png"));
        anewgame1 = new Texture(Gdx.files.internal("play_button_active.png"));
        anewgame2 = new Texture(Gdx.files.internal("exit_button_active.png"));
        background = new Texture(Gdx.files.internal("bgawal.jpg"));
        judul = new Texture(Gdx.files.internal("judul.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,600);
        
    }
    
    int xm_play = 550, ym_play = 180, wm_play = 150, hm_play = 50;
    int xm_exit = 550, ym_exit = 100, wm_exit = 150, hm_exit = 50;
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
            game.batch.draw(anewgame1, xm_play, ym_play,wm_play,hm_play);
        }
        else{
            game.batch.draw(newgame1, xm_play, ym_play,wm_play,hm_play);
        }
        if (cek_menu_exit()==1){
            game.batch.draw(anewgame2, xm_exit, ym_exit,wm_exit,hm_exit);
        }
        else{
            game.batch.draw(newgame2, xm_exit, ym_exit,wm_exit,hm_exit);
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
        if (cek_menu_play()==1)
        {
            System.out.println("masuk left");
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
