package io.grpc.examples.routeguide;

import java.util.Random;

interface Latency {

    static void wip() {
        try {
            // simulate latency
            Thread.sleep(22L + new Random().nextInt(222));
        } catch (InterruptedException ignored) { }
    }
}
