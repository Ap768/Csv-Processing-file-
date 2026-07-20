package emmms.document_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import emmms.document_management.dto.FileUploadResponse;
import emmms.document_management.service.GetAllFilesService;
import emmms.document_management.service.UploadDocumentService;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DocumentController {

    @Autowired
    private UploadDocumentService uploadDocumentService;

    @Autowired
    private GetAllFilesService getAllFilesService;

    @PostMapping("/upload")
    public ResponseEntity<List<FileUploadResponse>> uploadFiles(
            @RequestParam("files") MultipartFile[] files) {

        List<FileUploadResponse> response =
                uploadDocumentService.uploadDocuments(files);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getFileList")
    public List<FileUploadResponse> getFileList() {
        return getAllFilesService.getFileList();
    }

    @GetMapping("/test")
    public String test() {
        return "Document Controller Working";
    }
}