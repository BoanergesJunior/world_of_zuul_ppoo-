import java.util.*;

public class Chave extends Item {
  private int vidaUtil;

  public Chave() {
    super();
    Random r = new Random();
    vidaUtil = r.nextInt(12) + 1;
  }

  /**
   * @return the vidaUtil
   */
  public int getVidaUtil() {
    return vidaUtil;
  }

  public void usarChave() {
    vidaUtil--;
  }
}