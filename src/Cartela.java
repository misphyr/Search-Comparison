import java.util.Random;

public class Cartela{

	int tam = 10;
	public int[] car = new int[tam];
	
	Cartela(){
		preencheCartela();
	}
	
	private void preencheCartela() {
		
			Random r = new Random();
			
			for(int i = 0; i < tam; i++) {
					car[i] = r.nextInt(Aposta.Limite);
					for(int a = 0; a < i; a++) {
					if(car[i] == car[a]) {
						i--;
						break;
					}
				}
			}
	}
}
