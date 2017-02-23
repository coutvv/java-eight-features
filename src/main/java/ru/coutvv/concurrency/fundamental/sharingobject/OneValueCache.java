package ru.coutvv.concurrency.fundamental.sharingobject;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by coutvv on 23.02.2017.
 */
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;
    public OneValueCache(BigInteger i, BigInteger[] factors) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getLastFactors(BigInteger i) {
        if(lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors,lastFactors.length);
    }
}
