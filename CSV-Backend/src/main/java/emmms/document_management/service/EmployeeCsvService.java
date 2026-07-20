package emmms.document_management.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emmms.document_management.entity.Employee;
import emmms.document_management.repository.EmployeeRepository;

@Service
public class EmployeeCsvService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveCsvData(File file) throws Exception {

        BufferedReader br =
                new BufferedReader(
                        new FileReader(file));

        String line;

        br.readLine(); 

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            Employee employee = new Employee();

            employee.setId(
                    Integer.parseInt(data[0].trim()));

            employee.setName(
                    data[1].trim());

            employee.setEmail(
                    data[2].trim());

            employee.setSalary(
                    Double.parseDouble(data[3].trim()));

            employeeRepository.save(employee);
        }

        br.close();
    }
}