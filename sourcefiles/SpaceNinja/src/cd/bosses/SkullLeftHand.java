package cd.bosses;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SkullLeftHand extends Boss {

	public SkullLeftHand() {
		super(900, 600, "lefthand");
		_width = 130;
		_height = (int)(140*1.3);
		_staticimage = new Image("skullboss/lfist.png");
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_spawning) {
			executeSpawn();
		}
		System.out.println(_x);
		
	}

	@Override
	public void spawn() {
		_x = -200;
		_y = 110;
		_spawning = true;
		_counter2 = 0;
		
	}
	
	public void executeSpawn() {
		if(_counter2 == 10) {
			_xvelocity = 5;
		}
		if(_counter2 == 75) {
			_xvelocity = 0;
			_spawning  = false;
		}
	}

}
