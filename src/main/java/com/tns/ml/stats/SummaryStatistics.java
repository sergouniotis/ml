package com.tns.ml.stats;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collector;

public class SummaryStatistics<T> implements Consumer<T> {

	private int count;

	final Comparator<? super T> comparator;

	private T min;

	private T max;

	private T sum;

	private BiFunction<T, T, T> add;

	private BiFunction<T, Integer, T> avg;

	public SummaryStatistics(Comparator<? super T> comparator, BiFunction<T, T, T> add, BiFunction<T, Integer, T> avg) {
		this.comparator = comparator;
		this.add = add;
		this.avg = avg;
	}

	public int count() {
		return count;
	}

	public T min() {
		return min;
	}

	public T max() {
		return max;
	}

	public T mean() {
		return this.avg.apply(this.sum, this.count);
	}

	@Override
	public void accept(T val) {
		if (count == 0)
			min = max = val;
		else if (comparator.compare(val, min) < 0)
			min = val;
		else if (comparator.compare(val, max) > 0)
			max = val;

		if (Objects.isNull(sum)) {
			this.sum = val;
		} else {
			this.sum = add.apply(this.sum, val);
		}

		count++;
	}

	public SummaryStatistics<T> combine(SummaryStatistics<T> that) {
		if (this.count == 0)
			return that;
		if (that.count == 0)
			return this;

		this.count += that.count;
		if (comparator.compare(that.min, this.min) < 0)
			this.min = that.min;
		if (comparator.compare(that.max, this.max) > 0)
			this.max = that.max;

		return this;
	}

	public static <T> Collector<T, SummaryStatistics<T>, SummaryStatistics<T>> collector(Comparator<? super T> comparator, BiFunction<T, T, T> add, BiFunction<T, Integer, T> avg) {
		return collector(new SummaryStatistics<>(comparator, add, avg));
	}

	public static <T> Collector<T, SummaryStatistics<T>, SummaryStatistics<T>> collector(SummaryStatistics<T> stats) {
		return Collector.of(() -> stats, SummaryStatistics::accept, SummaryStatistics::combine, Collector.Characteristics.UNORDERED, Collector.Characteristics.IDENTITY_FINISH);
	}
}
