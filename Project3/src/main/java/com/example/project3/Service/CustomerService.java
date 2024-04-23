package com.example.project3.Service;

import com.example.project3.API.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepostitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final AuthRepository authRepository;
    private final CustomerRepostitory customerRepostitory;

    public List<Customer> getAllCustomer(){
        return customerRepostitory.findAll();
    }

    public void registerCustomer(CustomerDTO customerDTO){
        User user=new User();
        user.setRole("CUSTOMER");
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        user.setUsername(customerDTO.getUsername());

        String hashPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        user.setPassword(hashPassword);

        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());


        user.setCustomer(customer);
        customer.setUser(user);

        authRepository.save(user);


        customerRepostitory.save(customer);
    }


    public void updateCustomer(Integer userId, CustomerDTO customerDTO){
        User user=authRepository.findUserByUserId(userId);
        Customer customer=customerRepostitory.findCustomerByCustomerId(user.getUserId());

        user.setName(customerDTO.getName());
        user.setUsername(customerDTO.getUsername());
        user.setEmail(customerDTO.getEmail());
        String hashPassword=new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        user.setPassword(hashPassword);

        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        authRepository.save(user);
        customerRepostitory.save(customer);

    }

    public void deleteCustomer(Integer adminId,Integer customerId){
        User user=authRepository.findUserByUserId(adminId);
        Customer customer=customerRepostitory.findCustomerByCustomerId(customerId);
        User user1=authRepository.findUserByUserId(customerId);

        customerRepostitory.delete(customer);

        authRepository.delete(user1);

    }
}
