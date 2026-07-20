package emms.document_management.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import emmms.document_management.dto.FileUploadResponse;
import emmms.document_management.service.UploadDocumentService;

public class UploadDocumentServiceTest {

    private UploadDocumentService uploadDocumentService;

    @BeforeEach
    public void setUp() {
        uploadDocumentService = new UploadDocumentService();
    }

    @Test
    public void testUploadValidCsvFile() {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "employee.csv",
                "text/csv",
                "id,name,email,salary\n1,Amaan,amaan@test.com,50000".getBytes());

        List<FileUploadResponse> response =
                uploadDocumentService.uploadDocuments(
                        new MockMultipartFile[]{file});

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("employee.csv",
                response.get(0).getFileName());
    }

    @Test
    public void testUploadInvalidFileType() {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "employee.txt",
                "text/plain",
                "Invalid Data".getBytes());

        List<FileUploadResponse> response =
                uploadDocumentService.uploadDocuments(
                        new MockMultipartFile[]{file});

        assertNotNull(response);
        assertEquals(1, response.size());

        assertTrue(
                response.get(0)
                        .getFileName()
                        .contains("Only CSV files are allowed"));
    }

    @Test
    public void testUploadMultipleCsvFiles() {

        MockMultipartFile file1 = new MockMultipartFile(
                "file",
                "employee1.csv",
                "text/csv",
                "id,name,email,salary\n1,Amaan,a@test.com,1000".getBytes());

        MockMultipartFile file2 = new MockMultipartFile(
                "file",
                "employee2.csv",
                "text/csv",
                "id,name,email,salary\n2,Rahul,r@test.com,2000".getBytes());

        List<FileUploadResponse> response =
                uploadDocumentService.uploadDocuments(
                        new MockMultipartFile[]{file1, file2});

        assertEquals(2, response.size());
    }
}