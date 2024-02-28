package com.example.mixdedrink.data.remote.request;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* Retrofit Background Tasks */
public class AppExecutors {
    private static AppExecutors instance;

    // Singleton
    public static AppExecutors getInstance() {
        if(instance == null) {
            instance = new AppExecutors();
        }
        return instance;
    }

    // Threads in pool, that can be executed
    private final ScheduledExecutorService cNetworkIO = Executors.newScheduledThreadPool(3);
    public ScheduledExecutorService networkIO() {
        return cNetworkIO;
    }

}
