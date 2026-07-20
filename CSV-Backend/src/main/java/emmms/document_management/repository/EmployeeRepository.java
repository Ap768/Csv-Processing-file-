package emmms.document_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import emmms.document_management.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee,Integer> {
}