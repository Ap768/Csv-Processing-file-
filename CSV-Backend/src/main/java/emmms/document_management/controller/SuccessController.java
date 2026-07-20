package emmms.document_management.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SuccessController {

    @GetMapping("/api/successFile")
    public List<String> getSuccessFiles() {

        List<String> files = new ArrayList<>();

        File folder = new File("D:/success");
        if (folder.exists() && folder.isDirectory()) {

            File[] fileList = folder.listFiles();

            if (fileList != null) {

                for (File file : fileList) {

                    if (file.isFile()) {
                        files.add(file.getName());
                    }
                }
            }
        }

        return files;
    }
}