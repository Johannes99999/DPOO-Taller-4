package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelEstado extends JPanel{
	
	private JTextField jugadasTextField;
	private JTextField jugadorTextField;
	
	public PanelEstado()
	{
		setLayout(new GridLayout(1,4));
		
		// Add the Jugadas label
		add(new JLabel("Jugadas:"));
		
		// Add the Jugadas text field
		jugadasTextField = new JTextField("0", 5);
		jugadasTextField.setEditable(false);
		add(jugadasTextField);
    
		// Add the Jugador label
		add(new JLabel("Jugador:"));
		
		//Add the Jugador text field
		jugadorTextField = new JTextField("", 5);
		jugadorTextField.setEditable(false);
		add(jugadorTextField);
		
	}
		
	public String getJugadasText() {
		return jugadasTextField.getText();
	}
	
	public void setJugadasText(String text) {
		jugadasTextField.setText(text);
	}
	
	public String getJugadorText() {
		return jugadorTextField.getText();
	}
	
	public void setJugadorText(String text) {
		jugadorTextField.setText(text);
	}
}
