package ua.edu.ucu.iterators;
import java.util.ArrayList;

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
        return currentIndex <= intList.size() - 1;
    }

    @Override
    public Integer next() {
        return intList.get(currentIndex++);
    }

    public void restart() {
        currentIndex = 0;
    }
}
