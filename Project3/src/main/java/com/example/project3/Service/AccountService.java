package com.example.project3.Service;

import com.example.project3.API.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.CustomerRepostitory;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepostitory customerRepostitory;
    private final EmployeeRepository employeeRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public List<Account> getMyAccounts(Integer customerId){
        Customer customer=customerRepostitory.findCustomerByCustomerId(customerId);
        return accountRepository.findAllByCustomer(customer);
    }

    public void createAccount(Integer customerId, Account account){
        Customer customer=customerRepostitory.findCustomerByCustomerId(customerId);
        if (account.getIsActive() == null) {
            account.setIsActive(false);
        }

        account.setCustomer(customer);
        accountRepository.save(account);
    }

    public void updateAccount( Integer customerId, Integer accId, Account account){
        Account account1=accountRepository.findAccountByAccId(accId);
        if (account1==null||!account.getIsActive()){
            throw new ApiException("account not found");
        }
        if (!account1.getCustomer().getCustomerId().equals(customerId)) {
            throw new ApiException("wrong customer id");
        }
        account1.setBalance(account.getBalance());
        accountRepository.save(account1);
    }

    public void deleteAccount(Integer customerId,Integer accId){
        Account account=accountRepository.findAccountByAccId(accId);
        if (account==null||!account.getIsActive()){
            throw new ApiException("account not found");
        }
        if (!account.getCustomer().getCustomerId().equals(customerId)) {
            throw new ApiException("wrong customer id");
        }
        accountRepository.delete(account);
    }

    //CUSTOMER

    public Account viewAccountDetails(Integer customerId,Integer accId){
        Account account=accountRepository.findAccountByAccId(accId);
        if (account == null||!account.getIsActive()) {
            throw new ApiException("Account not found");
        }
        return account;
    }

    public void deposit(Integer customerId,Integer accId, Double amount) {
        Account account = accountRepository.findAccountByAccId(accId);
        if (account == null||!account.getIsActive()) {
            throw new ApiException("Account not found");
        }
        if (amount <= 0) {
            throw new ApiException("Deposit amount must be positive");
        }

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdraw(Integer customerId,Integer accId, Double amount) {
        Account account = accountRepository.findAccountByAccId(accId);
        if (account == null|| !account.getIsActive()) {
            throw new ApiException("Account not found");
        }
        if (amount <= 0) {
            throw new ApiException("Withdrawal amount must be positive");
        }
        if (amount > account.getBalance()) {
            throw new ApiException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void transfer(Integer customerId,Integer acc1Id, Integer acc2Id, Double amount ){
        Account account1=accountRepository.findAccountByAccId(acc1Id);
        Account account2=accountRepository.findAccountByAccId(acc2Id);
        if (account1==null || account2==null){
            throw new ApiException("one of the account not found");
        }
        if (!account1.getIsActive()||!account2.getIsActive()){
            throw new ApiException("one of the account is not active");
        }
        if (account1.getAccId().equals(account2.getAccId())){
            throw new ApiException("you cannot transfer to the same account");
        }
        if (account1.getBalance() < amount || account1.getBalance() == 0){
            throw new ApiException("insufficient balance in account 1");
        }
        if (!account1.getCustomer().getCustomerId().equals(account2.getCustomer().getCustomerId())){
            throw new ApiException("the account provided is not associated to your account");
        }
        account1.setBalance(account1.getBalance()-amount);
        account2.setBalance(account2.getBalance()+amount);

        accountRepository.save(account1);
        accountRepository.save(account2);

    }


    //EMPLOYEE
    public void activateAccount(Integer empId,Integer accId){
        Account account=accountRepository.findAccountByAccId(accId);
        if (account==null){
            throw new ApiException("Account not found");
        }
        account.setIsActive(true);
        accountRepository.save(account);
    }

    public void blockAccount(Integer empId,Integer accId){
        Account account=accountRepository.findAccountByAccId(accId);
        if (account==null){
            throw new ApiException("Account not found");
        }
        if (!account.getIsActive()){
            throw new ApiException("account is already blocked");
        }
        account.setIsActive(false);
        accountRepository.save(account);
    }


}

