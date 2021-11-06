import java.util.*;

public class Comodo {
  public String nome;
  public String descricao;
  private HashMap<String, Comodo> saidas;
  private Item item;

  /**
   * Cria um Comodo com a "descricao" passada. Inicialmente, ele nao tem saidas.
   * "descricao" eh algo como "uma cozinha" ou " Create a room described
   * "description". Initially, it has no exits. "description" is something like "a
   * kitchen" or "um jardim aberto".
   * 
   * @param nome O nome do comodo.
   */
  public Comodo(String nome) {
    this.nome = nome;
    descricao = "Você está no(a) comodo: " + nome;
    saidas = new HashMap<String, Comodo>();
    item = null;
  }

  /**
   * Define as saidas do Comodo. Cada direcao ou leva a um outro Comodo ou eh null
   * (nenhuma saida para la).
   * 
   * @param nomeComodo Direção para onde jogador deseja ir.
   * @param comodo     desejado.
   */
  public void ajustarSaidas(String nomeComodo, Comodo comodo) {
    saidas.put(nomeComodo, comodo);
  }

  /**
   * @return A descricao do Comodo.
   */
  public String getDescricao() {
    return descricao;
  }

  public String getSaidas() {
    String direcoes = "";
    for (String d : saidas.keySet()) {
      direcoes = direcoes + d + ", ";
    }
    return direcoes;
  }

  public Comodo getComodo(String nome) {
    return (Comodo) saidas.get(nome);
  }

  public String getNome() {
    return nome;
  }

  public boolean porta() {
    Random r = new Random();
    return r.nextBoolean();
  }

  public final void setItem(Item i) {
    item = i;
  }

  public Item getItem() {
    return item;
  }

  public HashMap<String, Comodo> getComodos() {
    return saidas;
  }

}
