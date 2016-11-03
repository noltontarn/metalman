package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class World {
	private MetalMan metalman;
	private MetalManGame metalmangame;
	private ArrayList<HealthPack> healthpack; 
	private ArrayList<EnemyBullet> enemybullet;
	private ArrayList<Bullet> bullet;
	private ArrayList<ShooterEnemy> shooterenemy;
	private ArrayList<WheelerEnemy> wheelerenemy;
	private ArrayList<Cannon> cannon;
	public int countBullet = 0;
	private int countdeadenemy = 0;
	private boolean checkhitdelay = true;
	private long hittimer;
	private Stage stage;
	private boolean wave2 = false;
	private boolean wave3 = false;
	private boolean wave4 = false;
	private boolean wave5 = false;
	private boolean wave6 = false;
	private float healthpackposx = 0;
	private float healthpackposy = 0;
	
	World(MetalManGame metalmangame) {
		this.metalmangame = metalmangame;
		metalman = new MetalMan (125,100);
		stage = new Stage();
		healthpack = new ArrayList<HealthPack>();
		bullet =new ArrayList<Bullet>();
		enemybullet = new ArrayList<EnemyBullet>();
		shooterenemy = new ArrayList<ShooterEnemy>();
		wheelerenemy = new ArrayList<WheelerEnemy>();
		cannon = new ArrayList<Cannon>();
		shooterenemy.add(new ShooterEnemy(700, 100, ShooterEnemy.FACELEFT, enemybullet));
		shooterenemy.add(new ShooterEnemy(30, 300, ShooterEnemy.FACERIGHT, enemybullet));
	}
	
	MetalMan getMetalMan() {
		return metalman;
	}
	
	ArrayList<HealthPack> getHealthPack() {
		return healthpack;
	}
	
	ArrayList<Bullet> getBullet() {
		return bullet;
	}
	
	ArrayList<EnemyBullet> getEnemyBullet() {
		return enemybullet;
	}
	
	ArrayList<ShooterEnemy> getShooterEnemy() {
		return shooterenemy;
	}
	
	ArrayList<WheelerEnemy> getWheelerEnemy() {
		return wheelerenemy;
	}
	
	ArrayList<Cannon> getCannon() {
		return cannon;
	}
	
	Stage getStage() {
		return stage;
	}
	
	public void update(float delta) {
		   waveUpdate();
	       metalman.update();
	       updateEnemy();
	       hitMetalMan();
	       checkHealthPack();
	}
	
	private void updateEnemy() {
	       for(ShooterEnemy s:shooterenemy) {
	    	   s.update();
	       }
	       for(WheelerEnemy w:wheelerenemy) {
	    	   w.update();
	       }
	       for(Cannon c:cannon) {
	    	   c.update();
	       }
	       ArrayList<Bullet> Removebullet = new ArrayList<Bullet>();
	       ArrayList<ShooterEnemy> removeshooter = new ArrayList<ShooterEnemy>();
	       ArrayList<WheelerEnemy> removewheeler = new ArrayList<WheelerEnemy>();
	       ArrayList<Cannon> removecannon = new ArrayList<Cannon>();
	       for(Bullet b:bullet) {
	    	   b.update();
	    	   for(ShooterEnemy s:shooterenemy) {
	    		   if(b.getPosition().x > s.getPosition().x && b.getPosition().x < s.getPosition().x + 100 &&
	    		      b.getPosition().y > s.getPosition().y && b.getPosition().y < s.getPosition().y + 100) {
	    			   Removebullet.add(b);
	    			   s.getDamage(20);
	    			   if(s.HP == 0) {
	    				   removeshooter.add(s);
	    				   healthpackposx = s.getPosition().x;
	    				   healthpackposy = s.getPosition().y;
	    				   countdeadenemy++;
	    			   }
	    		   }
	    	   }
	    	   for(WheelerEnemy w:wheelerenemy) {
	    		   if(b.getPosition().x > w.getPosition().x && b.getPosition().x < w.getPosition().x + 100 &&
	    		      b.getPosition().y > w.getPosition().y && b.getPosition().y < w.getPosition().y + 100) {
	    			   Removebullet.add(b);
	    			   w.getDamage(20);
	    			   if(w.HP == 0) {
	    				   removewheeler.add(w);
	    				   healthpackposx = w.getPosition().x;
	    				   healthpackposy = w.getPosition().y;
	    				   countdeadenemy++;
	    			   }
	    		   }
	    	   }
	    	   for(Cannon c:cannon) {
	    		   if(b.getPosition().x > c.getPosition().x && b.getPosition().x < c.getPosition().x + 100 &&
	    		      b.getPosition().y > c.getPosition().y && b.getPosition().y < c.getPosition().y + 60) {
	    			   Removebullet.add(b);
	    			   c.getDamage(20);
	    			   if(c.HP == 0) {
	    				   removecannon.add(c);
	    				   healthpackposx = c.getPosition().x;
	    				   healthpackposy = c.getPosition().y;
	    				   countdeadenemy++;
	    			   }
	    		   }
	    	   }
	    	   if(b.getPosition().x > 770 || b.getPosition().x < 10) {
	    		   Removebullet.add(b);
	    	   }
	       }
	       bullet.removeAll(Removebullet);
	       shooterenemy.removeAll(removeshooter);
	       wheelerenemy.removeAll(removewheeler);
	       cannon.removeAll(removecannon);
	}
	
	private void hitMetalMan() {
	       ArrayList<EnemyBullet> RemoveEnemybullet = new ArrayList<EnemyBullet>();
	       for(EnemyBullet b:enemybullet) {
	    	   b.update();
	    	   if(b.getPosition().x > metalman.getPosition().x && b.getPosition().x < metalman.getPosition().x + 100 &&
	    		  b.getPosition().y > metalman.getPosition().y && b.getPosition().y < metalman.getPosition().y + 100) {
	    		   RemoveEnemybullet.add(b);
	    		   if(b.bigbullet) {
	    			   metalman.getDamage(15);
	    		   }
	    		   else {
	    			   metalman.getDamage(5);
	    		   }
	    	   }
	       }
	       enemybullet.removeAll(RemoveEnemybullet);
	       for(WheelerEnemy w:wheelerenemy) {
	    	   if(w.getPosition().x > metalman.getPosition().x - 100 && w.getPosition().x < metalman.getPosition().x + 100 &&
	    		  w.getPosition().y >= metalman.getPosition().y && w.getPosition().y < metalman.getPosition().y + 100) {
	    		   delayHit();
	    		   if(checkhitdelay == true && TimeUtils.millis() - hittimer > 1000) {
	    			   metalman.getDamage(10);
	    			   checkhitdelay = false;
	    		   }
	    	   }
	       }
	}
	
	private void delayHit() {
		if(checkhitdelay == false) {
			hittimer = TimeUtils.millis();
			checkhitdelay = true;
		}
	}
    
    private void waveUpdate() {
    	if(countdeadenemy == 2 && wave2 == false) {
			healthpack.add(new HealthPack(healthpackposx + 50, healthpackposy));
    		wheelerenemy.add(new WheelerEnemy(400, 100, WheelerEnemy.FACERIGHT, 0, 800));
    		wheelerenemy.add(new WheelerEnemy(400, 500, WheelerEnemy.FACERIGHT, 200, 600));
    		wave2 = true;
    	}
    	else if(countdeadenemy == 4 && wave3 == false) {
    		healthpack.add(new HealthPack(healthpackposx + 50, healthpackposy));
    		wheelerenemy.add(new WheelerEnemy(100, 300, WheelerEnemy.FACERIGHT, 0, 300));
    		wheelerenemy.add(new WheelerEnemy(700, 300, WheelerEnemy.FACELEFT, 500, 800));
    		shooterenemy.add(new ShooterEnemy(50, 100, ShooterEnemy.FACERIGHT, enemybullet));
    		wave3 = true;
    	}
    	else if(countdeadenemy == 7 && wave4 == false) {
    		healthpack.add(new HealthPack(healthpackposx + 50, healthpackposy));
    		cannon.add(new Cannon(50, 300, Cannon.FACERIGHT, enemybullet));
    		cannon.add(new Cannon(700, 100, Cannon.FACELEFT, enemybullet));
    		wave4 = true;
    	}
    	else if(countdeadenemy == 9 && wave5 == false) {
    		healthpack.add(new HealthPack(healthpackposx + 50, healthpackposy));
    		cannon.add(new Cannon(700, 300, Cannon.FACELEFT, enemybullet));
    		wheelerenemy.add(new WheelerEnemy(400, 500, WheelerEnemy.FACERIGHT, 200, 600));
    		shooterenemy.add(new ShooterEnemy(50, 100, ShooterEnemy.FACERIGHT, enemybullet));
    		wave5 = true;
    	}
    	else if(countdeadenemy == 12 && wave6 == false) {
    		healthpack.add(new HealthPack(healthpackposx + 50, healthpackposy));
    		wave6 = true;
    	}
    }
    
    private void checkHealthPack() {
    	ArrayList<HealthPack> removehealthpack = new ArrayList<HealthPack>();
    	for(HealthPack h : healthpack) {
    		if(h.getPosition().x > metalman.getPosition().x && h.getPosition().x < metalman.getPosition().x + 100 &&
	    		  h.getPosition().y >= metalman.getPosition().y && h.getPosition().y < metalman.getPosition().y + 100) {
    			metalman.HP += 20;
    			if(metalman.HP >= 100) {
    				metalman.HP = 100;
    			}
    			System.out.println(metalman.HP);
    			removehealthpack.add(h);
    		}
    	}
    	healthpack.removeAll(removehealthpack);
    }
}
    
