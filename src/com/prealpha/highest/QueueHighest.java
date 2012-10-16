package com.prealpha.highest;

import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

final class QueueHighest implements Highest {
    private static final class FixedCapacityPriorityQueue<E extends Comparable<E>> extends PriorityQueue<E> {
        private final int capacity;

        public FixedCapacityPriorityQueue(int capacity) {
            super(capacity);
            this.capacity = capacity;
        }

        @Override
        public boolean add(E e) {
            if (size() == capacity && capacity > 0) {
                if (e.compareTo(peek()) > 0) {
                    poll();
                    return super.add(e);
                } else {
                    return false;
                }
            } else {
                return super.add(e);
            }
        }

        @Override
        public boolean offer(E e) {
            if (size() == capacity && capacity > 0) {
                if (e.compareTo(peek()) > 0) {
                    poll();
                    return super.offer(e);
                } else {
                    return false;
                }
            } else {
                return super.offer(e);
            }
        }
    }

    @Override
    public Collection<Integer> findHighest(int n, Iterator<Integer> data) {
        Queue<Integer> queue = new FixedCapacityPriorityQueue<Integer>(n);
        while (data.hasNext()) {
            queue.offer(data.next());
        }
        return queue;
    }
}
