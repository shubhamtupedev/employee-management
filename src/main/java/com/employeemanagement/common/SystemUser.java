package com.employeemanagement.common;

import com.employeemanagement.entity.*;
import com.employeemanagement.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Component
public class SystemUser {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersRoleRepository usersRoleRepository;
    private final RoleRepository roleRepository;

    public SystemUser(EmployeeRepository employeeRepository, AddressRepository addressRepository, DepartmentRepository departmentRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UsersRoleRepository usersRoleRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usersRoleRepository = usersRoleRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    @Transactional
    public void createSystemUserOnStartup() throws IOException {
        Employee employee = new Employee();
        employee.setFirstName("Shubham");
        employee.setLastName("Tupe");
        employee.setEmail("Shubham.tupe@accelya.com");
        employee.setPersonalEmail("shubhamtupe1997@gmail.com");
        employee.setPhoneNumber("8879070411");
        employee.setGender("Male");
        employee.setEmployeeCode("EMP_01");
        employee.setDateOfJoining(LocalDate.now());
        employee.setEmploymentType("P");
        employee.setDesignation("CEO");
        employee.setSalaryGrade("A");
        employee.setStatus("A");
        employee.setDateOfBirth(LocalDate.now());

        Address address = new Address();
        address.setAddressLine1("A-4 Room No.6");
        address.setAddressLine2("Deonar Municipal Colony");
        address.setStreet("Govandi");
        address.setPostalCode("400043");
        address.setCity("Mumbai");
        address.setState("Maharashtra");
        address.setCountry("INDIA");
        address.setEmployee(employee);

        Department department = new Department();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        department.setEmployee(employees);
        department.setDepartmentCode("EXEC_MGMT");
        department.setDepartmentName("Executive Management");
        department.setDepartmentDescription("Handles overall company strategy, decision-making, and leadership.");

        Optional<Employee> employeeOp = employeeRepository.findByEmail(employee.getEmail());

        if (employeeOp.isEmpty()) {
            employeeRepository.save(employee);
            addressRepository.save(address);
            departmentRepository.save(department);
        }
        Users user = new Users();
        user.setUsername("admin@gmail.com".toUpperCase());
        user.setInvalidAttemptCount(0);
        user.setUserAccountEnabled(true);
        user.setUserEmailVerified(false);
        String password = "admin@123";
        user.setPassword(bCryptPasswordEncoder.encode(password));

        UsersRoles usersRoles = new UsersRoles();
        Set<UsersRoles> usersRolesSet = new HashSet<>();
        usersRoles.setUsers(user);
        usersRolesSet.add(usersRoles);

        Role role = new Role();
        role.setRoleName("ROLE_CEO");
        usersRoles.setRole(role);
        role.setUserRoles(usersRolesSet);

        Optional<Users> users = userRepository.findByUsername(user.getUsername().toUpperCase());
        if (users.isEmpty()) {
            userRepository.save(user);
            roleRepository.save(role);
            usersRoleRepository.save(usersRoles);
        }
    }
}
