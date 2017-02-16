package ru.coutvv.concurrency.fundamental.threadsafety;

import net.jcip.annotations.NotThreadSafe;

/**
 * Created by lomovtsevrs on 16.02.2017.
 */
@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if(instance == null)
            instance = new ExpensiveObject();
        return instance;
    }

}
class ExpensiveObject {
}
