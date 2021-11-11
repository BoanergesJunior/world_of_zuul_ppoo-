package src;

import java.util.*;

public class Comodo {
  public String nome;
  public String descricao;
  private HashMap<String, Comodo> saidas;
  private Item item;

  /**
   * Cria um Comodo com a descricao passada. Inicialmente, ele nao tem saidas.
   * descricao e algo como "Cozinha".
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
   * @param nomeComodo Direção para onde jogador pode ir.
   * @param comodo     Como que esta sendo ajustado.
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

  /**
   * Retorna todas as saidas do Comodo.
   */
  public String getSaidas() {
    String direcoes = "";
    for (String d : saidas.keySet()) {
      direcoes = direcoes + d + ", ";
    }
    return direcoes;
  }

  /**
   * retorna um objeto Comodo
   * 
   * @param nome
   * @return
   */
  public Comodo getComodo(String nome) {
    return saidas.get(nome);
  }

  public String getNome() {
    return nome;
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
