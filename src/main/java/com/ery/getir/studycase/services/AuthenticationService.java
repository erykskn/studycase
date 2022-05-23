package com.ery.getir.studycase.services;

import com.ery.getir.studycase.collections.Customer;
import com.ery.getir.studycase.dtos.CustomerDto;
import com.ery.getir.studycase.dtos.JWTDto;
import com.ery.getir.studycase.exceptions.UserNotFoundException;
import com.ery.getir.studycase.exceptions.UsernameOrPasswordIncorrectException;
import com.ery.getir.studycase.responses.JWTResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final JwtTokenService jwtTokenService;
    private final ModelMapper modelMapper;

    public AuthenticationService(PasswordEncoder passwordEncoder, CustomerService customerService, JwtTokenService jwtTokenService, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.customerService = customerService;
        this.jwtTokenService = jwtTokenService;
        this.modelMapper = modelMapper;
    }

    public JWTResponse token(JWTDto authenticationRequest) {
        Customer customer = customerService.getCustomerByEmail(authenticationRequest.getUsername())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), customer.getPassword())) {
            throw new UsernameOrPasswordIncorrectException();
        }
        String token = jwtTokenService.generateToken(modelMapper.map(customer, CustomerDto.class));
        JWTResponse response = new JWTResponse();
        response.setToken(token);
        return response;
    }
}