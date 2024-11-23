package org.example;

import org.example.task.Exercises;

public class Main {
    public static void main(String[] args) {
        System.out.println("Count brackets - " + Exercises.NumOfBrackets("())(()())",2));
        Exercises.searchShortestWay("src/main/resources/fileInputData.txt");
        System.out.println(Exercises.sumOFDigitsFactorial(100));
    }
}