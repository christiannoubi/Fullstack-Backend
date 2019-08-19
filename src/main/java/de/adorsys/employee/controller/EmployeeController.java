package de.adorsys.employee.controller;

import de.adorsys.employee.domain.Employee;
import de.adorsys.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    // List of Employee
    @GetMapping
    public ResponseEntity<?> getEmployees () {
        return new ResponseEntity<Object>(employeeService.getEmployees(),HttpStatus.OK);
    }

    // get Employee by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployee(id),HttpStatus.OK);
    }

    // delete Employee by id
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?>deleteEmployee (@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployee(id);
        return new ResponseEntity<Object>(employeeService.getEmployees(),HttpStatus.OK);
    }

    // save or add a Employee
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity <?>save(@RequestBody Employee employee) {
        employeeService.save(employee);
        return  new ResponseEntity<Object>(employeeService.getEmployees(), HttpStatus.OK);
    }
    // ------------------------update a Employee-------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee updated = employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
