package com.tns.ml.datasources;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public abstract class CSVDataSource<T> implements DataSource<T> {

	protected String csvFilePath;

	public CSVDataSource(String csvFilePath) {
		super();
		this.csvFilePath = csvFilePath;
	}

	@Override
	public Set<T> read() {
		Set<T> set = new HashSet<>();
		try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(csvFilePath); Reader in = new InputStreamReader(is)) {
			Iterable<CSVRecord> records = csvFormat().parse(in);
			for (CSVRecord record : records) {

				T t = convert(record);

				set.add(t);

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return set;
	}

	protected CSVFormat csvFormat() {
		return CSVFormat.DEFAULT;
	}

	protected abstract T convert(CSVRecord record);

}
