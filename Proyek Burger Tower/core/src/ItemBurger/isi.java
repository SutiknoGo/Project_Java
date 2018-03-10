package ItemBurger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

public class isi {
    ArrayList<Texture> gambar;
    public isi(){
        gambar = new ArrayList<Texture>();
        Texture add = new Texture(Gdx.files.internal("1.png"));
        gambar.add(add);
        add = new Texture(Gdx.files.internal("2.png"));
        gambar.add(add);
        add = new Texture(Gdx.files.internal("3.png"));
        gambar.add(add);
        add = new Texture(Gdx.files.internal("4.png"));
        gambar.add(add);
        add = new Texture(Gdx.files.internal("5.png"));
        gambar.add(add);
        add = new Texture(Gdx.files.internal("6.png"));
        gambar.add(add);
        add = new Texture(Gdx.files.internal("7.png"));
        gambar.add(add);
        add = new Texture(Gdx.files.internal("8.png"));
        gambar.add(add);
        add = new Texture(Gdx.files.internal("9.png"));
        gambar.add(add);
        add = new Texture(Gdx.files.internal("10.png"));
        gambar.add(add);
        
    }

    public Texture getGambar(int i) {
        return gambar.get(i);
    }
}
