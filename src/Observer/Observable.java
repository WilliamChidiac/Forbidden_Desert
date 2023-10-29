
package Observer;

import java.util.ArrayList;
import java.util.Iterator;

public class Observable {
    private ArrayList<Observer> observers = new ArrayList();

    public Observable() {
    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void notifyObservers() {
        Iterator var1 = this.observers.iterator();

        while(var1.hasNext()) {
            Observer o = (Observer)var1.next();
            o.update();
        }

    }
}