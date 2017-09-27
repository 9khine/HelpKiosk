package com.prototype.helpkiosk.ui;
// Fig 21.6: MediaPanel.java
// A JPanel the plays media from a URL
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
import javax.swing.JPanel;

//import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class MediaPanel extends JPanel {
	public URL mediaURL;
	String mediaURLstr;
	Player mediaPlayer;
	//EmbeddedMediaPlayer mediaPlayerV = null;
	
	//private EmbeddedMediaPlayerComponent ourMediaPlayer;
	
	public MediaPanel() {
		setLayout(new BorderLayout()); // use a BorderLayout
	}

	public void show() {
		// Use lightweight components for Swing compatibility
		Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );

		try {
			
			if (mediaURL != null) {
				
				System.out.println("mediaURL is: " + mediaURL);
				// create a player to play the media specified in the URL
				mediaPlayer = Manager.createRealizedPlayer(mediaURL);
				
				System.out.println("mediaPlayer vis component: " + mediaPlayer.getVisualComponent());
				// get the components for the video and the play-back controls
				Component video = mediaPlayer.getVisualComponent();
				//video.setVisible(true);
				mediaPlayer.getGainControl();
				mediaPlayer.addControllerListener((ControllerListener) mediaPlayer);
				Component controls = mediaPlayer.getControlPanelComponent();

				controls.setPreferredSize(new Dimension(controls.getWidth(), 20));

				video.addMouseListener(
						new MouseAdapter() {
							public void mouseEntered(MouseEvent evt) {
								mediaPlayer.prefetch();
								mediaPlayer.setMediaTime(new Time(0));
								mediaPlayer.start();
							}
							public void mouseExited(MouseEvent evt) {
								System.out.println("!!! What is media state - " + mediaPlayer.getState());
								mediaPlayer.stop();
							}
							public void mouseClicked(MouseEvent evt) {
								mediaPlayer.prefetch();
								mediaPlayer.setMediaTime(new Time(0));
								mediaPlayer.start();
								//mediaPlayer.stop();
							}
							public void mousePressed(MouseEvent evt) {
								//Time pausedTime = mediaPlayer.getMediaTime();
								//mediaPlayer.stop();
								//mediaPlayer.setMediaTime(pausedTime);
								//mediaPlayer.start();
							}

							public void mouseReleased(MouseEvent evt) {
								//mediaPlayer.start();
							}	
						});	

				if ( video != null ) {
					add( video, BorderLayout.CENTER ); // add video component
					System.out.println("video is in CENTER");
				}

				if ( controls != null ) {
					add( controls, BorderLayout.SOUTH ); // add controls
					System.out.println("controls are in SOUTH");
				}
			}
		} // end try
		catch ( NoPlayerException noPlayerException )
		{
			System.err.println( "No media player found" );
		} // end catch
		catch ( CannotRealizeException cannotRealizeException )
		{
			System.err.println( "Could not realize media player" );
		} // end catch
		catch ( IOException iOException )
		{
			System.err.println( "Error reading from the source" );
		} // end catch
	}
	
	public String selectURL (String type) {
		
		String home = System.getProperty("user.home");
		// TODO: set this to local video folder
		//String videoFolder = home + "/git/HelpKioskKhine/video";
		String videoFolder = home + "/git/HelpKiosk/video";		
		String videoUrl = null;
		
		// Contact
		if (type=="openContact") {
	        videoUrl = "file:/"+videoFolder+"/contacts/contact_open_app.mpeg";
		} else if(type=="newContact"){
	        videoUrl = "file://"+videoFolder+"/contacts/contact_add_new.mpeg";
		} else if(type=="enterInfo"){
	        videoUrl = "file://"+videoFolder+"/contacts/contact_enter_info.mpeg";
		} else if(type=="saveContact"){
	        videoUrl = "file://"+videoFolder+"/contacts/contact_save.mpeg";
		} 
		
		// Camera
		else if(type=="openCam"){
	        videoUrl = "file:"+videoFolder+"/camera/camera_open_app.mpeg";
		} else if(type=="takePicture"){
	        videoUrl = "file:"+videoFolder+"/camera/camera_focus_take.mpeg";
		} 
		
		// Clock
		else if(type=="openClock"){
	        videoUrl = "file:"+videoFolder+"/clock/clock_open_app.mpeg";
		} else if(type=="touchAlarm"){
	        videoUrl = "file:"+videoFolder+"/clock/clock_tap_alarm.mpeg";
		} else if(type=="setAlarm"){
	        videoUrl = "file:"+videoFolder+"/clock/clock_set_time.mpeg";
		} else if(type=="saveAlarm"){
	        videoUrl = "file:"+videoFolder+"/clock/clock_save.mpeg";
		}
		
		// Message
		else if(type=="openMessage"){
	        videoUrl = "file:"+videoFolder+"/message/message_open_app.mpeg";
		} else if(type=="newMessage"){
	        videoUrl = "file:"+videoFolder+"/message/message_new_tap.mpeg";
		} else if(type=="enterMessage"){
	        videoUrl = "file:"+videoFolder+"/message/message_enter_msg.mpeg";
		} else if(type=="send"){
	        videoUrl = "file:"+videoFolder+"/message/message_send.mpeg";
		}
		
		// Phone
		else if(type=="openPhone"){
	        videoUrl = "file:"+videoFolder+"/phone/phone_open_app.mpeg";
		} else if(type=="enterNumber"){
	        videoUrl = "file:"+videoFolder+"/phone/phone_enter_number.mpeg";
		} else if(type=="call"){
	        videoUrl = "file:"+videoFolder+"/phone/phone_tap_call.mpeg";
		} 
		
		// Gallery
		else if(type=="openGallery"){
	        videoUrl = "file:"+videoFolder+"/gallery/gallery_open_app.mpeg";
		} else if(type=="select"){
	        videoUrl = "file:"+videoFolder+"/gallery/gallery_select.mpeg";
		} else if(type=="menuTap"){
	        videoUrl = "file:"+videoFolder+"/gallery/gallery_menu_tap.mpeg";
		} 
		
		// None
		else if(type=="nothing"){
			videoUrl = "";
		}
		
		return videoUrl;
	}
	
	public void setURL(URL mediaURL){
		this.mediaURL = mediaURL;
	}
	
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
	
} // end class MediaPanel
