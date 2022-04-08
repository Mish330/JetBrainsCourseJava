/*
https://hyperskill.org/learn/daily/3663#solutions

A right rotation is an operation that shifts each element of an array to the right. For example, if an array is {1,2,3,4,5} and we right rotate it by 1, the new array will be {5,1,2,3,4}. If we rotate it by 2, the new array will be {4,5,1,2,3}. It goes like this: {1,2,3,4,5} -> {5,1,2,3,4} -> {4,5,1,2,3}.

Write a program that performs a right rotation on an array by a given number.

Input format:
The first line is an array of numbers.
The second line is the number of rotations.

Output format:
Resulting array
*/

import java.util.Scanner;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr1 = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
        int shiftArr = sc.nextInt();
        int arrLen = arr1.length;
        int[] arr2 = new int[arrLen];
        if (shiftArr > arrLen) {
            shiftArr = shiftArr - arrLen * (shiftArr / arrLen);
        }
        for (int i = 0; i < arrLen; i++) {
            if (arrLen - shiftArr + i < arrLen) {
                arr2[i] = arr1[arrLen - shiftArr + i];
            } else {
                arr2[i] = arr1[i - shiftArr];
            }
            System.out.print(arr2[i] + " ");
        }
        sc.close();
    }
}
