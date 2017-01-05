package org.lenskart.tests;

import org.apache.log4j.Logger;

public class TestLogger{
	
	private static final Logger log = Logger.getRootLogger();
	
	public static void main(String args[]){
		
		log.info("Info message");
		log.debug("Debug message");
		
	}

}
