package com.example.demo.FunctionCall;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerTool {

    private final CustomerRepository customerRepository;
    @Tool(name = "uniqueNameForGetCustomerById",description = "고객 ID로 고객 조회", returnDirect = true)
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Tool(description = "모든 고객 조회", returnDirect = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}

