import java.util.Random;

public class Jogador {

	//variaveis para tamanho quadrado da matriz (car)tela, matriz cartela, vetor de nºs n encontrados e quantidade de números n encontrados
	//Já que passei os nFs para cá, colocar a função que os usa aqui tbm
	int tam = 5;
	int[][] car = new int[tam][tam];	
	public int[] notFound = new int[100]; 
	public int nF = 0;
	
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
	
	
}
