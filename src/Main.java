import java.util.Random;

public class Main {

	public static int count=0;
	public static int sub = -1;
	
	public static int[] notFound = new int[100]; 
	public static int nF = 0;
	
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
		BuscaFeitaPorFa(j1.tam,vet,j1.car);
		System.out.println("Contador = " + count + "\nNúmero da quebra = " + sub);
		System.out.println("\n");
		j1.ExibirCartela();
		
		System.out.println("Quantidade de números não encontrados: "+ nF);
		int a = 0;

		System.out.println("\nNúmeros não encontrados: ");
		while(a < nF && nF > 0) {
		System.out.print(notFound[a] + " | ");
		a++;
		}
	}
	//stringzinn

	public static void BuscaFeitaPorFa(int tam, int[] vet,int[][] car) {
		boolean p = false;
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				for(int a = 0; a < vet.length; a++) {
					count++;
					
					if(car[i][j] == vet[a]) {
						sub = vet[a];
						p = false;
						break;
					}
					
					if((count / 100) != i && (count % 100) == 0) {
						System.out.println("***Número não sorteado***");
						notFound[nF] = car[i][j];
						nF++;
					}
				}
			}
	}
	}
}

