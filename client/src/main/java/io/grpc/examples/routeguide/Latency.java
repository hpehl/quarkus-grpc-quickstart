package io.grpc.examples.routeguide;

import java.util.Random;

interface Latency {

    static void suspend() {
        try {
            // simulate latency
            Thread.sleep(22L + new Random().nextInt(222));
        } catch (InterruptedException ignored) { }
    }
}
