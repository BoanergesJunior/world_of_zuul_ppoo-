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

  public Tela(Jogo jogo) {

    tentativas = jogo.getTentativas();

    quantidadeTentativas = new JLabel("");

    telaPrincipal = new JFrame("World of zuul escritório");
    textoTentativas = new JLabel("<html><h2>Número de Tentativas Restantes:</h2></html>");

    textoDicasEncontradas = new JLabel("<html><h2>Dicas encontradas:</h2></html>");
    dica1 = new JLabel("<html><br><strong><h3>O tesouro não está no escritório</h3></strong><br></html>");
    dica2 = new JLabel("<html><br><strong><h3>O tesouro não está no quarto 1</h3></strong><br></html>");

    textoBemVindo = new JLabel("Bem-Vindo ao escritório");

    textoAjuda = new JLabel("Digite 'ajuda' se você precisar de ajuda.");

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

  public void setChave(int quant) {

  }
}
