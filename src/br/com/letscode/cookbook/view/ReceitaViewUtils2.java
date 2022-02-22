package br.com.letscode.cookbook.view;

//Utilitários para manipulação dos Ingredientes

import java.util.List;
import java.util.Locale;

import br.com.letscode.cookbook.domain.Ingrediente;
import br.com.letscode.cookbook.enums.TipoMedida;

public class ReceitaViewUtils2 {
	
	
	
	public static void editordeIngredientes (List<Ingrediente> ingredientes, String msg) {
		
        boolean continua = true;
        
        if (!ingredientes.isEmpty()) {
       	 
       	imprimeIngredientes (ingredientes);
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
		                 insereLinha(ingredientes);
		                 imprimeIngredientes(ingredientes);
		                 break;
		             case "-":
		                 apagaLinha(ingredientes);
		                 imprimeIngredientes(ingredientes);
		                 break;
		             case "M":
		                 modificaLinha(ingredientes);
		                 imprimeIngredientes(ingredientes);
		                 break;
		             case "X":
		                 System.out.printf("%s terminada %n", msg);
		                 continua = false;
		                 break;
		             default:
		                 System.out.println("Opção inválida!!!");
		         }
	        
	   } while (continua);
	}
	
	private static void insereLinha(List<Ingrediente> ingredientes) {
		
		String nome = editarNome ();
		int j = editaTipoMedida ();
		String quantidade = editarQuantidade ();
		Ingrediente novoIngrediente = new Ingrediente (nome, Double.parseDouble(quantidade),TipoMedida.values()[j]);
		ingredientes.add(novoIngrediente);
	}
	
	private static void apagaLinha(List<Ingrediente> ingredientes) {
		
		String numLinha ="0"; 
		if (!ingredientes.isEmpty()) {
			imprimeIngredientes(ingredientes);
			do {
				numLinha = ConsoleUtils.getUserInput("Digite número da linha a ser apagada: %n" );
			} while (numLinha.isBlank()|| numLinha.isEmpty() || !(numLinha.matches("[0-9]*")) || Integer.parseInt(numLinha) >= ingredientes.size());
			ingredientes.remove(Integer.parseInt(numLinha));
		}
		else {
			System.out.println("Não há linha a apagar");
		}
		
	}
	
	private static void modificaLinha(List<Ingrediente> ingredientes) {
		String numLinha ="0"; 
		String novaLinha = "";
		if (!ingredientes.isEmpty()) {
			do {
				numLinha = ConsoleUtils.getUserInput("Digite número da linha que você quer alterar: %n" );
			} while (numLinha.isBlank()|| numLinha.isEmpty() || !(numLinha.matches("[0-9]*"))|| Integer.parseInt(numLinha) >= ingredientes.size());
			String nome = editarNome ();
			int j = editaTipoMedida ();
			String quantidade = editarQuantidade ();
			Ingrediente novoIngrediente = new Ingrediente (nome, Double.parseDouble(quantidade),TipoMedida.values()[j]);
			ingredientes.set(Integer.parseInt(numLinha), novoIngrediente);
		}
		else {
			System.out.println("Não há linha a modificar");
		}
	}
	
//	Utilitárias de Ingredientes
	
	private static String editarNome () {
		String novaLinha = "";
		do {
			novaLinha = ConsoleUtils.getUserInput("Digite o conteúdo da nova linha: %n" );
		} while (novaLinha.isBlank()|| novaLinha.isEmpty());
		return novaLinha;
	}
	
	private static String editarQuantidade () {
		String qtd ="";
		do {
			qtd = ConsoleUtils.getUserInput("Digite a quantidade:");
    	} while (qtd.isBlank()|| qtd.isEmpty()
    			|| !(qtd.matches(("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))));
		return qtd;
	}
	
	public static int editaTipoMedida () {
		int ret = 0;
    	StringBuilder sb = new StringBuilder();
        String[] options = new String[TipoMedida.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, TipoMedida.values()[i]));
        }
        System.out.println("Digite o número da opção:");
        String opcao1 = ConsoleUtils.getUserOption(sb.toString(), options);
        for (int i = 0; i < options.length; i++) {
            if (opcao1.equalsIgnoreCase(options[i])) {
            	ret = i;
            }
        }
        return ret;
	}
	

	
	private static void imprimeIngredientes (List<Ingrediente> ingredientes) {
		for (int i = 0; i < ingredientes.size(); i++) {
			System.out.printf("Linha %d: %s %.1f %s %n", i, ingredientes.get(i).getNome(), ingredientes.get(i).getQuantidade(),
			ingredientes.get(i).getTipo());
		} 
	}

}
