package com.daanielowsky.crm.Services;

import com.daanielowsky.crm.DTO.EmployeeDTO;
import com.daanielowsky.crm.Entities.Employee;
import com.daanielowsky.crm.Enums.Roles;
import com.daanielowsky.crm.Repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

    public void editEmployee(Employee employee) {
        Employee employeeById = getEmployeeById(employee.getId());
        log.info("Started to edit employee with ID: " + employeeById.getId());
        employeeById.setName(employee.getName());
        employeeById.setSurname(employee.getSurname());
        employeeById.setEmail(employee.getEmail());
        employeeById.setSalary(employee.getSalary());
        employeeById.setRoles(employee.getRoles());
        employeeRepository.save(employeeById);
        log.info("Success of editing employee.");
    }
}
