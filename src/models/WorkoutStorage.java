package models;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkoutStorage {
    // private static final String FILE_NAME = System.getProperty("user.home") + "workouts.json";
    private static final String FILE_NAME = "workouts.json"; // saves to working dir

    // Construct gson with date and time
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
        .setPrettyPrinting()
        .create();

    public static void save(List<Workout> workouts) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(workouts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Workout> load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Workout>>() {}.getType();
            List<Workout> workouts = gson.fromJson(reader, listType);
            if (workouts != null) {
                return workouts;
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
