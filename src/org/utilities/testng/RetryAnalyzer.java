package org.utilities.testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.utilities.testng.RetryCountIfFails;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int count = 0;
	// private int maxCount = 5;

	@Override
	public boolean retry(ITestResult result) {

		RetryCountIfFails retryAnnotation = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(RetryCountIfFails.class);

		if (retryAnnotation != null && (count < retryAnnotation.value())) {
			count++;
			return true;
		}
		return false;
	}

}