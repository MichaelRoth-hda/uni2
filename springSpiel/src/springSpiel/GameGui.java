package springSpiel;

import java.util.Vector;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
//import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.JTextPane;
//import javax.swing.JTextField;
import javax.swing.JTextArea;
//import ai.Ai;

public class GameGui {

	Feld gameField = new Feld();
	String selected = "LINKS";
	String output = "";
	String savedOutput = "";
	Feld manSaved = new Feld();

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGui window = new GameGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public GameGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 852, 1262);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton[][] btn;
		btn = new JButton[7][7];

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if ((i < 2 || i > 4) && (j < 2 || j > 4)) {
					btn[i][j] = null;
					// System.out.print("0");
				} else {
					btn[i][j] = new JButton("" + i + j);
					btn[i][j].setBounds((25 + (j * 80)), (5 + (i * 40)), 60, 25);
					frame.getContentPane().add(btn[i][j]);
				}
			}
		}

		JPanel panel = new JPanel();
		panel.setBounds(15, 283, 492, 43);
		frame.getContentPane().add(panel);

		JRadioButton rdbtnLINKS = new JRadioButton("LINKS");

		rdbtnLINKS.setSelected(true);
		panel.add(rdbtnLINKS);

		JRadioButton rdbtnOBEN = new JRadioButton("OBEN");

		panel.add(rdbtnOBEN);

		JRadioButton rdbtnUNTEN = new JRadioButton("UNTEN");
		panel.add(rdbtnUNTEN);

		JRadioButton rdbtnRECHTS = new JRadioButton("RECHTS");
		panel.add(rdbtnRECHTS);

		JTextArea outputPane = new JTextArea();
		outputPane.setEditable(false);
		outputPane.setBounds(15, 338, 492, 879);
		frame.getContentPane().add(outputPane);

		JButton btnSave = new JButton("Speichern");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manSaved =  gameField.copy();
				savedOutput = output;				
			}
		});
		btnSave.setBounds(519, 468, 117, 25);
		frame.getContentPane().add(btnSave);

		JButton btnLoad = new JButton("Laden");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameField = manSaved.copy();
				output = savedOutput;
				
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
			}
		});
		btnLoad.setBounds(652, 468, 117, 25);
		frame.getContentPane().add(btnLoad);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(519, 505, 250, 25);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnSolve = new JButton("solve");
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Solver solver = new Solver(gameField);
				try {
					solver.worldRecord();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
			}
		});
		btnSolve.setBounds(519, 301, 250, 25);
		frame.getContentPane().add(btnSolve);

		btn[0][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(0, 2, selected)) {
					output = output + "\n" + selected + " (0,2)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btn[0][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(0, 3, selected)) {
					output = output + "\n" + selected + " (0,3)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btn[0][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(0, 4, selected)) {
					output = output + "\n" + selected + " (0,4)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btn[1][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(1, 2, selected)) {
					output = output + "\n" + selected + " (1,2)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btn[1][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(1, 3, selected)) {
					output = output + "\n" + selected + " (1,3)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btn[1][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(1, 4, selected)) {
					output = output + "\n" + selected + " (1,4)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btn[2][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(2, 0, selected)) {
					output = output + "\n" + selected + " (2,0)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[2][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(2, 1, selected)) {
					output = output + "\n" + selected + " (2,1)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[2][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(2, 2, selected)) {
					output = output + "\n" + selected + " (2,2)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[2][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(2, 3, selected)) {
					output = output + "\n" + selected + " (2,3)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[2][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(2, 4, selected)) {
					output = output + "\n" + selected + " (2,4)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[2][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(2, 5, selected)) {
					output = output + "\n" + selected + " (2,5)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[2][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(2, 6, selected)) {
					output = output + "\n" + selected + " (2,6)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btn[3][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(3, 0, selected)) {
					output = output + "\n" + selected + " (3,0)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[3][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(3, 1, selected)) {
					output = output + "\n" + selected + " (3,1)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[3][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(3, 2, selected)) {
					output = output + "\n" + selected + " (3,2)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[3][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(3, 3, selected)) {
					output = output + "\n" + selected + " (3,3)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[3][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(3, 4, selected)) {
					output = output + "\n" + selected + " (3,4)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[3][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(3, 5, selected)) {
					output = output + "\n" + selected + " (3,5)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[3][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(3, 6, selected)) {
					output = output + "\n" + selected + " (3,6)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btn[4][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(4, 0, selected)) {
					output = output + "\n" + selected + " (4,0)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[4][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(4, 1, selected)) {
					output = output + "\n" + selected + " (4,1)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[4][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(4, 2, selected)) {
					output = output + "\n" + selected + " (4,2)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[4][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(4, 3, selected)) {
					output = output + "\n" + selected + " (4,3)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[4][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(4, 4, selected)) {
					output = output + "\n" + selected + " (4,4)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[4][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(4, 5, selected)) {
					output = output + "\n" + selected + " (4,5)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[4][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(4, 6, selected)) {
					output = output + "\n" + selected + " (4,6)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btn[5][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(5, 2, selected)) {
					output = output + "\n" + selected + " (5,2)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[5][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(5, 3, selected)) {
					output = output + "\n" + selected + " (5,3)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[5][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(5, 4, selected)) {
					output = output + "\n" + selected + " (5,4)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btn[6][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(6, 2, selected)) {
					output = output + "\n" + selected + " (6,2)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[6][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(6, 3, selected)) {
					output = output + "\n" + selected + " (6,3)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});
		btn[6][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameField.jump(6, 4, selected)) {
					output = output + "\n" + selected + " (6,4)";
				}
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						if ((i < 2 || i > 4) && (j < 2 || j > 4)) {

						} else {
							if (gameField.isFree(i, j)) {
								btn[i][j].setText("");
							} else {
								btn[i][j].setText("O");
							}
						}
					}
				}
				outputPane.setText(output);
			}
		});

		rdbtnLINKS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				rdbtnLINKS.setSelected(true);
				rdbtnOBEN.setSelected(false);
				rdbtnUNTEN.setSelected(false);
				rdbtnRECHTS.setSelected(false);
				selected = "LINKS";
				// btn02.setText(selected);
			}
		});
		rdbtnOBEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				rdbtnLINKS.setSelected(false);
				rdbtnOBEN.setSelected(true);
				rdbtnUNTEN.setSelected(false);
				rdbtnRECHTS.setSelected(false);
				selected = "HOCH";
				// btn02.setText(selected);
			}
		});
		rdbtnUNTEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				rdbtnLINKS.setSelected(false);
				rdbtnOBEN.setSelected(false);
				rdbtnUNTEN.setSelected(true);
				rdbtnRECHTS.setSelected(false);
				selected = "RUNTER";
				// btn02.setText(selected);
			}
		});
		rdbtnRECHTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				rdbtnLINKS.setSelected(false);
				rdbtnOBEN.setSelected(false);
				rdbtnUNTEN.setSelected(false);
				rdbtnRECHTS.setSelected(true);
				selected = "RECHTS";
				// btn02.setText(selected);
			}
		});
	}
}
