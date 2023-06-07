package orig.kganofficial.iskanakoapp;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Counter {
  private String value = null;
  public static Counter c = new Counter();
  private final List<Listener> listeners = new CopyOnWriteArrayList<Listener>();

  private void fireAfterValueChanged() {
    for (Listener listener : listeners) {
      listener.afterValueChanged(this);
    }
  }
  
  public void setValue(String value) {
	  
    this.value = value;
	fireAfterValueChanged();
  }

  public String getValue() {
    return value;
  }


  public void addListener(Listener listener) {
    listeners.add(listener);
  }

  public void removeListener(Listener listener) {
    listeners.remove(listener);
  }

  public interface Listener {
    void afterValueChanged(Counter counter);
  }

}