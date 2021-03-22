package ru.bevz;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StorageService {

	private final List<String> content = readFile("words.txt");

	public List<String> getContent() {
		return content;
	}

	private List<String> readFile(String fileName) {
		List<String> listStr = new ArrayList<>();
		String path = "./src/main/resources/";
		try {
			listStr = Files.readAllLines(Paths.get(path + fileName));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
							null,
							"Файл не найден!",
							"Ошибка",
							JOptionPane.ERROR_MESSAGE,
							null
			);
		}
		return listStr;
	}
}