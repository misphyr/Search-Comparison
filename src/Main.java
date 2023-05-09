import java.util.Random;

public class Main {

	//Como será:
	//Terá um vetor com os valores sorteados(Vetor solução)
	//Cada um dos 4 Apostadores terá uma cartela (tamanho indefinido)
	//Cada cartela passará por dois processos:
	//Busca Sequencial e Busca Binária
	//Para Busca Binária, o vetor de solução deverá se organizado cada um com um sort diferente:
	//Quick, Select e Shell, O 4º pode ser com quaquer um
	//Terá um contador em cada busca
	//O primeiro apostador a preencher todos os números, ganha
	//Os números do vetor solução não usados nas cartelas devem ser mostrados
	
	public static int[] vet = new int[100];

	public static void main(String[] args) {
		//método para preencher o vetor com random's
		preencheVetorResultado();
		
		// Jogador(int[] vet, int x)
		// vet = vetor solução
		// x = Tipo de Sort:
		// 1 = Quick / 2 = Shell / 3 = Select //
		
		int[] teste = new int[100]; // Criando e instanciando um vetor novo
		teste = vet; 
		System.out.print("|\n|------------------------------------------------");
		System.out.println("\n| Primeira Cartela:");
		Jogador j1 = new Jogador(teste,1); // utilizando o quicksort para jogador 1
		
		System.out.print("\n|\n|------------------------------------------------");
		System.out.println("\n| Segunda Cartela:");
		Jogador j2 = new Jogador(teste,1); // utilizando o quicksort para jogador 2
//		
//		Jogador j3 = new Jogador(vet);
//		
//		Jogador j4 = new Jogador(vet);
	}

	static void preencheVetorResultado() {
		Random r = new Random();
		for(int i=0;i<vet.length;i++) {
			vet[i] = r.nextInt(100);
			for(int a = 0; a < i; a++) {
				if(vet[i] == vet[a]) {
					i--;
					break;
				}
			}
		}
	}
}


