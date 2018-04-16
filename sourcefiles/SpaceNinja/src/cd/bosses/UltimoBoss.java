package cd.bosses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.Backdrop;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class UltimoBoss extends Boss {

	private List<Image> _balls = new ArrayList<Image>();
	private int _spriteindex;
	private int _rate2 = 3;
	private int _form;
	private int _repeat1;
	private int _atkcount;
	private Backdrop _flash = new Backdrop(new Image("ultimoboss/flash.png"), 0, 0, 900, 600);
	private boolean _changeform1;
	private boolean _changeform2;
	private boolean _changeform3;
	private boolean _changeform4;
	private boolean _changeform0;
	private int _atk1count;
	
	
	public UltimoBoss() {
		super(900, 600, "ultimoboss");
		_health = 2000;
		_width = 150;
		_height = 150;
		_staticimage = new Image("ultimoboss/1.png");
		_balls.add(new Image("ultimoboss/balls1/1.png"));
		_balls.add(new Image("ultimoboss/balls1/2.png"));
		_balls.add(new Image("ultimoboss/balls1/3.png"));
		_balls.add(new Image("ultimoboss/balls1/4.png"));
		_balls.add(new Image("ultimoboss/balls1/5.png"));
		_balls.add(new Image("ultimoboss/balls1/6.png"));
		_balls.add(new Image("ultimoboss/balls1/7.png"));
		_balls.add(new Image("ultimoboss/balls1/8.png"));
		_balls.add(new Image("ultimoboss/balls1/9.png"));
		_balls.add(new Image("ultimoboss/balls1/10.png"));
		_balls.add(new Image("ultimoboss/balls1/11.png"));
		_balls.add(new Image("ultimoboss/balls1/12.png"));
		_balls.add(new Image("ultimoboss/balls1/13.png"));
		_balls.add(new Image("ultimoboss/balls1/14.png"));
		_balls.add(new Image("ultimoboss/balls1/15.png"));
		_balls.add(new Image("ultimoboss/balls1/16.png"));
		_balls.add(new Image("ultimoboss/balls1/17.png"));
		_balls.add(new Image("ultimoboss/balls1/18.png"));
		_balls.add(new Image("ultimoboss/balls1/19.png"));
		_balls.add(new Image("ultimoboss/balls1/20.png"));
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		Image bd = _balls.get(_spriteindex);
		if(_counter % _rate2 == 0) {
			if(_spriteindex < _balls.size() -1) {
				_spriteindex++;
			} else {
				_spriteindex = 0;
			}
		}
		TheGame._gc.drawImage(bd, _x - 112, _y-112, 375, 375);
		if(_spawning) {
			executeSpawn();
		}
		if(_changeform1) {
			changeform1();
		}
		
		
		
		if(_attack1) {
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead
				&& !_changeform0 && !_changeform1 && !_changeform2 && !_changeform3 && !_changeform4) {
		if(_counter1 < 29) {
			_yvelocity = 0;
			if(_counter % 3 == 0){
			if(_counter1 < 15) {
				_y -=3;
			} else {
				_y += 3;
			}
			}
		} else {
			_counter1 = 0;
		}
		if(_counter3 == 120 && !_won) {
			Random r = new Random();
			int i = r.nextInt(3);
			_counter3 = 0;
			if(_atkcount == 3) {
				_counter4 = 0;
				if(_form == 0) {
					_changeform1 = true;
				} else if(_form == 1) {
					_changeform2 = true;
				} else if(_form == 2) {
					_changeform3 = true;
				} else if(_form == 3) {
					_changeform4 = true;
				} else if(_form == 4) {
					_changeform0 = true;
				}
			} else {
			i = 1;
			if(i == 0) {
				if(_form == 0) {
				attack1();
				}
			}
			if(i == 1) {
				if(_form == 0){
				attack2();
				}
			}
			if(i == 2) {
				if(_form == 0){
				attack3();
				}
			}
			_atkcount++;
			}
		}
		}
	}

	
	public void attack1() {
		_counter4 = 0;
		_attack1 = true;
		_rate2 = 1;
	}
	
	private void executeAttack1() {
		if(_counter4 == 15) {
			_yvelocity = 13;
		}
		if(_counter4 == 30) {
			_yvelocity = 0;
		}
		if(((_counter4 >= 40&& _counter4 < 80) || (_counter4 >= 110&& _counter4 < 150))  && _counter4 % 3 == 0 ) {
			String s;
			if(_atk1count == 0) {
				s = "r";
				_atk1count = 1;
			} else if(_atk1count == 1) {
				s = "g";
				_atk1count = 2;
			} else {
				s = "y";
				_atk1count = 0;
			}
			Image i = new Image("ultimoboss/shots/" + s + ".png");
			Hitbox a = new HitboxImpl("uball", this, false, _x-50, _y+25, 75, 75, -15, 0, 0, 1, i);
			a.setDissappearOnHit(false);
			a.setCircle(true);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 150) {
			_yvelocity = -13;
		}
		if(_counter4 == 165) {
			_yvelocity = 0;
			_counter3 = 0;
			_attack1 = false;
			_rate2 = 3;
		}
	}
	
	public void attack2() {
		_rate2 = 1;
		_counter4 = 0;
		_attack2 = true;
	}
	
	public void executeAttack2() {
		if(_counter4 > 20 && _counter4 % 5 == 0) {
			Hitbox a = new HitboxImpl("uball", this, false, _x-50, _y+112, 35, 35, -15, 15, 0, 1, new Image("ultimoboss/shots/r.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			Hitbox b = new HitboxImpl("uball", this, false, _x-50, _y+50, 35, 35, -21, 0, 0, 1, new Image("ultimoboss/shots/g.png"));
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(b);
			Hitbox c = new HitboxImpl("uball", this, false, _x-50, _y, 35, 35, -15, -15, 0, 1, new Image("ultimoboss/shots/y.png"));
			c.setCircle(true);
			c.setDissappearOnHit(false);
			TheGame._attacks.add(c);
		}
		if(_counter4 == 70) {
			_xvelocity = -18;
		}
		if(_counter4 == 93) {
			_xvelocity = 0;
		}
		if(_counter4 == 98) {
			_xvelocity = 10;
		}
		if(_counter4 >= 98 && _x >= 645){
			_x = 645;
			_rate2 = 3;
			_xvelocity = 0;
			_attack2 = false;
			_counter3 = 0;
		}
		}

	private void changeform0() {
		// TODO Auto-generated method stub
		
	}

	private void changeform4() {
		// TODO Auto-generated method stub
		
	}

	private void changeform3() {
		// TODO Auto-generated method stub
		
	}

	private void changeform2() {
		// TODO Auto-generated method stub
		
	}

	private void changeform1() {
		if(_counter4 == 1) {
			_rate2 = 1;
		}
		if(_counter4 == 20) {
			TheGame._frontdrops.add(_flash);
		}
		if(_counter4 == 22) {
			_balls.clear();
			_balls.add(new Image("ultimoboss/balls2/1.png"));
			_balls.add(new Image("ultimoboss/balls2/2.png"));
			_balls.add(new Image("ultimoboss/balls2/3.png"));
			_balls.add(new Image("ultimoboss/balls2/4.png"));
			_balls.add(new Image("ultimoboss/balls2/5.png"));
			_balls.add(new Image("ultimoboss/balls2/6.png"));
			_balls.add(new Image("ultimoboss/balls2/7.png"));
			_balls.add(new Image("ultimoboss/balls2/8.png"));
			_balls.add(new Image("ultimoboss/balls2/9.png"));
			_balls.add(new Image("ultimoboss/balls2/10.png"));
			_balls.add(new Image("ultimoboss/balls2/11.png"));
			_balls.add(new Image("ultimoboss/balls2/12.png"));
			_balls.add(new Image("ultimoboss/balls2/13.png"));
			_balls.add(new Image("ultimoboss/balls2/14.png"));
			_balls.add(new Image("ultimoboss/balls2/15.png"));
			_balls.add(new Image("ultimoboss/balls2/16.png"));
			_balls.add(new Image("ultimoboss/balls2/17.png"));
			_balls.add(new Image("ultimoboss/balls2/18.png"));
			_balls.add(new Image("ultimoboss/balls2/19.png"));
			_balls.add(new Image("ultimoboss/balls2/20.png"));
		}
		if(_counter4 == 25) {
			TheGame._frontdrops.remove(_flash);
		}
		if(_counter4 == 40) {
			_rate2 = 3;
			_counter3 = 0;
			_changeform1 = false;
			_form = 1;
		}
		
	}

	@Override
	public void spawn() {
		_y = 125;
		_spawning = true;
		_counter2 = 0;
		_xvelocity = -5;
	}
	
	public void executeSpawn() {
		if(_counter2 == 50) {
			_xvelocity = 0;
			System.out.println(_x);
		}
	}

}
