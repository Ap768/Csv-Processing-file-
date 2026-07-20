package document_management.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import emmms.document_management.service.EmployeeCsvService;

@SpringBootTest
public class FileProcessingIntegrationTest {

    @Autowired
    private EmployeeCsvService employeeCsvService;

    @Test
    public void testEmployeeCsvService() throws Exception {

        File file = File.createTempFile("employee", ".csv");

        FileWriter writer = new FileWriter(file);

        writer.write("id,name,email,salary\n");
        writer.write("10,John,john@test.com,70000\n");

        writer.close();

        employeeCsvService.saveCsvData(file);

        assertTrue(file.exists());
    }
}