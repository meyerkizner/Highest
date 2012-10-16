package com.prealpha.highest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

final class HighestTest {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            for (int j = 3; j <= 7; j++) {
                if (j > i) {
                    runTest(new ArrayHighest(), (int)Math.pow(10, i), (int)Math.pow(10, j));
                    runTest(new QueueHighest(), (int)Math.pow(10, i), (int)Math.pow(10, j));
                }
            }
        }
    }

    private static Collection<Integer> generateData(int size) {
        Collection<Integer> data = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            data.add(RANDOM.nextInt());
        }
        return data;
    }

    private static void runTest(Highest highest, int n, int size) {
        Set<Long> times = new HashSet<Long>(50);
        for (int i = 0; i < 50; i++) {
            Iterator<Integer> iterator = generateData(size).iterator();
            long nanos = System.nanoTime();
            Collection<Integer> result = highest.findHighest(n, iterator);
            long elapsed = System.nanoTime() - nanos;
            times.add(elapsed);
            System.out.println(elapsed);
            System.out.println(result);
        }

        BigInteger sum = BigInteger.ZERO;
        for (long time : times) {
            sum = sum.add(BigInteger.valueOf(time));
        }
        System.out.printf("AVERAGE VALUE FOR n=%d, size=%d: %s\n", n, size, sum.divide(BigInteger.valueOf(50)));
    }

    private HighestTest() {
    }
}
