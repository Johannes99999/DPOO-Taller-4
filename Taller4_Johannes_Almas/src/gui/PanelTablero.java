package gui;

import javax.swing.*;

import uniandes.dpoo.taller4.modelo.Tablero;

import java.awt.*;
import java.awt.event.*;

public class PanelTablero extends JPanel {
    private Tablero tablero;
    private Image image;
    private int size;
    private int dificultad = 5;


    public PanelTablero(int size, Tablero tablero) {
    	this.size = size;
    	this.tablero = tablero;
    	image = Toolkit.getDefaultToolkit().getImage("./data/luz.png");

        addMouseListener(new MouseListener());
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size * 50, size * 50);
    }

    class MouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            int rectWidth = getWidth() / size;
            int rectHeight = getHeight() / size;
            int x = e.getX() / rectWidth;
            int y = e.getY() / rectHeight;
            if (x >= 0 && x < size && y >= 0 && y < size) {
            	//Cambia el estado de las casillas necesarias
            	LightsOutGUI.jugar(x,y);
            	//Verifica, después de cada click, si terminó el juego
                if ( LightsOutGUI.tableroIluminado() )
                {
                	LightsOutGUI.gameCompleted();
                }
                LightsOutGUI.reloadBoard(size);
                repaint();
            }
        }
    }
        
    
    public void setBoardSize(int size)
    {
    	this.size = size;
    }
    
    public int getBoardSize()
    {
    	return this.size;
    }
    
    public void setDificultad(int dificultad)
    {
    	this.dificultad = dificultad;
    }
    
    public int getDificultad()
    {
    	return this.dificultad;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth() / size;
        int height = getHeight() / size;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tablero.darTablero()[i][j]) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.YELLOW);
                }
                //Dibuja las casillas
                g.fillRect(i * width, j * height, width, height);

                int imageWidth = image.getWidth(this);
                int imageHeight = image.getHeight(this);
                //Dibuja las imagenes en las casillas
                if (imageWidth > 0 && imageHeight > 0) {
                	//Para que el imagen no se agranda demasiado
                    int drawWidth = Math.min(width, imageWidth);
                    int drawHeight = Math.min(height, imageHeight);
                    g.drawImage(image, i * width + (width - drawWidth) / 2,
                            j * height + (height - drawHeight) / 2, drawWidth, drawHeight, null);
                }
                //Dibuja el borde de las casillas
                g.setColor(Color.GRAY);
                g.drawRect(i * width, j * height, width, height);
            }
        }
    }
}
