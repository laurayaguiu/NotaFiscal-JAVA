import java.util.Scanner;
public class ProjetoLaura {
    // método estático que representa o menu
      static void menuOpcoes() {   //não deveria ter um public?
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
        idsProdutos[0] = 101; nomesProdutos[0] = "Mouse Game"; precosProdutos[0] = 150.00; estoquesProdutos[0] = 2;
        idsProdutos[1] = 203; nomesProdutos[1] = "Teclado Mecanico"; precosProdutos[1] = 350.00; estoquesProdutos[1] = 1;
        idsProdutos[2] = 301; nomesProdutos[2] = "Headset 7.1"; precosProdutos[2] = 420.50; estoquesProdutos[2] = 1;
        idsProdutos[3] = 401; nomesProdutos[3] = "Fone"; precosProdutos[3] = 250.90; estoquesProdutos[3] = 0;

		// base = true; valida a inicialização da base ,coloquei no main

        System.out.print("Base iniciada com sucesso!"); 
    }
    
    //ITEM 2
    static void catalogoProdutos(int[] idsProdutos, String[] nomesProdutos, double[] precosProdutos, int[] estoquesProdutos) {
		System.out.print("Lista de produtos com estoque positivo:");
		System.out.println("\nID |       Produto      | Valor unitário  | Estoque ");
        System.out.println("-------------------------------------------------------");
		//Percorre os produtos e imprime os que estão com estoque positivo
		for (int i = 0; i < idsProdutos.length; i++) {
			if(idsProdutos[i] != 0 && estoquesProdutos[i] > 0) {
				System.out.printf("%d   |      %s       |      R$ %.2f    |     %d\n",idsProdutos[i], nomesProdutos[i], precosProdutos[i], estoquesProdutos[i]);
			}
			
		}

    }
    
    //ITEM 3
    static void adicionarItem(int[] idsProdutos, int[] estoquesProdutos, int[] vendaAtualIds, int[] vendaAtualQuantidades) {
        System.out.print("Digite o ID do produto: ");
        int id = entrada.nextInt();

        // Verifica se o ID existe e pega o índice onde ele 
        int indiceProduto = -1; // numero falso
        for (int i = 0; i < idsProdutos.length; i++) {
            if (idsProdutos[i] == id) {
                indiceProduto = i; // indice do produto q está o ID informado
                break;
            }
        }

        if (indiceProduto == -1) {
            System.out.println("ID inválido!");
            return;
        }

        // Pede a quantidade
        System.out.print("Digite a quantidade: ");
        int quantidade = entrada.nextInt();

        // "Se dentro da lisa estoqueProdutos no indice que corresponde ao ID informado a quantidade é menor q a solicitada"
        if (estoquesProdutos[indiceProduto] < quantidade) {
            System.out.println("Estoque insuficiente!");
            return;
        }

        // Encontra a primeira posição vazia na lista da venda atual
        int indiceVenda = -1; // numero falso
        for (int i = 0; i < vendaAtualIds.length; i++) {
            if (vendaAtualIds[i] == 0) { 
                indiceVenda = i; // indice vazio
                break;
            }
        }

        if (indiceVenda == -1) {
            System.out.println("Não há espaço para adicionar mais itens na venda!");
            return;
        }

        // Adiciona o item aos vetores da venda atual
        vendaAtualIds[indiceVenda] = id;
        vendaAtualQuantidades[indiceVenda] = quantidade;
        vendaAtualCont++; // incrementa o contador global, essa linha foi o chat q encrementou para mim ent não entendi direito como funciona

        System.out.println("Item adicionado com sucesso!");
    }

    //ITEM 4
    //Ver resumo da venda atual: Exibe os itens, subtotais e o total da venda atual de forma clara.
    static void resumoAtual(int[] vendaAtualIds, String[] nomesProdutos, double[] precosProdutos, int[] vendaAtualQuantidades) {
        if (vendaAtualCont == 0) {
            System.out.println("Nenhum item na venda atual.");
            return; // termina funçao j´q nao tem item 
        }

        System.out.println("\nID | Produto        | Quantidade | VL. UNIT. | VL. TOTAL");
        System.out.println("---------------------------------------------------------");

        double totalVenda = 0;

        for (int i = 0; i < vendaAtualCont; i++) {
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
    static void finalizarVenda() {
		//Verifica se existe algum produto na venda atual
		if(vendaAtualCont == 0){ 
			System.out.println("Nenhum item na venda atual.");
		}

		//Gera um novo ID para o pedido

		//Salva o ID do pedido 
		historicoIdsPedidos = //salvar Id

		//Salva o valor total do pedido
		historicoValoresPedidos = //salvar valor total
			
		//Para cada item na venda atual, adiciona uma linha na matriz historicoItensVendidos

		// Debita a quantidade do estoquesProdutos

		//Chama o método para imprimir a nota fiscal formatada
		notaFiscal(//parâmentros);

		//Limpa os vetores da venda atual, deixando o sistema pronto para a próxima transação
		vendaAtualCont = 0 //não seria 2 arrays, com a quantidade e o id?
	}
	public static void notaFiscal(){

		//Imprimir a nota fiscal do jeito que ele pediu -> usando printf()

	}

    //ITEM 6
    static void historicoitensVendidos() { //matriz
		//Percorre os vetores de histórico e imprime um resumo de todas as vendas finalizadas (ex: Pedido ID: 1001 - Valor Total: R$ 1070.50).
		for (int id = 0; id < historicoIdsPedidos.lenght; i++){
			
		}

		for (int v = 0; v < historicoValoresPedidos.lenght;i++){

		}

		//Ver como imprimir os dois juntos!!!
	}

    //ITEM 7
    static void vendaEspecifica() {
		//Solicita o ID do pedido
		System.out.print("Digite o ID de Pedido:");
		int idPedido = input.nextInt();

		//Percorre a matriz historicoItensVendidos e encontra todos os itens do pedido
		while (//estiver na linha do pedido, armazenar o item){
			
		//Reimprime a nota fiscal completa da transação 
		notaFiscal(//parâmetros) -> chamar o método nota fiscal de novo?
		}
	}

    //ITEM 8
    static void reporEstoque() {
		// Solicita um ID de produto e uma quantidade	
		System.out.print("Digite um ID de produto:");
		int idDigitado = input.nextInt();

		System.out.print("Digite a quantidade:");
		int quant = input.nextInt();

		//Adiciona a quantidade no estoque no produto certo
		
	}
    
    //ITEM 9
    static void relatorioEstoqueBaixo() {
		 //Imprime uma lista de todos os produtos cujo estoque esteja abaixo de um limiar pré-definido (ex: 10 unidades)
		System.out.println("---LISTA DE PRODUTOS COM ESTOQUE ABAIXO DO LIMITE---")
		for (int p = 0; p < estoquesProdutos.lenght; p++){
			if (estoquesProdutos[p] < //limite){
				System.out.println(//produto, id, quantidade talvez)
		]
	}


    //Catálogo de produtos, ESTA FALTANDO DIZER A QUANTIDAD DE POSICOES EM CADA ARRAY!
    //  (OBRIGATÓTIO EM JAVA DETERMINAR O TAMANHO DA LISTA)!
    int[] idsProdutos
    int[] estoquesProdutos;
    String[] nomesProdutos;
    double[] precosProdutos;
    // boolean base; , coloquei algo similar no main, não sei se foi a melhor escolha

    //venda atual
    int[] vendaAtualIds;
    int[] vendaAtualQuantidades;
	int vendaAtualCont = 0;

    //Histórico de Vendas
    int[] historicoIdsPedidos;
    double[] historicoValoresPedidos;
    int[][] historicoItensVendidos;
      
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
              baseInicializada = true // agora pode continuar
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
