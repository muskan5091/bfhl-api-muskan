package com.bhfl.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LogicService {

   
    public List<Integer> fibonacci(int n) {
        List<Integer> res = new ArrayList<>();
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            res.add(a);
            int temp = a + b;
            a = b;
            b = temp;
        }
        return res;
    }

    
    public List<Integer> primes(List<Integer> nums) {
        List<Integer> res = new ArrayList<>();
        for (int n : nums) {
            if (isPrime(n)) res.add(n);
        }
        return res;
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }

    
   public int hcf(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) return 0;

        int result = nums.get(0);
        for (int n : nums) {
            result = gcd(result, n);
        }
        return Math.abs(result);
    }

    
    public int lcm(List<Integer> nums) {
        int result = nums.get(0);
        for (int n : nums)
            result = (result * n) / gcd(result, n);
        return result;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private final GeminiService geminiService;

public LogicService(GeminiService geminiService) {
    this.geminiService = geminiService;
}


    public String aiAnswer(String question) {
        return geminiService.fetchSingleWordAnswer(question);
    }
}
