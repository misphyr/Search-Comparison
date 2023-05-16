import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	// Como será:
	//[v] Terá um vetor com os valores sorteados(Vetor solução)					
	//[v] Cada um dos 4 Apostadores terá uma cartela (tamanho indefinido)
	//[v] Cada cartela passará por dois processos:
	//[v] Busca Sequencial e Busca Binária
	//[v] Para Busca Binária, o vetor de solução deverá se organizado cada um com um sort diferente:
	//[v] Quick, Select e Shell, O 4º pode ser com quaquer um
	//[v] Terá um contador em cada busca
	//[x] O primeiro apostador a preencher todos os números, ganha
	//[v] Os números do vetor solução não usados nas cartelas devem ser mostrados

	private static Aposta a = new Aposta();
	
	public static void main(String[] args) {
		
		Interface i = new Interface(a);
		i.start();
		
	}
	
	
	}


