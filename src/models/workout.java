package models;

import java.time.LocalDateTime;

public class Workout {
    private String workoutName;
    private int sets;
    private int reps;
    private double weight;
    private LocalDateTime loggedDateTime;

    public Workout(String workoutName, int sets, int reps, double weight, LocalDateTime loggedDateTime) {
        this.workoutName = workoutName;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.loggedDateTime = loggedDateTime;

    }

    public String getWorkoutName() {
        return workoutName;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDateTime getLoggedDateTime() {
        return loggedDateTime;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutName='" + workoutName + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                ", weight=" + weight +
                ", loggedDateTime=" + loggedDateTime +
                '}';
    }

}
