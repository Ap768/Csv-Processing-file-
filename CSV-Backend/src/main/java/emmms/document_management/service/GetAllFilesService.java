package emmms.document_management.service;

import emmms.document_management.dto.FileUploadResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GetAllFilesService {

	@Value("${upload.path}")
	private String UPLOAD_FOLDER;

	@Value("${success.path}")
	private String SUCCESS_FOLDER;

	@Value("${error.path}")
	private String ERROR_FOLDER;
    public List<FileUploadResponse> getFileList() {

        List<FileUploadResponse> fileList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        addFilesFromFolder(UPLOAD_FOLDER, fileList, formatter, "Uploaded");
        addFilesFromFolder(SUCCESS_FOLDER, fileList, formatter, "Success");
        addFilesFromFolder(ERROR_FOLDER, fileList, formatter, "Error");

        fileList.sort((a, b) -> b.getDateTime().compareTo(a.getDateTime()));

        return fileList;
    }

    private void addFilesFromFolder(
            String folderPath,
            List<FileUploadResponse> list,
            SimpleDateFormat formatter,
            String status) {

        File folder = new File(folderPath);
        if (!folder.exists()) return;

        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                String dateTime = formatter.format(new Date(file.lastModified()));
                list.add(new FileUploadResponse(file.getName(), dateTime, status));
            }
        }
    }
}