package com.tns.ml.classification;

public interface Classifier<T, C> {

	C classify(T datum);

}
