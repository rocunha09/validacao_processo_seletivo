package validacao_processo_seletivo_basico;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	static BigDecimal salarioBase = new BigDecimal(2000.00);

	public static void main(String[] args) {
		String[] c = selecaoCandidatos();
		imprimirSelecionados(c);
		ligarParaCandidatos(c);
		
		/*************************************************************************************************/
		
		System.out.println("\n\n");
		System.out.println("----  Segundo Exercício  ----");
		contador();
	}
	
	static void ligarParaCandidatos(String[] c) {
		for (int i = 0; i < c.length; i++) {
			if(c[i] == null)
				continue;
			int tentativasRealizadas = 1;
			boolean continuarTentando = true;
			boolean atendeu = false;
			do {
				atendeu = atender();
				continuarTentando = !atendeu;
				if(!continuarTentando)
					System.out.println("CONTATO REALIZADO COM SUCESSO COM O CANDIDATO " + c[i]);
				System.out.println(tentativasRealizadas + " TENTATIVAS REALIZADAS...");
				tentativasRealizadas++;
					
			}while(continuarTentando && tentativasRealizadas <= 3);
			
		}
	}
	
	static boolean atender() {
		return new Random().nextInt(3) == 1;
	}
	
	static void imprimirSelecionados(String[] c) {
		System.out.println("Imprimindo lista de candidatos selecionados...");
		for (int i = 0; i < c.length; i++) {
			if(c[i] == null)
				return;
			System.out.printf("O candidato de número %d é: %s\n", i, c[i]);
		}
	}
	
	static String[] selecaoCandidatos() {
		String[] candidatos = {"Rafael", "João", "Maria"};
		String[] selecionados = new String[candidatos.length];
		int j = 0;
		for (int i = 0; i < candidatos.length; i++) {
			BigDecimal valor = valorPretendido();
			System.out.println("O candidato " + candidatos[i] + " solicitou este valor de salário: " + valor);
			if(salarioBase.compareTo(valor) >= 0) {
				System.out.println("O candidato " + candidatos[i] + " foi selecionado para a vaga");
				selecionados[j] = candidatos[i];
				j++;
			}
		}
		return selecionados;
	}
	
	static BigDecimal valorPretendido() {
		return new BigDecimal(ThreadLocalRandom.current().nextDouble(1700, 2300));
	}
	
	static void analisarCandidato(BigDecimal salarioPretendido) {
		
		if(salarioBase.compareTo(salarioPretendido) > 0)
			System.out.println("LIGAR PARA O CANDIDATO");
		else if(salarioBase.compareTo(salarioPretendido) == 0)
			System.out.println("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
		else 
			System.out.println("AGUARDADNO RESULTADO DOS DEMAIS CANDIDATOS");
		
	}
	
	private static void contador() {
		Scanner terminal = new Scanner(System.in);
		
		System.out.println("Digite o primeiro parâmetro");
		int parametroUm = terminal.nextInt();
		terminal.nextLine();
		
		System.out.println("Digite o segundo parâmetro");
		int parametroDois = terminal.nextInt();
		terminal.nextLine();
		
		try {
			contar(parametroUm, parametroDois);
		}
		catch (ParametrosInvalidosException exception) {
			System.out.println(exception.getMessage());
		}
		
	}
	
	
	/*************************************************************************************************/
	
	static void contar(int parametroUm, int parametroDois ) throws ParametrosInvalidosException {
		if(parametroUm > parametroDois)
			throw new ParametrosInvalidosException();
		
		int contagem = parametroDois - parametroUm;
		for (int i = 1; i <= contagem; i++) {
			System.out.printf("Imprimindo o número %d\n", i);
		}
	}

}

class ParametrosInvalidosException extends Exception {

    // Construtor padrão
    public ParametrosInvalidosException() {
        super("O segundo parâmetro deve ser maior que o primeiro.");
    }

    // Construtor que aceita uma mensagem personalizada
    public ParametrosInvalidosException(String message) {
        super(message);
    }
}
