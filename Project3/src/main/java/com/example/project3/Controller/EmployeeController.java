package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping("/get-all")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/register-employee")
    public ResponseEntity registerEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.registerEmployee(employeeDTO);
        return ResponseEntity.ok(new ApiResponse("Employee registered"));
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.updateEmployee(user.getUserId(), employeeDTO);
        return ResponseEntity.ok(new ApiResponse("Employee information updated"));
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal User user, @PathVariable Integer employeeId){
        employeeService.deleteEmployee(user.getUserId(), employeeId);
        return ResponseEntity.ok(new ApiResponse("Employee information deleted"));
    }
}
