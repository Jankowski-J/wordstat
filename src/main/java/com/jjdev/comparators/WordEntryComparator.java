package com.jjdev.comparators;

import com.jjdev.model.WordEntry;

import java.util.Comparator;

public class WordEntryComparator implements Comparator<WordEntry> {

    @Override
    public int compare(WordEntry o1, WordEntry o2) {
        int difference = -(o1.getCount() - o2.getCount());

        if (difference == 0) {
            difference = o1.getWord().compareTo(o2.getWord());
        }

        return difference;
    }
}
