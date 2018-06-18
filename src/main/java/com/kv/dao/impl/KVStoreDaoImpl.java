package com.kv.dao.impl;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kv.dao.KVStoreDao;
import com.kv.model.KVStore;
import com.kv.utilities.CacheUtitlity;

@Repository
public class KVStoreDaoImpl implements KVStoreDao {
	
	private static Logger logger = Logger.getLogger(KVStoreDaoImpl.class);
	
	@Autowired
	private CacheUtitlity cacheUtility;
	
	private KVStore<Object, Object> kvStore;
	
	@PostConstruct
	void init(){
		kvStore = new KVStore<>();
	}

	@Override
	public void put(String key, String value) {
		logger.info("Dao : Setting Value");
		kvStore.put(key, value);
		cacheUtility.updateCache(key, value);
	}

	@Override
	public String get(String key) {
		String cachedData = cacheUtility.getCachedData(key);
		if(cachedData != null) {
			logger.info("Getting Cahced Value");
			return cachedData;
		}else {
			logger.info("Dao : Getting Value");
			Object val = kvStore.get(key);
			if(val != null) {
				cacheUtility.updateCache(key, val.toString());
			}
			return val != null ? val.toString() : null;
		}
	}

}
