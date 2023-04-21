package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import uniandes.dpoo.taller4.modelo.RegistroTop10;

public class FrameTop10 extends JDialog{
	
	private RegistroTop10[] top10;
	
	
	public FrameTop10(JFrame parent)
	{
		super(parent, "Top 10", true);
		
		top10 = LightsOutGUI.darRegistros();
		
		setTitle("Top 10");
		setSize(220, 260); 
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		
		JLabel title = new JLabel("#  Nombre");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.CENTER);
		add(title, BorderLayout.NORTH);

		
		//Hace una lista de los elementos de la forma dos JLabels en 
		//JPanels diferente dentro de otro JPanel
        DefaultListModel<JPanel> listModel = new DefaultListModel<>();
		for (int i = 0; i < top10.length; i++) {
			
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1,2));
			
			JPanel rank = new JPanel();
			JPanel player = new JPanel();
			
			rank.setPreferredSize(new Dimension(20, 20)); 
			player.setPreferredSize(new Dimension(80, 20)); 
			
			
			
			rank.add(new JLabel(Integer.toString(i+1)));
			player.add(new JLabel(top10[i].toString()));
			
			panel.add(rank);
			panel.add(player);
			
			listModel.addElement(panel);
			
		}
		
		//Agrega la lista a un JList
		JList<JPanel> top10List = new JList<>(listModel);
		top10List.setBackground(getBackground());
		top10List.setCellRenderer(new JPanelRenderer());
		top10List.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        //Agrega el JList a un scrollPane
		JScrollPane scrollPane = new JScrollPane(top10List);
		add(scrollPane, BorderLayout.CENTER);
		
		setVisible(true);
		
	}
	
	
	public class JPanelRenderer extends JPanel implements ListCellRenderer<JPanel> {

	    private Font goldFont;
	    private Font silverFont;
	    private Font bronzeFont;
	    private Font defaultFont;

	    public JPanelRenderer() {
	        goldFont = new Font("Brush Script MT", Font.BOLD + Font.ITALIC, 15);
	        silverFont = new Font("Agency FB", Font.BOLD, 14);
	        bronzeFont = new Font("Bauhaus 93", Font.ITALIC, 13);
	        defaultFont = new Font("Arial", Font.PLAIN, 12);
	    }

	    @Override
	    public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel value, int index, boolean isSelected, boolean cellHasFocus) {
	        // Set background color based on selection
	        if (isSelected) {
	            setBackground(list.getSelectionBackground());
	        } else {
	            setBackground(list.getBackground());
	        }

	        JLabel rankLabel = (JLabel) ((JPanel) value.getComponent(0)).getComponent(0);
	        JLabel playerLabel = (JLabel) ((JPanel) value.getComponent(1)).getComponent(0);

	        // Set font based on index
	        if (index == 0) {
	            rankLabel.setForeground(new Color(184, 134, 11));
	            rankLabel.setFont(goldFont);
	            playerLabel.setForeground(new Color(184, 134, 11));
	            playerLabel.setFont(goldFont);
	        } else if (index == 1) {
	            rankLabel.setForeground(new Color(192, 192, 192));
	            rankLabel.setFont(silverFont);
	            playerLabel.setForeground(new Color(192, 192, 192));
	            playerLabel.setFont(silverFont);
	        } else if (index == 2) {
	            rankLabel.setForeground(new Color(205, 127, 50));
	            rankLabel.setFont(bronzeFont);
	            playerLabel.setForeground(new Color(205, 127, 50));
	            playerLabel.setFont(bronzeFont);
	        } else {
	            rankLabel.setForeground(list.getForeground());
	            rankLabel.setFont(defaultFont);
	            playerLabel.setForeground(list.getForeground());
	            playerLabel.setFont(defaultFont);
	        }

	        removeAll();
	        add(value);

	        return this;
	    }
	}
}


