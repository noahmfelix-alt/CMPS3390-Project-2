package view;

import models.Workout;
import models.WorkoutList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class WeeklyLogView extends JFrame {
    private final WorkoutList workoutList;
    private final DefaultTableModel tableModel;
    private final JTable workoutTable;
    

    public WeeklyLogView() {
        workoutList = new WorkoutList();

        setTitle("Workout Tracker");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 247, 250));

        LocalDate today = LocalDate.now();
        String todayText = "Workout Log - " + today.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));

        JLabel titleLabel = new JLabel(todayText, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"Exercise", "Sets", "Reps", "Weight", "Day", "Last Logged"},
                0
        ) {
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
        });

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void addWorkout(Workout workout) {
        workoutList.addWorkout(workout);
        refreshTable();
    }

    public void addWorkouts(java.util.List<Workout> workouts) {
        workoutList.addWorkouts(workouts);
        refreshTable();
    }

    public void updateWorkout(int index, Workout workout) {
        workoutList.updateWorkout(index, workout);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

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
}