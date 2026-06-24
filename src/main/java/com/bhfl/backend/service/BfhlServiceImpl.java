package com.bhfl.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bhfl.backend.dto.RequestDTO;
import com.bhfl.backend.dto.ResponseDTO;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public ResponseDTO processData(RequestDTO request) {

        List<String> evenNumbers = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();

        long sum = 0;

        StringBuilder alphabetString = new StringBuilder();

        for (String item : request.getData()) {

            if (item.matches("\\d+")) {

                int num = Integer.parseInt(item);

                sum += num;

                if (num % 2 == 0)
                    evenNumbers.add(item);
                else
                    oddNumbers.add(item);

            }

            else if (item.matches("[a-zA-Z]+")) {

                alphabets.add(item.toUpperCase());

                alphabetString.append(item);
            }

            else {

                specialCharacters.add(item);
            }
        }

        String concatString =
                getAlternateCaps(
                        alphabetString.reverse().toString()
                );

        ResponseDTO response = new ResponseDTO();

        response.setIs_success(true);

        response.setUser_id("muskan_rani_22042005");

        response.setEmail("muskan0578.be23@chitkara.edu.in");

        response.setRoll_number("2310990578");

        response.setEven_numbers(evenNumbers);

        response.setOdd_numbers(oddNumbers);

        response.setAlphabets(alphabets);

        response.setSpecial_characters(specialCharacters);

        response.setSum(String.valueOf(sum));

        response.setConcat_string(concatString);

        return response;
    }

    private String getAlternateCaps(String input) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            if (i % 2 == 0)
                result.append(
                        Character.toUpperCase(input.charAt(i))
                );
            else
                result.append(
                        Character.toLowerCase(input.charAt(i))
                );
        }

        return result.toString();
    }
}