import java.util.Arrays;
import java.util.Random;

public class Aposta {


	protected static int tamRes = 10;
	protected static int[] res = new int[tamRes];
	private final int QNTJ = 4;
	private int[][] notFound = new int[QNTJ][tamRes];
	
	Aposta() {
		preencheResultado();
		adicionaJogadores();
		
	
	}
	
	private void adicionaJogadores() {
		// Jogador(int[] vet, int x)
		// vet = vetor solução
		// x = Tipo de Sort:
		// 1 = Quick / 2 = Shell / 3 = Select //
		Jogador[] j = new Jogador[QNTJ];
		for(int i = 0; i < QNTJ; i++) {
		j[i] = new Jogador(i);
		j[i].Tudo();
		pegaNaoEncontrados(j[i],i);
		exibeTudo(j[i] ,i);
		}
	}
	
	private void preencheResultado() {
		Random r = new Random();
		for(int i=0;i<res.length-1;i++) {
			res[i] = r.nextInt(tamRes);
			for(int a = 0; a < i; a++) {
				if(res[i] == res[a]) {
					i--;
					break;
				}
			}
		}
	}
	private void exibeResultado() {
	System.out.print("| Vetor Resultado: \n| ");
	for(int i = 0; i < res.length; i++) {
		System.out.print(res[i] + "|");;
	}
	System.out.print("\n|\n");;
	}
	
	public void exibeInformacoes(Jogador J, int i ) {
		i++;
		System.out.print("| Jogador " + i + "\n"  
					   + "| Contador BS: " + J.cB + "\n" 
					   + "| Contador BB: " + J.cS + "\n");
		
	}
	
	private void exibeCartelas(Jogador J) {

		System.out.print("| Cartela: \n| ");
		for(int i = 0 ; i < J.c.car.length ; i++){
		System.out.print(J.c.car[i] + "|");
		}
		System.out.print("\n");
	}
	
	private void pegaNaoEncontrados(Jogador J, int index) {
		boolean temNoVetor = false;
		for(int a = 0 ; a < res.length - 1; a++) {
			for(int i = 0 ; i < J.c.car.length - 1; i++) {
					if(res[a] == J.c.car[i]) {
					temNoVetor = true;;
					}
			}
			if(temNoVetor == false) {
			notFound[index][a] = res[a];
			
			}
			temNoVetor = false;
		}
	}
	private void exibeNaoEncontrados(Jogador J,int index) {
		System.out.print("| Não foram encontrados na cartela:\n| ");
		for(int a = 0 ; a < notFound[index].length - 1; a++) {
				System.out.print(notFound[index][a] + " |");
		}

	}
	
	private void exibeTudo(Jogador J, int i) {
		exibeResultado();
		exibeInformacoes(J, i);
		exibeCartelas(J);	
		exibeNaoEncontrados(J,i);

		System.out.print("\n| -------------------------------------\n");
	}

	
}
