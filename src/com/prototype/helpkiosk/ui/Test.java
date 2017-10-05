package com.prototype.helpkiosk.ui;


import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage)
    {
        //Add a scene
        Group root = new Group();
        Scene scene = new Scene(root, 500, 200);


        String home = System.getProperty("user.home");
		String videoFolder = home + "/git/HelpKiosk/video";	
		String filename = videoFolder+"/old_videos/openclock.MPG";
				
		File vid = new File(filename);
		
       // String filepath = "file:///C:/Users/git/HelpKiosk/video/contacts/contact_open_app.mpeg";
        //URL filepath = getClass().getResource("contact_open_app.mpeg");
		
		
        Media pick = new Media(vid.toURI().toString());
        MediaPlayer player = new MediaPlayer(pick);
        player.play();

        //Add a mediaView, to display the media. Its necessary !
        //This mediaView is added to a Pane
        MediaView mediaView = new MediaView(player);
        ((Group)scene.getRoot()).getChildren().add(mediaView);

        //show the stage
        primaryStage.setTitle("Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        System.out.println("javafx.runtime.version: " + System.getProperties().get("javafx.runtime.version"));

         launch(args);
    }
}
