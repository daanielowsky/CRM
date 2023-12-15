package com.daanielowsky.crm.Repositories;

import com.daanielowsky.crm.Entities.Employee;
import com.daanielowsky.crm.Enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    List<Employee> getAllByRoles(Roles roles);
    Optional<Employee> getEmployeeById(Long id);

    List<Employee> getAllBy();
}
