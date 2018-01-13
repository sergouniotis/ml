package com.tns.ml.api;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.math3.ml.distance.ChebyshevDistance;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.ml.distance.ManhattanDistance;

import com.tns.ml.common.Feature;
import com.tns.ml.stats.SummaryStatistics;

/**
 * The purpose of this class is to provide functionalities used by the
 * algorithms for a specific class
 * 
 * @author sergouniotis
 *
 * @param <T>
 */
public interface Descriptor<T> {

	default SummaryStatistics<T> summaryStatistics() {
		return new SummaryStatistics<T>(comparator(), add(), divide());
	}

	/**
	 * Return the function for computing the distance between two entities. The
	 * default implementation computes the Euclidean Distance.
	 * 
	 * @return the function for computing the distance between two instances (
	 *         entities )
	 */
	default BiFunction<T, T, Double> distance() {
		return (a, b) -> {
			double[] aVector = this.converter().apply(a);
			double[] bVector = this.converter().apply(b);
			return new ChebyshevDistance().compute(aVector, bVector);
		};
	}

	List<Feature<T, ?>> features();

	BiFunction<T, T, T> add();

	BiFunction<T, T, T> subtract();

	BiFunction<T, Integer, T> multiply();

	BiFunction<T, Integer, T> divide();

	Comparator<T> comparator();

	Function<T, double[]> converter();

}
