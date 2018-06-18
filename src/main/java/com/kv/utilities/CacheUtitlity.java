package com.kv.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CacheUtitlity {
	private static Logger logger = Logger.getLogger(CacheUtitlity.class);
	
	@Value("${cache.path}")
	private String cacheLocation;
	
	@Value("${cache.filename}")
	private String filename;
	
	public void updateCache(String key, String value) {
		logger.info("Updating cache...");
		String content = key + " " + value;
		try {
			File file = new File(cacheLocation+"/"+filename);
			List<String> lines = FileUtils.readLines(file);
			List<String> updatedLines = lines.stream().filter(s -> {
																	String[] strs = s.split(" ");
																	return !strs[0].equals(key);
																	})
													.collect(Collectors.toList());
			FileUtils.writeLines(file, updatedLines, false);
			Files.write(Paths.get(cacheLocation+"/"+filename), content.getBytes(), StandardOpenOption.APPEND);
			logger.info("Cache updation done.");
		} catch (IOException e) {
			logger.error("error in updating cache : " +e.getMessage());
			
		}
	}
	
	public String getCachedData(String key) {
		logger.info("Getting cached data...");
		try {
			File file = new File(cacheLocation+"/"+filename);
			List<String> lines = FileUtils.readLines(file);
			String val = lines.stream().filter(s -> {
														String[] strs = s.split(" ");
														return (strs[0].equals(key));
													})
										.map(s -> s.split(" ")[1])
										.findFirst().get();
			logger.info("Got data in cached : "+val);
			return val;
		} catch (IOException e) {
			logger.error("error in getting cached data : " +e.getMessage());
		}
		return null;
	}
}
