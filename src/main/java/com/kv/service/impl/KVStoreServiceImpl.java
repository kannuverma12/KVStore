package com.kv.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kv.dao.KVStoreDao;
import com.kv.service.KVStoreService;

@Service
public class KVStoreServiceImpl implements KVStoreService {
	
	private static Logger logger = Logger.getLogger(KVStoreServiceImpl.class);
	
	@Autowired
	private KVStoreDao kvStoreDao;

	@Override
	public void put(String key, String value) {
		logger.info("Service : Setting Value");
		kvStoreDao.put(key, value);
	}

	@Override
	public String get(String key) {
		logger.info("Service : Getting Value");
		return kvStoreDao.get(key);
	}

}
