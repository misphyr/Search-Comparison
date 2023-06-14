
class Jogador {

	// Contador de interações
	public int cS = 0;
	public int cB = 0;
	public int sort = 0;

	// Nova Cartela
	Cartela c = new Cartela();

	Jogador(int sort) {
		this.sort = sort + 1;

	}

	public void Buscas(int[] res) {

		// Busca Sequêncial
		BuscaS(c.car, res);

		// Busca binária
		BuscaB(c.car, res);

	}

	private void BuscaS(int[] car, int[] res) {
		int cSBackup = -1;
		// Tenho que ter um jeito de Mandar o cS na vez que o último número da cartela
		// seja encontrado (não a última posição do vetor)
		for (int i = 0; i < res.length; i++) {
			for (int a = 0; a < car.length; a++) {
				cS++;
				if (res[i] == car[a]) {
					// Valor encontrado
					cSBackup = cS;
					break;
				}

			}

		}
		cS = cSBackup;
		// Valor não encontrado
	}

	private void BuscaB(int[] car, int[] res) {

		ordenador(res);
		for (int i = 0; i < res.length; i++) {
			BuscaBinaria(car, res[i]);
		}

	}

	public void BuscaBinaria(int[] vet, int x) {
		int low = 0;
		int high = vet.length - 1;
		while (low <= high) {
			cB++;
			int medium = (high + low) / 2;
			if (x > vet[medium]) {
				low = medium + 1;
			} else if (x < vet[medium]) {
				high = medium - 1;
			} else {
				// medium é o número encontrado
				return;
			}
		}
		// caso saia é o número não encontrado

		return;

	}

	private void ordenador(int[] res) {

		switch (sort) {
		case 1:
			QuickSort(res, 0, res.length - 1);
			break;
		case 2:
			ShellSort(res);
			break;
		case 3:
			SelectSort(res);
			break;
		default:
			SelectSort(res);
			break;
		}

	}

	public void ShellSort(int[] vet) {
		int h = 1;
		while (h < vet.length) {
			h = h * 3 + 1;
		}

		h /= 3;
		int c, j;

		while (h > 0) {
			for (int i = h; i < vet.length; i++) {
				c = vet[i];
				j = i;
				cB++;
				while (j >= h && vet[j - h] > c) {

					vet[j] = vet[j - h];
					j -= h;

				}

				vet[j] = c;

			}
			h /= 2;

		}

	}

	public void SelectSort(int[] vet) {
		for (int i = 0; i < vet.length - 1; i++) {
			int a = i;
			for (int j = i + 1; j < vet.length; j++) {
				cB++;
				if (vet[j] < vet[a]) {
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
		int i = (low - 1);

		for (int j = low; j < high; j++) {
			cB++;
			if (vet[j] <= pivot) {
				i++;
				int aux = vet[i];
				vet[i] = vet[j];
				vet[j] = aux;
			}
		}

		int temp = vet[i + 1];
		vet[i + 1] = vet[high];
		vet[high] = temp;
		return i + 1;
	}

	void QuickSort(int vet[], int low, int high) {
		if (low < high) {
			/*
			 * pi é o índice da partição, arr[pi] é agora o lado direito
			 */
			int pi = QSparticao(vet, low, high);
			// Ordene os elementos recursivamente
			// antes e depois da partição

			QuickSort(vet, low, pi - 1);
			QuickSort(vet, pi + 1, high);
		}
	}

}
