package com.example.serviceclient.util;

import com.example.common.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

/**
 * @Author wangjiaxing
 * @Date 2023/1/11
 */
@Component
public class SensitiveLogUtil {

    private Function<String,String> sensitiveFunc = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return "En: "+ s;
        }
    };

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final List<String> sensitiveFields = Lists.newArrayList("IDNUMBER", "EMAIL", "ACCOUNTNUMBER","PHONE","MOBILE");
    private static final List<String> excludeFields = Lists.newArrayList("ENCRYPTED");


    public String toSensitiveEncryptedStringLog(String oldJsonStr) {
        try {
            Object obj = null;
            if (oldJsonStr.trim().startsWith("[") && oldJsonStr.trim().endsWith("]")) {
                obj = JsonUtil.parse(oldJsonStr, new TypeReference<List>() {
                });
            } else if(oldJsonStr.trim().startsWith("{") && oldJsonStr.trim().endsWith("}")){
                obj = JsonUtil.parse(oldJsonStr, new TypeReference<Map<String, Object>>() {
                });
            }else {
                return oldJsonStr;
            }
            return obj == null ? oldJsonStr : JsonUtil.toString(encryptedObj(obj));
        } catch (Exception e) {
            log.warn("toSensitiveEncryptedStringLog warn");
            return oldJsonStr;
        }
    }

    private Object encryptedObj(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            if (obj instanceof String) {
                return obj;
            } else if (obj instanceof Collection) {
                Collection collect = (Collection) obj;
                Iterator iterator = collect.iterator();
                List<Object> list = new ArrayList<>();
                while (iterator.hasNext()) {
                    list.add(encryptedObj(iterator.next()));
                }
                return list;
            } else if (obj instanceof Map) {
                Map objMap = (Map) obj;
                Set<Map.Entry> sets = objMap.entrySet();
                sets.stream().forEach(it -> {
                    if (it.getValue() instanceof String && isSensitiveStr((String) it.getKey())) {
                        objMap.put(it.getKey(), sensitiveFunc.apply((String)it.getValue()));
                    } else {
                        objMap.put(it.getKey(), encryptedObj(it.getValue()));
                    }
                });
                return objMap;
            } else {
                return obj;
            }

        } catch (Exception e) {
            return obj;
        }
    }

    public static boolean isSensitiveStr(String str) {
        if(str==null) {
            return false;
        }

        for (String field : excludeFields) {
            if(str.toUpperCase().contains(field)) {
                return false;
            }
        }

        for (String sensitiveFiled : sensitiveFields) {
            if(str.toUpperCase().contains(sensitiveFiled)) {
                return true;
            }
        }
        return false;
    }

}
