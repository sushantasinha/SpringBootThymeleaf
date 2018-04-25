package com.spring;

public class CacheModel {
	public CacheModel(String cacheKey, String cacheValue) {
		super();
		this.cacheKey = cacheKey;
		this.cacheValue = cacheValue;
	}
	private String cacheKey;
	private String cacheValue;
	public String getCacheKey() {
		return cacheKey;
	}
	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
	public String getCacheValue() {
		return cacheValue;
	}
	public void setCacheValue(String cacheValue) {
		this.cacheValue = cacheValue;
	}
}
