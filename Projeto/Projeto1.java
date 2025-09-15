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
    static void inicializarBase() {}

    static void catalogoProdutos() {}

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
        
        

    }
}
