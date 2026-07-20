package emms.document_management.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import emmms.document_management.repository.EmployeeRepository;
import emmms.document_management.service.EmployeeCsvService;

public class EmployeeCsvServiceTest {

    @InjectMocks
    private EmployeeCsvService employeeCsvService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCsvData() throws Exception {

        File file = File.createTempFile("employee", ".csv");

        FileWriter writer = new FileWriter(file);

        writer.write("id,name,email,salary\n");
        writer.write("1,Amaan,amaan@test.com,50000\n");
        writer.write("2,Rahul,rahul@test.com,45000\n");

        writer.close();

        employeeCsvService.saveCsvData(file);

        verify(employeeRepository, times(2)).save(org.mockito.ArgumentMatchers.any());
    }
}