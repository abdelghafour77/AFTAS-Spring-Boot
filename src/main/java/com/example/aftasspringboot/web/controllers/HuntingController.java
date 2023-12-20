package com.example.aftasspringboot.web.controllers;

import com.example.aftasspringboot.Services.HuntingService;
import com.example.aftasspringboot.dtos.requests.HuntingRequest;
import com.example.aftasspringboot.entities.Hunting;
import com.example.aftasspringboot.handler.response.ResponseMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hunting")
@AllArgsConstructor
public class HuntingController {
    private final HuntingService huntingService;

    @PostMapping
    public Hunting addHunting(@RequestBody @Valid HuntingRequest huntingRequest) {
        return huntingService.addHunting(huntingRequest);
    }



}
