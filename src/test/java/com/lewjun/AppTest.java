package com.lewjun;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 *
 * @author LewJun
 */
public class AppTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

	@Test
	public void testGreet() {
		LOGGER.info("info");
		LOGGER.warn("warn");
		LOGGER.debug("debug");
		LOGGER.error("error");
	}
}
