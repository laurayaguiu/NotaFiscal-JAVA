# NotaFiscal-JAVA
1. OBJETIVO
    O objetivo deste projeto é praticar e solidificar os seguintes conceitos de programação:
        • Manipulação de vetores e matrizes para armazenar e relacionar dados.
        • Uso de vetores paralelos para estruturar informações.
        • Implementação de lógica de programação com estruturas de decisão e repetição.
        • Gerenciamento de estado do sistema (ex: estoque, venda atual, histórico).
        • Encapsulamento da lógica em métodos estáticos.
        • Formatação de saída de texto para gerar relatórios e documentos legíveis.
        • OBS: Não será permitido o uso de estruturas diferentes daquelas vistas em aula, ou seja, uso de
        classes, vetores dinâmicos, etc.

2. ESTRUTURA DE DADOS PRINCIPAL
    O sistema será construído sobre três blocos de dados interligados, usando apenas vetores e matrizes.
        1. Catálogo de produtos (vetores paralelos): será a base de produtos.
        o int[] idsProdutos: Armazena os códigos numéricos únicos de cada produto.
        o String[] nomesProdutos: Armazena as descrições dos produtos.
        o double[] precosProdutos: Armazena os preços unitários de cada produto.
        o int[] estoquesProdutos: Armazena a quantidade disponível de cada produto.
        2. Venda Atual (vetores paralelos): representa a transação em andamento, sendo limpa após cada
        venda concluída.
        o int[] vendaAtualIds: Armazena os IDs dos produtos na venda.
        o int[] vendaAtualQuantidades: Armazena a quantidade de cada produto.
        3. Histórico de Vendas (vetores e matriz): armazena os dados de todas as vendas finalizadas.
        o int[] historicoIdsPedidos: Armazena um ID único para cada pedido concluído (ex: 1001,
        1002, ...).
        o double[] historicoValoresPedidos: Vetor paralelo ao anterior, armazena o valor total final de
        cada pedido.
        o int[][] historicoItensVendidos: Uma matriz que armazena cada item de cada pedido. Cada
        linha representa um item vendido e possui 3 colunas: [ID do Pedido, ID do Produto,
        Quantidade Vendida].

3. MENU DE OPÇÕES
    O programa deve apresentar o seguinte menu de funcionalidades:

        1. Inicializar base

        2. Exibir catálogo de produtos

        3. Adicionar item à venda

        4. Ver resumo da venda atual

        5. Finalizar venda (gerar histórico e Nota Fiscal)

        6. Ver histórico de vendas

        7. Buscar venda específica do histórico

        8. (Admin) Repor estoque
        
        9. (Admin) Relatório de estoque baixo

4. DESCRIÇÃO DETALHADA DAS OPÇÕES
Cada opção do menu corresponde a uma funcionalidade que deve ser implementada, através da chamada a
métodos estáticos.
    1. Inicializar base: Preenche os vetores do catálogo com dados pré-definidos. Inicializa os vetores da
    venda atual e do histórico como vazios. Esta opção deve ser executada antes de qualquer outra. Não
    deve ser permitido acionar as demais funções sem antes executar esta; avise o usuário por meio de
    mensagens.
    2. Exibir catálogo de produtos: Imprime a lista de produtos com estoque maior que zero, de forma
    organizada.
    3. Adicionar item à venda: Solicita ID e quantidade ao usuário, valida se o produto existe e se há
    estoque suficiente. Se válido, adiciona o item aos vetores da venda atual.
    4. Ver resumo da venda atual: Exibe os itens, subtotais e o total da venda atual de forma clara.
    5. Finalizar Venda: Executa a seguinte sequência:
    a. Gera um novo ID para o Pedido.
    b. Salva o ID do pedido e seu valor total nos vetores de histórico (historicoIdsPedidos,
    historicoValoresPedidos).
    c. Para cada item na venda atual, adiciona uma linha na matriz historicoItensVendidos.
    d. Debita a quantidade do estoquesProdutos
    e. Chama o método para imprimir a nota fiscal formatada.
    f. Limpa os vetores da venda atual, deixando o sistema pronto para a próxima transação.
    6. Ver histórico de vendas: Percorre os vetores de histórico e imprime um resumo de todas as vendas
    finalizadas (ex: Pedido ID: 1001 - Valor Total: R$ 1070.50).
    7. Buscar venda específica do histórico: Solicita um ID de Pedido ao usuário. O sistema deve então
    percorrer a matriz historicoItensVendidos, encontrar todos os itens correspondentes àquele pedido e
    reimprimir a nota fiscal completa daquela transação.
    8. (Admin) Repor estoque: Solicita um ID de produto e uma quantidade, e adiciona o valor ao estoque
    correspondente.
    9. (Admin) Relatório de estoque baixo: Imprime uma lista de todos os produtos cujo estoque esteja
    abaixo de um limiar pré-definido (ex: 10 unidades).
        