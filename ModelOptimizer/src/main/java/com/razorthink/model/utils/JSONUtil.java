package com.razorthink.model.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @Type Class
 * 
 * @Description utilities for JSON operation
 * 
 * @author RZT
 * 
 */
public class JSONUtil {

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static ObjectMapper mapper = new ObjectMapper();

	static
	{
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setVisibilityChecker(
				VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
	}

	/***
	 * @Type Method
	 * 
	 * @description converts object to json
	 * 
	 * @param o
	 *            object
	 * @return String
	 */
	public static String stringify( Object o )
	{

		return gson.toJson(o);

	}

	/**
	 * Will NOT return null on exception
	 * 
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String stringifyNoCatch( Object object ) throws JsonProcessingException
	{
		return mapper.writeValueAsString(object);
	}

	/**
	 * @Type Method
	 * 
	 * @description Converts string to object
	 * 
	 * @param jsonString
	 * @param type
	 * @return
	 */
	public static <T> T parse( String jsonString, Type type )
	{
		return gson.fromJson(jsonString, type);
	}

	public static <T> T jacksonParse( String jsonString, Class<T> type )
			throws JsonParseException, JsonMappingException, IOException
	{
		return mapper.readValue(jsonString, type);
	}

	public static <T> T cloneBean( T bean )
	{
		String serializedCopy = stringify(bean);
		return parse(serializedCopy, bean.getClass());

	}

	public static ObjectMapper buildNewObjectMapper()
	{
		ObjectMapper newObjectMapper = new ObjectMapper();
		newObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		newObjectMapper.setVisibilityChecker(
				VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		newObjectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		return newObjectMapper;
	}

}
