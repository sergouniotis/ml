package com.tns.ml.common;

import java.util.function.Function;

public class Predictor<T, V> implements Feature<T, V> {

	private Function<T, V> f;

	private Long index;

	private String name;

	private Class<V> type;

	public Predictor(Function<T, V> f, Long index, String name, Class<V> type) {
		super();
		this.f = f;
		this.index = index;
		this.name = name;
		this.type = type;
	}

	public Predictor(Function<T, V> f, String name, Class<V> type) {
		super();
		this.f = f;
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
		return type;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Predictor other = (Predictor) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
