package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;


public class MapIterator implements RenewableIterator {
    private RenewableIterator previousIterator;
    private IntUnaryOperator comparator;

    public MapIterator(RenewableIterator prev, IntUnaryOperator comp) {
        this.previousIterator = prev;
        this.comparator = comp;
    }

    @Override
    public boolean hasNext() {
        return previousIterator.hasNext();
    }

    @Override
    public Integer next() {
        return comparator.apply(previousIterator.next());
    }

    @Override
    public void restart() {
        previousIterator.restart();
    }
}