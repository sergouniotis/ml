package com.tns.ml.converters;

public interface ArrayConverter<T> {

	double[] to(T t);

	T from(double[] array);

}
