package com.daanielowsky.crm.Services;

import com.daanielowsky.crm.DTO.EmployeeDTO;
import com.daanielowsky.crm.Entities.Employee;
import com.daanielowsky.crm.Repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllBy();
    }

}
