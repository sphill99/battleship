import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu {
	
	private static Color navy = new Color (13, 13, 77);
	
	public static void drawMenu(Graphics gc) {
		gc.setColor(navy);
		gc.fillRect(0, 0, 750, 750);
		gc.setColor(Color.WHITE);
		BufferedImage b1 = null;
		try {
			b1 = ImageIO.read(new File ("files/Battleship.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gc.drawImage(b1, 75, 100, 600, 200, null);
		gc.drawString("Battleship", 50, 700);
		gc.drawString("SPACE to play, I for instructions", 50, 725);
	}
	
	public static void drawInstructions(Graphics gc) {
		gc.setColor(navy);
		gc.fillRect(0, 0, 750, 750);
		BufferedImage b1 = null;
		try {
			b1 = ImageIO.read(new File ("files/Battleship.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gc.drawImage(b1, 75, 50, 600, 200, null);
		Font f = new Font("rockwell", Font.BOLD, 20);
		gc.setFont(f);
		gc.setColor(Color.WHITE);
		gc.drawString("To play the game, click SPACE", 50, 300);
		gc.drawString("Click SPACE to attack a ship", 50, 350);
		gc.drawString("Move the selector with the arrow keys", 50, 400);
		gc.drawString("Click R to reset the game", 50, 450);
		gc.drawString("You are playing against the Computer", 50, 500);
		gc.drawString("Their attacks show up on the bottom screen", 50, 550);
		gc.drawString("Your attacks show up on the top screen", 50, 600);
		gc.drawString("The goal is to hit all their ships before they hit yours", 50, 650);
		gc.drawString("ENJOY!", 50, 705);
	}
	
	public static void drawEndScreen(Graphics gc, boolean youWin, Battleship b) {
		gc.setColor(navy);
		gc.fillRect(0, 0, 750, 750);
		BufferedImage b1 = null;
		try {
			b1 = ImageIO.read(new File ("files/BattleshipLose.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gc.drawImage(b1, 0, 0, 750, 750, null);
		gc.setColor(Color.WHITE);
		Font f = new Font("rockwell", Font.BOLD, 36);
		gc.setFont(f);
		if (youWin) {
			gc.drawString("YOU WIN", 350, 370);
		} else {
			gc.drawString("YOU LOST TO A COMPUTER", 150, 370);
			gc.drawString("" + b.getMyShots(), 350, 450);
		}
		gc.drawString("To go back to the main menu", 100, 600);
		gc.drawString("press the spacebar", 150, 650);
	}
}
