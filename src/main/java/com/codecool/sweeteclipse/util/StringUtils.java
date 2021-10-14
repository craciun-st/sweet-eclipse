package com.codecool.sweeteclipse.util;

import javax.validation.constraints.NotBlank;
import java.util.Random;
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

    public static String generateRandomAlphaNumericString(byte length, long seed) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random(seed);

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))  // filter non-alphanumeric ASCII codes
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
