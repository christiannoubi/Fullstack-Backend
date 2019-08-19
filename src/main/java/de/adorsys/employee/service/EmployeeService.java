package de.adorsys.employee.service;
import de.adorsys.employee.domain.Employee;
import de.adorsys.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

   @Autowired
    EmployeeRepository employeeRepository;

   public List<Employee> getEmployees() {
       return employeeRepository.findAll();
   }

   public Optional<Employee> getEmployee (Long id) {
       return employeeRepository.findById(id);
   }

   public Employee save (Employee employee) {
       return employeeRepository.save(employee);
   }

   public void deleteEmployee ( Long id) {
       Employee employee = employeeRepository.findById(id).orElse(new Employee());
       employeeRepository.delete(employee);
   }

   public Optional<Employee> findById(Long id) {
       return employeeRepository.findById(id);
   }

   public Employee updateEmployee (Long id, Employee employee) {
       Optional<Employee> entity = findById(id);
      if (!entity.isPresent()) {
          throw new RuntimeException("Unable update. User with id "+ id + "not found");
      }
      Employee employeeEntity = entity.get();
      employeeEntity.setFirstName(employee.getFirstName());
      employeeEntity.setLastName(employee.getLastName());
      employeeEntity.setUsername(employee.getUsername());
      employeeEntity.setPassword(employee.getPassword());
      employeeEntity.setEmail(employee.getEmail());
      employeeRepository.save(employeeEntity);
       return employeeEntity;
   }
}
