package ru.bevz;


import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataService {

	private final StorageService storageService = new StorageService();

	//Определить среднюю длину строки в файле words.txt (результат округляем, пустые строки не считаем)
	public int wordsAverageLength() {
		return storageService.getContent()
						.stream()
						.filter(x -> !x.isEmpty())
						.collect(Collectors.averagingInt(String::length))
						.intValue();
	}

	//Найти 30 самых длинных слов
	public List<String> longestWords() {
		return storageService.getContent()
						.stream()
						.flatMap(x -> Stream.of(getWords(x)))
						.sorted((o1, o2) -> o2.length() - o1.length())
						.limit(30)
						.collect(Collectors.toList());
	}

	//Найти слово, которое встречается чаще всего и содержит больше 3 символов (ignore case = true)
	public String mostFrequentWord() {
		return storageService.getContent()
						.stream()
						.flatMap(x -> Stream.of(getWords(x)))
						.filter(x -> x.length() > 3)
						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
						.entrySet()
						.stream()
						.max((o1, o2) -> (int) (o1.getValue() - o2.getValue()))
						.orElse(new AbstractMap.SimpleEntry<>("", 0L))
						.getKey();
	}

	//Найти все слова в которых содержатся более 4 уникальных гласных в файле words.txt (выполнять параллельно)
	public List<String> wordsWith5UniqueVowels() {
		return storageService.getContent()
						.parallelStream()
						.flatMap(x -> Stream.of(getWords(x)))
						.collect(Collectors.toSet())
						.stream()
						.filter(x -> getVowelLetters(x).size() > 4)
						.collect(Collectors.toList());
	}

	private String[] getWords(String content) {
		return Pattern.compile("\\W", Pattern.UNICODE_CHARACTER_CLASS).split(content);
	}

	private Set<Character> getVowelLetters(String word) {
		return word != null ? word.chars()
						.mapToObj(x -> (char) x)
						.filter(x -> !x.toString().isEmpty() && "аеёиоуыэюяaeiouy".contains(x.toString().toLowerCase(Locale.ROOT)))
						.collect(Collectors.toSet()) : new HashSet<>();
	}
}