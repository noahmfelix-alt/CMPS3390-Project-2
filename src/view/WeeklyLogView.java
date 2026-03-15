package view;

import models.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WeeklyLogView extends JFrame {
    private final DefaultTableModel tableModel;
    private final JTable workoutTable;
    private final ArrayList<Workout> workouts;

    public WeeklyLogView() {
        workouts = new ArrayList<>();

        setTitle("Workout Tracker - Weekly Log");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        String weekText = "Week of " + startOfWeek.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));

        JLabel weekLabel = new JLabel(weekText, SwingConstants.CENTER);
        weekLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(weekLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[] { "Excercise", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Personal Best",
                        "Last Logged" },
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        workoutTable = new JTable(tableModel);
        mainPanel.add(new JScrollPane(workoutTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add Workout");
        JButton editButton = new JButton("Edit Workout");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);

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
            String selectedExcercise = tableModel.getValueAt(selectedRow, 0).toString();

            for (int i = 0; i < workouts.size(); i++) {
                if (workouts.get(i).getWorkoutName().equalsIgnoreCase(selectedExcercise)) {
                    // I commented this out so it would at least open the view
                    // new EditWorkoutView(this, workouts.get(i), i);
                    setVisible(false);
                    return;
                }
            }

        });

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
        refreshTable();

    }

    public void updateWorkout(int index, Workout workout) {
        workouts.set(index, workout);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        for (Workout workout : workouts) {
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
                return "Mon";
            case "TUESDAY":
                return "Tue";
            case "WEDNESDAY":
                return "Wed";
            case "THURSDAY":
                return "Thu";
            case "FRIDAY":
                return "Fri";
            case "SATURDAY":
                return "Sat";
            case "SUNDAY":
                return "Sun";
            default:
                return "";
        }
    }

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
