package br.com.letscode.cookbook.view;

import java.util.Locale;


import br.com.letscode.cookbook.domain.Receita;
import br.com.letscode.cookbook.domain.Rendimento;
import br.com.letscode.cookbook.enums.Categoria;
import br.com.letscode.cookbook.enums.TipoRendimento;

public class EditReceitaView {
    private Receita receita;

    public EditReceitaView(Receita receita) {
        this.receita = new Receita(receita);
    }

    private boolean mostrarMenuEdicao () {
    	 String[] options = {"N", "C", "T", "R", "I","P", "X"};
    	 System.out.println("                               MENU DE EDIÇÃO");
         StringBuilder menuEdicao = new StringBuilder("_".repeat(100) + "%n");
         menuEdicao.append (" N : Editar Nome %n");
         menuEdicao.append (" C : Editar Categoria %n");
         menuEdicao.append (" T : Editar Tempo de Preparo %n");
         menuEdicao.append (" R : Editar Rendimento %n");
         menuEdicao.append (" I : Editar Ingredientes %n");
         menuEdicao.append (" P : Editar Preparo %n");
         menuEdicao.append (" X : SAIR!! %n");
         menuEdicao.append("_".repeat(100) + "%n");
         
         String opcao = ConsoleUtils.getUserOption(menuEdicao.toString(), options).toUpperCase(Locale.getDefault());
         switch (opcao) {
             case "N":
                 editarNome();
                 break;
             case "C":
                 editarCategoria();
                 break;
             case "T":
                 editarTempo();
                 break;
             case "R":
                 editarRendimento();
                 break;
             case "I":
                 editarIngredientes();
                 break;
             case "P":
             	editarPreparo();
             	break;
             case "X":
                 System.out.println("Fim da Edição");
                 return false;
             default:
                 System.out.println("Opção inválida!!!");
         }
         return true;

    }
    
    public Receita edit() {
    	boolean confirm = false;
    	
    	do {
    		
    	}while (mostrarMenuEdicao ());
       
    	String opcao = ConsoleUtils.getUserOption("Você confirma a operação anterior?%nS - Sim   N - Não", "S", "N");
        if (opcao.equalsIgnoreCase("S")) {
           confirm = true;
        }
    	
        if (confirm) {
            return receita;
        } else {
            return null;
        }
  }

    private void editarNome() {
    	if (!(receita.getNome().isBlank()|| receita.getNome().isEmpty())) {
    		System.out.printf("Nome atual: %s %n", receita.getNome());
    	}
    	String novoNome ="";
    	do {
    	novoNome = ConsoleUtils.getUserInput("Digite o novo nome da receita:");
    	} while (novoNome.isBlank()|| novoNome.isEmpty());
    	receita.setNome(novoNome);
    	new ReceitaView (receita).fullView (System.out);
    }
    
    private void editarCategoria() {
    	 if (receita.getCategoria() != null) {
    		 System.out.printf ("Categoria atual: %s %n", receita.getCategoria());
    	 }
    	StringBuilder sb = new StringBuilder("Digite a nova categoria da receita\n");
         String[] options = new String[Categoria.values().length];
         for (int i = 0; i < options.length; i++) {
             options[i] = String.valueOf(i);
             sb.append(String.format("%d - %s%n", i, Categoria.values()[i]));
         }
         String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        
         for (int i = 0; i < options.length; i++) {
             if (opcao.equalsIgnoreCase(options[i])) {
                 receita.setCategoria(Categoria.values()[i]);
                 break;
             }
         }
         new ReceitaView (receita).fullView (System.out);
    }
    
    private void editarTempo() {
    	// Imprime tempo atual (se !=0)
    	// Coloca Prompt
    	// Coleta entrada e seta novo tempo
    	// Imprime receita
    	if (receita.getTempoPreparo() != 0) {
    		System.out.printf("Tempo de preparo atual (em minutos): %.1f %n", receita.getTempoPreparo());
    	}
    	String novoTempo = "";
    	do {
    		novoTempo = ConsoleUtils.getUserInput("Digite o novo tempo de Preparo (em minutos):");
    	} while (novoTempo.isBlank()|| novoTempo.isEmpty()
    			|| !(novoTempo.matches(("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))));
    	receita.setTempoPreparo(Double.parseDouble(novoTempo));
    	new ReceitaView (receita).fullView (System.out);
    }
    
    private void editarRendimento() {
    	if (receita.getRendimento() != null ) {
    		if (receita.getRendimento().getMinimo() != receita.getRendimento().getMaximo()) {
    			System.out.printf("Rendimento máximo atual: %s %s;   Rendimento mínimo atual: %s %s%n",
    					receita.getRendimento().getMaximo(), receita.getRendimento().getTipo().name(), 
    					receita.getRendimento().getMinimo(), receita.getRendimento().getTipo().name());
    		}
    		else {
    			System.out.printf("Rendimento atual: %s %s%n",
    					receita.getRendimento().getMaximo(), receita.getRendimento().getTipo().name() );
    		}
    	}
    	int j = ReceitaViewUtils.editaTipoRendimento ();
    	
    	String novoRendimentoMax = "";
    	do {
        		novoRendimentoMax = ConsoleUtils.getUserInput("Digite o Rendimento máximo:");
        	} while (novoRendimentoMax.isBlank()|| novoRendimentoMax.isEmpty() || !(novoRendimentoMax.matches("[0-9]*")));
    	
    	
    	String novoRendimentoMin = "";
    	do {
    		novoRendimentoMin = ConsoleUtils.getUserInput("Digite o Rendimento mínimo:");
    	} while (novoRendimentoMin.isBlank()|| novoRendimentoMin.isEmpty() || !(novoRendimentoMin.matches("[0-9]*")));
    	
    	Rendimento novoRendimento = new Rendimento (Integer.parseInt(novoRendimentoMin), Integer.parseInt(novoRendimentoMax), 
    			TipoRendimento.values () [j]);
    	receita.setRendimento(novoRendimento);
    	new ReceitaView (receita).fullView (System.out);
    }
    	

    	
    
    
    private void editarIngredientes() {
    	ReceitaViewUtils2.editordeIngredientes(receita.getIngredientes(), "EDIÇÃO DE INGREDIENTES");
    	new ReceitaView (receita).fullView (System.out);
    }
        
    private void editarPreparo() {
    	ReceitaViewUtils.editordeLinha (receita.getPreparo(),"EDIÇÃO DE PREPARO");
    	new ReceitaView (receita).fullView (System.out);
    }
    
    
    
}
