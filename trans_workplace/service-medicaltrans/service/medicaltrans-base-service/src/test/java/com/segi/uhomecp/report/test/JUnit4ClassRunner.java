package com.segi.uhomecp.report.test;

import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;


public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {
	
	static {
	    try {
	      Log4jConfigurer.initLogging( "classpath:log4j.xml" );
	      System.setProperty("UHOME_CONFIG_PATH", "file:D:/appconfig/trans/uhome-config.properties");
	    }
	    catch( FileNotFoundException ex ) {
	      System.err.println( "Cannot Initialize log4j" );
	    }
	  }

	  public JUnit4ClassRunner( Class<?> clazz ) throws InitializationError {
	    super( clazz );
	  }  
    
}

