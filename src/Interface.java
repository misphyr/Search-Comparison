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
public class Interface extends Canvas implements Runnable {

	private Thread thread;

	private final int HEIGH = 720;
	private final int WIDTH = 1280;
	private Dimension d = new Dimension(WIDTH, HEIGH);
	private JFrame frame;

	private boolean isRunning;
	private BufferedImage image;

	private static Teclas Keys = new Teclas();
	public static Aposta a;
	public static int numJog = 1;

	public Interface(Aposta a) {
		Interface.a = a;
		setPreferredSize(d);
		initFrame();
		image = new BufferedImage(WIDTH, HEIGH, BufferedImage.TYPE_INT_RGB);

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
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGH);

		g.setColor(Color.black);
		g.setFont(new Font("Arial", 40, 16));

		if (numJog == 0) {
			try (BufferedReader br = new BufferedReader(new FileReader("resultadoAposta.txt"))) {
				g.setColor(Color.black);
				g.setFont(new Font("Arial", 40, 20));
				String linha = br.readLine();
				if (linha != null) {
					g.drawString(linha, xMargem, yMargem);
				}
				for (int i = 0; i < Aposta.QNTJ; i++) {
					try (BufferedReader tst = new BufferedReader(new FileReader("Jogador" + (i + 1) + ".txt"))) {
						String line;
						String sortWord = "";
						while ((line = tst.readLine()) != null) {
							if (line.startsWith("Contador BS")) {
								g.drawString("Jogador " + (i + 1) + ": " + line, xMargem, yMargem + 40 + (20 * i));

							} else if (line.endsWith("Sort")) {
								sortWord = line;
							} else if (line.startsWith("Contador BB")) {

								g.drawString(sortWord + " -", xMargem + 400, yMargem + 40 + (20 * i));
								g.drawString(line, xMargem + 520, yMargem + 40 + (20 * i));
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String fileName = "jogador" + numJog + ".txt";
			try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
				int linha = 0;
				String line;
				while ((line = br.readLine()) != null) {
					char[] chara = line.toCharArray();
					// Se começar com um número:
					if (line.startsWith("0") || line.startsWith("1") || line.startsWith("2") || line.startsWith("3")
							|| line.startsWith("4") || line.startsWith("5") || line.startsWith("6")
							|| line.startsWith("7") || line.startsWith("8") || line.startsWith("9")) {
						int xchar = 0;
						for (int i = 0; i < chara.length - 1; i++) {

							if (chara[i] == '|') {
								chara[i] = '-';
							}

							g.setColor(Color.DARK_GRAY);
							g.setFont(new Font("Arial", 40, 16));
							g.drawString(String.valueOf(chara[i]), xMargem + (tamLetra * xchar),
									yMargem + (20 * linha));
							xchar++;
							if (xchar > 115) {
								linha++;
								xchar = 0;
							}

						}

					} else {
						g.setColor(Color.black);
						g.setFont(new Font("Arial", 40, 20));
						g.drawString(line, xMargem, yMargem + (20 * linha));
					}
					linha++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Fim da render
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGH, null);
		bs.show();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
			}

		}
		stop();
	}

	void teste() {
		a.temArquivo = true;
	}
}

class Teclas extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		int a = e.getKeyCode();
		if (a == KeyEvent.VK_RIGHT) {
			Interface.numJog++;
		}
		if (a == KeyEvent.VK_LEFT) {
			Interface.numJog--;
		}
		if (Interface.numJog > Aposta.QNTJ) {
			Interface.numJog = Aposta.QNTJ;
		}
		if (Interface.numJog < 0) {
			Interface.numJog = 0;
		}

		if (a == KeyEvent.VK_1) {
			Interface.a.preencheResultado();
			Interface.a.adicionaJogadores();
		}

		if (a == KeyEvent.VK_3) {
			System.exit(0);
		}

		if (a == KeyEvent.BUTTON1_DOWN_MASK) {
			System.out.println("Teste");
		}

	}
}