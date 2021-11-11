package src;

import java.util.*;

/**
 * Classe que controla os itens que o jogo possui, Dica ou Chave. Ela possui a
 * funcao de definir se o item foi encontrado ou nao e definir o comodo em que
 * este item estara.
 */
public class Item {
  private int local;
  private boolean encontrado;

  public Item() {
    local = 0;
    encontrado = false;
  }

  public void setEncontrado(boolean e) {
    encontrado = e;
  }

  public boolean getEncontrado() {
    return encontrado;
  }

  /**
   * Metodo responsavel por randomizar o local do item
   * 
   * @param comodos HashMap com todos os comodos
   * @return retorna um valor inteiro que representa a chave do HashMap que é o
   *         local do item
   */
  private int espalhar(HashMap<Integer, Comodo> comodos) {
    Random r = new Random();
    Integer sorteio = r.nextInt(comodos.size()) + 1;
    Comodo c = comodos.get(sorteio);
    while (c.getItem() != null) {
      sorteio = r.nextInt(comodos.size() + 1);
      c = comodos.get(sorteio);
    }

    c.setItem(this);

    return sorteio;
  }

  /**
   * Metodo responsavel por retornar setar o local em que o item ira ficar
   * 
   * @param comodos HashMap com todos os comodos do jogo
   * @return o local em que o item foi escolhido para estar durante a execução do
   *         jogo
   */
  public int setLocal(HashMap<Integer, Comodo> comodos) {
    local = espalhar(comodos);
    return local;
  }

  public int getLocal() {
    return local;
  }

}