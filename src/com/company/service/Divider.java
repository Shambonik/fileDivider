package com.company.service;

import com.company.entity.FileEntity;

import java.io.IOException;
import java.util.Arrays;

public class Divider {

    private static final Integer MAX_LENGTH = 26214400;
    private static final String DIRECTORY_NAME = "DIVIDED\\";

    public void divideFile(String fullAddress) throws IOException {
        FileEntity originalFile = new FileEntity(fullAddress);
        Writer.createDirectory(originalFile.getAddress() + DIRECTORY_NAME);
        Integer originalLength = originalFile.getContent().length;
        for (int i = 0; i < originalLength; i += MAX_LENGTH) {
            if (originalLength - i <= MAX_LENGTH) {
                Writer.writeFile(createFile(originalFile, i, originalLength));
            } else {
                Writer.writeFile(createFile(originalFile, i, i + MAX_LENGTH));
            }
        }
    }

    private FileEntity createFile(FileEntity originalFile, Integer startIndex, Integer endIndex) {
        FileEntity file = new FileEntity(
                originalFile.getName(),
                originalFile.getExtension(),
                originalFile.getAddress() + DIRECTORY_NAME,
                startIndex,
                Arrays.copyOfRange(originalFile.getContent(), startIndex, endIndex)
        );
        file.setContent(file.getReversedContent());
        return file;
    }
}
