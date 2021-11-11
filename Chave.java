import java.util.*;

public class Chave extends Item {

  public Chave() {
    super();
  }

  public void randomizarChave(int size, Chave chave, HashMap<Integer, Comodo> comodos) {
    chave.setLocal(comodos);
  }
}