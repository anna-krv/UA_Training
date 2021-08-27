package ua.finalproject.periodicals.old.controller;

public enum ErrorType {
    NONE,
    ACCOUNT,
    FORMAT,
    LOGIN,
    NOT_UNIQUE,
    NOT_UNIQUE_USER,
    MONEY,
    SUBSCRIPTION,
    SEARCH,
    RESOURCE,
    OTHER;

    @Override
    public String toString() {
        return "error."+super.toString().toLowerCase();
    }
}
