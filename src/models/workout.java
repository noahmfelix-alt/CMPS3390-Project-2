package models;

public class workout {
    private String workoutName;
    private int sets;
    private int reps;

    public workout(String workoutName, int sets, int reps) {
        this.workoutName = workoutName;
        this.sets = sets;
        this.reps = reps;
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

}
