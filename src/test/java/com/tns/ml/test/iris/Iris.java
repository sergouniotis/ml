package com.tns.ml.test.iris;

import java.util.Optional;

public class Iris {

	private Double petalLength;

	private Double petalWidth;

	private Double sepalLength;

	private Double sepalWidth;

	private Optional<String> category;

	public Iris() {
		super();
	}

	public Iris(Double petalLength, Double petalWidth, Double sepalLength, Double sepalWidth, String category) {
		super();
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.category = Optional.ofNullable(category);
	}

	public Double getPetalLength() {
		return petalLength;
	}

	public void setPetalLength(Double petalLength) {
		this.petalLength = petalLength;
	}

	public Double getPetalWidth() {
		return petalWidth;
	}

	public void setPetalWidth(Double petalWidth) {
		this.petalWidth = petalWidth;
	}

	public Double getSepalLength() {
		return sepalLength;
	}

	public void setSepalLength(Double sepalLength) {
		this.sepalLength = sepalLength;
	}

	public Double getSepalWidth() {
		return sepalWidth;
	}

	public void setSepalWidth(Double sepalWidth) {
		this.sepalWidth = sepalWidth;
	}

	public String getCategory() {
		return this.category.get();
	}

	public void setCategory(String category) {
		this.category = Optional.ofNullable(category);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(petalLength);
		builder.append(",");
		builder.append(petalWidth);
		builder.append(",");
		builder.append(sepalLength);
		builder.append(",");
		builder.append(sepalWidth);
		builder.append(",");
		builder.append(category.orElse(""));
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((petalLength == null) ? 0 : petalLength.hashCode());
		result = prime * result + ((petalWidth == null) ? 0 : petalWidth.hashCode());
		result = prime * result + ((sepalLength == null) ? 0 : sepalLength.hashCode());
		result = prime * result + ((sepalWidth == null) ? 0 : sepalWidth.hashCode());
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
		Iris other = (Iris) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (petalLength == null) {
			if (other.petalLength != null)
				return false;
		} else if (!petalLength.equals(other.petalLength))
			return false;
		if (petalWidth == null) {
			if (other.petalWidth != null)
				return false;
		} else if (!petalWidth.equals(other.petalWidth))
			return false;
		if (sepalLength == null) {
			if (other.sepalLength != null)
				return false;
		} else if (!sepalLength.equals(other.sepalLength))
			return false;
		if (sepalWidth == null) {
			if (other.sepalWidth != null)
				return false;
		} else if (!sepalWidth.equals(other.sepalWidth))
			return false;
		return true;
	}

}
