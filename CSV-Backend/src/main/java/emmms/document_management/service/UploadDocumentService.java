package emmms.document_management.service;

import emmms.document_management.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UploadDocumentService {

    @Value("${upload.path}")
    private String UPLOAD_FOLDER;

    public List<FileUploadResponse> uploadDocuments(MultipartFile[] files) {

        List<FileUploadResponse> responseList = new ArrayList<>();

        File folder = new File(UPLOAD_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        for (MultipartFile file : files) {

            try {

                String fileName = file.getOriginalFilename();

                if (fileName == null || !fileName.toLowerCase().endsWith(".csv")) {
                    throw new RuntimeException("Only CSV files are allowed");
                }

                File destinationFile =
                        new File(UPLOAD_FOLDER + File.separator + fileName);

                try (InputStream inputStream = file.getInputStream()) {

                    Files.copy(
                            inputStream,
                            destinationFile.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                }

                System.out.println(
                        "File saved at : " + destinationFile.getAbsolutePath());

                String dateTime =
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .format(new Date(destinationFile.lastModified()));

                responseList.add(
                        new FileUploadResponse(
                                fileName,
                                dateTime));

            } catch (Exception e) {

                e.printStackTrace();

                responseList.add(
                        new FileUploadResponse(
                                e.getMessage(),
                                null));
            }
        }

        return responseList;
    }
}