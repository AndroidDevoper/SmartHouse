package com.example.tabletapp.Tablet.Notes;

import com.example.lib.Note;

import java.util.ArrayList;
import java.util.Date;

public class NotesSorter {

    private static int FIRST_HAS_LOW_DATE = 1;
    private static int SECOND_HAS_LOW_DATE = 2;
    private static int DATE_FIRST_EQUALS_SECOND =0;

    private static int FIRST_HAS_LOW_TIME = 1;
    private static int SECOND_HAS_LOW_TIME = 2;
    private static int TIME_FIRST_EQUALS_SECOND =0;

    public static ArrayList<Note> sort(ArrayList<Note> list) {
        ArrayList<Note> sortList = new ArrayList<>();
        ArrayList<Note> cloneList = new ArrayList<>(list);
        for (Note note : cloneList) {
            if (!note.isAddTime) {
                sortList.add(note);
                list.remove(note);
            }
        }
        cloneList = new ArrayList<>(list);
        for (Note note : cloneList) {
            Note first = note;
            list.remove(first);
            ArrayList<Note> cloneList2 = new ArrayList<>(list);
            for (Note second : cloneList2) {
                if (sortList.contains(first))
                    first = second;
                int resultCompareDate  = NotesSorter.compareLowDate(first, second);
                if (resultCompareDate == NotesSorter.SECOND_HAS_LOW_DATE) {
                    list.add(first);
                    list.remove(second);
                    first = second;
                }
                else if (resultCompareDate == NotesSorter.DATE_FIRST_EQUALS_SECOND) {
                    int resultCompareTime = NotesSorter.compareLowTime(first, second);
                    if (resultCompareTime == NotesSorter.SECOND_HAS_LOW_TIME) {
                        list.add(first);
                        list.remove(second);
                        first = second;
                    }
                }
            }
            sortList.add(first);
        }
        return sortList;
    }

    private static int compareLowDate(Note first, Note second) {

        int firstNoteMonth = convertIntSecondValue(first.getDate());
        int secondNoteMonth = convertIntSecondValue(second.getDate());
        if (firstNoteMonth > secondNoteMonth)
            return SECOND_HAS_LOW_DATE;
        else if (firstNoteMonth < secondNoteMonth)
            return FIRST_HAS_LOW_DATE;

        int firstNoteDay = convertIntFirstValue(first.getDate());
        int secondNoteDay = convertIntFirstValue(second.getDate());
        if (firstNoteDay > secondNoteDay)
            return SECOND_HAS_LOW_DATE;
        else if (firstNoteDay < secondNoteDay)
            return FIRST_HAS_LOW_DATE;
        else
            return DATE_FIRST_EQUALS_SECOND;
    }

    private static int compareLowTime(Note first, Note second) {
        int firstNoteHours = convertIntFirstValue(first.getTime());
        int secondNoteHours = convertIntFirstValue(second.getTime());
        if (firstNoteHours > secondNoteHours)
            return SECOND_HAS_LOW_DATE;
        else if (firstNoteHours < secondNoteHours)
            return FIRST_HAS_LOW_DATE;

        int firstNoteMinutes = convertIntSecondValue(first.getTime());
        int secondNoteMinutes = convertIntSecondValue(second.getTime());
        if (firstNoteMinutes > secondNoteMinutes)
            return SECOND_HAS_LOW_DATE;
        else if (firstNoteMinutes < secondNoteMinutes)
            return FIRST_HAS_LOW_DATE;
        else
            return TIME_FIRST_EQUALS_SECOND;
    }

    private static int convertIntFirstValue(String noteTime) {
        String result;
        if (noteTime.length() == 5)
            result = noteTime.substring(0,2);
        else
            result = String.valueOf(noteTime.charAt(0));
        return Integer.parseInt(result);
    }

    private static int convertIntSecondValue(String noteTime) {
        String result;
        if (noteTime.length() == 5)
            result = noteTime.substring(3,5);
        else
            result = noteTime.substring(2,4);

        return Integer.parseInt(result);
    }

    public static void setNextDay(Note note) {
        Date tomorrow = new Date(new Date().getTime() + 86400000L);
        int nextDay = tomorrow.getDate();
        int nextMonth = tomorrow.getMonth();
        nextMonth++;
        String result;
        if(nextMonth == 10 || nextMonth == 11 || nextMonth == 12)
            result = nextDay + "." + nextMonth;
        else
            result = nextDay + ".0" + nextMonth;

        note.addTime(note.getTime(), result);
    }
}
