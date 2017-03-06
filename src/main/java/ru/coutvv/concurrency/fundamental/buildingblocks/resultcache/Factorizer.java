package ru.coutvv.concurrency.fundamental.buildingblocks.resultcache;

import net.jcip.annotations.ThreadSafe;
import ru.coutvv.concurrency.fundamental.threadsafety.BookServlet;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.function.Function;

/**
 * Типа потокобезопасный сервлет  использующмй кэшинг
 */
@ThreadSafe
public class Factorizer extends BookServlet {

    private final Function<BigInteger, BigInteger[]> c = (arg) -> factor(arg);

    private final Memorizer<BigInteger, BigInteger[]> cache = new Memorizer<>(c);


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        BigInteger i = extractFromRequest(req);
        encodeIntoResponse(res, cache.compute(i));

    }
}
