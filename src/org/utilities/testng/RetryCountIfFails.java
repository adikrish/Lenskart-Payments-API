/**
 * 
 */
package org.utilities.testng;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Documented
@Retention(RUNTIME)
/**
 * @author Adithya
 * 
 *         Specify the number of times to retry test method if fails Default
 *         value is 0, can be defined number of retries in tag
 */
public @interface RetryCountIfFails {

	int value() default 0;
}
