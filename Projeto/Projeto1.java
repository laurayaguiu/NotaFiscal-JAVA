import java.util.Scanner;
public class Projeto1 {
    // método estático q representa o menu
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
    
    //metódos estáticos de cada opção do menu
    static void inicializarBase() {
		idsProdutos = {101, 203, 301, 401}
		nomesProdutos = {"Mouse Game", "Teclado Mecanico", "Headset 7.1", "Fone"}
		precosProdutos = {150.00, 350.00, 420.50, 250.90}
		estoquesProdutos = {2, 1, 1, 0}

		vendaAtualIds = {}
		vendaAtualQuantidades = {}

		historicoIdsPedidos = {}
		historicoValoresPedidos = {}
		historicoItensVendidos = {}{3}
		base = true;

        System.out.print("Base iniciada com sucesso!");
    }

    static void catalogoProdutos() {
        if(base == false){
			System.out.println("Antes de continuar, inicialize a base!");
		}
		System.out.print("Lista de produtos com estoque positivo:")
		System.out.println("\nID |       Produto      | Valor unitário  | Estoque ");
        System.out.println("-----------------------------------------------");
		for (int i = 0; i < idsProdutos.length; i++) {
			
		}

    }

    static void adicionarItem() {}

    static void resumoAtual() {}

    static void finalizarVenda() {}

    static void historicoVendas() {}

    static void vendaEspecifica() {}

    static void reporEstoque() {}
    
    static void relatorioEstoque() {}

    // max de cada array , variável costante não dá para mudar!
    //OBS: coloquei valores aleatórios, discutir sobre!
    final int MaxProdutos = 45;
    final int QuantCadaProduto = 60;

    //Catálogo de produtos
    int[] idsProdutos = new int[MaxProdutos], estoquesProdutos = new int[MaxProdutos];
    String[] nomesProdutos = new String[MaxProdutos];
    double[] precosProdutos = new double[MaxProdutos];

    //venda atual
    int[] vendaAtualIds = new int[MaxProdutos],  vendaAtualQuantidades = new int[QuantCadaProduto];

    //Histórico de Vendas
    int[] historicoIdsPedidos = new int[MaxProdutos];
    double[] historicoValoresPedidos = new double[MaxProdutos];
    int[][] historicoItensVendidos = new int[MaxProdutos][3];
      
    public static void main(String[] agrs) { 
        Scanner input = new Scanner(System.in)
        int escolhaMenu = new int
        menuOpcoes()
        System.out.print("Selecione uma opção (obrigatório iniciar base primeiro):")
        escolhaMenu = input.nextInt()
        /* a primeira escolha precisa ser 1, como fazer?
        while escolhaMenu != 1 {
            System.err.println("Erro, obrigatório iniciar base primeiro");
            System.out.print("Selecione outra opção:")
            escolhaMenu = input.nextInt()
        }
        */
        if escolhaMenu == 0 {
            System.out.print("Programa encerrado com sucesso!");
        }

        while (escolhaMenu != 0) {
            System.out.print("Selecione outra opção:")
            escolhaMenu = input.nextInt()
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
                break;
            }
        }
    }
}
