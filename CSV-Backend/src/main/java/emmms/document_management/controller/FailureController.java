package emmms.document_management.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class FailureController {

    @GetMapping("/api/failureFile")
    public List<String> getFailureFiles() {

        List<String> files = new ArrayList<>();

        File folder = new File("D:/error");

        if (folder.exists() && folder.isDirectory()) {

            File[] fileList = folder.listFiles();

            if (fileList != null) {

                Arrays.sort(fileList,
                        (f1, f2) ->
                                Long.compare(
                                        f2.lastModified(),
                                        f1.lastModified()));

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