package cd.bosses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.Backdrop;
import cd.CharLinkedHitbox;
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
	private int _atkcount = 3;
	private Backdrop _flash = new Backdrop(new Image("ultimoboss/flash.png"), 0, 0, 900, 600);
	private Hitbox _body = new CharLinkedHitbox("ultimobody", this, 0, 1);
	private boolean _changeform1;
	private boolean _changeform2;
	private boolean _changeform3;
	private boolean _changeform4;
	private boolean _changeform0;
	private int _atk1count;
	private boolean _gattack1;
	private int _gatk1count;
	private boolean _gattack2;
	private Hitbox _gdrop;
	private boolean _stunned;
	
	
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
		_body = new CharLinkedHitbox("ultimobody", this, 0, 1);
		_body.setCircle(true);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
		}
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
		if(_changeform2) {
			changeform2();
		}
		
		
		
		if(_attack1) {
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(_gattack1) {
			executeGAttack1();
		}
		if(_gattack2) {
			executeGAttack2();
		}
		
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_gattack1 && !_gattack2 &&  !_dead
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
			_form = 1;
			_atkcount = 3;
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
				_atkcount = 0;
			} else {
			i = 1;
			if(i == 0) {
				if(_form == 0) {
				attack1();
				} else if (_form == 1) {
				gattack1();
				}
			}
			if(i == 1) {
				if(_form == 0){
				attack2();
				} else if(_form == 1) {
				gattack2();
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
	
	public void gattack1() {
		_gattack1 = true;
		_rate2 = 1;
		_counter4 = 0;
	}
	
	public void executeGAttack1() {
		if(_counter4 == 15) {
			_yvelocity = 18;
		}
		if(_counter4 == 25) {
			_yvelocity = 0;
		}
		if(_counter4 == 35) {
			Hitbox a = new HitboxImpl("uball", this, false, _x, _y, 150, 150, -10, 0, 0, 1, new Image("ultimoboss/shots/g.png"));
			a.setDissappearOnHit(false);
			a.setCircle(true);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 40) {
			_yvelocity = -14;
		}
		if(_counter4 == 50) {
			_yvelocity = 0;
		}
		if(_counter4 == 60) {
			Hitbox a = new HitboxImpl("uball", this, false, _x, _y, 150, 150, -10, 0, 0, 1, new Image("ultimoboss/shots/g.png"));
			a.setDissappearOnHit(false);
			a.setCircle(true);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 65) {
			_yvelocity = 14;
		}
		if(_counter4 == 75) {
			_yvelocity = 0;
		}
		if(_counter4 == 85 && _gatk1count < 3) {
			_counter4 = 34;
			_gatk1count++;
		}
		if(_counter4 == 90) {
			_yvelocity = -18;
		}
		if(_counter4 == 100) {
			_yvelocity = 0;
			_gattack1 = false;
			_gatk1count = 0;
			_counter3 = 0;
			_rate2 = 3;
		}
	}
	
	public void gattack2() {
		_rate2 = 1;
		_counter4 = 0;
		_gattack2 = true;
		_yvelocity = -5;
	}
	
	public void executeGAttack2() {
		if(_counter4 == 15) {
			_yvelocity = 0;
		}
		if(_counter4 == 25) {
			_gdrop = new HitboxImpl("uball", this, true, _x-25, _y-25, 200, 200, 0, 3, 0, 1, new Image("ultimoboss/shots/g.png"));
			_gdrop.setDissappearOnHit(false);
			_gdrop.setCircle(true);
			TheGame._attacks.add(_gdrop);
		}
		if(_counter4 > 25 && _gdrop.getY()+200 >= 442 && _gdrop.getYVelocity() != 0) {
			_gdrop.setY(442-200);
			_gdrop.setYVelocity(0);
			TheGame.playSound("/rockboss/sounds/slam.wav");
			if(TheGame._character1.getY() == 392) {
				TheGame._character1.setCanAct(false);
				TheGame._character1.setXVelocity(0);
				TheGame._character1.setYVelocity(0);
				_stunned = true;
			}
		}
		if(_stunned) {
			TheGame._gc.drawImage(new Image("ninja/stun.png"), TheGame._character1.getX() - 10, TheGame._character1.getY());
			TheGame._gc.drawImage(new Image("ninja/stun.png"), TheGame._character1.getX() + TheGame._character1.getWidth(), TheGame._character1.getY());
		}
		if(_counter4 == 75) {
			_gdrop.setXVelocity(-25);
			_yvelocity = 5;
		}
		if(_counter4 >= 75 && _counter4 % 5 == 0 && _counter4 < 90) {
			Hitbox a = new HitboxImpl("uball", this, false, _x, _y+50, 50, 50, -10, 2, 0, 1, new Image("ultimoboss/shots/g.png"));
			a.setDissappearOnHit(false);
			a.setCircle(true);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 90) {
			_yvelocity = 0;
		}
		if(_counter4 == 100) {
			_stunned = false;
			TheGame._character1.setCanAct(true);	
			_gattack2 = false;
			_counter3 = 0;
			_rate2 = 3;
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
		if(_counter4 == 1) {
			_rate2 = 1;
		}
		if(_counter4 == 20) {
			TheGame._frontdrops.add(_flash);
		}
		if(_counter4 == 22) {
			_balls.clear();
			_balls.add(new Image("ultimoboss/balls3/1.png"));
			_balls.add(new Image("ultimoboss/balls3/2.png"));
			_balls.add(new Image("ultimoboss/balls3/3.png"));
			_balls.add(new Image("ultimoboss/balls3/4.png"));
			_balls.add(new Image("ultimoboss/balls3/5.png"));
			_balls.add(new Image("ultimoboss/balls3/6.png"));
			_balls.add(new Image("ultimoboss/balls3/7.png"));
			_balls.add(new Image("ultimoboss/balls3/8.png"));
			_balls.add(new Image("ultimoboss/balls3/9.png"));
			_balls.add(new Image("ultimoboss/balls3/10.png"));
			_balls.add(new Image("ultimoboss/balls3/11.png"));
			_balls.add(new Image("ultimoboss/balls3/12.png"));
			_balls.add(new Image("ultimoboss/balls3/13.png"));
			_balls.add(new Image("ultimoboss/balls3/14.png"));
			_balls.add(new Image("ultimoboss/balls3/15.png"));
			_balls.add(new Image("ultimoboss/balls3/16.png"));
			_balls.add(new Image("ultimoboss/balls3/17.png"));
			_balls.add(new Image("ultimoboss/balls3/18.png"));
			_balls.add(new Image("ultimoboss/balls3/19.png"));
			_balls.add(new Image("ultimoboss/balls3/20.png"));
		}
		if(_counter4 == 25) {
			TheGame._frontdrops.remove(_flash);
		}
		if(_counter4 == 40) {
			_rate2 = 3;
			_counter3 = 0;
			_changeform2 = false;
			_form = 2;
		}
		
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
		}
	}

}
