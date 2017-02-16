package ru.coutvv.concurrency.fundamental.threadsafety;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by lomovtsevrs on 16.02.2017.
 */
public abstract class BookServlet implements Servlet{
    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    protected BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("0");
    }

    protected BigInteger[] factor(BigInteger bi) {
        BigInteger[] result = new BigInteger[bi.intValue()];
        for(int i = 0; i < bi.intValue(); i++) {
            result[i] = new BigInteger("1" + i);
        }

        return result;
    }

    protected void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {

    }
}
