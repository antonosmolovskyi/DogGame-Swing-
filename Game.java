package thesis;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Game extends JFrame {
	private JButton left;
	private JButton right;
	private JButton up;
	private JButton down;
	private JPanel panel;
	private GridLayout grid = new GridLayout(11, 10, 1, 1);
	private JLabel[][] label = new JLabel[10][10];

	private int positionDogX = 0;
	private int positionDogY = 0;

	private int positionBombX = 0;
	private int positionBombY = 0;

	private int steps = 0;
	private int hp = 1000;

	Dog dog = new Dog(0, 0);
	Bomb bomb = new Bomb(1, 1);

	public Game() {
		
		
		add(init());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 850);
		initListener();
	}
private JPanel init() {
	panel = new JPanel();
	left = new JButton("Left");
	right = new JButton("Right");
	up = new JButton("Up");
	down = new JButton("Down");
	panel.setLayout(grid);

	for (int i = 0; i < label.length; i++) {
		for (int j = 0; j < label.length; j++) {
			if (i == 0 && j == 0) {
				label[i][j] = new JLabel(new ImageIcon("dog.png"));

				dog.setCurrentPositionX(i);
				dog.setCurrentPositionY(j);
				dog.setHp(hp);

			} else {
				label[i][j] = new JLabel(new ImageIcon("grass.jpg"));
				int randX = (int) (Math.random() * 10);
				int randY = (int) (Math.random() * 10);
				drawMonster(randX, randY);
			}
			panel.add(label[i][j]);
		}
	}
	panel.add(new JLabel(""));
	panel.add(new JLabel(""));
	panel.add(new JLabel(""));
	panel.add(left);
	panel.add(right);
	panel.add(up);
	panel.add(down);
	panel.add(new JLabel(""));
	panel.add(new JLabel(""));
	panel.add(new JLabel(""));
	return panel;
}

private JPanel makePanel() {
	JPanel panel = new JPanel();
	
	panel.setLayout(grid);

	for (int i = 0; i < label.length; i++) {
		for (int j = 0; j < label.length; j++) {
			panel.add(label[i][j]);
		}
	}
	panel.add(new JLabel(""));
	panel.add(new JLabel(""));
	panel.add(new JLabel(""));
	panel.add(left);
	panel.add(right);
	panel.add(up);
	panel.add(down);
	panel.add(new JLabel(""));
	panel.add(new JLabel(""));
	panel.add(new JLabel(""));
	return panel;
}
	public void connectionToMySql() {
		//ConnectionMySQL conn = new ConnectionMySQL();
			//try {
			//	conn.addPlayer("Anton", dog.getSteps());
				System.out.println(dog.toString());
			//} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				//e.printStackTrace();
	//		}
	}
	
	public void drawMonster(int randX, int randY ) {
//		bomb.setCurrentPositionX(randX);
//		positionBombX = randX;
//		bomb.setCurrentPositionY(randY);
//		positionBombY = randY;
//		label[randX][randY] = new JLabel(new ImageIcon("monster.jpg"));
	}

	public void stepsCounter() {
		hp--;
		steps++;
		dog.setSteps(steps);
		dog.setHp(hp);
	}

	public void rePaintPictures() {
		panel.setVisible(false);
		remove(panel);
		panel = makePanel();
		panel.setVisible(true);
		add(panel);
	}
	
	public void initListener() {

		left.addActionListener(new ActionListener() { // left

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dog.getCurrentPositionX() == 0) {
					label[positionDogX][positionDogY].setIcon(new ImageIcon("grass.jpg"));
					positionDogX = 9;
					label[positionDogX][positionDogY].setIcon(new ImageIcon("dogLeft.png"));

					dog.setCurrentPositionX(positionDogX);
				} else if (positionDogX == positionBombX--) {
					hp -= 10;
					label[positionDogX][positionDogY].setIcon(new ImageIcon("grass.jpg"));
					label[positionDogX--][positionDogY].setIcon(new ImageIcon("dogLeft.png"));

					int randX = (int) (Math.random() * 10);
					int randY = (int) (Math.random() * 10);

					if (randX != dog.getCurrentPositionX() || randY != dog.getCurrentPositionX()) {
						hp -= 10;
						drawMonster(randX, randY);
					}

					dog.setCurrentPositionX(positionDogX--);
				} else {
					label[positionDogX][positionDogY].setIcon(new ImageIcon("grass.jpg"));
					label[positionDogX--][positionDogY].setIcon(new ImageIcon("dogLeft.png"));
					dog.setCurrentPositionX(positionDogX);
				}
				if(dog.getHp() <= 0) {
					connectionToMySql();
				}
				System.out.println(dog.getCurrentPositionX());
				stepsCounter();
				rePaintPictures();
				System.out.println("Log:Dog:"+dog);
			}

		});
		right.addActionListener(new ActionListener() { // right

			@Override
			public void actionPerformed(ActionEvent e) {
		
					label[positionDogX][positionDogY].setIcon(new ImageIcon("grass.jpg"));
					label[positionDogX][positionDogY++].setIcon(new ImageIcon("dog.png"));
					dog.setCurrentPositionY(positionDogY);
				
				stepsCounter();
				if(dog.getHp() <= 0) {
					connectionToMySql();
				}
				rePaintPictures();
				System.out.println("Log:Dog:"+dog);
			}

		});
		up.addActionListener(new ActionListener() { // up

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dog.getCurrentPositionY() == 0) {
					label[positionDogX][positionDogY].setIcon(new ImageIcon("grass.jpg"));
					positionDogY = 9;
					label[positionDogX][positionDogY].setIcon(new ImageIcon("dog.png"));

					dog.setCurrentPositionY(positionDogY);
				} else if (positionDogY == positionBombY++) {
					hp -= 10;
					label[positionDogX][positionDogY].setIcon(new ImageIcon("grass.jpg"));
					label[positionDogX][positionDogY++].setIcon(new ImageIcon("dog.png"));

					int randX = (int) (Math.random() * 10);
					int randY = (int) (Math.random() * 10);

					if (randX != dog.getCurrentPositionX() || randY != dog.getCurrentPositionX()) {
						hp -= 10;
						drawMonster(randX, randY);
					}

					dog.setCurrentPositionY(positionDogY);
				} else {
					label[positionDogX][positionDogY].setIcon(new ImageIcon("grass.jpg"));
					label[positionDogX][positionDogY--].setIcon(new ImageIcon("dog.png"));
					dog.setCurrentPositionY(positionDogY);
				}
				stepsCounter();
			//	connectionToMySql();
				rePaintPictures();
				System.out.println("Log:Dog:"+dog);
			}

		});
		down.addActionListener(new ActionListener() { // down

			@Override
			public void actionPerformed(ActionEvent e) {

				if (dog.getCurrentPositionY() == 9) {
					label[positionDogX][positionDogY].setIcon(new ImageIcon("grass.jpg"));
					positionDogY = 0;
					label[positionDogX][positionDogY].setIcon(new ImageIcon("dogLeft.png"));

					dog.setCurrentPositionY(positionDogY);
				} else if (positionDogY == positionBombY) {
					hp -= 10;
					label[positionDogX][positionDogY].setIcon(new ImageIcon("grass.jpg"));
					label[positionDogX][positionDogY++].setIcon(new ImageIcon("dogLeft.png"));

					int randX = (int) (Math.random() * 10);
					int randY = (int) (Math.random() * 10);

					if (randX != dog.getCurrentPositionX() || randY != dog.getCurrentPositionX()) {
						hp -= 10;
						drawMonster(randX, randY);
					}

					dog.setCurrentPositionY(positionDogY);
				} else {
					label[positionDogX][positionDogY].setIcon(new ImageIcon("grass.jpg"));
					label[positionDogX][positionDogY++].setIcon(new ImageIcon("dogLeft.png"));

					dog.setCurrentPositionY(positionDogY);
				}
				stepsCounter();
				if(dog.getHp() <= 0) {
					connectionToMySql();
				}
				rePaintPictures();
				System.out.println("Log:Dog:"+dog);
			}

		});
	}
}
