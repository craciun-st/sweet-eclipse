package com.codecool.sweeteclipse.util;

import javax.validation.constraints.NotBlank;
import java.util.UUID;


public class StringUtils {

    public static final String DEFAULT_FOLDER_NAME = "000_EMPTY";

    public static String getFileKey(String toFolder, String toFilename, String defaultFolder) {
        String properFolder = sanitizedFolder(
                toFolder,
                (defaultFolder != null) && (!defaultFolder.equals("")) ? defaultFolder : DEFAULT_FOLDER_NAME
        );
        String properFilename = sanitizedFilename(toFilename);
        return String.format("%s/%s", properFolder, properFilename);
    }

    public static String sanitizedFolder(String toFolder, @NotBlank String defaultFolder) {
        String sanitized = (toFolder == null || toFolder.equals("")) ? defaultFolder : toFolder;
        sanitized = sanitized.substring(0, Math.min(sanitized.length(), 256));
        sanitized = sanitized.replaceAll("[^a-zA-Z0-9-_\\.]", "_");

        return sanitized;
    }

    public static String sanitizedFilename(String filename) {
        String sanitized = (filename == null || filename.equals("")) ? UUID.randomUUID().toString() : filename;
        sanitized = sanitized.substring(0, Math.min(sanitized.length(), 256));
        sanitized = sanitized.replaceAll("[^a-zA-Z0-9-_\\.]", "_");

        return sanitized;
    }
}
