package com.tns.ml.utils.collectors;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class RealMatrix2DCollector implements Collector<double[][], RealMatrix, RealMatrix> {

	static final Set<Collector.Characteristics> CH_ID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));

	private int row;
	private int column;

	private int rows;

	private int columns;

	public RealMatrix2DCollector(int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
		this.row = 0;
	}

	@Override
	public Supplier<RealMatrix> supplier() {
		return () -> {
			return MatrixUtils.createRealMatrix(rows, columns);
		};
	}

	@Override
	public BiConsumer<RealMatrix, double[][]> accumulator() {
		return (matrix, array) -> matrix.setRow(row, array[row]);
	}

	@Override
	public BinaryOperator<RealMatrix> combiner() {
		return (a, b) -> a.add(b);
	}

	@Override
	public Function<RealMatrix, RealMatrix> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return CH_ID;
	}

}
