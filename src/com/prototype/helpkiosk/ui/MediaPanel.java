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
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.swing.JPanel;

public class MediaPanel extends JPanel
{
	public URL mediaURL;
	Player mediaPlayer=null;
	
   public MediaPanel()
   {
      setLayout( new BorderLayout() ); // use a BorderLayout
      
     
   } // end MediaPanel constructor
   
   public void show(){
	   // Use lightweight components for Swing compatibility
	      Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
	      
	      try
	      {
	    	  if(mediaURL!=null){
		         // create a player to play the media specified in the URL
		         mediaPlayer = Manager.createRealizedPlayer( mediaURL );
		         
		         // get the components for the video and the play-back controls
		         Component video = mediaPlayer.getVisualComponent();
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
		         
		         if ( video != null ) 
		            add( video, BorderLayout.CENTER ); // add video component
		         
		         if ( controls != null ) 
		            add( controls, BorderLayout.SOUTH ); // add controls
	
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
   
   public void setURL(URL mediaURL){
	   this.mediaURL = mediaURL;
   }
   
} // end class MediaPanel
