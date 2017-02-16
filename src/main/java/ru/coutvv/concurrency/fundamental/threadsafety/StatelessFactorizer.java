package ru.coutvv.concurrency.fundamental.threadsafety;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by lomovtsevrs on 16.02.2017.
 */
@ThreadSafe
public class StatelessFactorizer extends BookServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(res, factors);
    }

}
