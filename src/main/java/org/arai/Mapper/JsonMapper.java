package org.arai.Mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {


    public static <T> T mapJsonToObject(String json, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error mapping JSON to object: " + e.getMessage(), e);
        }
    }


    public static String mapObjectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Error mapping object to JSON: " + e.getMessage(), e);
        }
    }

}
