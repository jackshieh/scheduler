package com.breakthrough.scheduler.common;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;

public class ObjectUtils {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(@NotNull Object object) {
		ObjectMapper mapper = new ObjectMapper();		
		return mapper.convertValue(object, Map.class);
	}
	
}
