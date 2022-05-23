package com.ery.getir.studycase.authentication;

import com.ery.getir.studycase.collections.Customer;
import com.ery.getir.studycase.repositories.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public UserDetailServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(username);

        if (!customerByEmail.isPresent()) {
            throw new UsernameNotFoundException("username not found");
        }

        return new User(customerByEmail.get().getName(), customerByEmail.get().getPassword(), Collections.emptyList());
    }
}
