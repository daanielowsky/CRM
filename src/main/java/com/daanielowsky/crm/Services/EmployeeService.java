package com.daanielowsky.crm.Services;

import com.daanielowsky.crm.DTO.EmployeeDTO;
import com.daanielowsky.crm.Entities.Employee;
import com.daanielowsky.crm.Enums.Roles;
import com.daanielowsky.crm.Repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getSalesRepresentativeForCustomerRegistration(){
        return employeeRepository.getAllByRoles(Roles.SALESREPRESENTATIVE);
    }

    public List<Employee> getListOfEmployees() {
        return employeeRepository.getAllBy();
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeById = employeeRepository.getEmployeeById(id);
        Employee employee = employeeById.orElse(null);
        return employee;
    }
}
