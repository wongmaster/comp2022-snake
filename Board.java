import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    public static final Graphics g = null;
	private Timer timer;
    private Score score;
    private Snake snakeCabeca;
    private Lista inicio1;
    private Lista lista;
    private Snake bodyCobra;
    private int fritasY;
    private int fritasX;
    private Image body;
    private Image cabeca;
    private Image fries;
    private Image fundo;
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;
    private int dots;
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
   
    private boolean isPlaying = true;
    

    private Font font;
       
    public Board() {

        addKeyListener(new TAdapter());
        
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);
        bFritas();
       // add(bodyCobra);
        score = new Score();
        add(score);       
        snakeCabeca = new Snake();
        add(snakeCabeca);
       lista = new Lista();
       
        timer = new Timer(170, this);
        timer.start();
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        inicializatGame();
        
    }

    
    private void inicializatGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        bFritas();

        timer = new Timer(170, this);
        timer.start();
        
    }

    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g; 
        //g2d.drawImage(fundo, 0,0,this);
       
        
       
       g2d.drawImage(cabeca, snakeCabeca.getX(), snakeCabeca.getY(),this );
       g2d.drawImage(fries, fritasX, fritasY, this);
       // g2d.drawImage(cabeca, snakeCabeca.getX(), snakeCabeca.getY(), 50, 55, this);
       
      if (lista.getInicio1() != null){
           Lista aux = lista.getInicio1();
           g2d.drawImage(body, aux.getX(), aux.getY(),this );
           
           while (aux.getProximo() != null) {
        	   
        	   aux = aux.getProximo();
        	   g2d.drawImage(body, aux.getX(), aux.getY(),this );
               
             
             
       }
      }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
       
      
        }


    public void paintIntro(Graphics g) {
    	
        if(isPlaying){
            isPlaying = false;
            Graphics2D g2d = (Graphics2D) g;
            try{
                File file = new File("fonts/VT323-Regular.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, file);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                font = font.deriveFont(Font.PLAIN,40);
                g2d.setFont(font);
            }catch (Exception e){
                System.out.println(e.toString());
            }   
            g2d.drawString("S N A K E: " + this.score, 300, 300);
        }	
     
    }  
    
    
    private void loadImages() {

        ImageIcon iid = new ImageIcon("images/body.png");
        body = iid.getImage();

        ImageIcon iia = new ImageIcon("images/fries.png");
        fries = iia.getImage();

        ImageIcon iih = new ImageIcon("images/head.png");
        cabeca = iih.getImage();
        
        ImageIcon iib = new ImageIcon("images/desingn.png");
        fundo = iib.getImage();
    }
    
    public void actionPerformed(ActionEvent e) {   
    	 if (isPlaying) {
    		 
             verificaFritas();
             verificarColisao();
             repaint();  
             snakeCabeca.move();
             
             if (snakeCabeca.getX() >= fritasX -10 && snakeCabeca.getX() <= fritasX + 10  &&
            		 snakeCabeca.getY() >= fritasY -10 && snakeCabeca.getY() <= fritasY + 10){
            	 bFritas();
            	 add(lista.inserirFinal(new Lista()));
                 
            	 score.addScore(1);
            	 
            		 
             }
             
             if (lista.getInicio1() != null){
                 Lista aux = lista.getInicio1();
                 
                 int rex = aux.getX();
                 int rey = aux.getY();
                 int reX1 = snakeCabeca.getX();
                 int reY1 = snakeCabeca.getY();
                 aux.setX(reX1);
                 aux.setY(reY1);
                 reX1 = rex;
                 reY1 = rey;
                 
                 
                 while (aux.getProximo() != null) {
              	   
                	 
                     aux = aux.getProximo();
                     rex = aux.getX();
                     rey = aux.getY();
                     aux.setX(reX1);
                     aux.setY(reY1);
                     reX1 = rex;
                     reY1 = rey;
                     
                 
               
             }
            }
         }
        
        
        
        
    }

    
    private void verificaFritas() {

        if ((x[0] == fritasX) && (y[0] == fritasY)) {

            dots++;
            bFritas();
        }
    }

private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 16);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    
private void pausarJogo(Graphics g) {
    
    String msg = "PAUSADO";
    Font small = new Font("Helvetica", Font.BOLD, 16);
    FontMetrics metr = getFontMetrics(small);

    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
}
    
    private void bFritas() {

        int r = (int) (Math.random() * RAND_POS);
        fritasX = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        fritasY = ((r * DOT_SIZE));
    }
    

  private void verificarColisao(){
	  for (int z = dots; z > 0; z--) {

          if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
              isPlaying = false;
          }
      }

      if (y[0] >= B_HEIGHT) {
    	  isPlaying = false;
      }

      if (y[0] < 0) {
    	  isPlaying = false;
      }

      if (x[0] >= B_WIDTH) {
    	  isPlaying = false;
      }

      if (x[0] < 0) {
    	  isPlaying = false;
      }
      
      if(!isPlaying) {
          timer.stop();
      }
    }
 
  
    private class TAdapter extends KeyAdapter {
    		
    	
        public void keyPressed(KeyEvent e) {
           
            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                	inicializatGame();
                    break;
                    
                case KeyEvent.VK_LEFT:
                	snakeCabeca.setDx(-30);
                	snakeCabeca.setDy(0);
                	 /*leftDirection = true;
                     upDirection = false;
                     downDirection = false;*/
                    break;
                    
                case KeyEvent.VK_RIGHT:
                	snakeCabeca.setDx(30);
                	snakeCabeca.setDy(0);
                	/*rightDirection = true;
                	upDirection = false;
                	downDirection = false;*/
                    break;
                    
                case KeyEvent.VK_UP:
                	snakeCabeca.setDx(0);
                	snakeCabeca.setDy(-30);
                	/*upDirection = true;
                    rightDirection = false;
                    leftDirection = false;*/
                    break;
                    
                case KeyEvent.VK_DOWN:
                	snakeCabeca.setDx(0);
                	snakeCabeca.setDy(30);
                	/*downDirection = true;
                    rightDirection = false;
                    leftDirection = false;*/
                    break;
                    
                case KeyEvent.VK_P:
                	isPlaying = false;
                	repaint();
                	timer.stop();
                	//pausarJogo(g);
                	break;
                	
                	
                case KeyEvent.VK_R:
                	isPlaying = true;
                	inicializatGame();
                	timer.start();
                	break;
                	
                	
                	
            }
            
        }
    }
    
  
  
    
    
    
}