package com.naver.cafe.common;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.cache.ehcache.EhCacheCacheManager;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class EhCacheUtil {
private Cache cache;
	
	public EhCacheUtil(EhCacheCacheManager ehCacheCacheManager, String cacheName) {
		cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
	}
	
	public <T> void put(String key, T value) {
		if(cache.get(key) != null) {
			cache.remove(key);
		}
		
		cache.put(new Element(key, value));
	}
	
	public <T> T get(String key) {
		Element element = cache.get(key);
		return (T) element.getObjectValue();
	}
	
	public void remove(String key) {
		cache.remove(key);
	}
	
	public void invalidate(String commonKey) {
		List<String> keys = cache.getKeys();
		
		keys.stream().forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
				
				if ( t.startsWith(commonKey + "_") ) {
					cache.remove(t);
				}
				
			}});
		
	}
}
