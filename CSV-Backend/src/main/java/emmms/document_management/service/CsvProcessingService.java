package emmms.document_management.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emmms.document_management.entity.Employee;
import emmms.document_management.repository.EmployeeRepository;

@Service
public class CsvProcessingService {

    @Autowired
    private EmployeeRepository repository;

    public boolean saveCsvData(File file) {

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            br.readLine(); 

            while((line = br.readLine()) != null) {

                String[] data = line.split(",");

                Employee employee = new Employee();

                employee.setId(
                        Integer.parseInt(data[0]));

                employee.setName(data[1]);

                employee.setEmail(data[2]);

                employee.setSalary(
                        Double.parseDouble(data[3]));

                repository.save(employee);
            }

            br.close();

            return true;

        } catch(Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}