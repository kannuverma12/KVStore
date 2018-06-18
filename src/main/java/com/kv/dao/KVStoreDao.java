package com.kv.dao;

public interface KVStoreDao {

	public void put(String key, String value);
	public String get(String key);
}
