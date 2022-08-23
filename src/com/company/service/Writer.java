package com.company.service;

import com.company.entity.FileEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class Writer {

	public static String createDirectory(String address) throws IOException {
		File directory = new File(address);
		if (directory.exists()) {
			Files.walk(directory.toPath())
					.sorted(Comparator.reverseOrder())
					.map(Path::toFile)
					.forEach(File::delete);
		}
		directory.mkdir();
		return address;
	}

	public static void writeFile(FileEntity fileEntity) throws IOException {
		System.out.println("Пишу: " + fileEntity.getFullAddress());
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileEntity.getFullAddress()));
		bos.write(fileEntity.getContent());
		bos.flush();
		bos.close();
	}
}
