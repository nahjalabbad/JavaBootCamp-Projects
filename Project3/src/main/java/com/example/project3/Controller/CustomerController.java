package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/get-all")
    public ResponseEntity getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @PostMapping("/register-customer")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.ok(new ApiResponse("customer registered"));
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user,@RequestBody @Valid CustomerDTO customerDTO){
        customerService.updateCustomer(user.getUserId(), customerDTO);
        return ResponseEntity.ok(new ApiResponse("customer information updated"));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user, @PathVariable Integer customerId){
        customerService.deleteCustomer(user.getUserId(),customerId);
        return ResponseEntity.ok(new ApiResponse("customer information deleted"));
    }


}
