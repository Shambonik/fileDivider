package com.company;

import com.company.service.Divider;
import com.company.service.Merger;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("Введите 1, чтобы разделить\n" +
				"Введите 2, чтобы соединить\n" +
				"Введите что-либо другое, чтоб завершить");
		String input = in.next();
		if(input.equals("1")) {
			Divider divider = new Divider();
			System.out.println("Введите полный путь к файлу");
			divider.divideFile(in.next());
		} else if(input.equals("2")){
			System.out.println("Введите полный путь к директории с разделенными файлами");
			Merger.mergeFiles(in.next());
		}
	}
}
