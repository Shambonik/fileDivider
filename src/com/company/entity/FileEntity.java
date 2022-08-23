package com.company.entity;

import com.company.service.Reader;

import java.io.IOException;
import java.nio.ByteBuffer;

public class FileEntity implements Comparable {

	private String name;
	private Integer number;
	private String extension;
	private String address;
	private byte[] content;

	public FileEntity(String name, String extension, String address, Integer number, byte[] content) {
		this.name = name;
		this.extension = extension;
		this.address = address;
		this.number = number;
		this.content = content;
	}

	public FileEntity(String name, String extension, String address) {
		this.name = name;
		this.extension = extension;
		this.address = address;
	}

	public FileEntity(String fullAddress) throws IOException {
		setNameAndExtensionAndAddressAndNumber(fullAddress);
		setContentFromFile(fullAddress);
	}

	private void setNameAndExtensionAndAddressAndNumber(String fullAddress) {
		Integer indexOfNameStart = fullAddress.lastIndexOf('\\') + 1;
		Integer indexOfNumberStart = fullAddress.lastIndexOf('_') + 1;
		Integer indexOfExtensionStart = fullAddress.lastIndexOf('.');
		String potentialNumber = fullAddress.substring(indexOfNumberStart, indexOfExtensionStart);
		if (potentialNumber.matches("^\\d*$")) {
			this.number = Integer.valueOf(potentialNumber);
			this.name = fullAddress.substring(indexOfNameStart, indexOfNumberStart - 1);
		} else {
			this.name = fullAddress.substring(indexOfNameStart, indexOfExtensionStart);
		}
		this.extension = fullAddress.substring(indexOfExtensionStart);
		this.address = fullAddress.substring(0, indexOfNameStart);
	}

	private void setContentFromFile(String fullAddress) throws IOException {
		this.content = Reader.readFileContent(fullAddress);
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public byte[] getContent() {
		return content;
	}

	public byte[] getReversedContent(){
		ByteBuffer buff = ByteBuffer.wrap(new byte[content.length]);
		for(int i = content.length - 1; i >= 0; i--){
			buff.put(content[i]);
		}
		return buff.array();
	}

	public String getName() {
		return name;
	}

	public String getExtension() {
		return extension;
	}

	public String getAddress() {
		return address;
	}

	public Integer getNumber() {
		return number;
	}

	public String getFullAddress() {
		if (number != null) {
			return address + name + "_" + number + extension;
		} else {
			return address + name + extension;
		}
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof FileEntity) {
			FileEntity comparingFileEntity = (FileEntity) o;
			if (comparingFileEntity != null) {
				return this.number - comparingFileEntity.getNumber();
			}
		}
		return 0;
	}
}
