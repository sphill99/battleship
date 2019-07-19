import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Battleship extends JComponent {
	private PegBoard myPegBoard;
	private ShipBoard myShipBoard;
	private PegBoard theirPegBoard;
	private ShipBoard theirShipBoard;
	private GameMode mode;
	private Cell[][] theirShips;
	private Cell[][] myShips;
	private boolean playerWins;
	private int myShots;
	private int theirShots;
	private int myHits;
	private int theirHits;
	private List<String> highscores;
	private boolean newHighScore;
	private BufferedWriter bw;
	private BufferedReader br;

	public Battleship () {
		myPegBoard = new PegBoard();
		myShipBoard = new ShipBoard();
		theirPegBoard = new PegBoard();
		theirShipBoard = new ShipBoard();
		theirShips = theirShipBoard.getYourBoard();
		myShips = myShipBoard.getYourBoard();
		mode = GameMode.MAINMENU;
		playerWins = false;
		myShots = 0;
		theirShots = 0;
		myHits = 0;
		theirHits = 0;
		highscores = new ArrayList<String>();
		newHighScore = false;
		
		File file = new File("highscores.txt");
		try {
			if (file.createNewFile()) {
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
		try {
			br = new BufferedReader(new FileReader("highscores.txt"));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		
		try {
			while (br.ready()) {
				try {
					highscores.add(br.readLine());
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		try {
			br.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter b = new BufferedWriter(writer);
		bw = b;
		Collections.sort(highscores);
		
		try {
			for (int i = 0; i < highscores.size(); i++) {
				bw.write(highscores.get(i), 0, highscores.get(i).length());
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String name = JOptionPane.showInputDialog("Your Name: ");
		
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {	
				int key = e.getKeyCode();
				if (mode == GameMode.PLAYING) {
					if (key == KeyEvent.VK_RIGHT) {
						KeyStroke k = KeyStroke.RIGHTKEY;
						myPegBoard.selector(k);
						repaint();
					} else if (key == KeyEvent.VK_LEFT) {
						KeyStroke k = KeyStroke.LEFTKEY;
						myPegBoard.selector(k);
						repaint();
					} else if (key == KeyEvent.VK_UP) {
						KeyStroke k = KeyStroke.UPKEY;
						myPegBoard.selector(k);
						repaint();
					} else if (key == KeyEvent.VK_DOWN) {
						KeyStroke k = KeyStroke.DOWNKEY;
						myPegBoard.selector(k);
						repaint();
					} else if (key == KeyEvent.VK_R) {
						theirShipBoard.reset();
						myShipBoard.reset();
						theirPegBoard.reset();
						myPegBoard.reset();
						myShots = 0;
						theirShots = 0;
						myHits = 0;
						theirHits = 0;
						repaint();
					} else if (key == KeyEvent.VK_SPACE) {
						Cell c = myPegBoard.attack(theirShips);
						if (c.getState() == Status.HIT) {
							myHits++;
						}
						theirShipBoard.removeShip(c);
						repaint();

						if (!c.equalsTo(new Cell(100, 100))) {
							myShots++;
							//							try {
							//								Thread.sleep(1000);
							//							} catch (InterruptedException e1) {
							//								// TODO Auto-generated catch block
							//								e1.printStackTrace();
							//							}
							Cell t = theirPegBoard.attackAI(myShips, 0);
							if (t.getState() == Status.HIT) {
								theirHits++;
							}
							theirShots++;
							repaint();
							myShipBoard.removeShip(t);
							if (myShipBoard.shipsLeft() == 0) {
								highscores.add("" + myShots + " Computer");
								try {
									bw.write("" + myShots + " Computer");
									bw.newLine();
									bw.flush();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								Collections.sort(highscores);
								mode = GameMode.ENDSCREEN;
								repaint();
							}
						}
						if (theirShipBoard.shipsLeft() == 0) {
							playerWins = true;
							highscores.add("" + myShots + " " + name);
							try {
								bw.write("" + myShots + " " + name);
								bw.newLine();
								bw.flush();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							Collections.sort(highscores);
//							if (highscores.size() > 1) {
//								if (myShots < highscores.get(1)) {
//									newHighScore = true;
//								}
//							}
							mode = GameMode.ENDSCREEN;
							repaint();
						}
						repaint();
					}
				} else if (mode == GameMode.MAINMENU) {
					if (key == KeyEvent.VK_SPACE) {
						mode = GameMode.PLAYING;
						repaint();
					} else if (key == KeyEvent.VK_I) {
						mode = GameMode.INSTRUCTIONS;
						repaint();
					}
				} else if (mode == GameMode.INSTRUCTIONS) {
					if (key == KeyEvent.VK_SPACE) {
						mode = GameMode.PLAYING;
						repaint();
					} else if (key == KeyEvent.VK_M) {
						mode = GameMode.MAINMENU;
						repaint();
					}
				} else if (mode == GameMode.ENDSCREEN){ 
					theirShipBoard.reset();
					myShipBoard.reset();
					theirPegBoard.reset();
					myPegBoard.reset();
					myShots = 0;
					theirShots = 0;
					myHits = 0;
					theirHits = 0;
					if (key == KeyEvent.VK_SPACE) {
						mode = GameMode.HIGHSCORES;
						repaint();
					}
				} else if (mode == GameMode.HIGHSCORES){
					if (key == KeyEvent.VK_SPACE) {
						mode = GameMode.MAINMENU;
						repaint();
					}
				}
			}
		});
	} 

	public enum GameMode {
		MAINMENU, INSTRUCTIONS, PLAYING, ENDSCREEN, HIGHSCORES;
	}

	public int getMyShots() {
		return myShots;
	}

	public void addFile() {
		File file = new File("highscores.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Writer writer = null;
		try {
			writer = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(writer);
		this.bw = bw;
	}



	@Override
	public void paintComponent(Graphics gc) {
		switch(mode) {
		case ENDSCREEN:
			Menu.drawEndScreen(gc, playerWins, this);
			break;
		case MAINMENU:
			Menu.drawMenu(gc);
			break;
		case PLAYING:
			gc.setColor(new Color(13, 13, 77));
			gc.fillRect(0, 0, 750, 750);
			myPegBoard.draw(gc);
			myShipBoard.draw(gc);
			gc.setColor(Color.WHITE);
			gc.drawString("Shots Fired: " + myShots, 600, 200);
			gc.drawString("Hits: " + myHits, 600, 225);
			gc.drawString("Shots Fired: " + theirShots, 600, 550);
			gc.drawString("Hits: " + theirHits, 600, 575);
			break;
		case INSTRUCTIONS: 
			Menu.drawInstructions(gc);
			break;
		case HIGHSCORES:
			gc.setColor(new Color(13, 13, 77));
			gc.fillRect(0, 0, 750, 750);
			gc.setColor(Color.WHITE);
			Font f = new Font("rockwell", Font.BOLD, 36);
			gc.setFont(f);
			gc.drawString("HIGHSCORES", 300, 150);
			Font fon = new Font("rockwell", Font.BOLD, 24);
			gc.setFont(fon);
			if (newHighScore) {
				gc.drawString("YOU GOT A NEW HIGH SCORE!", 200, 50);
			}
			if (highscores.size() >= 5) {
				for (int i = 0; i < 5; i++) {
					gc.drawString("" + (i + 1) + ": " + highscores.get(i), 315, 200 + 50 * i);
				}
			} else {
				for (int i = 0; i < highscores.size(); i++) {
					gc.drawString("" + (i + 1) + ": " + highscores.get(i), 315, 200 + 50 * i);
				}
			}
		default:
			break;
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(750, 750);
	}
}
