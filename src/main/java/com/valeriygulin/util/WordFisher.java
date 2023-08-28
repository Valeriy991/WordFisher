package com.valeriygulin.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class WordFisher {
    public Map<String, Long> vocabulary = new HashMap<>();
    private List<String> stopwords = new ArrayList<>();
    private String inputTextFile;
    private String stopwordsFile;

    public WordFisher(String inputTextFile, String stopwordsFile) throws IOException {
        this.inputTextFile = inputTextFile;
        this.stopwordsFile = stopwordsFile;
        this.getStopwords(stopwordsFile);
        this.buildVocabulary(inputTextFile);
    }

    /**
     * This method populates the stopwords list from a file containing all
     * stopwords, as pointed to by the member variable stopwordsFile.
     * This file contains one stopword per line.
     * You should be familiar with how to read from a file from the previous labs.
     */
    private void getStopwords(String fileName) throws IOException {
        String reader = new String(Files.readAllBytes(Paths.get(fileName)));
        String[] split = reader.split("\r\n");
        this.stopwords.addAll(Arrays.asList(split));
    }

    private void buildVocabulary(String fileName) throws IOException {
        String reader = new String(Files.readAllBytes(Paths.get(fileName))).toLowerCase()
                .replaceAll("[^a-zA-Z0-9 ]", "");
        String[] s = reader.split(" ");
        this.vocabulary = Arrays.stream(s).filter(x -> !x.isEmpty())
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
    }

    /**
     * Посчитать количество слов в тексте
     */
    public int getWordCount() {
        // TODO: Return the total number of words in inputTextFile.
        // This can be calculated using vocabulary.
        return this.vocabulary.values().stream().mapToInt(Long::intValue).sum();
    }

    /**
     * Получить количество уникальных слов
     */
    public int getNumUniqueWords() {
        // TODO: Return the number of unique words.
        // This should be the same as the number of keys in vocabulary.
        return this.vocabulary.values().stream().filter(x -> x == 1).mapToInt(Long::intValue).sum();
    }

    /**
     * Посчитать сколько раз в тексте встречается заданное слово
     */
    public long getFrequency(String word) {
        // TODO: Return the number of times word occurs.
        // (Should be one simple line of code.)
        // Think about what vocabulary stores.
        return this.vocabulary.getOrDefault(word, -1L);
    }

    /**
     * Удалить из словаря слова встречающиеся в списке СтопСлов.
     */
    public void pruneVocabulary() {
        Map<String, Long> res = new HashMap<>();
        for (Map.Entry<String, Long> pair : this.vocabulary.entrySet()) {
            for (int i = 0; i < this.stopwords.size(); i++) {
                if (pair.getKey().equals(this.stopwords.get(i))) {
                    res.put(pair.getKey(), pair.getValue());
                }
            }
        }
        this.vocabulary = res;
    }

    /**
     * Получить количество n топ слов.
     */
    public List<String> getTopWords(int n) {
        List<String> res = new ArrayList<>();
        List<Map.Entry<String, Long>> entries = this.vocabulary.entrySet().stream()
                .sorted(Comparator.comparingInt(x -> -x.getValue().intValue())).limit(n).toList();
        for (Map.Entry<String, Long> entry : entries) {
            res.add(entry.getKey());
        }
        return res;
    }

    /**
     * Получить общие топовые слова из текста.
     */
    public List<String> commonPopularWords(int n, WordFisher other) {
        int count = 0;
        List<String> res = new ArrayList<>();
        List<String> topWords = other.getTopWords(n);
        List<String> topWords1 = this.getTopWords(n);
        for (String top1 : topWords) {
            for (String top2 : topWords1) {
                if (top1.equals(top2)) {
                    res.add(top1);
                    count++;
                }
            }
        }
        if (count == 0) {
            return null;
        }
        return res;
    }

    @Override
    public String toString() {
        return "WordFisher{" +
                "vocabulary=" + vocabulary +
                ", stopwords=" + stopwords +
                ", inputTextFile='" + inputTextFile + '\'' +
                ", stopwordsFile='" + stopwordsFile + '\'' +
                '}';
    }
}
