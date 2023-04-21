package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelConfiguraciones extends JPanel implements ActionListener{
	
	JButton newGameButton;
	JButton restartGameButton;
	JButton topTenButton;
	JButton changePlayerButton;
	
	public PanelConfiguraciones()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//Los glue para centralizar los botones
		add(Box.createVerticalGlue());
		//Agrega los botones
		newGameButton = new JButton("NUEVO");
		newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(this);
        newGameButton.setBackground(Color.BLUE);
		newGameButton.setForeground(Color.WHITE);
		newGameButton.setMaximumSize(new Dimension(500, 25));
		newGameButton.setFocusPainted(false);
        add(newGameButton);
        add(Box.createRigidArea(new Dimension(0, 15)));

        restartGameButton = new JButton("REINICIAR");
        restartGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartGameButton.addActionListener(this);
        restartGameButton.setBackground(Color.BLUE);
		restartGameButton.setForeground(Color.WHITE);
		restartGameButton.setMaximumSize(new Dimension(500, 25));
		restartGameButton.setFocusPainted(false);
        add(restartGameButton);
        add(Box.createRigidArea(new Dimension(0, 15)));

        topTenButton = new JButton("TOP-10");
        topTenButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        topTenButton.addActionListener(this);
        topTenButton.setBackground(Color.BLUE);
		topTenButton.setForeground(Color.WHITE);
		topTenButton.setMaximumSize(new Dimension(500, 25));
		topTenButton.setFocusPainted(false);
        add(topTenButton);
        add(Box.createRigidArea(new Dimension(0, 15)));

        changePlayerButton = new JButton("CAMBIAR JUGADOR");
        changePlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePlayerButton.addActionListener(this);
        changePlayerButton.setBackground(Color.BLUE);
        changePlayerButton.setForeground(Color.WHITE);
		changePlayerButton.setMaximumSize(new Dimension(500, 25));
		changePlayerButton.setFocusPainted(false);
        add(changePlayerButton);
        
        add(Box.createVerticalGlue());
        
       
	}
	
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == newGameButton) {
	    	LightsOutGUI.newGame();
	    } else if (e.getSource() == restartGameButton) {
	    	LightsOutGUI.reiniciar();
	    } else if (e.getSource() == topTenButton) {
	        LightsOutGUI.displayTop10();
	    } else if (e.getSource() == changePlayerButton) {
	        LightsOutGUI.changePlayer();
	    }
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, super.getPreferredSize().height);
	}
}
