package src;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JLabel;

/**
 * Essa eh a classe principal da aplicacao "World of Zull". "World of Zuul" eh
 * um jogo de aventura muito simples, baseado em texto. Usuarios podem caminhar
 * em um cenario. E eh tudo! Ele realmente precisa ser estendido para fazer algo
 * interessante!
 * 
 * Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 * "jogar".
 * 
 * Essa classe principal cria e inicializa todas as outras: ela cria os
 * ambientes, cria o analisador e comeca o jogo. Ela tambem avalia e executa os
 * comandos que o analisador retorna.
 * 
 * @author Andre Medeiros - 201811266
 * @author Boanerges Junior - 201821136
 * @author Matheus Peternelli - 201820283
 * @author Tiago Carlos - 201820272
 */

public class Jogo {
    private Analisador analisador;
    private int tentativas;
    private HashMap<Integer, Comodo> comodos;
    private Comodo comodoAtual;
    private Tela interfaceGrafica;
    private Chave chave;
    private Dica dica1;
    private Dica dica2;
    private JLabel quantidadeTentativas;
    private int randomizar;

    /**
     * Cria o jogo e inicializa o mapa interno.
     */
    public Jogo() {
        Random r = new Random();
        randomizar = 0;
        analisador = new Analisador();
        tentativas = r.nextInt(20) + 5;
        comodos = new HashMap<Integer, Comodo>();
        chave = new Chave();
        dica1 = new Dica("A chave não está no(a)");
        dica2 = new Dica("A chave não está no(a)");
        interfaceGrafica = new Tela(this);
        quantidadeTentativas = interfaceGrafica.getQuantidadeTentativas();

        criarComodos();
        chave.setLocal(comodos);

        dica1.setLocal(comodos);
        dica1.setDescricao(comodos);

        dica2.setLocal(comodos);
        dica2.setDescricao(comodos);

        System.out.println("Chave: " + comodos.get(chave.getLocal()).getNome());
        System.out.println("Dica 1: " + comodos.get(dica1.getLocal()).getNome());
        System.out.println("Dica 2: " + comodos.get(dica2.getLocal()).getNome());

        gravar("output");
        interfaceGrafica.exibir();
    }

    /**
     * Metodo responsável por criar os comodos do jogo. Ajustar as saídas de cada
     * comodo e Define o comodo inicial.
     */
    private void criarComodos() {
        Comodo cozinha, banheiro, corredor, sala1, sala2, salaDiretoria, rh, recepcao, salaDescanco, salaReunioes,
                entradaSaida, saida;

        cozinha = new Comodo("Cozinha");
        banheiro = new Comodo("Banheiro");
        corredor = new Comodo("Corredor");
        sala1 = new Comodo("Sala 1");
        sala2 = new Comodo("Sala 2");
        salaDiretoria = new Comodo("Sala Diretoria");
        rh = new Comodo("RH");
        recepcao = new Comodo("Recepcao");
        salaDescanco = new Comodo("Sala Descanco");
        salaReunioes = new Comodo("Sala Reunioes");
        entradaSaida = new Comodo("Entrada Saida");
        saida = new Comodo("Saida");

        // adiciona no HashMap
        comodos.put(1, cozinha);
        comodos.put(2, banheiro);
        comodos.put(3, corredor);
        comodos.put(4, sala1);
        comodos.put(5, sala2);
        comodos.put(6, salaDiretoria);
        comodos.put(7, rh);
        comodos.put(8, recepcao);
        comodos.put(9, salaDescanco);
        comodos.put(10, salaReunioes);
        comodos.put(11, entradaSaida);

        // ajusta as saidas
        cozinha.ajustarSaidas("SalaDescanco", salaDescanco);
        cozinha.ajustarSaidas("EntradaSaida", entradaSaida);

        banheiro.ajustarSaidas("Corredor", corredor);

        corredor.ajustarSaidas("Banheiro", banheiro);
        corredor.ajustarSaidas("Sala1", sala1);
        corredor.ajustarSaidas("Sala2", sala2);
        corredor.ajustarSaidas("SalaDiretoria", salaDiretoria);
        corredor.ajustarSaidas("Recepcao", recepcao);

        sala1.ajustarSaidas("Corredor", corredor);
        sala1.ajustarSaidas("Sala2", sala2);

        sala2.ajustarSaidas("Corredor", corredor);
        sala2.ajustarSaidas("Sala1", sala1);
        sala2.ajustarSaidas("SalaDescanco", salaDescanco);

        salaDiretoria.ajustarSaidas("RH", rh);
        salaDiretoria.ajustarSaidas("Corredor", corredor);

        rh.ajustarSaidas("SalaDiretoria", salaDiretoria);
        rh.ajustarSaidas("Recepcao", recepcao);
        rh.ajustarSaidas("SalaReunioes", salaReunioes);

        recepcao.ajustarSaidas("RH", rh);
        recepcao.ajustarSaidas("EntradaSaida", entradaSaida);
        recepcao.ajustarSaidas("Corredor", corredor);
        recepcao.ajustarSaidas("SalaDescanco", salaDescanco);

        salaReunioes.ajustarSaidas("RH", rh);

        entradaSaida.ajustarSaidas("Cozinha", cozinha);
        entradaSaida.ajustarSaidas("Recepcao", recepcao);

        salaDescanco.ajustarSaidas("Sala2", sala2);
        salaDescanco.ajustarSaidas("Cozinha", cozinha);
        salaDescanco.ajustarSaidas("Recepcao", recepcao);

        entradaSaida.ajustarSaidas("Saida", saida);
        comodoAtual = sala1;

        verificaItem();
    }

    /**
     * Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() {
        imprimeLocalizacaoAtual();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.

        boolean terminado = false;
        while (!terminado && temTentativas()) {

            quantidadeTentativas.setText(String.valueOf(tentativas));

            Comando comando = analisador.pegarComando(interfaceGrafica.getText());
            terminado = processarComando(comando);
            if (!temTentativas()) {
                interfaceGrafica.gameOver();
                interfaceGrafica.travarEntrada();
            }
        }
        interfaceGrafica.travarEntrada();
    }

    /*
     * Método que imprime/apresenta as saídas disponíveis do lugar em que se
     * encontra o jogador
     */
    private void imprimeLocalizacaoAtual() {
        setSalaAtual(comodoAtual.getDescricao());
        setSaidas(comodoAtual.getSaidas());
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no novo Comodo, caso
     * contrario imprime mensagem de erro.
     */
    private void irParaComodo(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            setExtra("Ir pra onde?");
            return;
        }

        String comodo = comando.getSegundaPalavra();

        // Tenta sair do Comodo atual
        Comodo proximoComodo = null;
        proximoComodo = comodoAtual.getComodo(comodo);
        if (proximoComodo == null) {
            setExtra("Não há passagem!");
        } else {
            interfaceGrafica.limparEntrada();
            if (proximoComodo.getNome().equals("Saida") && chave.getEncontrado()) {
                interfaceGrafica.setAchouChave("<html><h2>Parabéns você achou a saída!!</h2></html>");
                interfaceGrafica.travarEntrada();
                interfaceGrafica.vitoria();
                sair();
            } else if (chave.getEncontrado() || !proximoComodo.getNome().equals("Saida")) {
                irDireto(proximoComodo);
                imprimeLocalizacaoAtual();
            } else {
                --tentativas;
                setExtra("Voce não pode sair");
                imprimeLocalizacaoAtual();
            }
        }

        verificaItem();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) {
        boolean querSair = false;

        if (comando.ehDesconhecido()) {
            setExtra("Entre com um comando");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        } else if (palavraDeComando.equals("ir")) {
            irParaComodo(comando);
        } else if (palavraDeComando.equals("observar")) {
            observar();
        } else if (palavraDeComando.equals("sair")) {
            querSair = sair();
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Print informacoes de ajuda. Aqui nos imprimimos algo bobo e enigmatico e a
     * lista de palavras de comando
     */
    private void imprimirAjuda() {
        setExtra("Suas palavras de comando sao: ir, sair, ajuda");
    }

    private void verificaItem() {
        if (comodoAtual.getItem() != null && !comodoAtual.getItem().getEncontrado()) {
            if (comodoAtual.getItem() instanceof Chave) {
                interfaceGrafica
                        .setAchouChave("<html><h2>Parabens voce achou uma chave, agora vá até a saída</h2></html>");
            } else if (comodoAtual.getItem() instanceof Dica) {
                if (comodoAtual.getItem() == dica1) {
                    interfaceGrafica.setDica1(dica1.getDescricao());
                } else {
                    interfaceGrafica.setDica2(dica2.getDescricao());
                }
            }
            comodoAtual.getItem().setEncontrado(true);
        }
    }

    /**
     * Imprime a descricao do local atual
     */
    private void observar() {
        imprimeLocalizacaoAtual();
    }

    /**
     * Verifica se o jogador tem mais tentativas
     * 
     * @return
     */
    private boolean temTentativas() {
        return tentativas > 0 ? true : false;
    }

    /**
     * Método que verifica se o jogador ainda tem tentativas restantes e define a
     * randomização da chave a cada 5 tentativas
     * 
     * @param proximoComodo Comodo a ser acessado
     */
    private void irDireto(Comodo proximoComodo) {
        if (temTentativas()) {
            comodoAtual = proximoComodo;
            tentativas--;
            randomizar++;
            if (randomizar == 5 && !chave.getEncontrado()) {
                chave.randomizarChave(chave, comodos);
                gravar("output");
            }
        }
    }

    /**
     * Metodo que grava a posição da chave e das dicas no arquivo
     * 
     * @param nomeArquivo Nome do arquivo de saida
     */
    private void gravar(String nomeArquivo) {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo);
            arquivo.write("Posição da Chave: " + comodos.get(chave.getLocal()).getNome() + "\n" + "Posicão da Dica1: "
                    + comodos.get(dica1.getLocal()).getNome() + "\n" + "Posição da Dica2: "
                    + comodos.get(dica2.getLocal()).getNome());
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Falha ao Salvar" + nomeArquivo);
        }
    }

    /**
     * "Sair" foi digitado. Verifica o resto do comando pra ver se nos queremos
     * realmente sair do jogo.
     * 
     * @return true, se este comando sai do jogo
     */
    private boolean sair() {
        return true;
    }

    public int getTentativas() {
        return tentativas;
    }

    /**
     * 
     * @param comodo seta na interface grafica o comodo atual
     */
    private void setSalaAtual(String comodo) {
        interfaceGrafica.setSalaAtual(comodo);
    }

    /**
     * 
     * @param saida seta na interface grafica quais a saidas do comodo atual
     */
    private void setSaidas(String saida) {
        interfaceGrafica.setSaidas(saida);
    }

    /**
     * 
     * @param extra seta na interface grafica saidas padroes
     */
    private void setExtra(String extra) {
        interfaceGrafica.setExtra(extra);
    }
}
