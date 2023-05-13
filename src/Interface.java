import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Interface extends Canvas implements Runnable{

	private Thread thread;

	private final int HEIGH = 400;
	private final int WIDTH = 800;
	private Dimension d = new Dimension(WIDTH,HEIGH);
	private JFrame frame;
	
	private boolean isRunning;
	private BufferedImage image;
	private Aposta a = new Aposta();
	
	public Interface(){
		
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
//		frame.addKeyListener(KeyPad);
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
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGH);

		
		
		g.setFont(new Font("Arial",Font.PLAIN,12));
		g.setColor(Color.WHITE);
		g.drawString("vetorResultado",20,40);

		for(int i = 0;i < a.res.length; i++) {
		g.drawString(Integer.toString(a.res[i]),20+(20*i),60);
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
}
