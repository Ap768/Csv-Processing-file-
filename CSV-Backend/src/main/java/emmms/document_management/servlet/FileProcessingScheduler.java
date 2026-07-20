package emmms.document_management.servlet;

import emmms.document_management.service.EmployeeCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.util.Date;

@Component
public class FileProcessingScheduler {

    @Autowired
    private EmployeeCsvService employeeCsvService;

    @Value("${upload.path}")
    private String UPLOAD_FOLDER;

    @Value("${success.path}")
    private String SUCCESS_FOLDER;

    @Value("${error.path}")
    private String ERROR_FOLDER;

    @Scheduled(fixedRate = 30000)
    public void processFiles() {

        new File(UPLOAD_FOLDER).mkdirs();
        new File(SUCCESS_FOLDER).mkdirs();
        new File(ERROR_FOLDER).mkdirs();

        System.out.println("Checking Upload Folder : " + new Date());

        File uploadFolder = new File(UPLOAD_FOLDER);
        File[] files = uploadFolder.listFiles(File::isFile);

        if (files == null || files.length == 0) {
            System.out.println("No Files Found");
            return;
        }

        for (File file : files) {

            try {

                System.out.println("Processing File : " + file.getName());

                if (!file.getName().toLowerCase().endsWith(".csv")) {

                    System.out.println("Not a CSV file");

                    moveFile(file, ERROR_FOLDER);

                    continue;
                }

                boolean valid = validateCsv(file);

                if (valid) {

                    employeeCsvService.saveCsvData(file);

                    moveFile(file, SUCCESS_FOLDER);

                    System.out.println(file.getName() + " -> SUCCESS");

                } else {

                    moveFile(file, ERROR_FOLDER);

                    System.out.println(file.getName() + " -> ERROR");
                }

            } catch (Exception e) {

                e.printStackTrace();

                try {

                    moveFile(file, ERROR_FOLDER);

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        }
    }

    private boolean validateCsv(File file) {

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(file))) {

            String header = br.readLine();

            if (header == null ||
                    !header.trim()
                            .toLowerCase()
                            .equals("id,name,email,salary")) {

                System.out.println(
                        "Invalid Header : " + header);

                return false;
            }

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length != 4) {

                    System.out.println("Invalid Column Count");

                    return false;
                }

                for (String value : data) {

                    if (value == null ||
                            value.trim().isEmpty()) {

                        System.out.println("Empty Value Found");

                        return false;
                    }
                }

                try {
                    Integer.parseInt(data[0].trim());
                } catch (Exception e) {

                    System.out.println(
                            "Invalid ID : " + data[0]);

                    return false;
                }

                try {
                    Double.parseDouble(data[3].trim());
                } catch (Exception e) {

                    System.out.println(
                            "Invalid Salary : " + data[3]);

                    return false;
                }
            }

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    private void moveFile(
            File source,
            String destinationFolder)
            throws IOException {

        File destination =
                new File(
                        destinationFolder
                                + "/"
                                + source.getName());

        System.out.println(
                "Source File      : "
                        + source.getAbsolutePath());

        System.out.println(
                "Destination File : "
                        + destination.getAbsolutePath());

        Files.move(
                source.toPath(),
                destination.toPath(),
                StandardCopyOption.REPLACE_EXISTING);

        System.out.println(
                "File Moved Successfully");
    }
}