import java.util.Scanner;
public class Projeto1 {
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
    
    //metódos estáticos de cada opção do menu
    static boolean inicializarBase() {
		idsProdutos = {101, 203, 301, 401}
		nomesProdutos = {"Mouse Game", "Teclado Mecanico", "Headset 7.1", "Fone"}
		precosProdutos = {150.00, 350.00, 420.50, 250.90}
		estoquesProdutos = {2, 1, 1, 0}

		vendaAtualIds = {}
		vendaAtualQuantidades = {}

		historicoIdsPedidos = {}
		historicoValoresPedidos = {}
		historicoItensVendidos = {}{3}
		base = true; //valida a inicialização da base 

        System.out.print("Base iniciada com sucesso!"); //retornar a base para ser acessível para os outros métodos?
    }

    static void catalogoProdutos() {
		//Forçar a inicialização da base 
        if(base == false){
			System.out.println("Antes de continuar, inicialize a base!");
		}

		System.out.print("Lista de produtos com estoque positivo:")
		System.out.println("\nID |       Produto      | Valor unitário  | Estoque ");
        System.out.println("-------------------------------------------------------");
		//Percorre os produtos e imprime os que estão com estoque positivo
		for (int i = 0; i < idsProdutos.length; i++) {
			if((idsProdutos[i] != 0 && estoquesProdutos[i] > 0) {
				System.out.printf("%d         %s             R$ %.2f         %d\n",idsProdutos[i], nomesProdutos[i], precosProdutos[i], estoquesProdutos[i]);
			}
			
		}

    }

    static void adicionarItem() {
		//Forçar a inicialização da base 
		if(base == false){
			System.out.println("Antes de continuar, inicialize a base!");
		} 
		//Pedir o ID do produto e a sua quantidade
		System.out.print("Digite o ID do produto: ");
        int id = entrada.nextInt();
        System.out.print("Digite a quantidade: ");
        int quantidade = entrada.nextInt();

		//Validar se o produto existe


		//Ver se há estoque o suficiente 
	}

    static void resumoAtual() {
		//Verifica se existe algum produto na venda atual
		if(vendaAtualCont == 0){
			System.out.println("Nenhum item na venda atual.");
		}

		System.out.println("\nID |     Produto    |   Quantidade  | VL. UNIT. | VL. TOTAL ");
        System.out.println("-------------------------------------------------------------------");
		//Listar os produtos adicionados 
	}

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

    static void historicoitensVendidos() { //matriz
		//Percorre os vetores de histórico e imprime um resumo de todas as vendas finalizadas (ex: Pedido ID: 1001 - Valor Total: R$ 1070.50).
		for (int id = 0; id < historicoIdsPedidos.lenght; i++){
			
		}

		for (int v = 0; v < historicoValoresPedidos.lenght;i++){

		}

		//Ver como imprimir os dois juntos!!!
	}

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

    static void reporEstoque() {
		// Solicita um ID de produto e uma quantidade	
		System.out.print("Digite um ID de produto:");
		int idDigitado = input.nextInt();

		System.out.print("Digite a quantidade:");
		int quant = input.nextInt();

		//Adiciona a quantidade no estoque no produto certo
		
	}
    
    static void relatorioEstoqueBaixo() {
		 //Imprime uma lista de todos os produtos cujo estoque esteja abaixo de um limiar pré-definido (ex: 10 unidades)
		System.out.println("---LISTA DE PRODUTOS COM ESTOQUE ABAIXO DO LIMITE---")
		for (int p = 0; p < estoquesProdutos.lenght; p++){
			if (estoquesProdutos[p] < //limite){
				System.out.println(//produto, id, quantidade talvez)
		]
	}

    // max de cada array , variável costante não dá para mudar!
    //OBS: coloquei valores aleatórios, discutir sobre!
    final int MaxProdutos = 45;
    final int QuantCadaProduto = 60;

    //Catálogo de produtos
    int[] idsProdutos = new int[MaxProdutos], estoquesProdutos = new int[MaxProdutos];
    String[] nomesProdutos = new String[MaxProdutos];
    double[] precosProdutos = new double[MaxProdutos];
    boolean base = new boolean;

    //venda atual
    int[] vendaAtualIds = new int[MaxProdutos],  vendaAtualQuantidades = new int[QuantCadaProduto];
	int vendaAtualCont = 0;

    //Histórico de Vendas
    int[] historicoIdsPedidos = new int[MaxProdutos];
    double[] historicoValoresPedidos = new double[MaxProdutos];
    int[][] historicoItensVendidos = new int[MaxProdutos][3];
      
    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in);
        int escolhaMenu = new int;
        do {
            menuOpcoes();
            System.out.print("Selecione uma opção (obrigatório iniciar base primeiro):");
            escolhaMenu = input.nextInt();
            switch (escolhaMenu) {
             case 1:  
              inicializarBase();
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
        entrada.close();
    }
}
