import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private JLabel label;
    private JButton start, stop, reset;
    private Timer timer;
    private int waktu = 0;

    private String formatWaktu(int total) {
        int jam = total / 3600000;
        int menit = (total % 3600000) / 60000;
        int detik = (total % 60000) / 1000;
        int md = (total % 1000) / 10;
        return String.format("%02d:%02d:%02d:%02d", jam, menit, detik, md);
    }

    public App() {
        setTitle("Stopwatch");
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(40, 44, 52));

        label = new JLabel(formatWaktu(waktu), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setForeground(Color.WHITE);
        add(label, BorderLayout.CENTER);

        start = new JButton("\u25B6");
        stop = new JButton("\u23F9");
        reset = new JButton("\u27F3");

        start.setFont(start.getFont().deriveFont(20f));
        stop.setFont(stop.getFont().deriveFont(20f));
        reset.setFont(reset.getFont().deriveFont(20f));
        start.setBackground(Color.GREEN);
        stop.setBackground(Color.RED);
        reset.setBackground(Color.CYAN);
        start.setFocusPainted(false);
        stop.setFocusPainted(false);
        reset.setFocusPainted(false);
        start.setPreferredSize(new Dimension(80, 50));
        stop.setPreferredSize(new Dimension(80, 50));
        reset.setPreferredSize(new Dimension(80, 50));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(40, 44, 52));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(start);
        buttonPanel.add(stop);
        buttonPanel.add(reset);
        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(1, event -> {
            waktu += 1;
            label.setText(formatWaktu(waktu));
        });

        start.addActionListener(event -> {
            timer.start();
            start.setEnabled(false);
            stop.setEnabled(true);
        });

        stop.addActionListener(event -> {
            timer.stop();
            start.setEnabled(true);
            stop.setEnabled(false);
        });

        reset.addActionListener(event -> {
            timer.stop();
            waktu = 0;
            label.setText(formatWaktu(waktu));
            start.setEnabled(true);
            stop.setEnabled(false);
        });

        stop.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new App().setVisible(true);
        });
    }
}