package com.example.rabbitmq.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Guankai.Feng
 * @date 2019-10-22
 **/
public class JsonUtil {
    private JsonUtil() {
    }

    private static final ObjectMapper COMMON_OBJECT_MAPPER = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    static {
        COMMON_OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).
            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static <T> T parse(String jsonString, Class<T> clazz) {
        try {
            return COMMON_OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(String jsonString, TypeReference<T> typeReference) {
        try {
            return COMMON_OBJECT_MAPPER.readValue(jsonString, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toString(Object object) {
        try {
            return COMMON_OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(byte[] src, int offset, int len, Class<T> valueType) {
        try {
            return COMMON_OBJECT_MAPPER.readValue(src, offset, len, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(Object map, TypeReference<T> typeReference) {
        return COMMON_OBJECT_MAPPER.convertValue(map, typeReference);
    }


}
