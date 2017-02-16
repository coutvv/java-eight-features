package ru.coutvv.concurrency.fundamental.threadsafety;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lomovtsevrs on 16.02.2017.
 */
@ThreadSafe
public class CountingFactorizer  extends BookServlet {

    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {return count.get();}

    public void service(ServletRequest req, ServletResponse res) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(res, factors);
    }

}
