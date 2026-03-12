package view;

import models.workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class WeeklyLogView extends JFrame {
    private final DefaultTableModel tableModel;
    private final JTable workoutTable;
    private final ArrayList<workout> workouts;

    public WeeklyLogView() {
        workouts = new ArrayList<>();

        setTitle("Workout Tracker - Weekly Log");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel maiPanel = new JPanel(new BorderLayout(10,10));
        main


    }
}

    