import java.util.*;

public class Dica extends Item {
  private String descricao;

  public Dica(String d) {
    super();
    descricao = d;
  }

  public void setDescricao(HashMap<Integer, Comodo> comodos) {
    Comodo c = (Comodo) comodos.get(setLocal(comodos));
    descricao = descricao + " " + c.getNome();
  }

  public void setDescricao(Comodo comodo) {
    HashMap<String, Comodo> h = comodo.getComodos();
    Random r = new Random();
    int sorteio = r.nextInt(h.size());
    ArrayList<Comodo> c = new ArrayList<Comodo>(h.values());
    descricao = descricao + " " + (c.get(sorteio).getNome());
  }

  public String getDescricao() {
    return descricao;
  }
}