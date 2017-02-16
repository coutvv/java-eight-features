package ru.coutvv.concurrency.fundamental.threadsafety;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * Created by lomovtsevrs on 16.02.2017.
 *
 * Будет норм работать в одном потоке.
 */
@NotThreadSafe
public class UnsafeCountingFactorizer extends BookServlet {
    private long count = 0;
    public long getCount() {return count;}

    public void service(ServletRequest req, ServletResponse res) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        ++count;
        encodeIntoResponse(res, factors);
    }
}
