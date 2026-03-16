package view;

import models.Workout;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
<<<<<<< HEAD
import java.time.format.DateTimeParseException;
=======
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59

public class AddWorkoutView extends JDialog {
    private JTextField workoutNameField;
    private JTextField setsField;
    private JTextField repsField;
    private JTextField weightField;
    private JTextField dateTimeField;

<<<<<<< HEAD
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
=======
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
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59

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
<<<<<<< HEAD
            String name = workoutNameField.getText();
            int sets = Integer.parseInt(setsField.getText());
            int reps = Integer.parseInt(repsField.getText());
            double weight = Double.parseDouble(weightField.getText());
            LocalDateTime loggedDateTime = LocalDateTime.parse(dateTimeField.getText());

            Workout workout = new Workout(name, sets, reps, weight, loggedDateTime);
            dailyworkouts.add(workout);

            workoutListModel.addElement(name + " " + sets + "x" + reps + " @ " + weight + " lbs");

=======
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

            Workout workout = new Workout(name, sets, reps, weight, loggedDateTime);
            dailyWorkouts.add(workout);

            workoutListModel.addElement(name + " - " + sets + " sets x " + reps + " reps @ " + weight + " lbs");

>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59
            workoutNameField.setText("");
            setsField.setText("");
            repsField.setText("");
            weightField.setText("");
<<<<<<< HEAD
            dateTimeField.setText(LocalDateTime.now().toString());
            workoutNameField.requestFocusInWindow();

        } catch (NumberFormatException | DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for sets, reps, and weight.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
=======
            dateTimeField.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            workoutNameField.requestFocusInWindow();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sets, reps, and weight must be valid numbers.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Use date format: yyyy-MM-dd HH:mm");
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59
        }
    }

    private void completeDay() {
<<<<<<< HEAD
        if (dailyworkouts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one workout before completing the day.",
                    "No Workouts Added",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        parentView.addWorkouts(dailyworkouts);
        dailyworkouts.clear();
=======
        if (dailyWorkouts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one workout before saving.");
            return;
        }

        parentView.addWorkouts(dailyWorkouts);
>>>>>>> b604d7a037c4597facf7fa70fc101b79bc8c3e59
        dispose();
    }
}