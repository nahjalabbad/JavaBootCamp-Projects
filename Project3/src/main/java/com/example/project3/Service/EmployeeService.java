package com.example.project3.Service;

import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final AuthRepository authRepository;
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void registerEmployee(EmployeeDTO employeeDTO){
        User user=new User();
        user.setRole("EMPLOYEE");
        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());
        user.setUsername(employeeDTO.getUsername());

        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        user.setPassword(hashPassword);

        Employee employee=new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());

        user.setEmployee(employee);
        employee.setUser(user);

        authRepository.save(user);


        employeeRepository.save(employee);
    }


    public void updateEmployee(Integer userId, EmployeeDTO employeeDTO){
        User user=authRepository.findUserByUserId(userId);
        Employee employee=employeeRepository.findEmployeeByEmpId(user.getUserId());

        user.setName(employeeDTO.getName());
        user.setUsername(employeeDTO.getUsername());
        user.setEmail(employeeDTO.getEmail());
        String hashPassword=new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        user.setPassword(hashPassword);

        employee.setSalary(employeeDTO.getSalary());
        employee.setPosition(employee.getPosition());

        authRepository.save(user);
        employeeRepository.save(employee);

    }

    public void deleteEmployee(Integer adminId,Integer employeeId){
        User user=authRepository.findUserByUserId(adminId);
        Employee employee=employeeRepository.findEmployeeByEmpId(employeeId);
        User user1=authRepository.findUserByUserId(employeeId);

        employeeRepository.delete(employee);

        authRepository.delete(user1);

    }
}
