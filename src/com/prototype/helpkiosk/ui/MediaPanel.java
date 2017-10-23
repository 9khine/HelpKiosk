package com.prototype.helpkiosk.ui;
// Fig 21.6: MediaPanel.java
// A JPanel the plays media from a URL
import java.awt.BorderLayout;

import javax.media.Player;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.animation.Timeline;
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
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;


public class MediaPanel extends JPanel {
	
	public String mediaURL;

	public MediaPanel() {
		Platform.setImplicitExit(false);
		setLayout(new BorderLayout()); // use a BorderLayout
		
	}
	
	private void initFX(JFXPanel fxPanel, String url) {
		// This method is invoked on the JavaFX thread
		Scene scene = createScene(url);
		fxPanel.setScene(scene);
	}

	private Scene createScene(String url) {
	
		final Label status = new Label("Init");
		final MediaPlayer mediaPlayer = createMediaPlayer(url, status);
		//Timeline FxTimer = new Timeline(new KeyFrame(Duration.millis(2500));
		mediaPlayer.play();
		//mediaPlayer.get
		//mediaPlayer.pause();
		MediaView view = new MediaView(mediaPlayer);
		
		DoubleProperty mvw = view.fitWidthProperty();
		DoubleProperty mvh = view.fitHeightProperty();
		mvw.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
		mvh.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
		view.setPreserveRatio(true);

		Group root  =  new  Group(view);	
		Scene scene  =  new  Scene(root, Color.ALICEBLUE);
		//((Group)scene.getRoot()).getChildren().add(view);
		
		mediaPlayer.setCycleCount(3);
		mediaPlayer.setAutoPlay(true);
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			boolean pause = false;
			
		    public void handle(MouseEvent mouseEvent) {
		    	if(mouseEvent.getClickCount() == 1 && !pause) {
			    	pause = true;
			    	mediaPlayer.pause();
		    	} 
		    	else if(mouseEvent.getClickCount() == 1 && pause) {
		    		pause = false;
			    	mediaPlayer.play();
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
		ImageIcon icon = new ImageIcon("img/videoBG.png"); 
		JLabel thumb = new JLabel();
		thumb.setIcon(icon);
		add(fxPanel, BorderLayout.CENTER);
	    initFX(fxPanel, mediaURL);
		//initFX(fxPanel, "http://www.html5videoplayer.net/videos/toystory.mp4");
	}

	public String selectURL (String type) {

		String home = System.getProperty("user.home");
		// NOTE: set this to local video folder
		//String videoFolder = home + "/git/HelpKioskKhine/video";
		//String videoFolder = home.replace("\\", "/") + "/git/HelpKiosk/video";	
		
		// TODO WINDOW switch url file path 
		String videoUrl = "file:///"+ home.replace("\\", "/") + "/eclipse-workspace/HelpKiosk/video";
		
		// MAC 
		//String videoUrl = "file:///"+ home.replace("\\", "/") + "/git/HelpKiosk/video";
		
		// Contact
		if (type=="openContact") {
			videoUrl = videoUrl +"/contacts/contact_open_app.mp4";
		} else if(type=="newContact"){
			videoUrl = videoUrl +"/contacts/contact_new_contact.mp4";
		} else if(type=="enterInfo"){
			videoUrl = videoUrl +"/contacts/contact_enter_info.mp4";
		} else if(type=="saveContact"){
			videoUrl = videoUrl +"/contacts/contact_save.mp4";
		} 

		// Camera
		else if(type=="openCam"){
			videoUrl = videoUrl +"/camera/camera_open_app.mp4";
		} else if(type=="previewPicture"){
			videoUrl = videoUrl +"/camera/camera_focus.mp4";
		} else if(type=="takePicture"){
			videoUrl = videoUrl +"/camera/camera_take_photo.mp4";
		} 

		// Clock
		else if(type=="openClock"){
			videoUrl = videoUrl +"/clock/clock_open_app.mp4";
		} else if(type=="touchAlarm"){
			videoUrl = videoUrl +"/clock/clock_tap_alarm.mp4";
		} else if(type=="setAlarm"){
			videoUrl = videoUrl +"/clock/clock_set_alarm.mp4";
		} else if(type=="saveAlarm"){
	        videoUrl = videoUrl +"/clock/clock_save.mp4";
		}

		// Message
		else if(type=="openMessage"){
			videoUrl = videoUrl +"/message/message_open_app.mp4";
		} else if(type=="newMessage"){
			videoUrl = videoUrl +"/message/message_new.mp4";
		} else if(type=="enterMessage"){
			videoUrl = videoUrl +"/message/message_enter.mp4";
		} else if(type=="send"){
			videoUrl = videoUrl +"/message/message_send.mp4";
		}

		// Phone
		else if(type=="openPhone"){
			videoUrl = videoUrl +"/phone/phone_open_app.mp4";
		} else if(type=="enterNumber"){
			videoUrl = videoUrl +"/phone/phone_enter_num.mp4";
		} else if(type=="call"){
			videoUrl = videoUrl +"/phone/phone_tap_call.mp4";
		} 

		// Gallery
		else if(type=="openGallery"){
			videoUrl = videoUrl +"/gallery/gallery_open_app.mp4";
		} else if(type=="select"){
			videoUrl = videoUrl +"/gallery/gallery_select.mp4";
		} else if(type=="menuTap"){
			videoUrl = videoUrl +"/gallery/gallery_menu_tap.mp4";
		} 

		// None
		else if(type=="nothing"){
			videoUrl = "";
		}

		return videoUrl;
	}

	public void setURL(String mediaURL) {
		this.mediaURL = mediaURL;
	}

} // end class MediaPanel
	
// ----------------------------------------------------------------------------------------------	
	
//	public void show() {
//		// Use lightweight components for Swing compatibility
//		Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
//
//		try {
//			
//			if (mediaURL != null) {
//				
//				System.out.println("mediaURL is: " + mediaURL);
//				// create a player to play the media specified in the URL
//				mediaPlayer = Manager.createRealizedPlayer(mediaURL);
//				
//				System.out.println("mediaPlayer vis component: " + mediaPlayer.getVisualComponent());
//				// get the components for the video and the play-back controls
//				Component video = mediaPlayer.getVisualComponent();
//				//video.setVisible(true);
//				mediaPlayer.getGainControl();
//				mediaPlayer.addControllerListener((ControllerListener) mediaPlayer);
//				Component controls = mediaPlayer.getControlPanelComponent();
//
//				controls.setPreferredSize(new Dimension(controls.getWidth(), 20));
//
//				video.addMouseListener(
//						new MouseAdapter() {
//							public void mouseEntered(MouseEvent evt) {
//								mediaPlayer.prefetch();
//								mediaPlayer.setMediaTime(new Time(0));
//								mediaPlayer.start();
//							}
//							public void mouseExited(MouseEvent evt) {
//								System.out.println("!!! What is media state - " + mediaPlayer.getState());
//								mediaPlayer.stop();
//							}
//							public void mouseClicked(MouseEvent evt) {
//								mediaPlayer.prefetch();
//								mediaPlayer.setMediaTime(new Time(0));
//								mediaPlayer.start();
//								//mediaPlayer.stop();
//							}
//							public void mousePressed(MouseEvent evt) {
//								//Time pausedTime = mediaPlayer.getMediaTime();
//								//mediaPlayer.stop();
//								//mediaPlayer.setMediaTime(pausedTime);
//								//mediaPlayer.start();
//							}
//
//							public void mouseReleased(MouseEvent evt) {
//								//mediaPlayer.start();
//							}	
//						});	
//
//				if ( video != null ) {
//					add( video, BorderLayout.CENTER ); // add video component
//					System.out.println("video is in CENTER");
//				}
//
//				if ( controls != null ) {
//					add( controls, BorderLayout.SOUTH ); // add controls
//					System.out.println("controls are in SOUTH");
//				}
//			}
//		} // end try
//		catch ( NoPlayerException noPlayerException )
//		{
//			System.err.println( "No media player found" );
//		} // end catch
//		catch ( CannotRealizeException cannotRealizeException )
//		{
//			System.err.println( "Could not realize media player" );
//		} // end catch
//		catch ( IOException iOException )
//		{
//			System.err.println( "Error reading from the source" );
//		} // end catch
//	}
	
	
	
	// Use lightweight components for Swing compatibility
//	Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
//	try {
//
//		if (mediaURL != null) {
//			
//			//----------------------------------------------------
//			//			player videos on desktop
//			//								String home = System.getProperty("user.home");
//			//								String videoFolder = home + "/git/HelpKiosk/video";	
//			//								String filename = videoFolder+"/old_videos/openclock.MPG";
//			//								
//			//								//System.out.println(filename);
//			//								
//			//								File vid = new File(filename);
//			//								Desktop.getDesktop().open(vid);
//			//----------------------------------------------------
//			
//		
//			// --------------------------------------------------
//			
//			System.out.println("mediaURL is: " + mediaURL);
//			// create a player to play the media specified in the URL
//			mediaPlayer = Manager.createRealizedPlayer(mediaURL);
//			
//			System.out.println("mediaPlayer is: " + mediaPlayer);
//			System.out.println("mediaPlayer vis component: " + mediaPlayer.getVisualComponent());
//			// get the components for the video and the play-back controls
//			Component video = mediaPlayer.getVisualComponent();
//			//video.setVisible(true);
//			mediaPlayer.getGainControl();
//			mediaPlayer.addControllerListener((ControllerListener) mediaPlayer);
//			Component controls = mediaPlayer.getControlPanelComponent();
//
//			controls.setPreferredSize(new Dimension(controls.getWidth(), 20));
//			
//			//---------------     	!!!!! 	ERROR ZONE 	!!!!! ----------------------------
//			//				video.addMouseListener(
//			//						new MouseAdapter() {
//			//							public void mouseClicked(MouseEvent evt) {
//			//								mediaPlayer.setMediaTime(new Time(0));
//			//								mediaPlayer.start();
//			//								mediaPlayer.stop();
//			//							}
//			//
//			//							public void mouseReleased(MouseEvent evt) {
//			//							}
//			//						});	
//			//
//			//				if ( video != null ) {
//			//					add( video, BorderLayout.CENTER ); // add video component
//			//					System.out.println("video is in CENTER");
//			//				}
//			//
//			//				if ( controls != null ) {
//			//					add( controls, BorderLayout.SOUTH ); // add controls
//			//					System.out.println("controls are in SOUTH");
////			}
//			//---------------     	!!!!! 	ERROR ZONE 	!!!!! ----------------------------
//			
//			
//		}
//	} // end try
//	catch ( NoPlayerException noPlayerException )
//	{
//		System.err.println( "No media player found" );
//	} // end catch
//	catch ( CannotRealizeException cannotRealizeException )
//	{
//		System.err.println( "Could not realize media player" );
//	} // end catch
//	catch ( IOException iOException )
//	{
//		System.err.println( "Error reading from the source" );
//	} // end catch
//	catch (Exception e) {
//		e.printStackTrace();
//	}


	//	//	https://stackoverflow.com/questions/20440484/embed-a-youtube-video-to-jframe
	//	// 	http://djproject.sourceforge.net/ns/
	//	public JPanel getBrowserPanel() {
	//	    JPanel webBrowserPanel = new JPanel(new BorderLayout());
	//	    JWebBrowser webBrowser = new JWebBrowser();
	//	    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
	//	    webBrowser.setBarsVisible(false);
	//	    webBrowser.navigate("https://www.youtube.com/watch?v=3tmd-ClpJxA");
	//	    return webBrowserPanel;
	//	}


	//	http://lethalman.blogspot.ca/2009/02/jmf-and-mpeg.html
	//	http://jffmpeg.sourceforge.net/
	//	Format[] inFormats = { new VideoFormat ("MPEG") };
	//	PlugInManager.addPlugIn ("net.sourceforge.jffmpeg.VideoDecoder", inFormats, null, PlugInManager.CODEC);
	//	PlugInManager.commit ();


