package com.app.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lk00564920
 * EntryApplication
 * Spring boot Main application
 *
 */
@SpringBootApplication
public class EntryApplication {
	
	private final static Logger logger = LoggerFactory.getLogger(EntryApplication.class);
	
    public static void main(String[] args) {    	
        SpringApplication.run(EntryApplication.class, args);
        logger.info("Guest Entry Application Started");        
    }
}
