package emms.document_management.servlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import emmms.document_management.servlet.FileProcessingScheduler;

public class FileProcessingSchedulerTest {

    private FileProcessingScheduler scheduler;

    @BeforeEach
    void setUp() {
        scheduler = new FileProcessingScheduler();
    }

    private boolean validate(File file) throws Exception {

        Method method =
                FileProcessingScheduler.class
                        .getDeclaredMethod("validateCsv", File.class);

        method.setAccessible(true);

        return (boolean) method.invoke(scheduler, file);
    }

    @Test
    void testValidCsv() throws Exception {

        File file = File.createTempFile("employee", ".csv");

        FileWriter writer = new FileWriter(file);

        writer.write("id,name,email,salary\n");
        writer.write("1,Amaan,amaan@test.com,50000\n");

        writer.close();

        assertTrue(validate(file));
    }

    @Test
    void testInvalidHeader() throws Exception {

        File file = File.createTempFile("employee", ".csv");

        FileWriter writer = new FileWriter(file);

        writer.write("name,email,salary\n");
        writer.write("Amaan,amaan@test.com,50000\n");

        writer.close();

        assertFalse(validate(file));
    }

    @Test
    void testInvalidColumnCount() throws Exception {

        File file = File.createTempFile("employee", ".csv");

        FileWriter writer = new FileWriter(file);

        writer.write("id,name,email,salary\n");
        writer.write("1,Amaan\n");

        writer.close();

        assertFalse(validate(file));
    }

    @Test
    void testInvalidEmployeeId() throws Exception {

        File file = File.createTempFile("employee", ".csv");

        FileWriter writer = new FileWriter(file);

        writer.write("id,name,email,salary\n");
        writer.write("ABC,Amaan,amaan@test.com,50000\n");

        writer.close();

        assertFalse(validate(file));
    }

    @Test
    void testInvalidSalary() throws Exception {

        File file = File.createTempFile("employee", ".csv");

        FileWriter writer = new FileWriter(file);

        writer.write("id,name,email,salary\n");
        writer.write("1,Amaan,amaan@test.com,ABC\n");

        writer.close();

        assertFalse(validate(file));
    }

    @Test
    void testEmptyValue() throws Exception {

        File file = File.createTempFile("employee", ".csv");

        FileWriter writer = new FileWriter(file);

        writer.write("id,name,email,salary\n");
        writer.write("1,,amaan@test.com,50000\n");

        writer.close();

        assertFalse(validate(file));
    }
}