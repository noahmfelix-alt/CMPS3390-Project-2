package view;
import models.Workout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EditWorkoutView extends JDialog {

    private final WeeklyLogView parentView;
    private final int workoutIndex;

    private JTextField workoutNameField;
    private JTextField setsField;
    private JTextField repsField;
    private JTextField weightField;
    private JTextField dateTimeField;

    public EditWorkoutView(WeeklyLogView parentView, Workout workout, int workoutIndex) {
        this.parentView = parentView;
        this.workoutIndex = workoutIndex;

        setTitle("Edit Workout");
        setSize(420, 320);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentView);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(250, 250, 250));

        JLabel titleLabel = new JLabel("Edit Workout", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBackground(new Color(250, 250, 250));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        workoutNameField = new JTextField(workout.getWorkoutName());
        setsField = new JTextField(String.valueOf(workout.getSets()));
        repsField = new JTextField(String.valueOf(workout.getReps()));
        weightField = new JTextField(String.valueOf(workout.getWeight()));
        dateTimeField = new JTextField(workout.getLoggedDateTime().format(formatter));

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

        panel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(250, 250, 250));

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> saveWorkout());
        cancelButton.addActionListener(e -> dispose());

        setContentPane(panel);
    }

    private void saveWorkout() {
        try {
            String name = workoutNameField.getText().trim();
            int sets = Integer.parseInt(setsField.getText().trim());
            int reps = Integer.parseInt(repsField.getText().trim());
            double weight = Double.parseDouble(weightField.getText().trim());
            LocalDateTime loggedDateTime = LocalDateTime.parse(
                    dateTimeField.getText().trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            );

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

            Workout updatedWorkout = new Workout(name, sets, reps, weight, loggedDateTime);
            parentView.updateWorkout(workoutIndex, updatedWorkout);

            JOptionPane.showMessageDialog(this, "Workout updated successfully.");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sets, reps, and weight must be valid numbers.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Use date format: yyyy-MM-dd HH:mm");
        }
    }
}

