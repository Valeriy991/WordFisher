package com.valeriygulin.program;

import com.valeriygulin.util.WordFisher;

import java.io.IOException;
import java.util.List;

public class WordFisherTester {
    public static void main(String[] args) {

        try {
            WordFisher alice = new WordFisher("carroll-alice.txt", "stopwords.txt");

            WordFisher moby = new WordFisher("moby-dick.txt", "stopwords.txt");

            /**
             * Посчитать количество слов в тексте
             */
            System.out.println("Посчитать количество слов в тексте");
            int wordCount = alice.getWordCount();
            System.out.println(wordCount);

            int wordCount2 = moby.getWordCount();
            System.out.println(wordCount2);

            /**
             * Получить количество уникальных слов
             */
            System.out.println("Получить количество уникальных слов");
            int numUniqueWords = alice.getNumUniqueWords();
            System.out.println(numUniqueWords);

            int numUniqueWords1 = moby.getNumUniqueWords();
            System.out.println(numUniqueWords1);

            /**
             * Посчитать сколько раз в тексте встречается заданное слово
             */
            System.out.println("Посчитать сколько раз в тексте встречается заданное слово");
            long whale = alice.getFrequency("whale");
            System.out.println(whale);

            long whale1 = moby.getFrequency("whale");
            System.out.println(whale1);

            /**
             * Удалить из словаря слова встречающиеся в списке СтопСлов.
             */
            System.out.println("Удалить из словаря слова встречающиеся в списке СтопСлов.");
            alice.pruneVocabulary();
            int wordCount1 = alice.getWordCount();
            System.out.println(wordCount1);
            moby.pruneVocabulary();
            int wordCount3 = moby.getWordCount();
            System.out.println(wordCount3);

            /**
             * Получить количество n топ слов.
             */
            System.out.println("Получить количество n топ слов.");
            List<String> topWords = alice.getTopWords(10);
            System.out.println(topWords);

            List<String> topWords1 = moby.getTopWords(10);
            System.out.println(topWords1);

            /**
             * Получить общие топовые слова из текста.
             */
            System.out.println("Получить общие топовые слова из текста.");
            List<String> list = alice.commonPopularWords(10, moby);
            System.out.println(list);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
