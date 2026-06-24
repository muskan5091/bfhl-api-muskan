package com.bhfl.backend.service;

import com.bhfl.backend.dto.RequestDTO;
import com.bhfl.backend.dto.ResponseDTO;

public interface BfhlService {

    ResponseDTO processData(RequestDTO request);
}
