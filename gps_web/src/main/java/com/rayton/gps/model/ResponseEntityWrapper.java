package com.rayton.gps.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntityWrapper {




    public static ResponseEntity<Map<String, Object>> OK() {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "ok...");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Map<String, Object>> Failed(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", msg);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }



}
