package com.bhfl.backend.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bhfl.backend.service.LogicService;

@RestController
public class ApiController {

    private final LogicService logicService;
    private static final String EMAIL = "muskan0578.be23@chitkara.edu.in";

    public ApiController(LogicService logicService) {
        this.logicService = logicService;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("is_success", true);
        res.put("official_email", EMAIL);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    
    @PostMapping("/bfhl")
    public ResponseEntity<Map<String, Object>> bfhl(@RequestBody Map<String, Object> body) {

        Map<String, Object> res = new LinkedHashMap<>();

        try {

            if (body == null || body.size() != 1) {
                return error("Invalid request: only one key allowed", HttpStatus.BAD_REQUEST);
            }

            String key = body.keySet().iterator().next();
            Object value = body.get(key);

            Object result;

            switch (key) {

                case "fibonacci":
                    if (!(value instanceof Number))
                        return error("fibonacci requires integer", HttpStatus.BAD_REQUEST);

                    result = logicService.fibonacci(((Number) value).intValue());
                    break;

                case "prime":
                    if (!(value instanceof List))
                        return error("prime requires array", HttpStatus.BAD_REQUEST);

                    result = logicService.primes((List<Integer>) value);
                    break;

                case "lcm":
                    if (!(value instanceof List))
                        return error("lcm requires array", HttpStatus.BAD_REQUEST);

                    result = logicService.lcm((List<Integer>) value);
                    break;

                case "hcf":
                    if (!(value instanceof List))
                        return error("hcf requires array", HttpStatus.BAD_REQUEST);

                    result = logicService.hcf((List<Integer>) value);
                    break;

                case "AI":
                    if (!(value instanceof String))
                        return error("AI requires string", HttpStatus.BAD_REQUEST);

                    result = logicService.aiAnswer(value.toString());
                    break;

                default:
                    return error("Invalid key", HttpStatus.BAD_REQUEST);
            }

            res.put("is_success", true);
            res.put("official_email", EMAIL);
            res.put("data", result);

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            return error("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Map<String, Object>> error(String msg, HttpStatus status) {
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("is_success", false);
        res.put("official_email", EMAIL);
        res.put("error", msg);
        return new ResponseEntity<>(res, status);
    }
}
