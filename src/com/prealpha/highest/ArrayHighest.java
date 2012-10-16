package com.prealpha.highest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

final class ArrayHighest implements Highest {
    @Override
    public Collection<Integer> findHighest(int n, Iterator<Integer> data) {
        int[] array = new int[n];
        Arrays.fill(array, Integer.MIN_VALUE);
        while (data.hasNext()) {
            int datum = data.next();
            if (array[0] > datum) {
                continue;
            }
            for (int i = (n - 1); i >= 0; i--) {
                if (datum > array[i]) {
                    System.arraycopy(array, 1, array, 0, i);
                    array[i] = datum;
                    break;
                }
            }
        }

        Collection<Integer> result = new ArrayList<Integer>(n);
        for (int i : array) {
            result.add(i);
        }
        return result;
    }
}
