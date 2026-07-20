package emmms.document_management.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtility {

    public static void moveToSuccess(File file)
            throws IOException {

        File destination =
                new File("D:/success/" + file.getName());

        Files.move(
                file.toPath(),
                destination.toPath(),
                StandardCopyOption.REPLACE_EXISTING);
    }

    public static void moveToError(File file)
            throws IOException {

        File destination =
                new File("D:/error/" + file.getName());

        Files.move(
                file.toPath(),
                destination.toPath(),
                StandardCopyOption.REPLACE_EXISTING);
    }
}