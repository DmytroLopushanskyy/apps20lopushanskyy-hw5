package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

public class FilterIterator implements RenewableIterator {
    private RenewableIterator previousIterator;
    private IntPredicate comparator;
    private Integer nextElement = null;

    public FilterIterator(RenewableIterator prev, IntPredicate comp) {
        this.previousIterator = prev;
        this.comparator = comp;
        next();
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    @Override
    public Integer next() {
        Integer currentElement = nextElement;
        boolean newElementIsFound = false;
        Integer element;
        while (previousIterator.hasNext()) {
            element = previousIterator.next();
            if (comparator.test(element)) {
                newElementIsFound = true;
                nextElement = element;
                break;
            }
        }
        if (!newElementIsFound) {
            nextElement = null;
        }
        return currentElement;
    }

    @Override
    public void restart() {
        previousIterator.restart();
        nextElement = null;
        next();
    }
}
