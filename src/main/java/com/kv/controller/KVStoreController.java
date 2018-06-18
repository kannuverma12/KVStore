package com.kv.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kv.service.KVStoreService;

@RestController
@RequestMapping(value="/kvstore")
public class KVStoreController {
	
	private static Logger logger = Logger.getLogger(KVStoreController.class);
	
	@Autowired
	private KVStoreService kvStoreService;

	@RequestMapping(value="/get/{key}", method=RequestMethod.GET)
	public String get(@PathVariable("key") String key) {
		logger.info("Controller : Getting Value");
		return kvStoreService.get(key);
	}
	
	@RequestMapping(value="/set/{key}", method=RequestMethod.POST)
	public void put(@PathVariable("key") String key, @RequestParam(value="value",required=true) String value) {
		logger.info("Controller : Setting Value = "+value+" for key = "+key);
		kvStoreService.put(key, value);
	}
}
