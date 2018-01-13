package com.tns.ml.test.iris;

import org.apache.commons.csv.CSVRecord;

import com.tns.ml.datasources.CSVDataSource;

public class IrisCsvDataSource extends CSVDataSource<Iris> {

	public IrisCsvDataSource(String csvFilePath) {
		super(csvFilePath);
	}

	protected Iris convert(CSVRecord record) {
		Iris iris = new Iris();
		iris.setSepalLength(Double.parseDouble(record.get(IrisArrayConverter.Attributes.sepal_length.ordinal())));
		iris.setSepalWidth(Double.parseDouble(record.get(IrisArrayConverter.Attributes.sepal_width.ordinal())));
		iris.setPetalLength(Double.parseDouble(record.get(IrisArrayConverter.Attributes.petal_length.ordinal())));
		iris.setPetalWidth(Double.parseDouble(record.get(IrisArrayConverter.Attributes.petal_width.ordinal())));
		iris.setCategory(record.get(4));
		return iris;
	}

}
