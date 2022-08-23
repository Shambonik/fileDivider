package com.company.service;

import com.company.entity.FileEntity;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class Merger {

	private static final String DIRECTORY_NAME = "\\MERGED\\";

	public static void mergeFiles(String address) throws IOException {
		String targetAddress = Writer.createDirectory(address + DIRECTORY_NAME);
		List<FileEntity> fileEntityList = Reader.getAllFilesInDirectory(address);
		fileEntityList.sort(FileEntity::compareTo);
		FileEntity firstFile = fileEntityList.get(0);
		String targetName = firstFile.getName();
		FileEntity targetFile = new FileEntity(targetName, firstFile.getExtension(), targetAddress);
		Integer targetContentLength = 0;
		for(FileEntity fileEntity : fileEntityList){
			targetContentLength += fileEntity.getContent().length;
		}
		ByteBuffer buff = ByteBuffer.wrap(new byte[targetContentLength]);
		for(FileEntity fileEntity : fileEntityList){
			buff.put(fileEntity.getReversedContent());
		}
		targetFile.setContent(buff.array());
		Writer.writeFile(targetFile);
	}
}
