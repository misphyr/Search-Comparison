import java.util.Random;

 class Jogador extends Aposta
 {	
	
	
	
	//Contador de interações
	private int cS=0;
	private int cB=0;
	private int sort = 0;
	
	Jogador(int sort) {
		this.sort = sort;
	}
	
	public void Tudo() {

		//Nova Cartela
		Cartela c = new Cartela();
		
		//Busca Sequêncial
		BuscaS();
		
		//Busca binária
		BuscaB();

		//Exibe tudo que precisa
		ExibirResultado();
	

	}
	
	private void BuscaB() {
		System.out.println("\n|------------------------------------------------\n|"
				+ "\n| Busca binária:");
		
		switch(sort) {
		case 1:
			System.out.println("|\n| Quick Sort");
			QuickSort(vet,0, vet.length - 1);	
			LoopBB(vet);
			break;
		case 2:
			System.out.println("|\n| Shell Sort");
			ShellSort();
			LoopBB();
			break;
		case 3:
			System.out.println("|\n| Select Sort");
			SelectSort();
			LoopBB();
			break;
		default:
			break;
		}
	}
	private void BuscaS() {
		System.out.println("|------------------------------------------------\n|"
				+ "\n| Busca sequencial:");
		
	}

	private void LoopBS(int[] car) {
		for(int i = 0; i < car.length; i++) {
			for(int i = 0; i< res.length;i++) {
				cS++;
				if(res[i] == car[i]) {
			//Valor encontrado
					
					break;
				}
		}
			
	}
		}
	private void LoopBB(int[] car) {
		for(int i = 0; i < car.length; i++) {
			BuscaBinaria(vet,car[i]);
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
		System.out.println("| CS  = " + cS + "\n| CB" + cB);
		
//		System.out.print("| Quantidade de números não encontrados: "+ nF);
//		
//		System.out.print("|\n| Números não encontrados:");
//		System.out.print("\n| ");
//		for(int a = 0;a < nF && nF > 0;a++) {
//			if(a != 0) {
//			System.out.print(" | ");
//			}
//		System.out.print(notFound[a]);
//		}
		System.out.print("\n| ");
		
		}
	
	//Busca temporaria, precisa da sequencial e da binária
	public void BuscaSequencial(int[] car, int x) {
		
		}
		//Valor não encontrado
		//Coloca no vetor de não encontrados e aumenta a posição que será usada pelo próximo
	}
	
	
	public void BuscaBinaria(int[] vet,int x) {
		int low = 0;
		int high = vet.length-1;
		while(low <= high){
			cB++;
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
				cB++;
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
	

	

}
