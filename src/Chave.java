package src;

import java.util.*;

/**
 * Metodo responsavel por gerar a chave do jogo e randomiza-la
 */
public class Chave extends Item {

  public Chave() {
    super();
  }

  /**
   * Metodo responsavel por randomizar a chave depois de um numero de tentativas
   * pre-definidas
   * 
   * @param chave   chave que vai ser randomizada
   * @param comodos HashMap com todos os comodos
   */
  public void randomizarChave(Chave chave, HashMap<Integer, Comodo> comodos) {
    chave.setLocal(comodos);
  }
}