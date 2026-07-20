package emms.document_management.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import emmms.document_management.dto.FileUploadResponse;
import emmms.document_management.service.GetAllFilesService;

public class GetAllFilesServiceTest {

    private GetAllFilesService getAllFilesService;

    @BeforeEach
    void setUp() {

        getAllFilesService = new GetAllFilesService();

        new File("D:/uploads").mkdirs();
        new File("D:/success").mkdirs();
        new File("D:/error").mkdirs();
    }

    @Test
    void testGetFileList() throws Exception {

        File file = new File("D:/uploads/test.csv");

        FileWriter writer = new FileWriter(file);
        writer.write("Sample Data");
        writer.close();

        List<FileUploadResponse> files =
                getAllFilesService.getFileList();

        assertNotNull(files);

        assertFalse(files.isEmpty());

        assertEquals("test.csv",
                files.get(0).getFileName());
    }

    @Test
    void testGetFileListWhenFoldersExist() {

        List<FileUploadResponse> files =
                getAllFilesService.getFileList();

        assertNotNull(files);
    }

    @Test
    void testReturnType() {

        List<FileUploadResponse> files =
                getAllFilesService.getFileList();

        assertTrue(files instanceof List);
    }
}