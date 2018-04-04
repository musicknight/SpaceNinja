package cd.bosses;

import java.util.Random;

import cd.Backdrop;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SkullBoss extends Boss {

	private int _hattack1;
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeat4;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	private int _attack3var;
	private Boss _lhand;
	private Backdrop _bd = new Backdrop(new Image("skullboss/2.png"), 900, 150, 186, 121);
	private boolean _form2;
	private Random _random = new Random();
	private Hitbox _line = null;
	private boolean _changeform;

	public SkullBoss() {
		super(900, 600, "skullboss");
		_width = (int)(186*1.3);
		_height = (int)(121*1.3);
		_health = 510;
		_staticimage = new Image("skullboss/1.png");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_lhand != null) {
			_lhand.render(gc);
			_lhand.incrementCounter();
		}
		if(_spawning) {
			executeSpawn();
		}
		if(_attack1) {
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(_attack3) {
			executeAttack3();
		}
		if(_changeform) {
			changeForm();
		}
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead&& !_changeform) {
			if(_counter1 < 29) {
				_yvelocity = 0;
				if(_counter % 3 == 0){
				if(_counter1 < 15) {
					_y -=1;
				} else {
					_y += 1;
				}
				}
			} else {
				_counter1 = 0;
			}
			if(_health < 500 && !_form2) {
				_changeform = true;
				_counter4 = 0;
			}
			if(_counter3 == 120 && !_won ) {
				Random r = new Random();
				int i = r.nextInt(3);
				_counter3 = 0;
				
				if(_repeat1 == 2) {
					i = 3;
				}
				if(_repeat2 == 2) {
					i = 1;
				}
				if(_repeat3 == 2) {
					i = 0;
				}
				if(_repeat4 == 2) {
					i = 2;
				}
				if(_repeatno1 == 5) {
					i = 0;
				}
				if(_repeatno2 == 5) {
					i = 1;
				}
				if(_repeatno3 == 5) {
					i = 2;
				}
				if(_repeatno4 == 5) {
					i = 3;
				}
				if(i == 0) {
					attack1();
					_repeat1++;
					_repeat2 = 0;
					_repeat3 = 0;
					_repeat4 = 0;
					_repeatno1 = 0;
					_repeatno2++;
					_repeatno3++;
					_repeatno4++;
				}
				if(i == 1) {
					attack2();
					_repeat2++;
					_repeat1 = 0;
					_repeat3 = 0;
					_repeat4 = 0;
					_repeatno1++;
					_repeatno2=0;
					_repeatno3++;
					_repeatno4++;
				}
				if(i == 2) {
					_attack3var = _random.nextInt(4);
					attack3();
					_repeat3++;
					_repeat1 = 0;
					_repeat2 = 0;
					_repeat4 = 0;
					_repeatno1++;
					_repeatno2++;
					_repeatno3=0;
					_repeatno4++;
				}
				if(i == 3) {
					//attack4();
					_repeat4++;
					_repeat1 = 0;
					_repeat2 = 0;
					_repeat3 = 0;
					_repeatno1++;
					_repeatno2++;
					_repeatno3++;
					_repeatno4=0;
				}
			}
		}
		
	}

	@Override
	public void spawn() {
		_y = 110;
		_x = 910;
		_spawning = true;
		_xvelocity = -4;
		_counter2 = 0;
		
	}
	
	public void executeSpawn() {
		if(_counter2 == 80) {
			_xvelocity = 0;
			_spawning = false;
		}
	}
	
	public void attack1() {
		_yvelocity = 10;
		_counter4 = 0;
		_attack1 = true;
	}
	
	public void executeAttack1() {
		if(_counter4 == 25) {
			_line = new HitboxImpl("line", this, false, 0, 442, 700, 125, 0, -10, 0, 1, new Image("skullboss/bone/line.png"));
			_line.setDissappearOnHit(false);
			TheGame._attacks.add(_line);
		}
		if(_counter4 == 35) {
			_line.setYVelocity(0);
		}
		if(_counter4 >= 15 && _counter4 <65) {
			_yvelocity = 0;
			TheGame._gc.drawImage(new Image("skullboss/bone/pre2.png"), 0, 438);
		}
		if(_counter4 == 65) {
			_line.setYVelocity(10);
			_yvelocity = -10;						
		}
		if(_counter4 >= 65 && _y<= 110) {
			_y = 110;
			_yvelocity = 0;
			_counter3 = 0;
			_attack1 = false;
		}
	}
	public void attack2() {
		_attack2 = true;
		_counter4 = 0;
		_yvelocity = 5;
	}
	public void executeAttack2() {
		if(_counter4 == 15) {
			_yvelocity = 0;
		}
		if(_counter4 >= 10 && _counter4 < 30) {
			TheGame._gc.drawImage(new Image("skullboss/bone/pre3.png"), 362, 438);
		}
		if(_counter4 >= 25 && _counter4 % 58 == 0 && _counter4 <= 300) {
			Hitbox a = new HitboxImpl("bone", this, false, 1, 1, 362, 60, 0, 6, 0, 1, new Image("skullboss/bone/boneh2.png"));
			a.setDissappearOnHit(false);
			Hitbox b = new HitboxImpl("bone", this, false, 362, 442, 362, 60, 0, -6, 0, 1, new Image("skullboss/bone/boneh2.png"));
			b.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame._attacks.add(b);
		}
		if(_counter4 == 300) {
			_yvelocity = -10;	
		}
		if(_counter4 >= 300 && _y<= 110) {
			_y = 110;
			_yvelocity = 0;
			_counter3 = 0;
			_attack2 = false;
		}
	}
	
	public void attack3() {
		_yvelocity = -15;
		_counter4 = 0;
		_attack3 = true;
	}
	
	public void executeAttack3() {
		if(_counter4 == 20) {
			_yvelocity = 0;
		}
		if(_counter4 >= 35 && _counter4 < 78) {
			if(_attack3var == 0 ) {
				TheGame._gc.drawImage(new Image("skullboss/bone/pre750.png"), 0, 60);
			} else if(_attack3var == 1) {
				TheGame._gc.drawImage(new Image("skullboss/bone/pre750.png"), 150, 60);
			} else if(_attack3var == 2) {
				TheGame._gc.drawImage(new Image("skullboss/bone/pre300.png"), 0, 60);
				TheGame._gc.drawImage(new Image("skullboss/bone/pre450.png"), 450, 60);
			} else if(_attack3var == 3) {
				TheGame._gc.drawImage(new Image("skullboss/bone/pre450.png"), 0, 60);
				TheGame._gc.drawImage(new Image("skullboss/bone/pre300.png"), 600, 60);
			}
		}
		if(_counter4 == 78) {
			if(_attack3var == 0 ) {
				Hitbox a = new HitboxImpl("bone", this, true, 0, -90, 750, 150, 0, 0, 0, 1, new Image("skullboss/bone/750.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
			} else if(_attack3var == 1) {
				Hitbox a = new HitboxImpl("bone", this, true, 150, -90, 750, 150, 0, 0, 0, 1, new Image("skullboss/bone/750.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
			} else if(_attack3var == 2) {
				TheGame._gc.drawImage(new Image("skullboss/bone/300.png"), 0, 60);
				Hitbox a = new HitboxImpl("bone", this, true, 0, -90, 300, 150, 0, 0, 0, 1, new Image("skullboss/bone/300.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				a = new HitboxImpl("bone", this, true, 450, -90, 450, 150, 0, 0, 0, 1, new Image("skullboss/bone/450.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
			} else if(_attack3var == 3) {
				TheGame._gc.drawImage(new Image("skullboss/bone/300.png"), 0, 60);
				Hitbox a = new HitboxImpl("bone", this, true, 0, -90, 450, 150, 0, 0, 0, 1, new Image("skullboss/bone/300.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				a = new HitboxImpl("bone", this, true, 600, -90, 300, 150, 0, 0, 0, 1, new Image("skullboss/bone/450.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
			}
		}
		if(_counter4 == 85) {
			_yvelocity = 15;
		}
		if(_counter4 >= 85 && _y >= 110) {
			_y = 110;
			_yvelocity = 0;
			_counter3 = 0;
			_attack3 = false;
		}
	}
	
	public void changeForm() {
		if(_counter4 == 1) {
			_xvelocity = 25;
		}
		if(_counter4 == 25) {
			_xvelocity = 0;
		}
		if(_counter4 >= 25 && _counter4 <115) {
			TheGame._backdrops.add(_bd);
			_bd.setX((900 - ((_counter4-10)*5)));
			
		}
		if(_counter4 == 25) {
			_width = 130;
			_height = (int)(140*1.3);
			_xvelocity = -5;
			_lhand = new SkullLeftHand();
			_lhand.spawn();
			_staticimage = new Image("skullboss/rfist.png");
		}
		if(_counter4 == 130) {
			_xvelocity = 0;
		}
		
	}
	
	public void handattack1() {
		
	}
	
	public void executeHandAttack1() {
		
	}

}
