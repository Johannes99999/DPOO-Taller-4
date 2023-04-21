package gui;

import javax.swing.*;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

public class LightsOutGUI extends JFrame {
	
	private static LightsOutGUI instance = null;

    private static PanelBotones panelBotones;
    private static PanelTablero boardPanel;
    private static PanelEstado bottomPanel;
    private static PanelConfiguraciones settingsPanel;
    private static Tablero tablero;
    private static Top10 top10;


    private LightsOutGUI() {
        // Initialize the main frame
        setTitle("Lights Out Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        // Create instance of Tablero
        tablero = new Tablero(5);
        tablero.desordenar(5);

        // Create and add PanelBotones
        panelBotones = new PanelBotones();
        add(panelBotones, BorderLayout.NORTH);
        
        // Create and add PanelTablero
        boardPanel = new PanelTablero(5, tablero);
        add(boardPanel, BorderLayout.CENTER);
        
        // Create and add PanelEstado
        bottomPanel = new PanelEstado();
        add(bottomPanel, BorderLayout.SOUTH);

        // Create and add PanelConfiguraciones
        settingsPanel = new PanelConfiguraciones();
        add(settingsPanel, BorderLayout.EAST);
        
        // Create the top10 
        top10 = new Top10();
        top10.cargarRecords(new File("./data/top10.csv"));
        
        
        
        setVisible(true);
        changePlayer();

    }
    
    
    //Retorna el único objeto LightsOutGUI
    public static LightsOutGUI getInstance() {
        if (instance == null) {
            instance = new LightsOutGUI();
        }
        return instance; 
    }
    //Get para el PanelTablero
    public static PanelTablero getBoardPanel() {
        return boardPanel;
    }
    //Set para el PanelTablero
    public static void setBoardPanel(PanelTablero panel) {
    	boardPanel = panel;
    }
    //Retorna una lista de los top 10
    public static RegistroTop10[] darRegistros() {
        return top10.darRegistros().toArray(new RegistroTop10[top10.darRegistros().size()]);
    }
    //Abre la ventana con los top10
    public static void displayTop10()
    {
    	new FrameTop10(getInstance());
    }
    //Desordena otra vez el tablero, teniendo en cuenta la dificultad
    public static void newGame() {
    	int boardSize = boardPanel.getBoardSize();
    	int dificultad = boardPanel.getDificultad();
    	tablero = new Tablero(boardSize);
    	tablero.desordenar(dificultad*boardSize/2);
    	reloadBoard(boardSize);
       
    }
    //Repaint el tablero con información actualizada
    public static void reloadBoard(int tamaño)
    {
    	 bottomPanel.setJugadasText(Integer.toString(tablero.darJugadas()));
    	 getInstance().remove(boardPanel);
         boardPanel = new PanelTablero(tamaño, tablero);
         getInstance().add(boardPanel, BorderLayout.CENTER);
         getInstance().revalidate();
         getInstance().repaint();
    }
    //Reinicia el tablero
    public static void reiniciar() {
		tablero.reiniciar();
		reloadBoard(tablero.darTablero().length);
	}
    //Cambia el estado de las casillas
    public static void jugar(int fila, int columna)
    {
    	tablero.jugar(fila,columna);
    }
    //Retorna true si el tablero es apagado (en el taller dice que deberían estar iluminados, pero en el codigo cuando están apagados)
    public static Boolean tableroIluminado()
    {
    	return tablero.tableroIluminado();
    }
    //Actualiza el top10 y abre una ventana de felicitaciones
    public static void gameCompleted()
    {
    	top10.agregarRegistro(bottomPanel.getJugadorText(), tablero.calcularPuntaje());
    	try {
			top10.salvarRecords(new File("./data/top10.csv"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported coding");
			e.printStackTrace();
		}
    	
    	displayCongratulations();
       
    }
    //Crea una ventana de felicitaciones
    public static void displayCongratulations() {
    	String message;
        JFrame frame = new JFrame("Congratulations!");
        frame.setSize(500, 100);
        frame.setLocationRelativeTo(null);
        
        if (top10.esTop10(tablero.calcularPuntaje()))
        {
        	message = "Congratulations! You have completed the game and made high scores!";
        } else 
        {
        	message = "Congratulations! You have completed the game. Try again to make the high scores!";
        }

        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(480, 50)); 


        JPanel panel = new JPanel();
        panel.add(label);

        frame.add(panel);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                reiniciar();
            }
        });
    }
    //Cambia el nombre del jugador
    public static void changePlayer() {
		
		JFrame frame = new JFrame("Cambiar Jugador");
		try {
	        String nuevoJugador = JOptionPane.showInputDialog(frame, "Entra su nombre:");
	        bottomPanel.setJugadorText(nuevoJugador.substring(0, Math.min(nuevoJugador.length(), 3)).toUpperCase());
	    } catch (Exception e) {
	    }
	}
    //main
    public static void main(String[] args)
    {
    	LightsOutGUI.getInstance();
    }
}
