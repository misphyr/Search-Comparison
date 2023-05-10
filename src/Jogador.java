import java.util.Random;

 class Jogador{ // teste 	
	
	//tamanho da cartela
	private int tam = 5;
	//vetor da cartela do jogador
	private int[] car = new int[tam];	
	//vetor dos nºs não encontrados
	private int[] notFound = new int[Main.tamVet]; 
	//forma de navegar no vetor dos não encontrados
	private int nF = 0;
	//Contador de interações
	private int count=0;
	
	
	Jogador(int[] vet,int x) {
		
		Cartela();
		count = 0;
		System.out.println("|------------------------------------------------\n|"
				+ "\n| Busca sequencial:");

		NEncontrados(vet);
		printa(vet);

		LoopBS(vet);
		ExibirResultado();
		
		Reset();
		System.out.println("\n|------------------------------------------------\n|"
				+ "\n| Busca binária:");
	switch(x) {
	case 1:
		System.out.println("|\n| Quick Sort");
		QuickSort(vet,0, vet.length - 1);	// Recebe cartela, 0 (low), tamanho do vetor - 1 (high) ((-1 por conta da recursão))
		LoopBB(vet);
		break;
	case 2:
		System.out.println("|\n| Shell Sort");
		ShellSort(vet);
		LoopBB(vet);
		break;
	case 3:
		System.out.println("|\n| Select Sort");
		SelectSort(vet);
		LoopBB(vet);
		break;
	}

	NEncontrados(vet);
	printa(vet);
	ExibirResultado();
	}
	private void LoopBS(int[] vet) {
		for(int i = 0; i < tam; i++) {
		BuscaSequencial(vet, car[i]);
		}
	}

	//Cartela do jogador
	//Ferramenta principal
	public void Cartela() {
		Random r = new Random();
		
		for(int i = 0; i < tam; i++) {
				car[i] = r.nextInt(100);
				for(int a = 0; a < i; a++) {
				if(car[i] == car[a]) {
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
		ExibirCartela();
		System.out.println("| Contador = " + count);
		
		System.out.print("| Quantidade de números não encontrados: "+ nF);
		
		System.out.print("|\n| Números não encontrados:");
		System.out.print("\n| ");
		for(int a = 0;a < nF && nF > 0;a++) {
			if(a != 0) {
			System.out.print(" | ");
			}
		System.out.print(notFound[a]);
		}
		
		}
	
	//Busca temporaria, precisa da sequencial e da binária
	public void BuscaSequencial(int[] vet, int x) {
		for(int i = 0; i< vet.length;i++) {
			count++;
			if(x == vet[i]) {
		//Valor encontrado
				
				return;
			}
		}
		//Valor não encontrado
		//Coloca no vetor de não encontrados e aumenta a posição que será usada pelo próximo
	}
	
	private void NEncontrados(int[] vet) {

		boolean temNoVetor = false;
		for(int a = 0 ; a < vet.length - 1; a++) {
			for(int i = 0 ; i < car.length - 1; i++) {
					if(vet[a] == car[i]) {
					temNoVetor = true;;
					}
			}
			if(temNoVetor == false) {
			notFound[nF] = vet[a];
			nF++;
			
			}
			temNoVetor = false;
		}
		
	}
	void LoopBB(int[] vet) {
		for(int i = 0; i < tam; i++) {
			BuscaBinaria(vet,car[i]);
			}
	}
	public void BuscaBinaria(int[] vet,int x) {
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
		
		return;
			
	}
	
	public void ShellSort(int[] vet) {
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
	
	public void SelectSort(int[] vet) {
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
	
	public int QSparticao(int vet[], int low, int high) { // Tava no documento do paulos ed
		int pivot = vet[high]; // Ponto chave
		int i = (low -1);
		
		for(int j = low; j < high; j++) {
			if(vet[j] <= pivot) {
				i++;
				int aux = vet[i];
				vet[i] = vet[j];
				vet[j] = aux;
			}
		}
		
		int temp = vet[i + 1];
		vet[i+1] = vet[high];
		vet[high] = temp;
		return i+1;
	}
	
	void QuickSort(int vet[], int low, int high) { 
		if(low < high) {
			/* pi é o índice da partição, 
            arr[pi] é agora o lado direito */
			int pi = QSparticao(vet, low, high);
	         // Ordene os elementos recursivamente
            // antes e depois da partição
			QuickSort(vet, low, pi - 1);
			QuickSort(vet, pi+1,high);
		}
	}
	
	void Reset() {
		this.count = 0;
		this.nF = 0 ;
		this.notFound = new int[100];
		
	}
	
	public void printa(int[] vet) {
	System.out.print("|\n| Vetor Solução:\n| ");
		for(int i=0;i<vet.length;i++) {

			if(i != 0) {
				System.out.print(" | ");
			}
				System.out.print(vet[i]);	
	}
}
	

}
