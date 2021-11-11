package src;

import java.util.*;

/**
 * Classe responsavel por criar a dica do jogo e setar suas configuracoes
 */
public class Dica extends Item {
  private String descricao;

  public Dica(String d) {
    super();
    descricao = d;
  }

  /**
   * Metodo responsavel por seta a descricao da dica
   * 
   * @param comodos comodos que a dica esta associada
   */
  public void setDescricao(HashMap<Integer, Comodo> comodos) {
    Comodo c = (Comodo) comodos.get(setLocal(comodos));
    descricao = descricao + " " + c.getNome();
  }

  public String getDescricao() {
    return descricao;
  }
}