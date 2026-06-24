package com.bhfl.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhfl.backend.dto.RequestDTO;
import com.bhfl.backend.dto.ResponseDTO;
import com.bhfl.backend.service.BfhlService;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService service;

    public BfhlController(BfhlService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseDTO processData(
            @RequestBody RequestDTO request) {

        return service.processData(request);
    }
}