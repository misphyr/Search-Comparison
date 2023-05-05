import java.util.Random;

public class Main {


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
		Jogador j1 = new Jogador(vet,2);
//		
//		Jogador j2 = new Jogador(vet);
//		
//		Jogador j3 = new Jogador(vet);
//		
//		Jogador j4 = new Jogador(vet);
	}

}

