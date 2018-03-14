package com.prototype.helpkiosk.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;


@SuppressWarnings("serial")
public class MediaPanel extends JPanel {
	
	public String mediaURL;

	public MediaPanel() {
		Platform.setImplicitExit(false);
		setLayout(new BorderLayout());
	}
	
	private void initFX(JFXPanel fxPanel, String url) {
		Scene scene = createScene(url);
		fxPanel.setScene(scene);
	}
	
	private Scene createScene(String url) {
	
		final Label status = new Label("Init");
		final MediaPlayer mediaPlayer = createMediaPlayer(url, status);
		
		MediaView view = new MediaView(mediaPlayer);
		
		DoubleProperty mvw = view.fitWidthProperty();
		DoubleProperty mvh = view.fitHeightProperty();
		mvw.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
		mvh.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
		
		view.setPreserveRatio(true);

		Group root  =  new  Group(view);	
		Scene scene  =  new  Scene(root, Color.ALICEBLUE);
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			
		    public void handle(MouseEvent mouseEvent) {
		    	
		    	if(mouseEvent.getClickCount() == 1 && mediaPlayer.getStatus() == Status.PLAYING) {
			    	mediaPlayer.pause();
		    	} 
		    	else if(mouseEvent.getClickCount() == 1 && 
		    			(mediaPlayer.getStatus() == Status.PAUSED
		    					|| mediaPlayer.getStatus() == Status.READY
		    					)){
			    	mediaPlayer.play();
			    	mediaPlayer.setCycleCount(100);
		    	}
		    }
		    	
		});	
		
		return (scene);
	}
	
	/** 
	   * creates a media player using a url to the media
	   * and tracks the status of playing the media via the status label 
	   */
	  private MediaPlayer createMediaPlayer(final String url, final Label status) {
	    Media hit = new Media(url);
	    MediaPlayer mediaPlayer = new MediaPlayer(hit);
	    mediaPlayer.setOnError(new Runnable() {
	      @Override public void run() {
	        status.setText("Error");
	      }
	    });
	    mediaPlayer.setOnPlaying(new Runnable() {
	      @Override public void run() {
	        status.setText("Playing: " + url);
	      }
	    });
	    mediaPlayer.setOnEndOfMedia(new Runnable() {
	      @Override public void run() {
	        status.setText("Done");
	      }   	
	    });
	    return mediaPlayer;
	  }
	
	public void show() {
		final JFXPanel fxPanel = new JFXPanel();
		fxPanel.setPreferredSize(new Dimension(445 + 100, 250 + 200));

		add(fxPanel, BorderLayout.CENTER);
	    initFX(fxPanel, mediaURL);
		//initFX(fxPanel, "http://www.html5videoplayer.net/videos/toystory.mp4");
	}

	public String selectURL (String type) {

		String home = System.getProperty("user.home");
		// NOTE: set this to local video folder
		//String videoFolder = home + "/git/HelpKioskKhine/video";
		//String videoFolder = home.replace("\\", "/") + "/git/HelpKiosk/video";	
		
		// WINDOW switch url file path 
		String videoUrl = "file:///"+ home.replace("\\", "/") + "/eclipse-workspace/HelpKiosk/video";
		
		// MAC 
		//String videoUrl = "file:///"+ home.replace("\\", "/") + "/git/HelpKiosk/video";
		
		switch(type) {
			// Contact
			case "openContact":
				videoUrl = videoUrl +"/contacts/contact_open_app.mp4";
				break;
			case "newContact":
				videoUrl = videoUrl +"/contacts/contact_new_contact.mp4";
				break;
			case "enterInfo":
				videoUrl = videoUrl +"/contacts/contact_enter_info.mp4";
				break;
			case "saveContact":
				videoUrl = videoUrl +"/contacts/contact_save.mp4";
				break;
				
			// Camera
			case "openCam":
				videoUrl = videoUrl +"/camera/camera_open_app.mp4";
				break;
			case "previewPicture":
				videoUrl = videoUrl +"/camera/camera_focus.mp4";
				break;
			case "takePicture":
				videoUrl = videoUrl +"/camera/camera_take_photo.mp4";
				break;
				
			// Clock
			case "openClock":
				videoUrl = videoUrl +"/clock/clock_open_app.mp4";
				break;
			case "touchAlarm":
				videoUrl = videoUrl +"/clock/clock_tap_alarm.mp4";
				break;
			case "setAlarm":
				videoUrl = videoUrl +"/clock/clock_set_alarm.mp4";
				break;
			case "saveAlarm":
		        videoUrl = videoUrl +"/clock/clock_save.mp4";
				break;
				
			// Message
			case "openMessage":
				videoUrl = videoUrl +"/message/message_open_app.mp4";
				break;
			case "newMessage":
				videoUrl = videoUrl +"/message/message_new.mp4";
				break;
			case "enterMessage":
				videoUrl = videoUrl +"/message/message_enter.mp4";
				break;
			case "send":
				videoUrl = videoUrl +"/message/message_send.mp4";
				break;
				
			// Phone
			case "openPhone":
				videoUrl = videoUrl +"/phone/phone_open_app.mp4";
				break;
			case "enterNumber":
				videoUrl = videoUrl +"/phone/phone_enter_num.mp4";
				break;
			case "call":
				videoUrl = videoUrl +"/phone/phone_call.mp4";
				break;
				
			// Gallery
			case "openGallery":
				videoUrl = videoUrl +"/gallery/gallery_open_app.mp4";
				break;
			case "select":
				videoUrl = videoUrl +"/gallery/gallery_select.mp4";
				break;
			case "menuTap":
				videoUrl = videoUrl +"/gallery/gallery_menu_tap.mp4";
				break;
			
			// nothing
			default:
				videoUrl = "";
				break;
		}
		

		return videoUrl;
	}

	public void setURL(String mediaURL) {
		this.mediaURL = mediaURL;
	}

} 