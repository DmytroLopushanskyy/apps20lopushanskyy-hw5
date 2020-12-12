package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;


public class FlatMapIterator implements RenewableIterator {
    private RenewableIterator previousIterator;
    private RenewableIterator curStreamIterator;
    private IntToIntStreamFunction comparator;

    public FlatMapIterator(RenewableIterator prev,
                           IntToIntStreamFunction comp) {
        this.previousIterator = prev;
        this.comparator = comp;

        if (!previousIterator.hasNext()) {
            this.curStreamIterator = new BaseIterator();
        } else {
            this.curStreamIterator = new BaseIterator(comparator
                    .applyAsIntStream(previousIterator.next()).toArray());
        }
    }

    @Override
    public boolean hasNext() {
        return curStreamIterator.hasNext();
    }

    @Override
    public Integer next() {
        Integer nextElement = curStreamIterator.next();
        if (!curStreamIterator.hasNext() && previousIterator.hasNext()) {
            curStreamIterator = new BaseIterator(comparator
                    .applyAsIntStream(previousIterator.next()).toArray());
        }
        return nextElement;
    }

    @Override
    public void restart() {
        previousIterator.restart();
        if (!previousIterator.hasNext()) {
            this.curStreamIterator = new BaseIterator();
        } else {
            this.curStreamIterator = new BaseIterator(comparator
                    .applyAsIntStream(previousIterator.next()).toArray());
        }
    }
}
