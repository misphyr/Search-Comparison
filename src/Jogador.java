import java.util.Random;

public class Jogador {

	int tam = 5;
	int[][] car = new int[tam][tam];
	
	public int[][] Cartela() {
		Random r = new Random();
		
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j< tam; j++) {
				car[i][j] = r.nextInt(100);
			}
		}
		return car;
	}
	
	public void ExibirCartela() {
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
			System.out.print(car[i][j] + " |");
			}
			System.out.println("\n");
		}
	}
	
	
}
