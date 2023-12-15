package com.example.aftasspringboot.Services;

import com.example.aftasspringboot.dtos.requests.HuntingRequest;
import com.example.aftasspringboot.entities.Hunting;

public interface HuntingService {

    Hunting addHunting(HuntingRequest huntingRequest);
}
