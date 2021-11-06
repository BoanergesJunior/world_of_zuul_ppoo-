import java.util.*;

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

  private int espalhar(HashMap<Integer, Comodo> comodos) {
    Random r = new Random();
    Integer sorteio = r.nextInt(comodos.size()) + 1;

    Comodo c = (Comodo) comodos.get(sorteio);
    while (c.getItem() != null) {
      sorteio = r.nextInt(comodos.size() + 1);
      c = (Comodo) comodos.get(sorteio);
    }

    c.setItem(this);

    System.out.println(sorteio);
    return sorteio;

  }

  public int setLocal(HashMap<Integer, Comodo> comodos) {
    local = espalhar(comodos);
    return local;
  }

  public int getLocal() {
    return local;
  }

}