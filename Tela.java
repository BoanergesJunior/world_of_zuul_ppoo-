import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Tela {

  int tentativas;

  private JFrame telaPrincipal;

  private JLabel textoTentativas;
  private JLabel quantidadeTentativas;

  /* Definindo Lado direito da parte superior */
  private JLabel textoDicasEncontradas;
  private JLabel dica1;
  private JLabel dica2;

  private JLabel textoBemVindo;
  private JLabel textoAjuda;
  private JTextField entradaTexto;
  private JLabel achouChave;
  private JLabel textoSaida;

  private JLabel salaAtual;
  private JLabel saidas;
  private JLabel extra;

  public Tela(Jogo jogo) {

    tentativas = jogo.getTentativas();

    quantidadeTentativas = new JLabel("");

    telaPrincipal = new JFrame("World of zuul escritório");
    textoTentativas = new JLabel("<html><h2>Número de Tentativas Restantes:</h2></html>");

    textoDicasEncontradas = new JLabel("<html><h2>Dicas encontradas:</h2></html>");
    dica1 = new JLabel("<html><br><strong><h3>Dica 1 ainda não foi encontrada</h3></strong><br></html>");
    dica2 = new JLabel("<html><br><strong><h3>Dica 1 ainda não foi encontrada</h3></strong><br></html>");

    textoBemVindo = new JLabel("Bem-Vindo ao escritório");

    textoAjuda = new JLabel("Digite 'ajuda' se você precisar de ajuda.");
    achouChave = new JLabel("<html><h2>Não encontrou a chave!</h2></html>");

    salaAtual = new JLabel("Você está no(a) comodo: Sala 1");
    textoSaida = new JLabel("Saidas:");
    saidas = new JLabel("Sala2, Corredor");

    extra = new JLabel("Suas palavras de comando sao: ir, sair, ajuda");

    entradaTexto = new JTextField();
    montarFramePrincipal();
    telaPrincipal.pack();
  }

  /* Método Responsável por montar o layout da Interface Gráfica */
  private void montarFramePrincipal() {

    // telaPrincipal.setSize(1000, 500);
    telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
    telaPrincipal.setLayout(new BorderLayout());

    /* Configurando Lado Esquerdo Superior */
    JPanel campoEsquerdoSuperior = new JPanel();
    campoEsquerdoSuperior.setPreferredSize(new Dimension(250, 370));
    // Aribuindo borda
    campoEsquerdoSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
    campoEsquerdoSuperior.setLayout(new BoxLayout(campoEsquerdoSuperior, BoxLayout.Y_AXIS));
    campoEsquerdoSuperior.setBackground(Color.WHITE);
    // Setando formato de texto tentativas
    textoTentativas.setFont(new Font("Courier New", Font.BOLD, 18));
    campoEsquerdoSuperior.add(textoTentativas);
    // Setando formato de texto quantidades tentativas
    quantidadeTentativas.setBorder(new EmptyBorder(0, 10, 0, 0));
    campoEsquerdoSuperior.add(quantidadeTentativas);
    campoEsquerdoSuperior.add(achouChave);

    /* Configurando Lado central Superior */
    JPanel campoCentralSuperior = new JPanel();
    campoCentralSuperior.setPreferredSize(new Dimension(500, 370));
    campoCentralSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
    campoCentralSuperior.setLayout(new BorderLayout());
    campoCentralSuperior.setBackground(Color.WHITE);

    /* Configurando Lado Direito Superior */
    JPanel campoDireitoSuperior = new JPanel();
    campoDireitoSuperior.setPreferredSize(new Dimension(250, 370));
    campoDireitoSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
    campoDireitoSuperior.setLayout(new BoxLayout(campoDireitoSuperior, BoxLayout.Y_AXIS));
    campoDireitoSuperior.setBackground(Color.WHITE);
    campoDireitoSuperior.add(textoDicasEncontradas);
    // Setando formato de dicas
    dica1.setBorder(new EmptyBorder(0, 10, 0, 0));
    campoDireitoSuperior.add(dica1);
    dica2.setBorder(new EmptyBorder(0, 10, 0, 0));
    campoDireitoSuperior.add(dica2);

    /* Configurando parte inferior */
    JPanel campoInferior = new JPanel();
    campoInferior.setPreferredSize(new Dimension(1000, 130));
    campoInferior.setBorder(BorderFactory.createLineBorder(Color.black));
    campoInferior.setLayout(new BoxLayout(campoInferior, BoxLayout.Y_AXIS));
    campoInferior.setBackground(Color.WHITE);
    campoInferior.add(textoBemVindo);
    campoInferior.add(textoAjuda);
    campoInferior.add(salaAtual);
    campoInferior.add(textoSaida);
    campoInferior.add(saidas);
    campoInferior.add(extra);
    campoInferior.add(entradaTexto);

    telaPrincipal.add(campoEsquerdoSuperior, BorderLayout.WEST);
    telaPrincipal.add(campoCentralSuperior, BorderLayout.CENTER);
    telaPrincipal.add(campoDireitoSuperior, BorderLayout.EAST);
    telaPrincipal.add(campoInferior, BorderLayout.SOUTH);
    telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Definindo mapa
    ImageIcon imgTituloJanela = new ImageIcon(getClass().getResource("mapa.png"));
    imgTituloJanela.setImage(imgTituloJanela.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
    telaPrincipal.add(new JLabel(imgTituloJanela));
  }

  public void exibir() {
    telaPrincipal.setVisible(true);
  }

  public JLabel getQuantidadeTentativas() {
    return quantidadeTentativas;
  }

  public void setSalaAtual(String sala) {
    this.salaAtual.setText(sala);
  }

  public void setSaidas(String saida) {
    this.saidas.setText(saida);
  }

  public void setExtra(String extra) {
    this.extra.setText(extra);
  }

  public void setAchouChave(String text) {
    this.achouChave.setText(text);
  }

  public void setDica1(String text) {
    this.dica1.setText(text);
  }

  public void setDica2(String text) {
    this.dica2.setText(text);
  }

  public void setChave(int quant) {

  }

  public void travarEntrada() {
    entradaTexto.setEditable(false);
  }

  public void limparEntrada() {
    entradaTexto.setText("");
  }

  public String getText() {
    return entradaTexto.getText();
  }

  public void gameOver() {
    textoBemVindo.setText("Game Over!");
    textoAjuda.setText("Tentativas esgotadas");
    salaAtual.setText("-");
    textoSaida.setText("-");
    saidas.setText("-");
    extra.setText("Obrigado por jogar. Ate mais!");
    achouChave.setText("<html><h2>Game Over! Acabaram as tentativas</h2></html>");
    quantidadeTentativas.setText("0");
  }

  public void vitoria() {
    textoBemVindo.setText("Vitória!");
    textoAjuda.setText("Você encontrou a saída");
    salaAtual.setText("-");
    textoSaida.setText("-");
    saidas.setText("-");
    extra.setText("Obrigado por jogar. Ate mais!");
  }
}
