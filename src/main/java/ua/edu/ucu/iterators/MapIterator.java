package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.NoSuchElementException;


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
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return comparator.apply(previousIterator.next());
    }

    @Override
    public void restart() {
        previousIterator.restart();
    }
}