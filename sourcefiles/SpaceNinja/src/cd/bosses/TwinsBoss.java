package cd.bosses;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class TwinsBoss extends Boss {

	public TwinsBoss() {
		super(200, 100, "twinsboss");
		_width = (int)(86 * 0.6);
		_height = (int)(192*0.6);
		_health = 1200;
		_sprites = new ArrayList<Image>();
		_sprites.add(new Image("twinsboss/a1.png"));
		_sprites.add(new Image("twinsboss/a2.png"));
		_rate = 5;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void spawn() {
		// TODO Auto-generated method stub
		
	}

}
