package com.oneluffy.zt;

public class InterfaceSuccessRateAnalyzer {

    public static String findLongestSuccessRatePeriod(int minAverageLost, int[] rates) {
        StringBuilder result = new StringBuilder();
        int maxLength = 0;
        int currentLength = 0;
        int currentStart = 0;

        for (int i = 0; i < rates.length; i++) {
            if (rates[i] <= minAverageLost) {
                if (currentLength == 0) {
                    currentStart = i;
                }
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    result.setLength(0); // Clear the result
                    result.append(currentStart).append("-").append(i - 1);
                } else if (currentLength == maxLength) {
                    if (result.length() > 0) {
                        result.append(" ");
                    }
                    result.append(currentStart).append("-").append(i - 1);
                }
                currentLength = 0;
            }
        }

        // Check the last segment
        if (currentLength > maxLength) {
            result.setLength(0); // Clear the result
            result.append(currentStart).append("-").append(rates.length - 1);
        } else if (currentLength == maxLength) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(currentStart).append("-").append(rates.length - 1);
        }

        return result.length() > 0 ? result.toString() : null;
    }

    public static void main(String[] args) {
        int minAverageLost = 2;
        int[] rates = {0, 0, 100, 2, 2, 99, 0, 2};
        String periods = findLongestSuccessRatePeriod(minAverageLost, rates);
        System.out.println(periods); // Output: 0-1 3-4 6-7
    }
}
