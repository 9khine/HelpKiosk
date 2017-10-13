package com.prototype.helpkiosk.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.jdesktop.swingworker.SwingWorker;

import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.prototype.helpkiosk.instruction.InstructionSingleton;

class LiveView extends JPanel implements ActionListener {
	private IDevice device;
	private GetScreenshotTask task;
	private BufferedImage image;
	private volatile boolean isLoading;
	private Crosshair crosshair;
	private Timer timer;
	private int refreshRate = 500;
	private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();
	private int width, height;

	LiveView (IDevice device) {
		setLayout(new FlowLayout());
		setOpaque(true);
		setBackground(Color.WHITE);

		this.device = device;
		this.width = 240;
		this.height = 400;

		this.timer = new Timer(refreshRate, this);
		this.timer.setInitialDelay(0);
		this.timer.setRepeats(true);

		JPanel panel = buildViewerAndControls();
		add(panel);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LiveView.this.timer.start();
			}
		});

	}

	private JPanel buildViewerAndControls() {
		JPanel panel = new JPanel();

		Crosshair viewPanel = new Crosshair(new ScreenshotViewer());
		viewPanel.setBackground(Color.WHITE);
		viewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		viewPanel.setBounds(0, 0, width, height);

		JLabel title = new JLabel("<html><br></html>");
		title.setFont(new Font("Helvetica", Font.BOLD, 23));
		title.setForeground(new Color(0x3B70A3));

		//ImageIcon img = new ImageIcon("img/nexusoneinhandblur.png");
		ImageIcon img = new ImageIcon("img/nexusoneinhand.png");

		JLabel bg = new JLabel(img);
		bg.setBackground(Color.WHITE);
		bg.setLayout(new BoxLayout(bg, BoxLayout.Y_AXIS));
		JPanel rigid = new JPanel();
		rigid.setOpaque(false);
		rigid.setLayout(new BoxLayout(rigid, BoxLayout.X_AXIS));
		rigid.setPreferredSize(new Dimension(100, 30));
		rigid.add(title);
		bg.add(rigid);

		JPanel rigid2 = new JPanel();
		rigid2.setOpaque(false);
		rigid2.setPreferredSize(new Dimension(92, height));

		JPanel rigid3 = new JPanel();
		rigid3.setOpaque(false);
		rigid3.setPreferredSize(new Dimension(10, height));

		JPanel filler = new JPanel();
		filler.setLayout(new BoxLayout(filler, BoxLayout.X_AXIS));
		filler.setOpaque(false);
		filler.add(rigid2);
		filler.add(viewPanel);
		filler.add(rigid3);

		JPanel rigid4 = new JPanel();
		rigid4.setLayout(null);
		rigid4.setOpaque(false);
		rigid4.setPreferredSize(new Dimension(width, 100));   
		instructionSingleton.setLowerContainer(rigid4);

		bg.add(filler);
		bg.add(rigid4);

		panel.add(bg);
		return panel;
	}

	void stop() {
		this.timer.stop();
	}

	void start() {
		this.timer.start();
	}

	public void actionPerformed(ActionEvent event) {
		// if no current task, start a new GetScreenshot task
		if ((this.task != null) && (!this.task.isDone())) {
//			System.out.println("Existing task, no new one");
			return;
		}
		//System.out.println("New screenshot task created!");
		this.task = new GetScreenshotTask();
		this.task.execute();
	}

	private class GetScreenshotTask extends SwingWorker<Boolean, Void> {
		// SwingWorkers are essentially new threads, creating a new one adds a new task to a free worker thread
		
		private GetScreenshotTask() {
			
		}

		protected Boolean doInBackground() throws Exception {
			/*
			 * Script to run in background:
			 * screenstream.bat in the admin-mux home directory
			 */
			
			long startTime = System.nanoTime();
			String home = System.getProperty("user.home");
			File newestImg = this.lastFileModified(home + "/git/HelpKiosk/screenshots/");
			ImageIcon screenshot = new ImageIcon(newestImg.getPath());
			Image screenShotImg = screenshot.getImage();
			Image scaledImage = screenShotImg.getScaledInstance(width,height,Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);

			long endTime = System.nanoTime();
			long duration3 = ((endTime - startTime)/1000000);
			
			boolean resize = false;
			boolean landscape = false;
			
				if (scaledIcon != null) {
					resize = true;
					LiveView.this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
					
				    // Draw the image on to the buffered image
				    Graphics2D bGr = LiveView.this.image.createGraphics();
				    bGr.drawImage(scaledImage, 0, 0, null);
				    bGr.dispose();
				}
			
				return Boolean.valueOf(resize);
		}
		
		private File lastFileModified(String dirPath) {
			File dir = new File(dirPath);
			File[] files = dir.listFiles();
			
			if (files == null || files.length == 0) {
				return null;
			} else if (files.length >= 3) {
				files[0].delete();
			}
			
			return files[files.length - 2];
		}


		protected void done() {
			try {
				if (((Boolean)get()).booleanValue()) {
					LiveView.this.validate();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			LiveView.this.repaint();
		}
	}

	class ScreenshotViewer extends JComponent {

		ScreenshotViewer() {
			setOpaque(true);
			setBounds(0,0,width,height);

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			// For highlighting the live view?
			JPanel glass = new JPanel();
			glass.setSize(new Dimension(width, height));
			glass.setPreferredSize(new Dimension(width, height));
			glass.setMaximumSize(new Dimension(width, height));
			glass.setOpaque(false);
			glass.setBounds(0,0,width,height);
			instructionSingleton.setHighlightContainer(glass);
			instructionSingleton.highlight("nothing", "camera");

			add(glass);
		}

		protected void paintComponent(Graphics g) {
			g.fillRect(0, 0, width, height);
			if (LiveView.this.isLoading) {
				return;
			}

			if (LiveView.this.image != null) {
				// if live view not null paint new image - this should be called every second
				g.drawImage(LiveView.this.image, 0, 0, width, height, null);
			}
		}

		public Dimension getPreferredSize() {
			return new Dimension(width, height);
		}
	}

	class Crosshair extends JPanel {
		private final LiveView.ScreenshotViewer screenshotViewer;

		Crosshair(LiveView.ScreenshotViewer screenshotViewer) {
			this.screenshotViewer = screenshotViewer;
			setOpaque(true);
			setLayout(new BorderLayout());
			setBounds(0,0,width, height);
			add(screenshotViewer);
		}

		public Dimension getPreferredSize() {
			return this.screenshotViewer.getPreferredSize();
		}

		public Dimension getMaximumSize() {
			return this.screenshotViewer.getPreferredSize();
		}
	}
}