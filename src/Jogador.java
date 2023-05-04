import java.util.Random;

public class Jogador {

	//variaveis para tamanho quadrado da matriz (car)tela, matriz cartela, vetor de nºs n encontrados e quantidade de números n encontrados
	//Já que passei os nFs para cá, colocar a função que os usa aqui tbm
	int tam = 5;
	int[][] car = new int[tam][tam];	
	
	public int[] notFound = new int[100]; 
	public int nF = 0;
	public int count=0;
	public int[] vet;

	Jogador(int[] vet) {
		this.vet = vet;
		Cartela();
		BuscaFeitaPorFa(this.vet);
		ExibirResultado();
	}
	//Cartela do jogador
	//Ferramenta principal
	public int[][] Cartela() {
		Random r = new Random();
		
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j< tam; j++) {
				car[i][j] = r.nextInt(100);
			}
		}
		return car;
	}
	
	//função que printa a cartela
	public void ExibirCartela() {
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
			System.out.print(car[i][j] + " |");
			}
			System.out.println("\n");
		}
	}
	
	public void ExibirResultado() {
		System.out.println("\n");
		System.out.println("Contador = " + count);
		System.out.println("\n");
		ExibirCartela();
		
		System.out.println("Quantidade de números não encontrados: "+ nF);
		int a = 0;

		System.out.println("\nNúmeros não encontrados: ");
		while(a < nF && nF > 0) {
		System.out.print(notFound[a] + " | ");
		a++;
		}
		}
	
	//Busca temporaria, precisa da sequencial e da binária
	public void BuscaFeitaPorFa(int[] vet) {
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				for(int a = 0; a < vet.length; a++) {
					count++;
					
					if(car[i][j] == vet[a]) {
						break;
					}
					
					if((count / 100) != i && (count % 100) == 0) {
						notFound[nF] = car[i][j];
						nF++;
					}
				}
			}
	}
	}

	
	
}
