package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.*;


public class AsIntStream implements IntStream {
    private RenewableIterator iterator;

    private AsIntStream(int[] values) {
        this.iterator = new BaseIterator(values);
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        guardEmpty();
        int sum = this.sum();
        iterator.restart();
        int count = Math.toIntExact(this.count());
        return sum / (double) count;
    }

    @Override
    public Integer max() {
        guardEmpty();
        return reduce(Integer.MIN_VALUE, Math::max);
    }

    @Override
    public Integer min() {
        guardEmpty();
        return reduce(Integer.MAX_VALUE, Math::min);
    }

    @Override
    public long count() {
        long res = reduce(0, (count, x) -> count += 1);
        iterator.restart();  // Keep iterator fresh after counting
        return res;
    }

    @Override
    public Integer sum() {
        guardEmpty();
        return reduce(0, (sum, x) -> sum += x);
    }

    @Override
    public void forEach(IntConsumer action) {
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        iterator = new FilterIterator(iterator, predicate);
        return this;
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        iterator = new MapIterator(iterator, mapper);
        return this;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        iterator = new FlatMapIterator(iterator, func);
        return this;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        while (iterator.hasNext()) {
            identity = op.apply(identity, iterator.next());
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        int[] intArr = new int[Math.toIntExact(this.count())];

        int i = 0;
        while (iterator.hasNext()) {
            intArr[i] = iterator.next();
            i++;
        }
        iterator.restart();
        return intArr;
    }

    private void guardEmpty() {
        if (this.count() == 0) {
            throw new IllegalArgumentException("No elements");
        }
    }
}
