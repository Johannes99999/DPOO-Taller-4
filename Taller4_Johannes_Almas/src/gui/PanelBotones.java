package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelBotones extends JPanel implements ActionListener {
	
	private JComboBox<String> boardSizeComboBox;
	private ButtonGroup difficultyButtonGroup;
    private JRadioButton easyRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton hardRadioButton;
	
	public PanelBotones()
	{
		// Inicializa PanelBotones
	    setLayout(new FlowLayout());
	    setBackground(Color.BLUE);
	    
	    // Add the Tamaño label
	    JLabel sizeLabel = new JLabel("Tamaño:");
	    sizeLabel.setForeground(Color.WHITE); 
	    add(sizeLabel);

	    // Add the board size combo box
	    String[] boardSizeOptions = {"5x5", "7x7", "10x10"};
	    boardSizeComboBox = new JComboBox<>(boardSizeOptions);
	    boardSizeComboBox.setPreferredSize(new Dimension(80, 20));
	    boardSizeComboBox.setBackground(Color.WHITE);
	    add(boardSizeComboBox);
	    boardSizeComboBox.addActionListener(this);
	    
	    // Add the Dificultad label
	    JLabel dificultadLabel = new JLabel("Dificultad:");
	    dificultadLabel.setForeground(Color.WHITE); 
	    add(dificultadLabel);
	    
	    // Create the difficulty radio buttons and add them to the panel
        difficultyButtonGroup = new ButtonGroup();
        
        easyRadioButton = new JRadioButton("Fácil", true);
        easyRadioButton.setBackground(Color.BLUE);
        easyRadioButton.setForeground(Color.WHITE);
        easyRadioButton.setActionCommand("Fácil");
        add(easyRadioButton);
        difficultyButtonGroup.add(easyRadioButton);
        easyRadioButton.addActionListener(this);
        
        mediumRadioButton = new JRadioButton("Medio");
        mediumRadioButton.setBackground(Color.BLUE);
        mediumRadioButton.setForeground(Color.WHITE);
        mediumRadioButton.setActionCommand("Medio");
        add(mediumRadioButton);
        difficultyButtonGroup.add(mediumRadioButton);
        mediumRadioButton.addActionListener(this);
        
        hardRadioButton = new JRadioButton("Difícil");
        hardRadioButton.setBackground(Color.BLUE);
        hardRadioButton.setForeground(Color.WHITE);
        hardRadioButton.setActionCommand("Difícil");
        add(hardRadioButton);
        difficultyButtonGroup.add(hardRadioButton);
        hardRadioButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getSource() == boardSizeComboBox) {
    		String selectedSize = (String) boardSizeComboBox.getSelectedItem();
            int size = Integer.parseInt(selectedSize.split("x")[0]);
            LightsOutGUI.getBoardPanel().setBoardSize(size);
            LightsOutGUI.newGame();
	    } else if (e.getSource() == easyRadioButton) {
	    	LightsOutGUI.getBoardPanel().setDificultad(1);
	    } else if (e.getSource() == mediumRadioButton) {
	    	LightsOutGUI.getBoardPanel().setDificultad(2);
	    } else if (e.getSource() == hardRadioButton) {
	    	LightsOutGUI.getBoardPanel().setDificultad(3);
	    }
    	
    	
    }
}