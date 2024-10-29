package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class inputArrayList {
    private ArrayList<String> L;

    public inputArrayList(int size) {
        L = new ArrayList<>(size);
    }

    public void addArrayList(String s) {
        if (!L.contains(s)) {
            L.add(s);
        }
    }

    public boolean isPalindromeString(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public void expandAndUpdateWithInput(int index) {
        int currentSize = L.size();

        if (index >= currentSize) {
            // Require string from user for intermediate indexes
            for (int i = currentSize; i <= index; i++) {
                String newString = JOptionPane.showInputDialog("Enter a string for index " + i + ":");
                if (newString != null) {  // If the user clicks "Cancel" it may return null, we need to check it.
                    L.add(newString);

                    // Palindrome check for newly added string
                    if (isPalindromeString(newString)) {
                        JOptionPane.showMessageDialog(null, "\"" + newString + "\" is a palindrome!");
                    } else {
                        JOptionPane.showMessageDialog(null, "\"" + newString + "\" is not a palindrome.");
                    }
                }
            }
        } else {
            // If an existing index is being updated
            String newString = JOptionPane.showInputDialog("Enter a new string for index " + index + ":");
            if (newString != null) {  // If the user clicks "Cancel" it may return null, we need to check it.
                L.set(index, newString);

                // Check palindrome for updated string
                if (isPalindromeString(newString)) {
                    JOptionPane.showMessageDialog(null, "\"" + newString + "\" is a palindrome!");
                } else {
                    JOptionPane.showMessageDialog(null, "\"" + newString + "\" is not a palindrome.");
                }
            }
        }
    }


    public void printArrayList() {
        for (int i = 0; i < L.size(); i++) {
            System.out.println(i + ": " + L.get(i));
        }
    }

    public int size() {
        return L.size();
    }

    public String get(int index) {
        return L.get(index);
    }
    public void remove(int index) {
        if (index >= 0 && index < L.size()) {
            L.remove(index);
        }
    }

}
