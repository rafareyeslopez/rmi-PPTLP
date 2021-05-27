import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Player extends JPanel {

	private ImageIcon rock = new ImageIcon (GameClient.class.getResource("Piedra.png"));
	private ImageIcon paper = new ImageIcon (GameClient.class.getResource("Papel.png"));
	private ImageIcon scissors = new ImageIcon (GameClient.class.getResource("Tijeras.png"));
        private ImageIcon lizard = new ImageIcon (GameClient.class.getResource("Lagarto.png"));
	private ImageIcon spock = new ImageIcon (GameClient.class.getResource("Spock.png"));

        private static final long serialVersionUID = 1L;
	private FlowLayout layout = new FlowLayout();
	//private Board shipBoard;
        private JButton lizardB, paperB, rockB, scissorsB, spockB;
	private String playerID, name, pass;
	
	public Player(String Name, GameClient game) {

		name = Name;
                // Crea el tablero con las opciones
                setLayout(new FlowLayout());
                rockB = new JButton ();
                rockB.setIcon(rock);
                rockB.setActionCommand("rock");
                rockB.addActionListener(game);
                add(rockB);
                paperB = new JButton ();
                paperB.setIcon(paper);
                paperB.setActionCommand("paper");
                paperB.addActionListener(game);
                add(paperB);
                scissorsB = new JButton ();
                scissorsB.setIcon(scissors);
                scissorsB.setActionCommand("scissors");
                scissorsB.addActionListener(game);
                add(scissorsB);
                lizardB = new JButton ();
                lizardB.setIcon(lizard);
                lizardB.setActionCommand("lizard");
                lizardB.addActionListener(game);
                add(lizardB);
                spockB = new JButton ();
                spockB.setIcon(spock);
                spockB.setActionCommand("spock");
                spockB.addActionListener(game);
                add(spockB);
	}

	public void block() { 
            rockB.setEnabled(false);
            paperB.setEnabled(false);
            scissorsB.setEnabled(false);
            lizardB.setEnabled(false);
            spockB.setEnabled(false);
        }
        
	public void unBlock() { 
            rockB.setEnabled(true);
            paperB.setEnabled(true);
            scissorsB.setEnabled(true);
            lizardB.setEnabled(true);
            spockB.setEnabled(true);
        }
        
        public String getData (String title) { 
            
            String data = "";
            String s = JOptionPane.showInputDialog (title);
            if ((s != null) && (!s.equals(""))) data = s; 
            else { 
                System.out.println("El jugador debe identificarse");
                System.exit(0);
            }
            return data;
        }
                
	
}
