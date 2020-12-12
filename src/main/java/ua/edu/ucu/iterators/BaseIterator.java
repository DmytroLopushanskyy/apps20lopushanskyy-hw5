package ua.edu.ucu.iterators;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BaseIterator implements RenewableIterator {
    private ArrayList<Integer> intList;
    private int currentIndex = 0;

    public BaseIterator(int... values) {
        this.intList = new ArrayList<>(values.length);
        for (int value : values) {
            intList.add(value);
        }
    }

    @Override
    public boolean hasNext() {
        if (currentIndex > intList.size() - 1) {
            return false;
        }
        return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return intList.get(currentIndex++);
    }

    public void restart() {
        currentIndex = 0;
    }
}
