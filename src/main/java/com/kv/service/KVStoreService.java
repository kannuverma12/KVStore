package com.kv.service;

public interface KVStoreService {

	public void put(String key, String value);
	public String get(String key);
}
