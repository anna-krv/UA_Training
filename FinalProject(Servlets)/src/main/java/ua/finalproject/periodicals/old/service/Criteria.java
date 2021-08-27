package ua.finalproject.periodicals.old.service;

import ua.finalproject.periodicals.old.entity.PeriodicalField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Criteria {
    private PeriodicalField fieldForSort;
    private int number;
    private String title;
    private List<String> topics;
    private Optional<Long> userId;

    public Criteria(String fieldForSort, int number, String title, List<String> topics, Optional<Long> userId) {
        try {
            this.fieldForSort = PeriodicalField.valueOf(fieldForSort.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException ex) {
            this.fieldForSort = PeriodicalField.TITLE;
        }
        this.number = number;
        this.title = title!=null? title: "";
        this.topics = topics != null ? topics: new ArrayList<>() ;
        this.userId = userId;
    }

    public PeriodicalField getFieldForSort() {
        return fieldForSort;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTopics() {
        return topics;
    }

    public Optional<Long> getUserId() {
        return userId;
    }
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
    public void setUserId(Optional<Long> userId) {
        this.userId = userId;
    }
}
