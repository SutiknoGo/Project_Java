
package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drop extends Game{
    public SpriteBatch batch;
    public BitmapFont font;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        font=new BitmapFont();
        ScreenMenuLevel5 mms = new ScreenMenuLevel5(this);
        //MainMenuScreen mms = new MainMenuScreen(this);
        Gdx.input.setInputProcessor(mms);
        this.setScreen(mms);
    }
    
    public void render(){
        super.render();
    }
   public void dispose(){
       batch.dispose();
       font.dispose();
   }

}
