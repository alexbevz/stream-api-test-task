package ru.bevz;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ru.bevz.Constant.ERROR;
import static ru.bevz.Constant.FILEPATH;

public class StorageService {

	private final List<String> content = readFile();

	public List<String> getContent() {
		return content;
	}

	private List<String> readFile() {
		List<String> listStr = new ArrayList<>();
		try {
			listStr = Files.readAllLines(Paths.get(FILEPATH));
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