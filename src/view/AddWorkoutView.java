package view;

import models.Workout;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddWorkoutView extends JDialog {
    // View for adding Workouts
    
    private JTextField workoutNameField;
    private JTextField setsField;
    private JTextField repsField;
    private JTextField weightField;
    private JTextField dateTimeField;

    private final WeeklyLogView parentView;
    private final List<Workout> dailyWorkouts = new ArrayList<>();

    private DefaultListModel<String> workoutListModel;

    public AddWorkoutView(WeeklyLogView parentView) {
        this.parentView = parentView;

        setTitle("Add Workouts");
        setSize(500, 450);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentView);

        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(250, 250, 250));

        JLabel titleLabel = new JLabel("Add Workouts", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBackground(new Color(250, 250, 250));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        workoutNameField = new JTextField();
        setsField = new JTextField();
        repsField = new JTextField();
        weightField = new JTextField();
        dateTimeField = new JTextField(LocalDateTime.now().format(formatter));

        formPanel.add(new JLabel("Workout Name:"));
        formPanel.add(workoutNameField);
        formPanel.add(new JLabel("Sets:"));
        formPanel.add(setsField);
        formPanel.add(new JLabel("Reps:"));
        formPanel.add(repsField);
        formPanel.add(new JLabel("Weight:"));
        formPanel.add(weightField);
        formPanel.add(new JLabel("Date & Time:"));
        formPanel.add(dateTimeField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        workoutListModel = new DefaultListModel<>();
        JList<String> workoutList = new JList<>(workoutListModel);
        JScrollPane listScrollPane = new JScrollPane(workoutList);
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Workouts Added This Session"));

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(new Color(250, 250, 250));
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(listScrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        buttonPanel.setBackground(new Color(250, 250, 250));

        JButton addWorkoutButton = new JButton("Add Workout");
        JButton completeDayButton = new JButton("Save to Main List");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(addWorkoutButton);
        buttonPanel.add(completeDayButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addWorkoutButton.addActionListener(e -> addWorkout());
        completeDayButton.addActionListener(e -> completeDay());
        cancelButton.addActionListener(e -> dispose());

        setContentPane(mainPanel);
    }

    private void addWorkout() {
        try {
            String name = workoutNameField.getText().trim();
            int sets = Integer.parseInt(setsField.getText().trim());
            int reps = Integer.parseInt(repsField.getText().trim());
            double weight = Double.parseDouble(weightField.getText().trim());
            LocalDateTime loggedDateTime = LocalDateTime.parse(
                    dateTimeField.getText().trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Workout name cannot be empty.");
                return;
            }

            if (sets <= 0 || reps <= 0) {
                JOptionPane.showMessageDialog(this, "Sets and reps must be greater than 0.");
                return;
            }

            if (weight < 0) {
                JOptionPane.showMessageDialog(this, "Weight cannot be negative.");
                return;
            }

            Workout workout = new Workout(name, sets, reps, weight, loggedDateTime);
            dailyWorkouts.add(workout);

            workoutListModel.addElement(name + " - " + sets + " sets x " + reps + " reps @ " + weight + " lbs");

            workoutNameField.setText("");
            setsField.setText("");
            repsField.setText("");
            weightField.setText("");
            dateTimeField.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            workoutNameField.requestFocusInWindow();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sets, reps, and weight must be valid numbers.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Use date format: yyyy-MM-dd HH:mm");
        }
    }

    private void completeDay() {
        if (dailyWorkouts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one workout before saving.");
            return;
        }

        parentView.addWorkouts(dailyWorkouts);
        dispose();
    }
}