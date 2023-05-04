import java.util.Random;

public class Main {

	public static int count=0;
	public static int sub = -1;
	

	//código bagunçado, será organizado
	public static void main(String[] args) {
		Random r = new Random();
		int[] vet = new int[100];
		for(int i=0;i<vet.length;i++) {
			vet[i] = r.nextInt(100);
		/*	System.out.print(" |" + vet[i]);
			if(i % 10 == 0 && i != 0) {
				System.out.println("\n");
			}
			*/
		}
		Jogador j1 = new Jogador();
		j1.Cartela();
		BuscaFeitaPorFa(vet,j1);
		System.out.println("Contador = " + count + "\nNúmero da quebra = " + sub);
		System.out.println("\n");
		j1.ExibirCartela();
		
		System.out.println("Quantidade de números não encontrados: "+ j1.nF);
		int a = 0;

		System.out.println("\nNúmeros não encontrados: ");
		while(a < j1.nF && j1.nF > 0) {
		System.out.print(j1.notFound[a] + " | ");
		a++;
		}
	}

	//Busca temporaria, precisa da sequencial e da binária
	public static void BuscaFeitaPorFa(int[] vet, Jogador j0) {
		boolean p = false;
		
		for(int i = 0; i < j0.tam; i++) {
			for(int j = 0; j < j0.tam; j++) {
				for(int a = 0; a < vet.length; a++) {
					count++;
					
					if(j0.car[i][j] == vet[a]) {
						sub = vet[a];
						p = false;
						break;
					}
					
					if((count / 100) != i && (count % 100) == 0) {
						j0.notFound[j0.nF] = j0.car[i][j];
						j0.nF++;
					}
				}
			}
	}
	}
}

