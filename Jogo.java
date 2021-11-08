import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

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
 * ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e executa os
 * comandos que o analisador retorna.
 * 
 * @author Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
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

    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        Random r = new Random();
        analisador = new Analisador();
        tentativas = r.nextInt(20);
        comodos = new HashMap<Integer, Comodo>();
        chave = new Chave();
        dica1 = new Dica("A chave não está no(a)");
        dica2 = new Dica("A chave não está no(a)");
        interfaceGrafica = new Tela(this);
        criarComodos();
        chave.setLocal(comodos);

        dica1.setLocal(comodos);
        dica1.setDescricao(comodos);

        dica2.setLocal(comodos);
        dica2.setDescricao(comodos);

        System.out.println("Chave: " + chave.getLocal());
        System.out.println("Dica 1: " + dica1.getLocal());
        System.out.println("Dica 2: " + dica2.getLocal());

        interfaceGrafica.exibir();
    }

    private void criarComodos() {
        Comodo cozinha, banheiro, corredor, sala1, sala2, salaDiretoria, rh, recepcao, salaDescanco, salaReunioes,
                entradaSaida, fora;

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
        fora = new Comodo("Fora");

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

        entradaSaida.ajustarSaidas("Fora", fora);
        comodoAtual = sala1;

        verificaItem();
    }

    /**
     * Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() {
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.

        boolean terminado = false;
        while (!terminado && temTentativas()) {

            JLabel quantidadeTentativas = interfaceGrafica.getQuantidadeTentativas();
            quantidadeTentativas.setText(String.valueOf(tentativas));

            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
            if (!temTentativas()) {
                System.out.println("Tentativas esgotadas");
            }
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();

        imprimeLocalizacaoAtual();
    }

    /*
     * Método que imprime/apresenta as saídas disponíveis do lugar em que se
     * encontra o jogador
     */
    private void imprimeLocalizacaoAtual() {
        System.out.println(comodoAtual.getDescricao());
        System.out.println("Saidas: ");
        System.out.print(comodoAtual.getSaidas());
        System.out.println();
        System.out.println(tentativas);
        // if (chave.getEncontrado()) {
        // System.out.println(chave.getVidaUtil());
        // }
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no novo Comodo, caso
     * contrario imprime mensagem de erro.
     */
    private void irParaComodo(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String comodo = comando.getSegundaPalavra();

        // Tenta sair do Comodo atual
        Comodo proximoComodo = null;
        proximoComodo = comodoAtual.getComodo(comodo);

        if (proximoComodo == null) {
            System.out.println("Não há passagem!");
        } else {
            if (proximoComodo.getNome().equals("Fora") && chave.getEncontrado()) {
                System.out.println("Parabéns você achou a saída!!");
                sair();
            } else if (chave.getEncontrado() || !proximoComodo.getNome().equals("Fora"))
                irDireto(proximoComodo);
            else {
                --tentativas;
                System.out.println("Voce não pode sair");
            }
        }

        imprimeLocalizacaoAtual();
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
            System.out.println("Eu nao entendi o que voce disse...");
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
     * Printe informacoes de ajuda. Aqui nos imprimimos algo bobo e enigmatico e a
     * lista de palavras de comando
     */
    private void imprimirAjuda() {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   ir sair ajuda");
    }

    private void verificaItem() {
        if (comodoAtual.getItem() != null && !comodoAtual.getItem().getEncontrado()) {
            if (comodoAtual.getItem() instanceof Chave) {
                System.out.println("Parabens voce achou uma chave, agora vá até a saída");
                interfaceGrafica.setChave(chave.getVidaUtil());
            } else if (comodoAtual.getItem() instanceof Dica) {
                if (comodoAtual.getItem() == dica1) {
                    System.out.println(dica1.getDescricao());
                } else {
                    System.out.println(dica2.getDescricao());
                }
            }
            comodoAtual.getItem().setEncontrado(true);
        }
    }

    private void observar() {
        imprimeLocalizacaoAtual();
    }

    private boolean temTentativas() {
        return tentativas > 0 ? true : false;
    }

    private void irDireto(Comodo proximoComodo) {
        if (temTentativas()) {
            comodoAtual = proximoComodo;
            tentativas--;
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
}
