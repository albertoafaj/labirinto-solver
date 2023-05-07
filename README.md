# Labirinto Solver
Este aplicativo Java lê um arquivo de entrada contendo um labirinto e encontra o caminho para a saída do labirinto. A saída do labirinto é salva em um novo arquivo de texto.

## Requisitos
Para executar o aplicativo, você precisará do seguinte:

* Java Development Kit (JDK) 8 ou superior
* Clonar o repositório;
* Um arquivo de entrada contendo um labirinto, no formato txt;
  
## Como usar
Para executar o aplicativo, execute o seguinte comando no terminal:

```java Main```

Ao ser executado, o aplicativo solicitará o caminho completo para o arquivo de entrada do labirinto.

Após a conclusão do processamento, o aplicativo criará um novo arquivo de texto contendo a solução do labirinto.

## Funcionalidades do projeto

1. Ler arquivo texto contendo o labirinto que deve ser resolvido;
2. Identificar a dimensão da matriz do labirinto;
3. Identificar a posição de origem;
4. Deslocar em direção a saída;
5. Seguir a ordem de prioridade de deslocamento;
6. Encontrar a única saída localizado em uma extremidade da 
matriz;
7. Gerar um arquivo texto de saída;