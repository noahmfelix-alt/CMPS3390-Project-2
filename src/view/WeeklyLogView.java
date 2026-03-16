package view;

import models.Workout;
import models.WorkoutList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
<<<<<<< HEAD
import java.util.ArrayList;
=======

>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59

public class WeeklyLogView extends JFrame {
    private final WorkoutList workoutList;
    private final DefaultTableModel tableModel;
    private final JTable workoutTable;
    

    public WeeklyLogView() {
<<<<<<< HEAD
        workouts = new ArrayList<>();
=======
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59
        workoutList = new WorkoutList();

        setTitle("Workout Tracker");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

<<<<<<< HEAD
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        String weekText = "Week of " + startOfWeek.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
=======
        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 247, 250));

        LocalDate today = LocalDate.now();
        String todayText = "Workout Log - " + today.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59

        JLabel titleLabel = new JLabel(todayText, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
<<<<<<< HEAD
                new Object[] { "Excercise", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Personal Best",
                        "Last Logged" },
                0) {
=======
                new Object[]{"Exercise", "Sets", "Reps", "Weight", "Day", "Last Logged"},
                0
        ) {
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

       workoutTable = new JTable(tableModel);
        workoutTable.setRowHeight(24);
        workoutTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        workoutTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        workoutTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(workoutTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("All Workouts"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.setBackground(new Color(245, 247, 250));

        JButton addButton = new JButton("Add Workout");
        JButton editButton = new JButton("Edit Workout");
        JButton deleteButton = new JButton("Delete Workout");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            AddWorkoutView addWorkoutView = new AddWorkoutView(this);
            addWorkoutView.setVisible(true);
        });

        editButton.addActionListener(e -> {
            int selectedRow = workoutTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a workout to edit.");
                return;
            }

<<<<<<< HEAD
            for (int i = 0; i < workoutList.getWorkouts().size(); i++) {
                if (workoutList.getWorkouts().get(i).getWorkoutName().equalsIgnoreCase(selectedExcercise)) {
                    // I commented this out so it would at least open the view
                    // new EditWorkoutView(this, workoutList.getWorkouts().get(i), i);
                    setVisible(true);
                    return;
                }
            }

=======
            Workout selectedWorkout = workoutList.getWorkouts().get(selectedRow);
            EditWorkoutView editWorkoutView = new EditWorkoutView(this, selectedWorkout, selectedRow);
            editWorkoutView.setVisible(true);
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = workoutTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a workout to delete.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this workout?",
                    "Delete Workout",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                workoutList.removeWorkout(selectedRow);
                refreshTable();
            }
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59
        });

        setContentPane(mainPanel);
        setVisible(true);
    }
<<<<<<< HEAD

    public void addWorkout(Workout workout) {
        workoutList.addWorkout(workout);
        refreshTable();
=======
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59

    public void addWorkout(Workout workout) {
        workoutList.addWorkout(workout);
        refreshTable();
    }

    public void addWorkouts(java.util.List<Workout> workouts) {
        workoutList.addWorkouts(workouts);
        refreshTable();
    }

    public void addWorkouts(java.util.List<Workout> workouts) {
        for (Workout workout : workouts) {
            workoutList.addWorkout(workout);
        }
        refreshTable();
    }

    public void updateWorkout(int index, Workout workout) {
        workoutList.updateWorkout(index, workout);
<<<<<<< HEAD
        refreshTable();
    }

    public void updateWorkouts(int index, java.util.List<Workout> workouts) {
        for (Workout workout : workouts) {
            workoutList.updateWorkout(index, workout);
        }
=======
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

<<<<<<< HEAD
        for (Workout workout : workoutList.getWorkouts()) {
            String excercise = workout.getWorkoutName();
            double weight = workout.getWeight();
            String day = getShortDay(workout);
            String time = workout.getLoggedDateTime().format(DateTimeFormatter.ofPattern("hh:mm a"));

            int rowIndex = findExcerciseRow(excercise);
            int dayColumn = getDayColumnIndex(day);

            if (rowIndex == -1) {
                Object[] row = new Object[10];
                row[0] = excercise;
                row[dayColumn] = weight;
                row[8] = weight;
                row[9] = time;
                tableModel.addRow(row);
            } else {
                tableModel.setValueAt(weight, rowIndex, 8);

            }

            tableModel.setValueAt(time, rowIndex, 9);
        }
    }

    private int findExcerciseRow(String excerciseName) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object value = tableModel.getValueAt(i, 0);
            if (value != null && value.toString().equalsIgnoreCase(excerciseName)) {
                return i;
            }

        }
        return -1;
    }

    private String getShortDay(Workout workout) {
        String fullday = workout.getLoggedDateTime().getDayOfWeek().toString();

        switch (fullday) {
            case "MONDAY":
=======
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

        for (Workout workout : workoutList.getWorkouts()) {
            tableModel.addRow(new Object[]{
                    workout.getWorkoutName(),
                    workout.getSets(),
                    workout.getReps(),
                    workout.getWeight(),
                    getShortDay(workout),
                    workout.getLoggedDateTime().format(dateTimeFormatter)
            });
        }
    }

    private String getShortDay(Workout workout) {
        switch (workout.getLoggedDateTime().getDayOfWeek()) {
            case MONDAY:
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59
                return "Mon";
            case TUESDAY:
                return "Tue";
            case WEDNESDAY:
                return "Wed";
            case THURSDAY:
                return "Thu";
            case FRIDAY:
                return "Fri";
            case SATURDAY:
                return "Sat";
            case SUNDAY:
                return "Sun";
            default:
                return "";
        }
    }
<<<<<<< HEAD

    private int getDayColumnIndex(String day) {
        switch (day) {
            case "Mon":
                return 1;
            case "Tue":
                return 2;
            case "Wed":
                return 3;
            case "Thu":
                return 4;
            case "Fri":
                return 5;
            case "Sat":
                return 6;
            case "Sun":
                return 7;
            default:
                return -1;
        }
    }

}
=======
}
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59
