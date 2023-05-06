import java.util.Random;

public class Jogador extends Main{
	
	//tamanho da cartela
	private int tam = 5;
	//vetor da cartela do jogador
	private int[] car = new int[tam];	
	//vetor dos nºs não encontrados
	private int[] notFound = new int[100]; 	
	//vetor solução
	private int[] vet;
	//forma de navegar no vetor dos não encontrados
	private int nF = 0;
	//Contador de interações
	private int count=0;

	Jogador(int[] vet,int x) {
		this.vet = vet;
		Cartela();
		count = 0;
		System.out.println("|------------------------------------------------\n|"
				+ "\n| Busca sequencial:");
		printa();

		for(int i = 0; i < tam; i++) {
				BuscaSequencial(car[i]);
		}
		ExibirResultado();
		
		Reset();
		System.out.println("\n|------------------------------------------------\n|"
				+ "\n| Busca binária:");
	switch(x) {
	case 1:
		QuickSort();	
		for(int i = 0; i < tam; i++) {
		BuscaBinaria(car[i]);
		}
		break;
	case 2:
		ShellSort();
		for(int i = 0; i < tam; i++) {
		BuscaBinaria(car[i]);
		}
		break;
	case 3:
		SelectSort();
		for(int i = 0; i < tam; i++) {
			BuscaBinaria(car[i]);
			}
		break;
	case 4:
		QuickSort();
		for(int i = 0; i < tam; i++) {
			BuscaBinaria(car[i]);
			}
		break;
	}
	printa();
	ExibirResultado();
	
		
	}
	//Cartela do jogador
	//Ferramenta principal
	public void Cartela() {
		Random r = new Random();
		
		for(int i = 0; i < tam; i++) {
				car[i] = r.nextInt(100);
				for(int a = 0; a < i; a++) {
				if(vet[i] == vet[a]) {
					i--;
					break;
				}
			}
		}
	}
	
	//função que printa a cartela
	public void ExibirCartela() {
		System.out.print("| Cartela:\n| ");
		for(int i = 0; i < tam; i++) {
			if(i != 0) {
				System.out.print(" | ");
			}
			System.out.print(car[i]);
		}
		System.out.print(" |\n|\n");;
	}
	
	public void ExibirResultado() {
		System.out.println("\n|");
		System.out.println("| Contador = " + count);
		ExibirCartela();
		
		System.out.println("| Quantidade de números não encontrados: "+ nF);

		System.out.print("|\n| Números não encontrados:");
		System.out.print("\n| ");
		for(int a = 0;a < nF && nF > 0;a++) {
			if(a != 0) {
			System.out.print(" | ");
			}
		System.out.print(notFound[a]);
		}
		
		System.out.print(" |");
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
		for (int i = 0; i < vet.length - 1; i++){
			int a = i;
			for(int j = i + 1; j < vet.length; j++){
				if(vet[j] < vet[a]) {
					a = j;
					}
				}
			int temp = vet[a];
					vet[a] = vet[i];
					vet[i] = temp;
					}
		
	}
	
	public void QuickSort() {
		
	}
	
	void Reset() {
		this.count = 0;
		this.nF = 0 ;
		this.notFound = new int[100];
		
	}
	
	public void printa() {
	System.out.print("|\n|\n| Vetor Solução:\n| ");
		for(int i=0;i<vet.length;i++) {

			if(i != 0) {
				System.out.print(" | ");
			}
				System.out.print(vet[i]);	
	}
		System.out.print("\n|");
}
	

}
