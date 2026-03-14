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

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue()- 1);
        String weekText = "Week of " + startOfWeek.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));

        JLabel weekLabel = new JLabel(weekText, SwingConstants.CENTER);
        weekLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(weekLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
            new Object[]{"Excercise", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Personal Best", "Last Logged"}, 0
        ) {
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
            new AddWorkoutView(this);
            setVisible(false);
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
                    new EditWorkoutView(this, workouts.get(i), i);
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
            String time =workout.getLoggedDateTime().format(DateTimeFormatter.ofPattern("hh:mm a"));

            int rowIndex = findExcerciseRow(excercise);
            int dayColumn
        }
    }

    }



    }
}

    