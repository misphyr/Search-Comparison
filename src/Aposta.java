import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Aposta {

	public final static int Limite = 100;
	protected static int tamRes = 100;
	protected static int[] res = new int[tamRes];
	protected static int[] sub = new int[tamRes];
	private final int QNTJ = 4;
	private int[][] notFound = new int[QNTJ][tamRes];
	
	Aposta() {
		
		preencheResultado();
		adicionaJogadores();
	}
	
	private void adicionaJogadores() {
		// Jogador(int[] vet, int x)
		// vet = vetor solução
		// x = Tipo de Sort:
		// 1 = Quick / 2 = Shell / 3 = Select //
		
		Jogador[] j = new Jogador[QNTJ];
		for(int i = 0; i < QNTJ; i++) {
			
		j[i] = new Jogador(i);
		j[i].Buscas(sub);
		pegaNaoEncontrados(j[i],i);
		exibeTudo(j[i] ,i);
		replaces(sub);
		}
		
		vencedor(j);
	}
	//POR QUE DIABOS NÃO TA PASSANDO O VALOR AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	//arrumei, fui idiota
	
	private void replaces(int[] sub) {
		for(int a=0;a<res.length-1;a++) {
			sub[a] = res[a];
	}
	}

	private void preencheResultado() {
		Random r = new Random();
		for(int i=0;i<res.length-1;i++) {
			res[i] = r.nextInt(Limite);
			for(int a = 0; a < i; a++) {
				if(res[i] == res[a]) {
					i--;
					break;
				}
			}
		}
	
		for(int i=0;i<res.length-1;i++) {
			sub[i] = res[i];
		}
		
	}
	
	private String exibeResultado() {
	String resultado = "Resultado:\n";
	System.out.print("| Vetor Resultado: \n| ");
	for(int i = 0; i < res.length; i++) {
		resultado += res[i] + "|";
		System.out.print(res[i] + "|");;
	}
	System.out.print("\n|\n");
	return resultado;
	}
	
	public String exibeInformacoes(Jogador J, int i ) {
		String informacoes = 
				     "Jogador " + i + "\n"  
				   + "Contador BS: " + J.cS + "\n" 
				   + "Contador BB: " + J.cB + "\n";
		i++;
		System.out.print("| Jogador " + i + "\n"  
					   + "| Contador BS: " + J.cS + "\n" 
					   + "| Contador BB: " + J.cB + "\n");
		return informacoes;
		
	}
	
	private String exibeCartelas(Jogador J) {
		String cartelas = "\nCartela:\n";
		System.out.print("| Cartela: \n| ");
		for(int i = 0 ; i < J.c.car.length ; i++){
		cartelas += J.c.car[i] + "|";
		System.out.print(J.c.car[i] + "|");
		}
		System.out.print("\n");
		return cartelas;
	}
	
	private void pegaNaoEncontrados(Jogador J, int index) {
		boolean temNoVetor = false;
		
		for(int a = 0 ; a < notFound[index].length - 1; a++) {
			notFound[index][a] = -1;
		}
		
		for(int a = 0 ; a < res.length - 1; a++) {
			for(int i = 0 ; i < J.c.car.length - 1; i++) {
					if(res[a] == J.c.car[i]) {
					temNoVetor = true;;
					}
			}
			if(temNoVetor == false) {
			notFound[index][a] = res[a];
			
			}
			temNoVetor = false;
		}
	}
	
	private String exibeNaoEncontrados(Jogador J,int index) {
		String naoEncontrados = "\nNão foram encontrados na cartela:\n";
		int c =0;
		
		System.out.print("| Não foram encontrados na cartela:\n| ");
		for(int a = 0 ; a < notFound[index].length - 1; a++) {
			if(!(notFound[index][a] == -1)) {
				naoEncontrados += notFound[index][a] + "|";
				System.out.print(notFound[index][a] + "|");
				c++;
			}
		}
		 System.out.print("\n| " + c + " números.\n");
		 return naoEncontrados;
	}
	
	private void exibeTudo(Jogador J, int i) {
		exibeResultado();
		exibeInformacoes(J, i);
		exibeCartelas(J);	
		exibeNaoEncontrados(J,i);

		try {
			
			String jogadorAtual = "Jogador" + (i + 1) +".txt";
            FileWriter writer = new FileWriter(jogadorAtual);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            bufferedWriter.write(exibeResultado());
            bufferedWriter.write(exibeInformacoes(J, i));
            bufferedWriter.write(exibeCartelas(J));
            bufferedWriter.write(exibeNaoEncontrados(J, i));
            
            bufferedWriter.newLine();
 
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		System.out.print("\n| -------------------------------------\n");
	}

	private void vencedor(Jogador[] J) {
		int menor = 1;
		for(int i = 1; i <= J.length; i++) {
			if(J[i-1].cB < J[menor].cB) {
				menor = i - 1;
			}
		}
		System.out.print("O vencedor é o Jogador " + (menor+1));
	}
	
}
