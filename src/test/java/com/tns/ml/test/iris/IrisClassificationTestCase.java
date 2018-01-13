package com.tns.ml.test.iris;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tns.ml.classification.KNNClassifier;

public class IrisClassificationTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(IrisClassificationTestCase.class);

	private Set<Iris> trainingDataSet;

	private Set<Iris> validationDataSet;

	@Before
	public void init() {

		IrisCsvDataSource ds = new IrisCsvDataSource("iris" + File.separator + "iris.data");
		Set<Iris> set = ds.read();

		// Split 70-30
		this.trainingDataSet = set.stream().limit((int) (set.size() * 0.7)).collect(Collectors.toSet());
		this.validationDataSet = set.stream().filter(i -> !trainingDataSet.contains(i)).collect(Collectors.toSet());

	}

	@Test
	public void testKnn() {
		KNNClassifier<Iris, String> knn = new KNNClassifier<>(3, trainingDataSet, Iris::getCategory, new IrisDescriptor());

		long correct = this.validationDataSet.stream().map(i -> {
			String label = knn.classify(i);
			if (i.getCategory().equals(label)) {
				return 1;
			}
			return 0;
		}).count();

		double efficiency = correct / this.validationDataSet.size();

		LOGGER.info(String.valueOf(efficiency));

		Assert.assertEquals(true, efficiency > 0.95);

	}

}
