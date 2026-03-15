package view;

import models.Workout;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddWorkoutView extends JDialog {
    private JTextField workoutNameField;
    private JTextField setsField;
    private JTextField repsField;
    private JTextField weightField;
    private JTextField dateTimeField;

    private JButton addWorkoutButton;
    private JButton completeDayButton;
    private JButton cancelButton;

    private WeeklyLogView parentView;
    private List<Workout> dailyworkouts = new ArrayList<>();

    private DefaultListModel<String> workoutListModel;
    private JList<String> workoutList;

    public AddWorkoutView(WeeklyLogView parentView) {
        this.parentView = parentView;
        setTitle("Add Workouts for the Day");
        setSize(600, 500);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentView);

        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        workoutNameField = new JTextField();
        setsField = new JTextField();
        repsField = new JTextField();
        weightField = new JTextField();
        dateTimeField = new JTextField(LocalDateTime.now().toString());

        inputPanel.add(new JLabel("Workout Name:"));
        inputPanel.add(workoutNameField);
        inputPanel.add(new JLabel("Sets:"));
        inputPanel.add(setsField);
        inputPanel.add(new JLabel("Reps:"));
        inputPanel.add(repsField);
        inputPanel.add(new JLabel("Weight:"));
        inputPanel.add(weightField);
        inputPanel.add(new JLabel("Logged Date & Time:"));
        inputPanel.add(dateTimeField);

        add(inputPanel, BorderLayout.NORTH);

        workoutListModel = new DefaultListModel<>();
        workoutList = new JList<>(workoutListModel);
        JScrollPane listScrollPane = new JScrollPane(workoutList);
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Today's Workouts"));
        add(listScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addWorkoutButton = new JButton("Add Workout");
        completeDayButton = new JButton("Complete Day");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(addWorkoutButton);
        buttonPanel.add(completeDayButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addWorkoutButton.addActionListener(e -> addWorkout());
        completeDayButton.addActionListener(e -> completeDay());
        cancelButton.addActionListener(e -> dispose());

    }

    private void addWorkout() {
        try {
            String name = workoutNameField.getText();
            int sets = Integer.parseInt(setsField.getText());
            int reps = Integer.parseInt(repsField.getText());
            double weight = Double.parseDouble(weightField.getText());
            LocalDateTime loggedDateTime = LocalDateTime.parse(dateTimeField.getText());

            Workout workout = new Workout(name, sets, reps, weight, loggedDateTime);
            dailyworkouts.add(workout);

            workoutListModel.addElement(name + " " + sets + "x" + reps + " @ " + weight + " lbs");

            workoutNameField.setText("");
            setsField.setText("");
            repsField.setText("");
            weightField.setText("");
            dateTimeField.setText(LocalDateTime.now().toString());
            workoutNameField.requestFocusInWindow();

        } catch (NumberFormatException | DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for sets, reps, and weight.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void completeDay() {
        if (dailyworkouts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one workout before completing the day.",
                    "No Workouts Added",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        parentView.addWorkouts(dailyworkouts);
        dailyworkouts.clear();
        dispose();
    }
}