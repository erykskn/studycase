package com.ery.getir.studycase.services;

import com.ery.getir.studycase.collections.Customer;
import com.ery.getir.studycase.dtos.CustomerDto;
import com.ery.getir.studycase.exceptions.UserAlreadySignUpException;
import com.ery.getir.studycase.repositories.CustomerRepository;
import com.ery.getir.studycase.requests.NewCustomerRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerDto createNewCustomer(NewCustomerRequest customerDto) {
        checkCustomerByEmail(customerDto.getEmail());
        customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        Customer customer = customerRepository.save(modelMapper.map(customerDto, Customer.class));
        return modelMapper.map(customer, CustomerDto.class);
    }

    private void checkCustomerByEmail(String email) {
        Optional<Customer> customerByEmail = getCustomerByEmail(email);
        if (customerByEmail.isPresent()) {
            throw new UserAlreadySignUpException();
        }
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }


    public Optional<Customer> findById(String id){
        return customerRepository.findById(id);
    }

}
