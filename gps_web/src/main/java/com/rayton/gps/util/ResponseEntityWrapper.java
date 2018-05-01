package com.rayton.gps.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntityWrapper {


    public static ResponseEntity<Map<String, Object>> OK() {
        Map<String, Object> map = new HashMap<>();
        map.put("error", false);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Map<String, Object>> Failed() {
        Map<String, Object> map = new HashMap<>();
        map.put("error", true);
        // 418
        return new ResponseEntity<>(map, HttpStatus.I_AM_A_TEAPOT);
    }


}