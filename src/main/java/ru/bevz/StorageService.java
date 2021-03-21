package ru.bevz;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.bevz.Constant.ERROR;

public class StorageService {

	private final List<String> content = readFile();

	public List<String> getContent() {
		return content;
	}

	private List<String> readFile() {
		String filepath = "D:\\Aleksandr\\Study\\IT\\IdeaProjects\\stream-api-test-task\\src\\main\\resources\\words.txt";
		List<String> listStr = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {
			listStr = reader.lines().collect(Collectors.toList());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
							null,
							"Файл не найден!",
							ERROR,
							JOptionPane.ERROR_MESSAGE,
							null
			);
		}
		return listStr;
	}
}