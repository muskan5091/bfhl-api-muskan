package com.bhfl.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhfl.backend.service.LogicService;

@RestController
public class ApiController {

    private final LogicService logicService;
    private static final String EMAIL = "muskan0578.be23@chitkara.edu.in";

    public ApiController(LogicService logicService) {
        this.logicService = logicService;
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> res = new HashMap<>();
        res.put("is_success ", true);
        res.put("official_email ", EMAIL);
        return res;
    }

   @PostMapping(value = "/bfhl", produces = "application/json")
public Map<String, Object> bfhl(@RequestBody Map<String, Object> body) {

    Map<String, Object> res = new HashMap<>();

    if (body == null || body.size() != 1) {
        res.put("is_success ", false);
        return res;
    }

    String key = body.keySet().iterator().next();
    Object value = body.get(key);
    Object result;

    switch (key) {
        case "fibonacci":
            result = logicService.fibonacci((Integer) value);
            break;
        case "prime":
            result = logicService.primes((java.util.List<Integer>) value);
            break;
        case "lcm":
            result = logicService.lcm((java.util.List<Integer>) value);
            break;
        case "hcf":
            result = logicService.hcf((java.util.List<Integer>) value);
            break;
        case "AI":
            result = logicService.aiAnswer(value.toString());
            break;
        default:
            res.put("is_success", false);
            return res;
    }

    res.put("is_success ", true);
    res.put("official_email ", EMAIL);
    res.put("data ", result);
    return res;
}

}
