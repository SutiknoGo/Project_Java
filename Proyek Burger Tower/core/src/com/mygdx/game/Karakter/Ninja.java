
package com.mygdx.game.Karakter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.javafx.scene.traversal.Direction;

public class ninja extends Character {
    SpriteBatch batch;
        Texture moveSheet;
        public Animation<TextureRegion> standbyAnimation;
        public Animation<TextureRegion> walkAnimation;
        TextureRegion[] karaktergerak = new TextureRegion[10];
        public float deltaTime;
	public Texture img;
        CharacterState currentState;
        CharacterDirection direction;
        public int arah;
	Sprite sprite;
        
    public void create () {
            batch = new SpriteBatch();
            img = new Texture("bg.png");
            moveSheet = new Texture(Gdx.files.internal("ninja.png"));
            TextureRegion[][] textureRegion = TextureRegion.split(moveSheet,40,64);
            for ( int i = 0 ; i < 10 ; i++ ){
                karaktergerak[i] = textureRegion[0][i];
            }
            
            walkAnimation = new Animation<TextureRegion>(1f, karaktergerak);
            walkAnimation.setPlayMode(Animation.PlayMode.LOOP);     
            standbyAnimation = new Animation<TextureRegion>(1f, karaktergerak);
            standbyAnimation.setPlayMode(Animation.PlayMode.LOOP);     
    }

    public void setCurrentState(CharacterState currentState) {
        this.currentState = currentState;
    }

    public void setDirection(CharacterDirection direction) {
        this.direction = direction;
    }
    public TextureRegion getKeyFrame(float deltatime){
        TextureRegion currentFrame = standbyAnimation.getKeyFrame(deltatime);
        
        if ( currentState == CharacterState.WALKING){
            
            currentFrame = walkAnimation.getKeyFrame(deltatime);
            
            if ( currentFrame.isFlipX() && direction == CharacterDirection.RIGHT){
                currentFrame.flip(true, false);
            }
            if ( !currentFrame.isFlipX() && direction == CharacterDirection.LEFT){
                currentFrame.flip(true, false);
            }
            
        } 
        return currentFrame;
    }
    public enum CharacterState{
        STANDBY,
        WALKING,
        ATTACKING,
        JUMPING_UP,
        JUMPING_DOWN;
    }
    
    public enum CharacterDirection{
        LEFT,
        RIGHT;
    }
}
