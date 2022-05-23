package com.ery.getir.studycase.base;


import com.ery.getir.studycase.StudycaseApplication;
import com.ery.getir.studycase.authentication.JWTAuthorizationFilter;
import com.ery.getir.studycase.collections.Customer;
import com.ery.getir.studycase.dtos.CustomerDto;
import com.ery.getir.studycase.repositories.CustomerRepository;
import com.ery.getir.studycase.requests.NewCustomerRequest;
import com.ery.getir.studycase.services.CustomerService;
import com.ery.getir.studycase.services.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(classes = StudycaseApplication.class)
@AutoConfigureMockMvc
public class BaseApplicationTest {

    protected String TEST_PASSWORD = "password123456";
    private String token;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Lazy
    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        NewCustomerRequest customer = new NewCustomerRequest();
        customer.setName("name");
        customer.setSurname("surname");
        customer.setEmail("username@gmail.com");
        customer.setPassword(TEST_PASSWORD);
        CustomerDto customerDto = customerService.createNewCustomer(customer);
        token = jwtTokenService.generateToken(customerDto);

    }

    protected Customer getCustomer() {
        Customer customer = new Customer();
        customer.setName("name");
        customer.setSurname("surname");
        customer.setEmail("username@gmail.com");
        return customer;
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.getDb().drop();
    }

    public ResultActions postRequest(String url, Object body, Object... varibles) throws Exception {
        return mvc.perform(post(url, varibles)
                .contentType(MediaType.APPLICATION_JSON)
                .header(JWTAuthorizationFilter.AUTH_HEADER, JWTAuthorizationFilter.AUTH_HEADER_PREFIX + token)
                .content(objectMapper.writeValueAsString(body)));
    }

    public ResultActions getRequest(String url, Object... varibles) throws Exception {
        return mvc.perform(get(URI.create(url))
                .header(JWTAuthorizationFilter.AUTH_HEADER, JWTAuthorizationFilter.AUTH_HEADER_PREFIX + token)
                .contentType(MediaType.APPLICATION_JSON));
    }
}
