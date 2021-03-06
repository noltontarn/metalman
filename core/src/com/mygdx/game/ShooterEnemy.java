package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class ShooterEnemy {
	 private Vector2 position;
	 public int Face = FACERIGHT;
	 public static final int FACERIGHT = 1;
	 public static final int FACELEFT = 2;
	 public static final int SPEED = 5;
	 public int HP = 120;
	 private boolean checkdelay = false;
	 private long bullettimer;
	 private ArrayList<EnemyBullet> enemybullet;
	 private int countbullet = 0;

	public ShooterEnemy(int x, int y,int face, ArrayList<EnemyBullet> enemybullet) {
		this.enemybullet = enemybullet;
		position = new Vector2(x,y);
		setFace(face);
	}
	
   public void setFace(int face) {
       Face = face;
   }
   
   public void getDamage(int damage) {
       HP -= damage;
       if(HP <= 0) {
       	HP = 0;
       }
       System.out.println(HP);
   }
   
   public Vector2 getPosition() {
       return position;    
   }
   
   public void update() {
	   delayEnemyBullet();
	   if(countbullet == 3) {
		   if(TimeUtils.millis() - bullettimer >1200) {
			   if(Face == FACERIGHT) {
				   enemybullet.add(new EnemyBullet(getPosition().x + 80, getPosition().y + 55, Face, false));
			   }
			   else if(Face == FACELEFT) {
				   enemybullet.add(new EnemyBullet(getPosition().x, getPosition().y + 55, Face, false));
			   }
	    	   checkdelay = false;
	    	   countbullet = 1;
		   }
	   }
	   else if(TimeUtils.millis() - bullettimer >100 && checkdelay == true) {
		   if(Face == FACERIGHT) {
			   enemybullet.add(new EnemyBullet(getPosition().x + 80, getPosition().y + 55, Face, false));
		   }
		   else if(Face == FACELEFT) {
			   enemybullet.add(new EnemyBullet(getPosition().x, getPosition().y + 55, Face, false));
		   }
    	   checkdelay = false;
    	   countbullet++;
   	   }
   }
   
   private void delayEnemyBullet() {
   	if(checkdelay == false) {
   		bullettimer = TimeUtils.millis();
   		checkdelay = true;
   	}
   }
}
