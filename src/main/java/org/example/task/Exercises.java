package org.example.task;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Exercises {

    public static int NumOfBrackets(String s,Integer n){
        s = s.replaceAll("[^()]",""); // Replace all symbols except brackets
        Queue<Character> brackets = new LinkedList<Character>();
        int countConnectBrackets = 0; // count right closed brackets
        int result = 0;
        for(Character c : s.toCharArray()){  // iterate through all characters in a string
            if(c == '('){
                brackets.add(c); // add ( to queue
            } else {
                if(!brackets.isEmpty()){ // if the queue is not empty, I add the right parenthesis count
                    countConnectBrackets++;
                    brackets.poll();
                } else {
                    countConnectBrackets = 0; // there were no closing parentheses before the closing parenthesis
                }
            }
            if(countConnectBrackets == n){ // If the number of correctly closed parentheses is equal to the search, I add the result
                result++;
                countConnectBrackets = 0;
            }
        }
        return result;
    }

    public static void searchShortestWay(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){ // open file with data
            System.out.println("Файл відкрито для читання.");
            int countTest = Integer.parseInt(bufferedReader.readLine()); // receive a number of tests
            for(int i = 0; i < countTest; i++){
                if(i!= 0){
                    System.out.println(bufferedReader.readLine()); // read empty line for second and following test
                }
                int countCity = Integer.parseInt(bufferedReader.readLine()); // read countCity
                int[][] adjacencyMatrix = new int[countCity][countCity]; // create adjacencyMatrix the row in this matrix represents
                // the city, and the column represents the cost to the city with the column index
                List<String> nameAllCity = new ArrayList<>(); // save  all city
                for(int j = 0; j < countCity; j++){
                    String city = bufferedReader.readLine();
                    nameAllCity.add(city);
                    int countNeighbors = Integer.parseInt(bufferedReader.readLine());
                    for(int k = 0; k < countNeighbors; k++){
                        String[] neighborAndCost = bufferedReader.readLine().split(" "); // get the number of the neighbor and the cost
                        adjacencyMatrix[j][Integer.parseInt(neighborAndCost[0])-1] = Integer.parseInt(neighborAndCost[1]); // save in matrix
                    }
                }
                int numbersPathToFind = Integer.parseInt(bufferedReader.readLine());
                for(int j = 0; j < numbersPathToFind; j++){
                    String[] startAndSearchCity = bufferedReader.readLine().split(" ");
                    int indexStart = nameAllCity.indexOf(startAndSearchCity[0]);
                    int indexEnd = nameAllCity.indexOf(startAndSearchCity[1]);
                    System.out.println("З "+ startAndSearchCity[0] +" до " + startAndSearchCity[1] + " найкоротший шлях коштує " +
                            recursiveSearchShortestWay(adjacencyMatrix,countCity,indexStart,indexEnd,0,"")); // go to recursive method for find the shortest path
                }
            }
        } catch (IOException e) {
            System.out.println("Файл не знайдено: " + e.getMessage());
        }
    }

    public static int recursiveSearchShortestWay(int[][] adjacencyMatrix,int countCity,int currentCity,int indexEnd, int cost,String visitedCity) {
        visitedCity += currentCity; // save information about visited city
        if(currentCity == indexEnd) {
          return cost; // return from recursion if it is at the destination point
        }
        int smallestCost = -1; // field for writing the shortest path
        for(int i = 0; i < countCity; i++){
            if(adjacencyMatrix[currentCity][i] != 0 && !visitedCity.contains(String.valueOf(i))) { // going to cities I haven't visited yet
                int resultCost = recursiveSearchShortestWay(adjacencyMatrix, countCity, i, indexEnd, cost+adjacencyMatrix[currentCity][i],visitedCity);
                if(smallestCost == -1 || resultCost < smallestCost){
                    smallestCost = resultCost; //  if the new one costs less than the old one
                }
            }
        }
        return smallestCost;
    }


    public static int sumOFDigitsFactorial(int number){
        BigInteger bigInteger = findFactorial(BigInteger.valueOf(100)); // find the factorial of a number
        String numberString = bigInteger.toString(); // convert BigInteger into a string
        int result = 0;
        for (int i = 0; i < numberString.length(); i++) {
            result += Character.getNumericValue(numberString.charAt(i)); // convert the symbol into a number and add to the sum
        }
        return result;
    }

    public static BigInteger findFactorial(BigInteger factorial){
        if(factorial.longValue() == 0){ // at 0, we end the recursion and return 0! = 1
            return BigInteger.ONE;
        }
        return factorial.multiply(findFactorial(BigInteger.valueOf(factorial.longValue()-1))); // recursion 100,99,98...
    }
}
