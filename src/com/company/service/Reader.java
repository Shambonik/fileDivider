package com.company.service;

import com.company.entity.FileEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {

	public static byte[] readFileContent(String fullAddress) throws IOException {
		return Files.readAllBytes(Paths.get(fullAddress));
	}

	public static List<FileEntity> getAllFilesInDirectory(String address) throws IOException{
		return Files.walk(Path.of(address))
				.map(path -> {
					System.out.println("Читаю: " + path);
					try {
						return new FileEntity(path.toString());
					} catch (IOException | StringIndexOutOfBoundsException e) {
						return null;
					}
				})
				.filter(fileEntity -> fileEntity != null)
				.collect(Collectors.toList());
	}

}
