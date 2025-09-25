import java.util.Scanner;
public class Main {
	
	static Scanner input = new Scanner(System.in);
	static int vendaAtualCont = 0;
    static int historicoCont = 0;
    static final int MAX_PRODUTOS = 50;
    static String  dataHora = "26/09/2025 09h00"; // Data e hora fixas para exemplo apenas
	
    // método estático que representa o menu
      static void menuOpcoes() {   
      System.out.println("""
        =========== MENU ===========
        1. Inicializar base
        2. Exibir catálogo de produtos
        3. Adicionar item à venda
        4. Ver resumo da venda atual
        5. Finalizar venda (gerar histórico e Nota Fiscal)
        6. Ver histórico de vendas
        7. Buscar venda específica histórico
        8. (Admin) Repor estoque
        9. (Admin) Relatório de estoque baixo
        =============================
        """);
	}
    
    // ITEM 1
    static void inicializarBase(int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos, int[] estoquesProdutos) {
        idsProdutos[0] = 101; nomesProdutos[0] = "Mouse Game"; precosProdutos[0] = 150.00; estoquesProdutos[0] = 2; // quantidade em estoque
        idsProdutos[1] = 203; nomesProdutos[1] = "Teclado Mecanico"; precosProdutos[1] = 350.00; estoquesProdutos[1] = 1; 
        idsProdutos[2] = 301; nomesProdutos[2] = "Headset 7.1"; precosProdutos[2] = 420.50; estoquesProdutos[2] = 1;
        idsProdutos[3] = 401; nomesProdutos[3] = "Fone"; precosProdutos[3] = 250.90; estoquesProdutos[3] = 0;

		vendaAtualCont = 0;
        historicoCont = 0;
        System.out.println("Base iniciada com sucesso!"); 
    }
    
    //ITEM 2
    static void catalogoProdutos(int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos, int[] estoquesProdutos) {
		System.out.println("Lista de produtos com estoque positivo:");
		System.out.printf("%-5s | %-20s | %-15s | %-6s\n", "ID", "Produto", "Valor unitário", "Estoque");
        System.out.println("-------------------------------------------------------");
		//Percorre os produtos e imprime os que estão com estoque positivo
		for (int i = 0; i < idsProdutos.length; i++) { 
			if(idsProdutos[i] != 0 && estoquesProdutos[i] > 0) { //
				System.out.printf("%-5d | %-20s | R$ %-13.2f | %-6d\n",idsProdutos[i], nomesProdutos[i], precosProdutos[i], estoquesProdutos[i]);
			}
		}
    }
    
    //ITEM 3
    static void adicionarItem(int[] idsProdutos, int[] estoquesProdutos, int[] vendaAtualIds, int[] vendaAtualQuantidades) {
        System.out.print("Digite o ID do produto: ");
        int id = input.nextInt();

        // Verifica se o ID existe no vetor de produtos  
		int indiceProduto = -1;  //-1 é um sinal de que ainda não achamos nada (pois não existe posição -1 em um vetor).
		//Se no final do loop continuar -1, quer dizer que o produto não existe.
		for (int i = 0; i < idsProdutos.length; i++) { //percorre a lista
            if (idsProdutos[i] == id) {
                indiceProduto = i; //guarda o indice onde o produto foi encontrado
                break;
            }
        }
        if (indiceProduto == -1) {
            System.out.println("ID inválido!");
            return;
        }

        // O usuario informa quantas unidades quer comprar
        System.out.print("Digite a quantidade: ");
        int quantidade = input.nextInt();

        // Checar se o estoque é suficiente
		// Se o estoque disponível (estoquesProdutos[indiceProduto]) for menor do que a quantidade desejada, não deixa comprar.
        if (estoquesProdutos[indiceProduto] < quantidade) {
            System.out.println("Estoque insuficiente!");
            return;
        }

        // Adiciona o item aos vetores da venda atual
        vendaAtualIds[vendaAtualCont] = id;
		vendaAtualQuantidades[vendaAtualCont] = quantidade;
		vendaAtualCont++; // contador global de quantos itens já foram adicionados na venda atual
		// Toda vez que você adiciona um item, ele soma +1.

        System.out.println("Item adicionado com sucesso!");
    }

    //ITEM 4
    //Ver resumo da venda atual: Exibe os itens, subtotais e o total da venda atual de forma clara.
    static void resumoAtual(int[] vendaAtualIds, int[] vendaAtualQuantidades, int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos) {
        if (vendaAtualCont == 0) {
            System.out.println("Nenhum item na venda atual.");
            return; //
        }

        System.out.printf("\nID |       Produto      | Valor unitário  | Estoque | Subtotal\n");
        System.out.println("---------------------------------------------------------");

        double totalVenda = 0;

        for (int i = 0; i < vendaAtualCont; i++) { //Percorre os itens da venda atual
            int id = vendaAtualIds[i];
            int quantidade = vendaAtualQuantidades[i];

            // Encontra o índice do produto para pegar nome e preço
            int indiceProduto = -1;  
            for (int j = 0; j < nomesProdutos.length; j++) {  
                if (idsProdutos[j] == id) {
                    indiceProduto = j;
                    break;
                }
            }

            String nome = nomesProdutos[indiceProduto];
            double preco = precosProdutos[indiceProduto];
            double subtotal = preco * quantidade;

            totalVenda += subtotal;

            System.out.printf("%d   |   %-12s   |   R$ %.2f     |   %d   |  R$ %.2f\n", id, nome, preco, quantidade, subtotal);
        }

        System.out.println("---------------------------------------------------------");
        System.out.printf("TOTAL DA VENDA: R$ %.2f\n", totalVenda);
    }

    //ITEM 5
    static void finalizarVenda(int[] vendaAtualIds, int[] vendaAtualQuantidades,
	                           int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos, int[] estoquesProdutos,
	                           int[] historicoIdsPedidos, double[] historicoValoresPedidos, int[][] historicoItensVendidos,
	                           int proximoPedidoId) {
		//Verifica se existe algum produto na venda atual
		if(vendaAtualCont == 0){ 
			System.out.println("Nenhum item na venda atual.");
			return;
		}

		//Gera um novo ID para o pedido
		int pedidoId = proximoPedidoId++;
        double totalVenda = 0;

		//cada venda finalizada fica registrada no vetor de IDs de pedidos. 
		historicoIdsPedidos[historicoCont] = pedidoId;  
		
		for (int i = 0; i < vendaAtualCont; i++) { //Pega o ID e a quantidade do item na venda atual
            int id = vendaAtualIds[i];
            int qtd = vendaAtualQuantidades[i];

			int indiceProduto = -1;
            for (int j = 0; j < idsProdutos.length; j++) { //Encontra o índice do produto no catálogo (idsProdutos) para acessar preço e estoque
                if (idsProdutos[j] == id) {
                    indiceProduto = j;
                    break;
                }
            }

			double preco = precosProdutos[indiceProduto];
            double subtotal = preco * qtd;
            totalVenda += subtotal;
			
			// Registra no histórico de itens vendidos
			// Cada linha da matriz historicoItensVendidos guarda [ID do Pedido, ID do Produto, Quantidade].
			// O índice historicoCont * MAX_PRODUTOS + i garante que cada venda ocupe um bloco diferente da matriz.
			historicoItensVendidos[historicoCont * MAX_PRODUTOS + i][0] = pedidoId; 
            historicoItensVendidos[historicoCont * MAX_PRODUTOS + i][1] = id;
            historicoItensVendidos[historicoCont * MAX_PRODUTOS + i][2] = qtd;

			estoquesProdutos[indiceProduto] -= qtd; // Subtrai a quantidade vendida do estoque do produto.

	}
		
			historicoValoresPedidos[historicoCont] = totalVenda; //Salva o valor total no histórico
     		historicoCont++; // Para que próximo pedido seja registrado na próxima posição.

	imprimirNotaFiscal(pedidoId, vendaAtualIds, vendaAtualQuantidades, idsProdutos, nomesProdutos, precosProdutos, vendaAtualCont, totalVenda);

        vendaAtualCont = 0; // Limpa a venda atual (carrinho)
        System.out.println("Venda finalizada com sucesso!\n");
    }

	public static void imprimirNotaFiscal(int pedidoId, int[] vendaAtualIds, int[] vendaAtualQuantidades,
		                                  int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos,
		                                  int vendaAtualCont, double totalVenda){
		

        System.out.println("*********************************************************************************************");
        System.out.println("*                                      MACKSHOP                                            *");
        System.out.println("*                                CNPJ: 12.345.678/0001-99                                  *");
        System.out.println("*********************************************************************************************");
        System.out.println("*                          NOTA FISCAL - VENDA AO CONSUMIDOR                               *");
        System.out.printf ("* Pedido ID: %d                                                                          *\n", pedidoId);
        System.out.printf ("* Data de Emissão: %s                                                        *\n", dataHora);
        System.out.println("*********************************************************************************************");
        System.out.printf ("* %s| %s| %s| %s| %s| %s *\n", "#", "ID", "DESCRIÇÃO", "QTD", "VL. UNIT.", "VL. TOTAL");
        System.out.println("---------------------------------------------------------------------------------------------");

        double subtotal = 0;
        for (int i = 0; i < vendaAtualCont; i++) {
            int id = vendaAtualIds[i];
            int qtd = vendaAtualQuantidades[i];

            int indiceProduto = -1;
            for (int j = 0; j < idsProdutos.length; j++) {
                if (idsProdutos[j] == id) {
                    indiceProduto = j;
                    break;
                }
            }

            String nome = nomesProdutos[indiceProduto];
            double preco = precosProdutos[indiceProduto];
            double totalItem = preco * qtd;
            subtotal += totalItem;

            System.out.printf("* %d| %d| %s| %d| R$ %.2f| R$ %.2f *\n",
                              (i+1), id, nome, qtd, preco, totalItem);
        }

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("* %s R$ %.2f *\n", "SUBTOTAL", subtotal);
        System.out.printf("* %s R$ %.2f *\n", "TOTAL", totalVenda);
        System.out.println("*********************************************************************************************");
        System.out.println("*                OBRIGADO PELA PREFERÊNCIA! VOLTE SEMPRE!                                   *");
        System.out.println("*********************************************************************************************\n");
    }
	

	

    //ITEM 6
    //: Percorre os vetores de histórico e imprime um resumo de todas as vendas finalizadas 
    static void historicoitensVendidos(int[] historicoIdsPedidos, double[] historicoValoresPedidos) { 
		System.out.println("\n--- HISTÓRICO DE VENDAS ---");

		if (historicoCont == 0) { 
			System.out.println("Nenhum pedido registrado."); 
			return; 
		}
		//Percorre os vetores de histórico e imprime um resumo de todas as vendas finalizadas (ex: Pedido ID: 1001 - Valor Total: R$ 1070.50).
		for (int i = 0; i < historicoCont; i++){
			System.out.printf("Pedido ID: %d - Valor Total: R$ %.2f\n",historicoIdsPedidos[i], historicoValoresPedidos[i]);	
		}

	}

    //ITEM 7
		
    static void vendaEspecifica(int[] historicoIdsPedidos, double[] historicoValoresPedidos, int[][] historicoItensVendidos,
                                int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos) {

        System.out.print("Digite o ID de Pedido: ");
        int idPedidoBuscado = input.nextInt();

        int[] vendaIds = new int[MAX_PRODUTOS];
        int[] vendaQtde = new int[MAX_PRODUTOS];
        int cont = 0;
        double totalVenda = 0;
		
		System.out.printf("--Nota Fiscal do Pedido %d--\n", idPedidoBuscado);

        for (int i = 0; i < historicoItensVendidos.length; i++) {
            if (historicoItensVendidos[i][0] == idPedidoBuscado) {
                vendaIds[cont] = historicoItensVendidos[i][1];
                vendaQtde[cont] = historicoItensVendidos[i][2];

                int indiceProduto = -1;
                for (int j = 0; j < idsProdutos.length; j++) {
                    if (idsProdutos[j] == vendaIds[cont]) {
                        indiceProduto = j;
                        break;
                    }
                }

                totalVenda += precosProdutos[indiceProduto] * vendaQtde[cont];
                cont++;
            }
        }

        if (cont == 0) {
            System.out.println("Pedido não encontrado!");
            return;
        }

        imprimirNotaFiscal(idPedidoBuscado, vendaIds, vendaQtde, idsProdutos, nomesProdutos, precosProdutos, cont, totalVenda);
    }

    //ITEM 8 
    static void reporEstoque(int[] idsProdutos, int[] estoquesProdutos) {
		// Solicita um ID de produto e uma quantidade	
		System.out.print("Digite o ID do produto que deseja repor estoque:");
		int idDigitado = input.nextInt();
        int indiceID = -1;
        for(int i = 0; i < idsProdutos.length; i++) {
            if (idsProdutos[i] == idDigitado) {
                indiceID = i;
                break; // para o laço e sai dele
            }
        }

        if (indiceID == -1) {
            System.out.println("ID inválido!");
            return; // sai do método aqui, não pergunta quantidade
        }

		System.out.print("Digite a nova quantidade de estoque:");
		int quant = input.nextInt();
        estoquesProdutos[indiceID] += quant;

       System.out.println("Estoque atualizado! ID: " + idDigitado + " Novo Estoque: " + estoquesProdutos[indiceID]);

	}
    
    //ITEM 9
    static void relatorioEstoque(int[] idsProdutos, String[] nomesProdutos, int[] estoquesProdutos) {
        System.out.println("--- LISTA DE PRODUTOS COM ESTOQUE ABAIXO DO LIMITE ---");

        for (int p = 0; p < estoquesProdutos.length; p++) {
            if (estoquesProdutos[p] < 10 && idsProdutos[p] != 0) {
                System.out.println("ID: " + idsProdutos[p] + " | Nome: " + nomesProdutos[p] + " | Estoque: " + estoquesProdutos[p]);
            }
        }
    }
      
    public static void main(String[] args) { 
				// Catálogo de produtos
	    int[] idsProdutos = new int[MAX_PRODUTOS];
        int[] estoquesProdutos = new int[MAX_PRODUTOS];
        String[] nomesProdutos = new String[MAX_PRODUTOS];
        double[] precosProdutos = new double[MAX_PRODUTOS];
	
	    //venda atual
	    int[] vendaAtualIds = new int[MAX_PRODUTOS];
        int[] vendaAtualQuantidades = new int[MAX_PRODUTOS];
	
	
	    //Histórico de Vendas
	     int[] historicoIdsPedidos = new int[MAX_PRODUTOS];
        double[] historicoValoresPedidos = new double[MAX_PRODUTOS];
        int[][] historicoItensVendidos = new int[MAX_PRODUTOS * MAX_PRODUTOS][3];

		 int proximoPedidoId = 1001;
		
      	int escolhaMenu;
        boolean baseInicializada = false; // começa com a base não inicializada
        do {
            menuOpcoes();
            System.out.print("Selecione uma opção:");
            escolhaMenu = input.nextInt();

            if (baseInicializada != true && escolhaMenu != 1) {
                System.out.println("Você precisa iniciar a base primeiro (opção 1).");
               continue; // volta para o início do loop
            }
            switch (escolhaMenu) {
             case 1:  
              inicializarBase(idsProdutos, nomesProdutos, precosProdutos, estoquesProdutos);
              baseInicializada = true; // agora pode continuar
              break;
             case 2:
                catalogoProdutos(idsProdutos, nomesProdutos, precosProdutos, estoquesProdutos);
                break;
             case 3:  
                adicionarItem(idsProdutos, estoquesProdutos, vendaAtualIds, vendaAtualQuantidades);
                break;
             case 4:  
                resumoAtual(vendaAtualIds, vendaAtualQuantidades, idsProdutos, nomesProdutos, precosProdutos);
                break;
              case 5:  
                finalizarVenda(vendaAtualIds, vendaAtualQuantidades,
                                         idsProdutos, nomesProdutos, precosProdutos, estoquesProdutos,
                                         historicoIdsPedidos, historicoValoresPedidos, historicoItensVendidos,
                                         proximoPedidoId);
                break;
             case 6:  
                historicoitensVendidos(historicoIdsPedidos, historicoValoresPedidos);
                break;
             case 7:
                vendaEspecifica(historicoIdsPedidos, historicoValoresPedidos, historicoItensVendidos,
                                          idsProdutos, nomesProdutos, precosProdutos);  
                break;
             case 8:  
                reporEstoque(idsProdutos, estoquesProdutos);
                break;
             case 9:  
                relatorioEstoque(idsProdutos, nomesProdutos, estoquesProdutos);
                break;
             default: 
                System.out.println("Número inválido");
            }
            
        } while (escolhaMenu != 0);
        input.close();
    }
}
