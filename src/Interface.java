import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Interface extends Canvas implements Runnable{

	private Thread thread;

	private final int HEIGH = 720;
	private final int WIDTH = 1280;
	private Dimension d = new Dimension(WIDTH,HEIGH);
	private JFrame frame;
	
	private boolean isRunning;
	private BufferedImage image;

	private static Teclas Keys = new Teclas();
	public static Aposta a;
	public static int numJog = 1;
	public Interface(Aposta a){
		Interface.a = a;
		setPreferredSize(d);
		initFrame();
		image = new BufferedImage(WIDTH,HEIGH,BufferedImage.TYPE_INT_RGB);
         
	}

	public void initFrame() {
		frame = new JFrame("Trabalho de ED2A3");
		frame.add(this);
		frame.setMinimumSize(d);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.isFocused();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.addKeyListener(Keys);

		System.out.print("Apertando 1");
		}

	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
		
	}
	
	public synchronized void stop() {
			isRunning = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	}

	public void tick() {

	}
	
	int xMargem = 40;
	int yMargem = 30;
	int tamLetra = 10;
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGH);

		g.setColor(Color.black);
    	g.setFont(new Font("Arial", 40,16));

		if(numJog == 0) {
			 try (BufferedReader br = new BufferedReader(new FileReader("resultadoAposta.txt"))) {
				 g.setColor(Color.black);
			    	g.setFont(new Font("Arial", 40,20));
				 g.drawString(br.readLine(), xMargem ,yMargem );
	            	
			 }
			 catch(IOException e) {
					e.printStackTrace();
					}
		}else {
				String fileName = "jogador" + numJog + ".txt";
		        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
					int linha = 0;
		            String line;
		            while ((line = br.readLine()) != null) {
		            	char[] chara = line.toCharArray();
		            	//Se começar com um número:
		            	if(line.toCharArray().length > 0 && (line.toCharArray()[0] >= '0' || line.toCharArray()[0] <= '9')) {
		            		int xchar = 0;
			            	for(int i = 0; i < chara.length;i++) {
			            		
			            		if(chara[i] == '|') {
			            			chara[i] = '´';
			            		}
			            		
			            	g.drawString(String.valueOf(chara[i]), xMargem + (tamLetra * xchar), yMargem + (20 * linha));
			            	if(xchar > 115) {
			            		linha++;
			            		xchar=0;
			            	}
			            	xchar++;
		            		}
			            	
		            	}else {
		            		g.drawString(line, xMargem, yMargem + (20 * linha));
		            	}
		            	linha++;
		            }
				} catch (IOException e) {
				e.printStackTrace();
			}
		        }
		//Fim da render
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image,0,0,WIDTH, HEIGH,null);
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
		long now = 	System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
			
		}
		stop();
	}
	
	void teste() {
		a.temArquivo = true;
	}
}

class Teclas extends KeyAdapter{
		
	@Override
	public void keyPressed(KeyEvent e) {

		int a = e.getKeyCode();
		if(a == KeyEvent.VK_RIGHT) {
			Interface.numJog++;
		}
		if(a == KeyEvent.VK_LEFT) {
			Interface.numJog--;
		}
		if(Interface.numJog > Aposta.QNTJ) {
			Interface.numJog = Aposta.QNTJ;
		}
		if(Interface.numJog < 0) {
			Interface.numJog = 0;
		}
		
		if(a == KeyEvent.VK_1) {
			Interface.a.preencheResultado();
			Interface.a.adicionaJogadores();
		}
		if(a == KeyEvent.VK_2) {
			System.exit(0);
		}
	
	}
}