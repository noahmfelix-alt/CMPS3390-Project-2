package models;

import java.util.*;

public class WorkoutList {
    // List of workouts - self-explanatory
    
    private List<Workout> workouts;

    public WorkoutList() {
        workouts = new ArrayList<>();
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public void addWorkouts(List<Workout> workouts) {
        this.workouts.addAll(workouts);
    }

    public void removeWorkout(int index) {
        if (index >= 0 && index < workouts.size()) {
            workouts.remove(index);
        }
    }

    public void updateWorkout(int index, Workout workout) {
        if (index >= 0 && index < workouts.size()) {
            workouts.set(index, workout);
        }
    }

}