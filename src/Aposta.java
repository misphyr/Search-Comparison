import java.util.Random;

public class Aposta {


	protected static int tamRes = 100;
	protected static int[] res = new int[tamRes];
	
	Aposta() {
		preencheResultado();
		adicionaJogadores();
	
	}
	
	private void adicionaJogadores() {
		// Jogador(int[] vet, int x)
		// vet = vetor solução
		// x = Tipo de Sort:
		// 1 = Quick / 2 = Shell / 3 = Select //

		Jogador j1 = new Jogador(1);
//		Jogador j2 = new Jogador(2);
//		Jogador j3 = new Jogador(3);
//		Jogador j4 = new Jogador(1);
	}
	
	private void preencheResultado() {
		Random r = new Random();
		for(int i=0;i<res.length;i++) {
			res[i] = r.nextInt(tamRes);
			for(int a = 0; a < i; a++) {
				if(res[i] == res[a]) {
					i--;
					break;
				}
			}
		}
	}
	
	
}
