package view;

import javax.swing. *;
import java.awt.*;

public class HomeView extends JFrame {

    public HomeView() {
        setTitle("FitTrack");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("FitTrack - Workout Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20 ));

        JButton addWorkoutButton = new JButton("Add Workout");
        JButton viewWorkoutsButton = new JButton("View Workouts");
        JButton exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1,10, 10));
        buttonPanel.add(addWorkoutButton);
        buttonPanel.add(viewWorkoutsButton);
        buttonPanel.add(exitButton);

        setLayout(new BorderLayout(15, 15));
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        exitButton.addActionListener(e -> System.exit(0));
        setVisible(true);
    

    }
    
}
