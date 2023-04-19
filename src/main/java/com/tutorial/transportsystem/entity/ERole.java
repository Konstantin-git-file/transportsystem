package com.tutorial.transportsystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ERole {
    @JsonProperty("user")
    USER,
    @JsonProperty("mod")
    MODERATOR,
    @JsonProperty("admin")
    ADMIN;


}

//    USER,
//    MODERATOR,
//    ADMIN;
//
//    private static Map<String, ERole> namesMap = new HashMap<String, ERole>(3);
//
//    static {
//        namesMap.put("user", USER);
//        namesMap.put("mod", MODERATOR);
//        namesMap.put("admin", ADMIN);
//    }
//
//    @JsonCreator
//    public static ERole forValue(String value) {
//        return namesMap.get(StringUtils.lowerCase(value));
//    }
//
//    @JsonValue
//    public String toValue() {
//        for (Map.Entry<String, ERole> entry : namesMap.entrySet()) {
//            if (entry.getValue() == this)
//                return entry.getKey();
//        }
//
//        return null; // or fail
//    }


