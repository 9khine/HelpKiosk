package com.prototype.helpkiosk.ui;
// Fig 21.6: MediaPanel.java
// A JPanel the plays media from a URL
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.media.format.VideoFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

public class MediaPanel extends JPanel {
	public URL mediaURL;
	String mediaURLstr;
	Player mediaPlayer;
	EmbeddedMediaPlayer mediaPlayerV = null;
	
	private EmbeddedMediaPlayerComponent ourMediaPlayer;
	
	public MediaPanel() {
		setLayout(new BorderLayout()); // use a BorderLayout
	}

	public void show() {
		// Use lightweight components for Swing compatibility
		Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );

		try {
			
			if (mediaURL != null) {
				
				//	http://lethalman.blogspot.ca/2009/02/jmf-and-mpeg.html
				//	http://jffmpeg.sourceforge.net/
//				Format[] inFormats = { new VideoFormat ("MPEG") };
//				PlugInManager.addPlugIn ("net.sourceforge.jffmpeg.VideoDecoder", inFormats, null, PlugInManager.CODEC);
//				PlugInManager.commit ();
				
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
							public void mousePressed(MouseEvent evt) {
								mediaPlayer.setMediaTime(new Time(0));
								mediaPlayer.start();
							}

							public void mouseReleased(MouseEvent evt) {
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
		
		if (type=="openContact") {
	        videoUrl = "file:/"+videoFolder+"/addContacts/OpenContacts.MPG";
		} else if(type=="newContact"){
	        videoUrl = "file://"+videoFolder+"/addContacts/new-contact.MPG";
		} else if(type=="newName"){
	        videoUrl = "file://"+videoFolder+"/addContacts/addname.MPG";
		} else if(type=="addothercomponent"){
	        videoUrl = "file://"+videoFolder+"/addContacts/addothercomponent.MPG";
		} else if(type=="contact_done"){
	        videoUrl = "file://"+videoFolder+"/addContacts/contact_done.MPG";
		} else if(type=="openCam"){
	        videoUrl = "file:"+videoFolder+"/takePicture/cam.MPG";
		} else if(type=="takePicture"){
	        videoUrl = "file:"+videoFolder+"/takePicture/take-picture.MPG";
		} else if(type=="openClock"){
	        videoUrl = "file:"+videoFolder+"/clock/openclock.MPG";
		} else if(type=="openAlarm"){
	        videoUrl = "file:"+videoFolder+"/clock/openalarm.MPG";
		} else if(type=="makeGreen"){
	        videoUrl = "file:"+videoFolder+"/clock/makegreen.MPG";
		} else if(type=="changeOrAdd"){
	        videoUrl = "file:"+videoFolder+"/changeoradd.MPG";
		} else if(type=="clockdone"){
	        videoUrl = "file:"+videoFolder+"/clock/clock_done.MPG";
		} else if(type=="goBack"){
	        videoUrl = "file:"+videoFolder+"/clock/clock_back.MPG";
		} else if(type=="nothing"){
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
	
} // end class MediaPanel
