package com.kv.model;

import org.apache.log4j.Logger;

public class KVStore<K,V> {
	private static Logger logger = Logger.getLogger(KVStore.class);
	
	private volatile KVEntry<K,V>[] bucket;
	volatile private static int capacity = 16;
	
	@SuppressWarnings("unchecked")
	public KVStore() {
		bucket = new KVEntry[capacity];
	}
	
	public void put(K key, V value) {
		logger.info("Model : Setting Value");
		if(key == null)
			return;
		int hashVal = hash(key);
		
		KVEntry<K,V> newEntry = new KVEntry<K, V>(key, value, null);
		
		if(bucket[hashVal] == null) {
			bucket[hashVal] = newEntry;
			logger.info("Model : New Entry : Added to bucket at location : "+hashVal);
		}else {
			KVEntry<K, V> previous = null;
			KVEntry<K, V> current = bucket[hashVal];
			
			while(current != null) {
				if(current.key.equals(key)) {
					if(previous == null) {
						newEntry.next = current.next;
						bucket[hashVal] = newEntry;
						logger.info("Model : Updated new entry at : "+hashVal);
						return;
					}else {
						newEntry.next = current.next;
						previous.next = newEntry;
						logger.info("Model : Updated previous and next Value");
						return;
					}
				}
				previous = current;
				current = current.next;
			}
			previous.next = newEntry;
		}
	}
	
	
	public V get(K key) {
		logger.info("Model : Getting Value");
		int hashVal = hash(key);
		if(bucket[hashVal] == null) {
			logger.info("Model : Hash Null");
			return null;
		}else {
			KVEntry<K, V> entry = bucket[hashVal];
			logger.info("Model : Got Entry");
			while(entry != null) {
				if(entry.key.equals(key)) {
					logger.info("Model : Returning value : "+entry.value);
					return entry.value;
				}
				entry = entry.next;
			}
			return null;
		}
		
	}

	private int hash(K key) {
		return Math.abs(key.hashCode() % capacity);
	}
	
}

class KVEntry<K, V>{
	K key;
	V value;
	KVEntry<K,V> next;
	
	public KVEntry(K key, V value, KVEntry<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}
}
