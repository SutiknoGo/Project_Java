
package com.mygdx.game;
import ItemBurger.isi;
import Rectangle.Crectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Karakter.Bos;
import com.mygdx.game.Karakter.Character;
import com.mygdx.game.Karakter.kroco;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class ScreenMenuLevel5 implements Screen, InputProcessor{
    final Drop game;
    SpriteBatch batch;
    Texture img;
    Sprite sprite;
    float deltaTime;
    Texture tbg;
    private Texture dropImage;
    private Texture bucketImage;
    private Sound dropSound;
    private Music rainMusic;
    private Rectangle bucket;
    private OrthographicCamera camera;
    private TextureRegion texturekarakter;
    private ArrayList<Crectangle> raindrops = new ArrayList<Crectangle>();
    private ArrayList<Crectangle> didapat = new ArrayList<Crectangle>();
    private long lastDropTime;
    private kroco me;
    public isi ImageBurger;
    public int ydidapat;
    public boolean miring;
    Crectangle ceking;
    public int aarah;
    BitmapFont font;
    CharSequence str;
    public int score;
    
    public ScreenMenuLevel5(final Drop game){
        this.game = game;
        font = new BitmapFont();
        score = 0;
        miring=false;
        ydidapat = 85;
        aarah = 2;
        sprite = new Sprite();
        me = new kroco();
        me.create();
        // load the images for the droplet and the bucket, 64x64 pixels each
        tbg = new Texture(Gdx.files.internal("bg.png"));
        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // start the playback of the background music immediately
        rainMusic.setLooping(true);
        rainMusic.play();
        //create the camera and the spritebatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        batch = new SpriteBatch();
        //create a rectangle to logically
        bucket = new Rectangle();
        bucket.x = 0;
        bucket.y = 45;
        bucket.width = 40;
        bucket.height = 64;
        
        ceking = new Crectangle();
        ceking.width = 40;
        ceking.height = 64;
        ceking.x = bucket.x;
        ceking.y = bucket.y;
        
        raindrops = new ArrayList<Crectangle>();
        spawnRaindrop();
        
        ImageBurger = new isi();
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
         deltaTime+= Gdx.graphics.getDeltaTime() + 0.2 ;
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            //cetak background
            batch.draw(tbg,0,0);
            //cetak score
            str = "" + score;
            font.setColor(Color.BLACK);
            font.getData().setScale(2, 2);
            font.draw(batch, str, 700, 600);
            //print isi turun
            for (Crectangle raindrop: raindrops) {
                batch.draw(ImageBurger.getGambar(raindrop.getJenis()), raindrop.x,raindrop.y);
            }
            //
            for (Crectangle dapat: didapat) {
                batch.draw(ImageBurger.getGambar(dapat.getJenis()),bucket.x-20,dapat.y);
            }
            
            
            TextureRegion currentFrame = me.getKeyFrame(deltaTime);
            batch.draw(currentFrame, bucket.x, bucket.y);
            batch.end();
            ceking.x = bucket.x;
            
            //mouse touch
            if(Gdx.input.isTouched()) 
            {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                bucket.x = touchPos.x - 64 / 2;
            }
            //keyboard
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                bucket.x -= 400 * Gdx.graphics.getDeltaTime();
                me.setCurrentState(Character.CharacterState.WALKING);
                me.setDirection(Character.CharacterDirection.LEFT);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                bucket.x += 400 * Gdx.graphics.getDeltaTime();aarah=3;
                me.setCurrentState(Character.CharacterState.WALKING);
                me.setDirection(Character.CharacterDirection.RIGHT);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                score = 0;
                didapat.clear();ceking.x = bucket.x;ceking.y = bucket.y;ydidapat = 80;    
            }

            //bucket screen limit
             if(bucket.x < 0) bucket.x = 0;
             if(bucket.x > 800 - 64) bucket.x = 800 - 64;

             // check if we need to create a new raindrop
             if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

             // move the raindrops, remove any that are beneath the bottom edge of
             // the screen or that hit the bucket. In the later case we play back
             // a sound effect as well.
             
            //cek coallition dan pergerakan itemdrop
            Iterator<Crectangle> iter = raindrops.iterator();
            while(iter.hasNext()) {
                Crectangle raindrop = iter.next();
                raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
                if(raindrop.y + 64 < 0) iter.remove();
                
                if(raindrop.overlaps(ceking)) {
                score+=50;
                ceking.y+=35;
                dropSound.play();
                ydidapat += 35;
                raindrop.setY(ydidapat);
                didapat.add(raindrop);
                iter.remove();
            }
        }
    }

    private void spawnRaindrop() 
    {           
        Crectangle raindrop = new Crectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 1000;
        raindrop.width = 80;
        raindrop.height = 35;
        raindrop.setJenis(MathUtils.random(0, 9));
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
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
        img.dispose();
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        batch.dispose();
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
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
