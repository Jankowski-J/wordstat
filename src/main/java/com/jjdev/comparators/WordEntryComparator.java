package com.jjdev.comparators;

import com.jjdev.model.WordEntry;

import java.util.Comparator;

public class WordEntryComparator implements Comparator<WordEntry> {

    @Override
    public int compare(WordEntry o1, WordEntry o2) {
        return -(o1.getCount() - o2.getCount());
    }
}
