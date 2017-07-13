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
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		if (mediaURL!=null) {
			System.out.println("VLCj stuff here ===========");
			new NativeDiscovery().discover();
			mediaURLstr = mediaURL.toString();
			System.out.println("mediaURLstring is: " + mediaURLstr);

			ourMediaPlayer = new EmbeddedMediaPlayerComponent();

			/* Set the canvas */
			Canvas c = new Canvas();
			c.setBackground(Color.black);
			c.setVisible(true);

			/* Set the layout */
			this.setLayout(new BorderLayout());

			/* Add the canvas */
			this.add(c, BorderLayout.CENTER);
			
			JPanel controlsPane = new JPanel();
			JButton playButton = new JButton("Play");
			controlsPane.add(playButton);
			JButton pauseButton = new JButton("Pause");
			controlsPane.add(pauseButton);
			JButton rewindButton = new JButton("Rewind");
			controlsPane.add(rewindButton);
			this.add(controlsPane, BorderLayout.SOUTH);
			
			playButton.addActionListener(new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent e) {
					ourMediaPlayer.getMediaPlayer().play();;
			    }
			});
			
			pauseButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	ourMediaPlayer.getMediaPlayer().pause();
			    }
			});
			
			rewindButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	ourMediaPlayer.getMediaPlayer().skip(-10000);
			    }
			});
			
			this.setVisible(true);
			this.play();
		}
	}
	
	public void play() {
	    /* Play the video */
	    ourMediaPlayer.getMediaPlayer().playMedia(mediaURLstr);
	}
	
	public void setURL(URL mediaURL){
		this.mediaURL = mediaURL;
	}
} // end class MediaPanel
