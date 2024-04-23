package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;


    @GetMapping("/get-all")
    public ResponseEntity getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/get")
    public ResponseEntity getMyAccounts(@AuthenticationPrincipal User customer){
        return ResponseEntity.ok(accountService.getMyAccounts(customer.getUserId()));
    }

    @PostMapping("/create-account")
    public ResponseEntity createAccount(@AuthenticationPrincipal User customer, @RequestBody @Valid Account account){
        accountService.createAccount(customer.getUserId(),account);
        return ResponseEntity.ok(new ApiResponse("new account created"));
    }

    @PutMapping("/update/{accId}")
    public ResponseEntity updateAccount(@AuthenticationPrincipal User customer, @PathVariable Integer accId, @RequestBody @Valid Account account){
        accountService.updateAccount(customer.getUserId(), accId ,account);
        return ResponseEntity.ok(new ApiResponse("account information updated"));
    }

    @DeleteMapping("/delete/{accId}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User customer, @PathVariable Integer accId){
        accountService.deleteAccount(customer.getUserId(),accId);
        return ResponseEntity.ok(new ApiResponse("account information deleted"));
    }

    //Customer

    @GetMapping("/view-details/{accId}")
    public ResponseEntity viewAccountDetails(@AuthenticationPrincipal User customer, @PathVariable Integer accId){
        return ResponseEntity.ok(accountService.viewAccountDetails(customer.getUserId(),accId));
    }

    @PutMapping("/deposit/{accId}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal User customer,@PathVariable Integer accId, @PathVariable Double amount){
        accountService.deposit(customer.getUserId(),accId,amount);
        return ResponseEntity.ok(new ApiResponse(amount+ " deposited to you account"));
    }

    @PutMapping("/withdraw/{accId}/{amount}")
    public ResponseEntity viewAccountDetails(@AuthenticationPrincipal User customer,@PathVariable Integer accId, @PathVariable Double amount){
        accountService.withdraw(customer.getUserId(),accId,amount);
        return ResponseEntity.ok(new ApiResponse(amount+ " withdrew from you account"));
    }

    @PostMapping("/transfer/{acc1Id}/{acc2Id}/{amount}")
    public ResponseEntity transfer(@AuthenticationPrincipal User customer, @PathVariable Integer acc1Id, @PathVariable Integer acc2Id, @PathVariable Double amount){
        accountService.transfer(customer.getUserId(),acc1Id, acc2Id, amount);
        return ResponseEntity.ok(new ApiResponse(amount+ " has been transferred to your second account"));
    }

    //EMPLOYEE

    @PutMapping("/activate/{accId}")
    public ResponseEntity activateAccount(@AuthenticationPrincipal User employee, @PathVariable Integer accId){
        accountService.activateAccount(employee.getUserId(), accId);
        return ResponseEntity.ok(new ApiResponse("account activated"));
    }

    @PutMapping("/block/{accId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User employee, @PathVariable Integer accId){
        accountService.blockAccount(employee.getUserId(), accId);
        return ResponseEntity.ok(new ApiResponse("account blocked"));
    }



}
