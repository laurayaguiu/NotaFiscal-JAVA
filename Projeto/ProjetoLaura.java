import java.util.Scanner;
public class ProjetoLaura {
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
    
    // ITEM 2
    static void inicializarBase(int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos, int[] estoquesProdutos) {
        idsProdutos[0] = 101; nomesProdutos[0] = "Mouse Game"; precosProdutos[0] = 150.00; estoquesProdutos[0] = 2; // quantidade em estoque
        idsProdutos[1] = 203; nomesProdutos[1] = "Teclado Mecanico"; precosProdutos[1] = 350.00; estoquesProdutos[1] = 1; 
        idsProdutos[2] = 301; nomesProdutos[2] = "Headset 7.1"; precosProdutos[2] = 420.50; estoquesProdutos[2] = 1;
        idsProdutos[3] = 401; nomesProdutos[3] = "Fone"; precosProdutos[3] = 250.90; estoquesProdutos[3] = 0;


        System.out.print("Base iniciada com sucesso!"); 
    }
    
    //ITEM 2
    static void catalogoProdutos(int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos, int[] estoquesProdutos) {
		System.out.print("Lista de produtos com estoque positivo:");
		System.out.println("\nID |       Produto      | Valor unitário  | Estoque ");
        System.out.println("-------------------------------------------------------");
		//Percorre os produtos e imprime os que estão com estoque positivo
		for (int i = 0; i < idsProdutos.length; i++) { 
			if(idsProdutos[i] != 0 && estoquesProdutos[i] > 0) { //
				System.out.printf("%d   |      %s       |      R$ %.2f    |     %d\n",idsProdutos[i], nomesProdutos[i], precosProdutos[i], estoquesProdutos[i]);
			}
		}
    }
    
    //ITEM 3
    static void adicionarItem(int[] idsProdutos, int[] estoquesProdutos, int[] vendaAtualIds, int[] vendaAtualQuantidades) {
        System.out.print("Digite o ID do produto: ");
        int id = entrada.nextInt();

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
        int quantidade = entrada.nextInt();

        // Checar se o estoque é suficiente
		// Se o estoque disponível (estoquesProdutos[indiceProduto]) for menor do que a quantidade desejada, não deixa comprar.
        if (estoquesProdutos[indiceProduto] < quantidade) {
            System.out.println("Estoque insuficiente!");
            return;
        }

        // Adiciona o item aos vetores da venda atual
        vendaAtualIds[indiceVenda] = id;
        vendaAtualQuantidades[indiceVenda] = quantidade;
        vendaAtualCont++; // contador global de quantos itens já foram adicionados na venda atual
		// Toda vez que você adiciona um item, ele soma +1.

        System.out.println("Item adicionado com sucesso!");
    }

    //ITEM 4
    //Ver resumo da venda atual: Exibe os itens, subtotais e o total da venda atual de forma clara.
    static void resumoAtual(int[] vendaAtualIds, String[] nomesProdutos, double[] precosProdutos, int[] vendaAtualQuantidades) {
        if (vendaAtualCont == 0) {
            System.out.println("Nenhum item na venda atual.");
            return; //
        }

        System.out.println("\nID | Produto        | Quantidade | VL. UNIT. | VL. TOTAL");
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

            System.out.printf("%d   |   %s   |   %d   |   R$ %.2f     |    R$ %.2f\n", id, nome, quantidade, preco, subtotal);
        }

        System.out.println("---------------------------------------------------------");
        System.out.printf("TOTAL DA VENDA: R$ %.2f\n", totalVenda);
    }

    //ITEM 5
    static void finalizarVenda(/*colocar parâmetros*/) {
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

	imprimirNotaFiscal(pedidoId, totalVenda);

        vendaAtualCont = 0; // Limpa a venda atual (carrinho)
        System.out.println("Venda finalizada com sucesso!\n");
    }

	public static void imprimirNotaFiscal(int pedidoId, double totalVenda){
		
		System.out.println("\n========= NOTA FISCAL =========");
        System.out.println("Pedido nº " + pedidoId);
        System.out.println("ID | Produto        | Quantidade | VL. UNIT. | VL. TOTAL");
        System.out.println("---------------------------------------------------------");

        for (int i = 0; i < vendaAtualCont; i++) { //Percorre todos os itens que o cliente adicionou à venda atual
            int id = vendaAtualIds[i];
            int qtd = vendaAtualQuantidades[i];
			int indiceProduto = -1; //Indica que ainda não encontramos o produto no catálogo.
            for (int j = 0; j < idsProdutos.length; j++) { //Procura a posição do produto no catálogo para acessar nome e preço.
                if (idsProdutos[j] == id) {
                    indiceProduto = j;
                    break;
                }
            }
			String nome = nomesProdutos[indiceProduto];
            double preco = precosProdutos[indiceProduto];
            double subtotal = preco * qtd;

            System.out.printf("%d | %-12s | %d | R$ %.2f | R$ %.2f\n", id, nome, qtd, preco, subtotal);
        }

        System.out.println("---------------------------------------------------------");
        System.out.printf("TOTAL: R$ %.2f\n", totalVenda);
        System.out.println("===============================\n");
    }

	

    //ITEM 6
    //: Percorre os vetores de histórico e imprime um resumo de todas as vendas finalizadas 
    static void historicoitensVendidos() { //matriz
		//Percorre os vetores de histórico e imprime um resumo de todas as vendas finalizadas (ex: Pedido ID: 1001 - Valor Total: R$ 1070.50).
		for (int id = 0; id < historicoIdsPedidos.lenght; i++){
			
		}

		for (int v = 0; v < historicoValoresPedidos.lenght;i++){

		}

		//Ver como imprimir os dois juntos!!!
	}

    //ITEM 7
    static void vendaEspecifica(int[][] historicoItensVendidos) {
		//Solicita o ID do pedido
		System.out.print("Digite o ID de Pedido:");
		int idPedidoBuscado = input.nextInt();

		//Cabeçalho da nota fiscal
		System.out.printf("--Nota Fiscal do Pedido %d--\n", idPedidoBuscado);
		

		//Percorre a matriz historicoItensVendidos e encontra todos os itens do pedido
		for(int i = 0; i <  historicoItensVendidos.lenght; i++){
			int idDoProduto = historicoItensVendidos[i][0];
			int idDoProduto = historicoItensVendidos[i][1];
			int quantidadeVendida = historicoItensVendidos[i][2];
			if(idDoPedido == idPedidoBuscado){
				notaFiscal(
			
			
		//Reimprime a nota fiscal completa da transação 
		imprimirNotaFiscal(int pedidoId, double totalVenda)
			}
		}
	}

    //ITEM 8 
    static void reporEstoque(int[] idsProdutos, int[] estoquesProdutos) {
		// Solicita um ID de produto e uma quantidade	
		System.out.print("Digite o ID do produto que deseja repor estoque:");
		int idDigitado = input.nextInt();
        int indiceID = -1;
        for(i = 0; i < idsProdutos.lenght; i++) {
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
        estoqueProdutos[indiceID] += quant;

        System.out.println("Estoque atualizado! "\n "ID:" +idDigitado + "Novo Estoque:" + estoquesProdutos[indiceID]);

	}
    
    //ITEM 9
    static void relatorioEstoqueBaixo(int[] idsProdutos, String[] nomesProdutos, int[] estoquesProdutos) {
        System.out.println("--- LISTA DE PRODUTOS COM ESTOQUE ABAIXO DO LIMITE ---");

        for (int p = 0; p < estoquesProdutos.length; p++) {
            if (estoquesProdutos[p] < 10) {
                System.out.println("ID: " + idsProdutos[p] + " | Nome: " + nomesProdutos[p] + " | Estoque: " + estoquesProdutos[p]);
            }
        }
    }

    //Catálogo de produtos, ESTA FALTANDO DIZER A QUANTIDAD DE POSICOES EM CADA ARRAY!
    //  (OBRIGATÓTIO EM JAVA DETERMINAR O TAMANHO DA LISTA)!
    int[] idsProdutos = new int[50];
    int[] estoquesProdutos = new int[50];
    String[] nomesProdutos = new String[50];
    double[] precosProdutos = new double[50];

    //venda atual
    int[] vendaAtualIds = new int[50];
    int[] vendaAtualQuantidades = new int[50];
	int vendaAtualCont = 0;

    //Histórico de Vendas
    int[] historicoIdsPedidos = new int[50];
    double[] historicoValoresPedidos = new double[50];
    int[][] historicoItensVendidos = new int[50][3];
      
    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in);
        int escolhaMenu = new int;
        boolean baseInicializada = false; // começa com a base não inicializada
        do {
            menuOpcoes();
            System.out.print("Selecione uma opção (obrigatório iniciar base primeiro):");
            escolhaMenu = input.nextInt();

            if (baseInicializada != true && escolhaMenu != 1) {
                System.out.println("Você precisa iniciar a base primeiro (opção 1).");
               continue; // volta para o início do loop
            }
            switch (escolhaMenu) {
             case 1:  
              inicializarBase();
              baseInicializada = true; // agora pode continuar
              break;
             case 2:
                catalogoProdutos();
                break;
             case 3:  
                adicionarItem();
                break;
             case 4:  
                resumoAtual();
                break;
             case 5:  
                finalizarVenda();
                break;
             case 6:  
                historicoVendas();
                break;
             case 7:
                vendaEspecifica();  
                break;
             case 8:  
                reporEstoque();
                break;
             case 9:  
                relatorioEstoque();
                break;
             default: 
                System.out.println("Número inválido");
            }
            
        } while (escolhaMenu != 0);
        input.close();
    }
}
