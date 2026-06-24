package com.bhfl.backend;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.bhfl.backend.dto.RequestDTO;
import com.bhfl.backend.dto.ResponseDTO;
import com.bhfl.backend.service.BfhlServiceImpl;

public class BfhlServiceTest {

    @Test
    void testExampleA() {

        BfhlServiceImpl service =
                new BfhlServiceImpl();

        RequestDTO request =
                new RequestDTO();

        request.setData(
                List.of("a","1","334","4","R","$")
        );

        ResponseDTO response =
                service.processData(request);

        assertEquals(
                "339",
                response.getSum()
        );

        assertEquals(
                "Ra",
                response.getConcat_string()
        );
    }
}