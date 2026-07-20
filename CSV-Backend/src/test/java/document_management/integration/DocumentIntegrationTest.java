package document_management.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import emmms.document_management.service.EmployeeCsvService;
import emmms.document_management.service.UploadDocumentService;

@SpringBootTest
public class DocumentIntegrationTest {

    @Autowired
    private EmployeeCsvService employeeCsvService;

    @Autowired
    private UploadDocumentService uploadDocumentService;

    @Test
    public void testCompleteCsvProcessingFlow() throws Exception {

        // Create temporary CSV file
        File file = File.createTempFile("employee", ".csv");

        FileWriter writer = new FileWriter(file);

        writer.write("id,name,email,salary\n");
        writer.write("1,Amaan,amaan@gmail.com,50000\n");
        writer.write("2,Rahul,rahul@gmail.com,45000\n");

        writer.close();

        assertTrue(file.exists());

        employeeCsvService.saveCsvData(file);

        assertNotNull(file);
    }
}