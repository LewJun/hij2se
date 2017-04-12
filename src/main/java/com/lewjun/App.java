package com.lewjun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 * @author LewJun
 */
public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		LOGGER.info("info");
		LOGGER.warn("warn");
		LOGGER.debug("debug");
		LOGGER.error("error");
	}
}
