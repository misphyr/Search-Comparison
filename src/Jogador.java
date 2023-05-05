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

	Jogador(int[] vet,int x) {
		this.vet = vet;
		Cartela();
		count = 0;
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				BuscaSequencial(car[i][j]);
			}
		}
		ExibirResultado();
		
		count = 0;
		Reset();
	switch(x) {
	case 1:
		QuickSort();	
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
		BuscaBinaria(car[i][j]);
		}
			}
		break;
	case 2:
		ShellSort();
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
		BuscaBinaria(car[i][j]);
		}
			}
		break;
	case 3:
		SelectSort();
		//BuscaBinaria();
		break;
	case 4:
		QuickSort();
		//BuscaBinaria();
		break;
	}
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
	public void BuscaSequencial(int x) {
		for(int i = 0; i< vet.length;i++) {
			count++;
			if(x == vet[i]) {
		//Valor encontrado
				
				return;
			}
		}
		//Valor não encontrado
		notFound[nF] = x;
		nF++;
		//Coloca no vetor de não encontrados e aumenta a posição que será usada pelo próximo
	}
	
	public void BuscaBinaria(int x) {
		int low = 0;
		int high = vet.length-1;
		while(low <= high){
			count++;
			int medium = (high + low) / 2;
			if(x > vet[medium] ) {
				low = medium + 1;
			}
			else if(x < vet[medium]) {
				high = medium -1;
			}else {
				//medium é o número encontrado
				return;
			}
		}
		//caso saia é o número não encontrado
		notFound[nF] = x;
		nF++;
		return;
			
	}
	
	public void ShellSort() {
		int h = 1;
		while(h < vet.length) {
			h = h * 3 + 1;
		}
		
		h /= 3;
		int c, j;
		
		while(h > 0){
			for(int i = h; i < vet.length; i++) {
				c = vet[i];
				j = i;
				count++;
				while(j >= h && vet[j - h] > c) {
			
					vet[j] = vet[j - h];
					j -= h;
					
				}
				
				vet[j] = c;
				
			}
			h /= 2;
			
		}
	
	}
	
	public void SelectSort() {
		
	}
	
	public void QuickSort() {
		
	}
	
	void Reset() {
		count = 0;
		nF = 0;
		notFound = new int[100]; 
		
	}
	

}
