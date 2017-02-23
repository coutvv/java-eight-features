package ru.coutvv.concurrency.fundamental.sharingobject;

import net.jcip.annotations.ThreadSafe;
import ru.coutvv.concurrency.fundamental.threadsafety.BookServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by coutvv on 23.02.2017.
 */
@ThreadSafe
public class VolatileCachedFactorizer extends BookServlet {

    private volatile OneValueCache cache =
            new OneValueCache(null, null);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i =extractFromRequest(req);
        BigInteger[] factors = cache.getLastFactors(i);
        if(factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(res, factors);
    }
}
