package com.tns.ml.common;

import java.util.function.Function;

public abstract class AbstractFeature<T, V> implements Feature<T, V> {

	protected Function<T, V> f;

	protected Long index;

	protected String name;

	protected Class<V> type;

	protected AbstractFeature(Function<T, V> f, Long index, String name, Class<V> type) {
		super();
		this.f = f;
		this.index = index;
		this.name = name;
		this.type = type;
	}

	@Override
	public Function<T, V> function() {
		return this.f;
	}

	@Override
	public Long index() {
		return this.index;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public Class<V> type() {
		return this.type;
	}

}
