package io.valhala.agonyserver.framework;

import io.valhala.agonyserver.CharacterMovement;
import io.valhala.agonyserver.Game;
import io.valhala.agonyserver.framework.entity.Enemy;
import io.valhala.agonyserver.framework.entity.Weapon;
import javafx.scene.Camera;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class PlayerCollision {
	private static boolean threat;
	
	public static boolean threatDetection(CharacterMovement player1, Rectangle r) {
		threat = false;
		if (player1.getBoundsInParent().intersects(r.getBoundsInParent())) {
			threat = true;
		}
		
		return threat;
	}
	public static void hit(Enemy player, Weapon weapon) {
		if (weapon.getBoundsInParent().intersects(player.getBoundsInParent())) {
			Game.root.getChildren().remove(player);
			player.dead = true;
		}
	
		
	}



	public static void collide(CharacterMovement r, Enemy player2) {
		// TODO Auto-generated method stub
		if (r.getBoundsInParent().intersects(player2.getBoundsInParent())) {
			Game.root.getChildren().remove(player2);
	}
}
	public static boolean camerastop(CharacterMovement c, ImageView v) {
		boolean intersects;
		if(c.getBoundsInParent().intersects(v.getBoundsInParent())) {
			intersects = true;
		}
		else
			intersects = false;
		return intersects;
	}
		
	}
