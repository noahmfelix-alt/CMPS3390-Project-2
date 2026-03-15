package view;

import models.Workout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class AddWorkoutView extends JDialog {

    public AddWorkoutView(JFrame parentView) {
        super(parentView, true);
        setTitle("Add Workout");
        setSize(400, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentView);

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Workout Name:"));
        JTextField workoutNameField = new JTextField();
        add(workoutNameField);

        add(new JLabel("Sets:"));
        JTextField setsField = new JTextField();
        add(setsField);

        add(new JLabel("Reps:"));
        JTextField repsField = new JTextField();
        add(repsField);

        add(new JLabel("Weight:"));
        JTextField weightField = new JTextField();
        add(weightField);

        add(new JLabel("Logged Date & Time:"));
        JTextField dateTimeField = new JTextField(LocalDateTime.now().toString());
        add(dateTimeField);

    }
}