package com.tns.ml.common;

import java.util.function.Function;

import org.apache.commons.lang3.ClassUtils;

public interface Feature<T, V> {

	Function<T, V> function();

	Long index();

	String name();

	Class<V> type();

	default boolean isNumerical() {
		return ClassUtils.isPrimitiveOrWrapper(type());
	}

	default boolean isNominal() {
		return String.class.equals(type());
	}

	default boolean isOrdinal() {
		return Enum.class.equals(type());
	}

}
