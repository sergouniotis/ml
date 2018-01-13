package com.tns.ml.datasources;

import java.util.Set;

public interface DataSource<T> {

	Set<T> read();

}
