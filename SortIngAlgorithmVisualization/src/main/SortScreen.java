package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;

import Audio.MidiSoundPlayer;

public class SortScreen extends JPanel{
	private MainApp theApp;
	private int[] array;

	public int[] indexArray;
//	private int screenWidth = 1920;
//	private int screenHeight = 1080;
//	private int size;
	private int totalBars;
	public long delay;
	private int decide;
	private int choice;
	Random rd;
	private String theName;
	private boolean green;
	private int visualizationChoice;
	public int mod = 4;
	public int mod2;
	private MidiSoundPlayer player;
	public int accesses;
	public int swaps;
	
	public int[] getArray() {
		return array;
	}


	public void setArray(int[] array) {
		this.array = array;
	}

	
	public void setTheName(String theName) {
		this.theName = theName;
	}


	public void setChoice(int choice) {
		this.choice = choice;
	}
	
	
	public SortScreen(MainApp theApp, int[] theArray) {
		//this.setSize(screenWidth, screenHeight);
		//Graphics2D panelGraphics = (Graphics2D) g.create();
		indexArray = new int[theArray.length+1];
		setBackground(Color.BLACK);
		this.theApp = theApp;
		this.array = theArray;
		this.totalBars = array.length;
		mod2 = (int) Math.pow(mod, 2);
		player = new MidiSoundPlayer(200);
		repaint();
	}
	
	public int getDecide() {
		return decide;
	}


	public int getChoice() {
		return choice;
	}


	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D panelGraphics = (Graphics2D) g.create();

		try
		{
			Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
			renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			panelGraphics.addRenderingHints(renderingHints);
			panelGraphics.setColor(Color.WHITE);
			panelGraphics.setFont(new Font("IMPACT", Font.BOLD, 35));
			panelGraphics.drawString("Algorithm: " + theName, 100, 50);
			panelGraphics.drawString("Delay: " + delay + "ms", 750, 50);
			panelGraphics.drawString("Array Accesses: " + accesses, 1050, 50);
			panelGraphics.drawString("Array Writes: " + swaps, 1500, 50);
			//panelGraphics.drawString("     Array Changes: " + "unfinished", 10, 80);

			drawBars(panelGraphics);
		} finally {
        	panelGraphics.dispose();
        }
    }
	
	public int getMaxValue() {
    	return Arrays.stream(array).max().orElse(Integer.MIN_VALUE);
    }
	
	private Color getColor(int index) {
//		decide = 6;
//		choice = 1;
		switch(decide)
		{
			case 1:
				return new Color(50+array[index],255-array[index],choice*10);
			case 2:
				return new Color(50+array[index],choice*10,255-array[index]);
			case 3:
				return new Color(50+array[index],20+array[index],choice*10);
			case 4:
				return new Color(50+array[index],choice*10,array[index]);
			case 5:
				return new Color(choice*10,50+array[index],255-array[index]);
			case 6:
				return new Color(choice*10,255-array[index],50+array[index]);
			case 7:
				return new Color(50+array[index],120+array[index]/2,choice*10);
			case 8:
				return new Color(choice*10,50+array[index],50+array[index]);
			case 9:
				return new Color(255-array[index],50+array[index],choice*10);
			case 10:
				return new Color(255-array[index],choice*10,50+array[index]);
			case 11:
				return new Color(150+array[index]/2,choice*10,array[index]);
			case 12:
				return new Color(255-array[index],choice*10,255-array[index]);
		} //7,8,12,11,3, kinda 12
		//10, 9, 5, 6, 7, 2, 11??, 4
		return null;
	}
	
	public void setDecide(int decide) {
		this.decide = decide;
	}

	private void drawBars(Graphics2D panelGraphics)
	{
		//panelGraphics.scale((double)1920/(double)totalBars, 1);
		
		int barWidth = 1920/totalBars;
		int bufferedImageWidth = getWidth();
		int bufferedImageHeight = getHeight();
        
		if(true) {
			if(bufferedImageWidth < 256) {
				bufferedImageWidth = 256;
			}
			
			double maxValue = getMaxValue();
		
			BufferedImage bufferedImage = new BufferedImage(bufferedImageWidth, bufferedImageHeight, BufferedImage.TYPE_INT_ARGB);
			//makeBufferedImageTransparent(bufferedImage);
			Graphics2D bufferedGraphics = null;
			
			
			try
			{
				bufferedGraphics = bufferedImage.createGraphics();
				
				for (int x = 0; x < totalBars; x++) {
					double currentValue = array[x];
					double percentOfMax = currentValue / maxValue;
					double heightPercentOfPanel = percentOfMax * 0.5;
					int height = (int) (heightPercentOfPanel * (double) getHeight());
					int xBegin = x*barWidth;
					int yBegin = 540;
					
					int val = indexArray[x];

					bufferedGraphics.setColor(new Color(val,val,val));
					if(indexArray[x]==240)
						player.makeSound(array[x]);
					
					if(indexArray[x]>0) {
						bufferedGraphics.fillRect(xBegin, 200, 6, 6);
						indexArray[x]-=80;
					}
					
					bufferedGraphics.setColor(getColor(x));
					
					visualizationChoice = 5;
					switch(visualizationChoice) {
					case 1:
						bufferedGraphics.fillRect(xBegin, yBegin-height/2, barWidth, height);
						break;
					case 2:
						bufferedGraphics.fillRect(xBegin, 840-height, barWidth, barWidth);
						break;
					case 3:
						bufferedGraphics.fillRect(xBegin, 840-height, barWidth, barWidth);
						bufferedGraphics.fillRect(xBegin, 300+height, barWidth, barWidth);
						break;
					case 4:
						bufferedGraphics.drawOval(xBegin, yBegin-height/2, height, height);
						break;
					case 5:
						//System.out.println(Math.sin(Math.PI*(double)2.00*(double)height/maxValue));
						bufferedGraphics.fillRect(xBegin, (int) ((double)640.00-(double)300.00*Math.sin(Math.PI*(double)2.00*(double)height/maxValue)), barWidth, barWidth*4);
						bufferedGraphics.fillRect(xBegin, (int) ((double)640-(double)200*Math.cos(Math.PI*(double)2*(double)height/maxValue)), barWidth, barWidth*4);
						bufferedGraphics.fillRect(xBegin, (int) ((double)640-(double)300*Math.sin(Math.PI*(double)2*(double)height/maxValue-Math.PI)), barWidth, barWidth*4);
						bufferedGraphics.fillRect(xBegin, (int) ((double)640-(double)200*Math.cos(Math.PI*(double)2*(double)height/maxValue-Math.PI)), barWidth, barWidth*4);
						break;
					case 6:
						//System.out.println(x);
						bufferedGraphics.fillRect(x, 0, 1, 1080);
						//bufferedGraphics.fillRect(xBegin, 1080-250, barWidth, 250);
//						panelGraphics.setFont(new Font("Helvetica", Font.BOLD, 70)); 
//						panelGraphics.drawString("THANKS FOR WATCHING", 500, 500);
//						panelGraphics.setFont(new Font("Helvetica", Font.ITALIC, 70)); 
//						panelGraphics.drawString("SUBSCRIBE FOR MORE", 500, 600);
						break;
					}
					
					
				}
			}
			finally
			{
				if(bufferedGraphics != null)
				{
					bufferedGraphics.dispose();
				}
			}
			
			panelGraphics.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
		}
	}


	public void setGreen(boolean b) {
		green = b;
	}

}
