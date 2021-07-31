package ua.kpi.tef.util;

import ua.kpi.tef.model.UserMeal;
import ua.kpi.tef.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList,
                LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        filteredWithExceeded
                .stream()
                .forEach(System.out::println);

    }

    /*
     * Get filtered list with correct exceed field.
     */
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {
        /* step 1: create map with key: date and value: number of calories
         * step 2: create map with key: date and value: indicator that calories are exceeded
         * step 3: create UserMealWithExceed from UserMeal and info from map
         * Time complexity: O(1) - put in hashmap/ get from hashmap. Repeat for n meals -> O(n).
         */
        Map<LocalDate, Integer> caloriesCounter = mealList
                .stream()
                .collect(Collectors.toMap(
                        meal -> meal.getDateTime().toLocalDate(),
                        UserMeal::getCalories,
                        (accumulatedCalories, calories) -> accumulatedCalories + calories
                ));
        Map<LocalDate, Boolean> caloriesExceed = caloriesCounter
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() > caloriesPerDay
                ));
        return mealList
                .stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal ->
                        new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                                caloriesExceed.get(meal.getDateTime().toLocalDate())))
                .collect(Collectors.toList());
    }

    /**
     * The same functionality as in getFilteredWithExceeded but written without streams.
     *
     * @param mealList
     * @param startTime      for filtering meals.
     * @param endTime        for filtering meals.
     * @param caloriesPerDay upper bound on calories.
     * @return list of UserMealWithExceed objects that have time such that startTime <= time <= endTime.
     */
    public static List<UserMealWithExceed> getFilteredWithExceededOldVersion(List<UserMeal> mealList, LocalTime startTime,
                                                                             LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesCounter = getCaloriesCounter(mealList);
        Map<LocalDate, Boolean> caloriesExceed = getCaloriesChecked(caloriesCounter, caloriesPerDay);
        List<UserMealWithExceed> filteredWithExceededMealList = new ArrayList<>();

        for (UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                filteredWithExceededMealList.add(new UserMealWithExceed(
                        meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        caloriesExceed.get(meal.getDateTime().toLocalDate())
                ));
            }
        }
        return filteredWithExceededMealList;
    }

    private static Map<LocalDate, Integer> getCaloriesCounter(List<UserMeal> mealList) {
        Map<LocalDate, Integer> caloriesCounter = new HashMap<>();

        for (UserMeal meal : mealList) {
            LocalDate date = meal.getDateTime().toLocalDate();
            caloriesCounter.putIfAbsent(date, 0);
            caloriesCounter.merge(date, meal.getCalories(), Integer::sum);
        }

        return caloriesCounter;
    }

    private static Map<LocalDate, Boolean> getCaloriesChecked(Map<LocalDate, Integer> caloriesCounter,
                                                              int caloriesPerDay) {
        Map<LocalDate, Boolean> caloriesExceed = new HashMap<>();
        for (Map.Entry<LocalDate, Integer> entry : caloriesCounter.entrySet()) {
            caloriesExceed.put(entry.getKey(), entry.getValue() > caloriesPerDay);
        }
        return caloriesExceed;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededVideoSolution(List<UserMeal> mealList, LocalTime startTime,
                                                                                LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList
                .stream()
                .collect(Collectors.groupingBy(
                        meal -> meal.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)
                ));
        return mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal -> new UserMealWithExceed(meal.getDateTime(),
                        meal.getDescription(), meal.getCalories(),
                        caloriesSumByDate.get(meal.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());

    }
}
