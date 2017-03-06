package ru.coutvv.concurrency.fundamental.buildingblocks;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by lomovtsevrs on 06.03.2017.
 */
public class PreLoader {

    private final FutureTask<ProductInfo> future =
            new FutureTask<ProductInfo>(() -> {
                return null;//load something from db for example
            });

    private final Thread thread = new Thread(future);
    public void start() {thread.start();}

    public ProductInfo get() throws ExecutionException, InterruptedException {
        return future.get();
    }
}

class ProductInfo {

}