package com.tns.ml.classification;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.tns.ml.api.Descriptor;

public class KNNClassifier<T, C> implements Classifier<T, C> {

	/**
	 * The training data
	 */
	private Collection<T> data;

	/**
	 * The number of neighbors
	 */
	private int k;

	/**
	 * Function for getting the class feature value from the T entity.
	 */
	private Function<T, C> cFunction;

	/**
	 * Descriptor of T
	 */
	private Descriptor<T> descriptor;

	public KNNClassifier(int k, Collection<T> data, Function<T, C> cFunction, Descriptor<T> descriptor) {
		super();
		this.data = data;
		this.k = k;
		this.cFunction = cFunction;
		this.descriptor = descriptor;
	}

	@Override
	public C classify(T datum) {

		// find nearest the k neighbors
		Collection<T> neighbors = this.data.stream().sorted(Comparator.comparing(t -> distance(datum, t))).limit(k).collect(Collectors.toSet());

		// let the neigbors vote
		Map<C, Long> map = neighbors.stream().collect(Collectors.groupingBy(cFunction, Collectors.counting()));

		// pick the most voted category
		return map.entrySet().stream().sorted(Map.Entry.<C, Long> comparingByValue().reversed()).limit(1).findFirst().get().getKey();

	}

	private double distance(T fom, T to) {
		return this.descriptor.distance().apply(fom, to);
	}

}
