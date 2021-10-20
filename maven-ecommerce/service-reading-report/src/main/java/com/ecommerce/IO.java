package com.ecommerce;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class IO {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void copy(Path source, File target) throws IOException {
        target.getParentFile().mkdirs();
        Files.copy(source, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void append(File target, String content) throws IOException {
        Files.write(target.toPath(), content.getBytes(), StandardOpenOption.APPEND);
    }
}
