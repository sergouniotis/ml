package com.tns.ml.test.iris;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.math3.linear.ArrayRealVector;

import com.tns.ml.api.Descriptor;
import com.tns.ml.common.Feature;
import com.tns.ml.common.Predictor;
import com.tns.ml.converters.ArrayConverter;

public class IrisDescriptor implements Descriptor<Iris> {

	protected List<Feature<Iris, ?>> features;

	private ArrayConverter<Iris> arrayConverter;

	public IrisDescriptor() {

		this.arrayConverter = new IrisArrayConverter();

		this.features = new ArrayList<>();
		this.features.add(new Predictor<>(Iris::getPetalLength, 0L, "petalLength", Double.class));
		this.features.add(new Predictor<>(Iris::getPetalWidth, 1L, "petalWidth", Double.class));
		this.features.add(new Predictor<>(Iris::getSepalLength, 2L, "sepalLength", Double.class));
		this.features.add(new Predictor<>(Iris::getSepalWidth, 3L, "sepalWidth", Double.class));

	}

	@Override
	public List<Feature<Iris, ?>> features() {
		return this.features;
	}

	@Override
	public BiFunction<Iris, Iris, Iris> add() {
		return (a, b) -> {
			ArrayRealVector av = new ArrayRealVector(this.arrayConverter.to(a));
			ArrayRealVector ab = new ArrayRealVector(this.arrayConverter.to(b));
			return this.arrayConverter.from(av.add(ab).getDataRef());
		};
	}

	@Override
	public BiFunction<Iris, Iris, Iris> subtract() {
		return (a, b) -> {
			ArrayRealVector av = new ArrayRealVector(this.arrayConverter.to(a));
			ArrayRealVector ab = new ArrayRealVector(this.arrayConverter.to(b));
			return this.arrayConverter.from(av.subtract(ab).getDataRef());
		};
	}

	@Override
	public BiFunction<Iris, Integer, Iris> multiply() {
		return (a, b) -> {
			ArrayRealVector av = new ArrayRealVector(this.arrayConverter.to(a));
			return this.arrayConverter.from(av.mapMultiply(b).toArray());
		};
	}

	@Override
	public BiFunction<Iris, Integer, Iris> divide() {
		return (a, b) -> {
			ArrayRealVector av = new ArrayRealVector(this.arrayConverter.to(a));
			return this.arrayConverter.from(av.mapDivide(b).toArray());
		};
	}

	@Override
	public Comparator<Iris> comparator() {
		return (a, b) -> {
			ArrayRealVector av = new ArrayRealVector(this.arrayConverter.to(a));
			ArrayRealVector bv = new ArrayRealVector(this.arrayConverter.to(b));
			return Double.valueOf(av.getNorm()).compareTo(bv.getNorm());
		};
	}

	@Override
	public Function<Iris, double[]> converter() {
		return (i) -> this.arrayConverter.to(i);
	}

}
