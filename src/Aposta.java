import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Aposta {

	public final static int Limite = 100;
	protected static int tamRes = 100;
	protected static int[] res = new int[tamRes];
	protected static int[] sub = new int[tamRes];
	public static final int QNTJ = 4;
	private int[][] notFound = new int[QNTJ][tamRes];
	public boolean temArquivo = false;

	Aposta() {
		verificaArquivo();

		if (!temArquivo) {
			preencheResultado();
			adicionaJogadores();
		}
	}

	private void verificaArquivo() {

		for (int i = 0; i < QNTJ; i++) {
			File f = new File("Jogador" + (i + 1) + ".txt");
			if (f.isFile()) {
				temArquivo = true;
			}
		}

	}

	Jogador[] j = new Jogador[QNTJ];

	void adicionaJogadores() {
		// Jogador(int[] vet, int x)
		// vet = vetor solução
		// x = Tipo de Sort:
		// 1 = Quick / 2 = Shell / 3 = Select //

		for (int i = 0; i < QNTJ; i++) {

			j[i] = new Jogador(i);
			j[i].Buscas(sub);
			pegaNaoEncontrados(j[i], i);
			exibeTudo(j[i], i);
			replaces(sub);
		}

		vencedor(j);
	}
	// POR QUE DIABOS NÃO TA PASSANDO O VALOR
	// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	// arrumei, fui idiota

	private void replaces(int[] sub) {
		for (int a = 0; a < res.length - 1; a++) {
			sub[a] = res[a];
		}
	}

	void preencheResultado() {
		Random r = new Random();
		for (int i = 0; i < res.length; i++) {
			res[i] = r.nextInt(Limite);
			for (int a = 0; a < i; a++) {
				if (res[i] == res[a]) {
					i--;
					break;
				}
			}
		}

		for (int i = 0; i < res.length - 1; i++) {
			sub[i] = res[i];
		}
	}

	private String exibeResultado() {
		String resultado = "Resultado:\n";
		for (int i = 0; i < res.length; i++) {
			if (res[i] < 10) {
				resultado += "0" + res[i] + "|";
			} else {
				resultado += res[i] + "|";
			}
		}
		resultado += "\n";
		return resultado;
	}

	public String exibeInformacoes(Jogador J, int i) {
		String sortWord;
		switch (J.sort) {
		case 1:
			sortWord = "Quick Sort";
			break;
		case 2:
			sortWord = "Shell Sort";
			break;
		case 3:
			sortWord = "Select Sort";
			break;
		default:
			sortWord = "Select Sort";
			break;
		}
		String informacoes = "Jogador " + (i + 1) + "\n\n" + "Contador BS: " + J.cS + "\n\n" + sortWord + "\n"
				+ "Contador BB: " + J.cB + "\n\n";
		i++;
		return informacoes;

	}

	private String exibeCartelas(Jogador J) {
		String cartelas = "\nCartela:\n";
		for (int i = 0; i < J.c.car.length; i++) {
			if (J.c.car[i] < 10) {
				cartelas += "0" + J.c.car[i] + "|";
			} else {

				cartelas += J.c.car[i] + "|";
			}

		}
		cartelas += "\n";
		return cartelas;
	}

	private void pegaNaoEncontrados(Jogador J, int index) {
		boolean temNoVetor = false;

		for (int a = 0; a < notFound[index].length - 1; a++) {
			notFound[index][a] = -1;
		}

		for (int a = 0; a < res.length; a++) {
			for (int i = 0; i < J.c.car.length; i++) {
				if (res[a] == J.c.car[i]) {
					temNoVetor = true;
					;
				}
			}
			if (temNoVetor == false) {
				notFound[index][a] = res[a];

			}
			temNoVetor = false;
		}
	}

	private String exibeNaoEncontrados(int index) {
		String naoEncontrados = "\nNão foram encontrados na cartela:\n";

		for (int a = 0; a < notFound[index].length - 1; a++) {
			if (!(notFound[index][a] == -1)) {
				if (notFound[index][a] < 10) {
					naoEncontrados += "0" + notFound[index][a] + "|";
				} else {
					naoEncontrados += notFound[index][a] + "|";
				}
			}
		}
		return naoEncontrados;
	}

	private void exibeTudo(Jogador J, int i) {

		try {

			String jogadorAtual = "Jogador" + (i + 1) + ".txt";
			FileWriter writer = new FileWriter(jogadorAtual);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write(exibeInformacoes(J, i));
			bufferedWriter.write(exibeResultado());
			bufferedWriter.write(exibeCartelas(J));
			bufferedWriter.write(exibeNaoEncontrados(i));

			bufferedWriter.newLine();

			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void vencedor(Jogador[] J) {
		int menor = 1;
		for (int i = 1; i <= J.length; i++) {
			if (J[i - 1].cS < J[menor].cS) {
				menor = i - 1;
			}
		}
		try {

			String fileName = "resultadoAposta.txt";
			FileWriter writer = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			menor = menor + 1;
			String vencedor = Integer.toString(menor);
			bufferedWriter.write("O vencedor é o " + vencedor + "º Jogador");

			bufferedWriter.newLine();

			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
