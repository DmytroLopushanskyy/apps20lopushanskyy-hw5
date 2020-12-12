package ua.edu.ucu.iterators;

import java.util.Iterator;

public interface RenewableIterator extends Iterator<Integer> {
    void restart();
}
