package io.valhala.agonyserver;

import java.io.FileNotFoundException;
import java.util.HashMap;


//import io.valhala.agonyserver.framework.graphics.ImageLoader;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game extends Application {
	
	static HashMap<KeyCode, Boolean> keys = new HashMap<>();
	int toon;
	Scene selection;
	Scene scene;
	static Pane root = new Pane();
	
	//Create Players and enemies
	CharacterMovement player1 = new CharacterMovement( "/images/Viking.png", 640, 512);
	CharacterMovement player2 = new CharacterMovement("/images/witch.png", 640, 512);
	CharacterMovement zombie1 = new CharacterMovement("/images/EvilKing.png", 500, 400);
	CharacterMovement zombie2 = new CharacterMovement("/images/reaper.png", 800, 700);
	CharacterMovement reaper = new CharacterMovement("/images/Zombie1.png", 300, 200);
	CharacterMovement EvilKing = new CharacterMovement("/images/Zombie2.png", 0, 900);

	
	public static void main(String[] args) {
		launch (args);
	}
	
	public void start(Stage primaryStage) throws FileNotFoundException {
		
		root.setPrefSize(1280, 1024);

		//Character Selection Screen Algorithm
		Label label1 = new Label("Please choose your character");
		Button btn1 = new Button("Viking");
		Button btn2 = new Button("Witch");
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				root.getChildren().addAll(player1);
				primaryStage.setScene(scene);
				toon =1;
			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				root.getChildren().addAll(player2);
				primaryStage.setScene(scene);//needs improvement
				toon = 2;
			}
		});
		root.getChildren().addAll(zombie1);
		root.getChildren().addAll(zombie2);
		root.getChildren().addAll(reaper);
		root.getChildren().addAll(EvilKing);
		
		VBox layout1 = new VBox(20);
		layout1.setLayoutX(640);
		layout1.setLayoutY(512);
		layout1.getChildren().addAll(label1, btn1, btn2);
		selection = new Scene(layout1, 1280, 1024);

		scene = new Scene(root);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));	
		scene.setOnKeyReleased(event-> {
			keys.put(event.getCode(), false);
		});
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (toon ==1)
					player1.update();
				else
					player2.update();
				zombie1.enemyUpdate('x');
				zombie2.enemyUpdate( 'y');
				reaper.enemyUpdate( 'x');
				EvilKing.enemyUpdate( 'y');
			}
		};
		timer.start();

		primaryStage.setTitle("Agony Servers");
		primaryStage.setScene(selection);
		primaryStage.show();
	}
	
		
}


		