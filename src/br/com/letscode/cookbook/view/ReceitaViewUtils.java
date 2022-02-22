package br.com.letscode.cookbook.view;

import java.util.ArrayList;

// Utilit�rios para manipula��o do Preparo

import java.util.List;
import java.util.Locale;

import br.com.letscode.cookbook.enums.TipoMedida;
import br.com.letscode.cookbook.enums.TipoRendimento;

public class ReceitaViewUtils {
	
		
	public static void editordeLinha (List <String> texto, String msg) {
		 
			
         boolean continua = true;
         
         if (!texto.isEmpty()) {
        	 
        	imprimeLista (texto);
         }
    	String[] options = {"+", "-", "M", "X"};
    	StringBuilder menuEdicao = new StringBuilder("_".repeat(100) + "%n");
    	menuEdicao.append (" + : Inserir nova linha %n");
    	menuEdicao.append (" - : Apagar linha %n");
    	menuEdicao.append (" M - Modificar linha %n");
    	menuEdicao.append (" X - SAIR %n");
    	menuEdicao.append("_".repeat(100) + "%n");
        
    	do {
    		 System.out.printf("                                           %s %n", msg);
	         String opcao = ConsoleUtils.getUserOption(menuEdicao.toString(), options).toUpperCase(Locale.getDefault());
	         switch (opcao) {
	             case "+":
	                 insereLinha(texto);
	                 imprimeLista (texto);
	                 break;
	             case "-":
	                 apagaLinha(texto);
	                 imprimeLista (texto);
	                 break;
	             case "M":
	                 modificaLinha(texto);
	                 imprimeLista (texto);
	                 break;
	             case "X":
	                 System.out.printf("%s terminada %n", msg);
	                 continua = false;
	                 break;
	             default:
	                 System.out.println("Op��o inv�lida!!!");
	         }
         
         } while (continua);
	}
	
	private static void insereLinha(List <String> texto) {
		String numLinha ="0"; 
		String novaLinha = "";
		if (!texto.isEmpty()) {
			do {
				numLinha = ConsoleUtils.getUserInput("Digite n�mero da posi��o onde voc� quer inserir a nova linha: %n" );
			} while (numLinha.isBlank()|| numLinha.isEmpty() || !(numLinha.matches("[0-9]*"))|| Integer.parseInt(numLinha) > texto.size());
		}
		do {
			novaLinha = ConsoleUtils.getUserInput("Digite o conte�do da nova linha: %n" );
		} while (novaLinha.isBlank()|| novaLinha.isEmpty());
		texto.add(Integer.parseInt(numLinha), novaLinha);
	}
	
	private static void apagaLinha(List <String> texto) {
		String numLinha ="0"; 
		if (!texto.isEmpty()) {
			do {
				numLinha = ConsoleUtils.getUserInput("Digite n�mero da linha a ser apagada: %n" );
			} while (numLinha.isBlank()|| numLinha.isEmpty() || !(numLinha.matches("[0-9]*"))|| Integer.parseInt(numLinha) >= texto.size());
			texto.remove(Integer.parseInt(numLinha));
		}
		else {
			System.out.println("N�o h� linha a apagar");
		}
	}
	
	private static void modificaLinha(List <String> texto) {
		String numLinha ="0"; 
		String novaLinha = "";
		if (!texto.isEmpty()) {
			do {
				numLinha = ConsoleUtils.getUserInput("Digite n�mero da linha que voc� quer alterar: %n" );
			} while (numLinha.isBlank()|| numLinha.isEmpty() || !(numLinha.matches("[0-9]*"))|| Integer.parseInt(numLinha) >= texto.size());
			do {
				novaLinha = ConsoleUtils.getUserInput("Digite o novo conte�do: %n" );
			} while (novaLinha.isBlank()|| novaLinha.isEmpty());
			texto.set(Integer.parseInt(numLinha), novaLinha);
		}
		else {
			System.out.println("N�o h� linha a modificar");
		}
	}
	
	private static void imprimeLista (List <String> texto) {
		for (int i = 0; i < texto.size(); i++) {
			System.out.printf("Linha %d: %s%n", i, texto.get(i));
		} 
	}
	
	public static int editaTipoRendimento () {
		int ret = 0;
    	StringBuilder sb = new StringBuilder();
        String[] options = new String[TipoRendimento.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, TipoRendimento.values()[i]));
        }
        System.out.println("Digite o n�mero da op��o:");
        String opcao1 = ConsoleUtils.getUserOption(sb.toString(), options);
        for (int i = 0; i < options.length; i++) {
            if (opcao1.equalsIgnoreCase(options[i])) {
		    	ret = i;
		    	break;
            }
        }
        return ret;
	}
}
