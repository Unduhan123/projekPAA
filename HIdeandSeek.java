package com.mycompany.mavenproject1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Mavenproject1 extends JFrame {
    private static final int MAP_SIZE = 8;
    private static final int CELL_SIZE = 50;
    private static final int RED_DROID_COUNT = 1;

    private JPanel mapPanel;
    private Droid redDroid;
    private Droid greenDroid;
    private java.util.List<Droid> redDroidList;
    private JButton startButton;
    private JButton stopButton;
    private JButton shuffleMapButton;
    private JButton shuffleRedDroidButton;
    private JButton shuffleGreenDroidButton;
    private JButton addRedDroidButton;
    private JSlider greenDroidVisibilitySlider;
    private JButton showRedVisibilityButton;
    private JButton showGreenVisibilityButton;

    public Mavenproject1() {
        setTitle("Mavenproject1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(MAP_SIZE, MAP_SIZE));
        mapPanel.setPreferredSize(new Dimension(MAP_SIZE * CELL_SIZE, MAP_SIZE * CELL_SIZE));

        initializeDroids();
        initializeMap();
        initializeButtons();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(mapPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void initializeDroids() {
        redDroid = new Droid("Red Droid", Color.RED);
        greenDroid = new Droid("Green Droid", Color.GREEN);
        redDroidList = new java.util.ArrayList<>();

        // Mengacak posisi awal Droid Merah dan Hijau
        redDroid.setRandomPosition(MAP_SIZE);
        greenDroid.setRandomPosition(MAP_SIZE);

        // Menambahkan listener agar Droid Merah mengejar Droid Hijau
        redDroid.addDroidMovedListener((x, y) -> {
            for (Droid redDroid : redDroidList) {
                redDroid.updateVisibility(x, y);
            }
            greenDroid.updateVisibility(x, y);
            refreshMap();
        });

        // Menambahkan listener agar Droid Hijau menghindari Droid Merah
        greenDroid.addDroidMovedListener((x, y) -> {
            redDroid.updateVisibility(x, y);
            refreshMap();
        });
    }

    private void initializeMap() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Mengacak peta
                if (Math.random() < 0.3) {
                    cellPanel.setBackground(Color.BLACK);
                }

                // Menambah Droid Merah
                if (i == redDroid.getX() && j == redDroid.getY()) {
                    cellPanel.setBackground(redDroid.getColor());
                }

                // Menambah Droid Hijau
                if (i == greenDroid.getX() && j == greenDroid.getY()) {
                    cellPanel.setBackground(greenDroid.getColor());
                }

                mapPanel.add(cellPanel);
            }
        }
    }

    private void initializeButtons() {
        startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            redDroid.startMoving(greenDroid.getX(), greenDroid.getY());
            greenDroid.startMoving(redDroid.getX(), redDroid.getY());
            for (Droid redDroid : redDroidList) {
                redDroid.startMoving(greenDroid.getX(), greenDroid.getY());
            }
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        });

        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> {
            redDroid.stopMoving();
            greenDroid.stopMoving();
            for (Droid redDroid : redDroidList) {
                redDroid.stopMoving();
            }
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        });

        shuffleMapButton = new JButton("Shuffle Map");
        shuffleMapButton.addActionListener(e -> {
            shuffleDroidPositions();
            refreshMap();
        });

        shuffleRedDroidButton = new JButton("Shuffle Red Droid");
        shuffleRedDroidButton.addActionListener(e -> {
            redDroid.setRandomPosition(MAP_SIZE);
            refreshMap();
        });

        shuffleGreenDroidButton = new JButton("Shuffle Green Droid");
        shuffleGreenDroidButton.addActionListener(e -> {
            greenDroid.setRandomPosition(MAP_SIZE);
            refreshMap();
        });

        addRedDroidButton = new JButton("Add Red Droid");
        addRedDroidButton.addActionListener(e -> {
            // Menambah Droid Merah baru
            Droid newRedDroid = new Droid("Red Droid", Color.RED);
            newRedDroid.setRandomPosition(MAP_SIZE);
            newRedDroid.addDroidMovedListener((x, y) -> {
                newRedDroid.updateVisibility(x, y);
            });
            redDroidList.add(newRedDroid);
            newRedDroid.startMoving(greenDroid.getX(), greenDroid.getY());
        });

        greenDroidVisibilitySlider = new JSlider(1, 5, 3);
        greenDroidVisibilitySlider.setMajorTickSpacing(1);
        greenDroidVisibilitySlider.setPaintTicks(true);

        showRedVisibilityButton = new JButton("Show Red Droid Visibility");
        showRedVisibilityButton.addActionListener(e -> {
            // Menampilkan pandangan Droid Merah
            redDroid.updateVisibility(greenDroid.getX(), greenDroid.getY());
            for (Droid redDroid : redDroidList) {
                redDroid.updateVisibility(greenDroid.getX(), greenDroid.getY());
            }
            refreshMap();
        });

        showGreenVisibilityButton = new JButton("Show Green Droid Visibility");
        showGreenVisibilityButton.addActionListener(e -> {
            // Menampilkan pandangan Droid Hijau
            int visibility = greenDroidVisibilitySlider.getValue();
            greenDroid.updateVisibility(redDroid.getX(), redDroid.getY(), visibility);
            refreshMap();
        });
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(9, 1));

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(shuffleMapButton);
        buttonPanel.add(shuffleRedDroidButton);
        buttonPanel.add(shuffleGreenDroidButton);
        buttonPanel.add(addRedDroidButton);
        buttonPanel.add(greenDroidVisibilitySlider);
        buttonPanel.add(showRedVisibilityButton);
        buttonPanel.add(showGreenVisibilityButton);

        return buttonPanel;
    }

    private void shuffleDroidPositions() {
        // Shuffle positions of red and green droids
        redDroid.setRandomPosition(MAP_SIZE);
        greenDroid.setRandomPosition(MAP_SIZE);

        // Shuffle positions of additional red droids
        for (Droid redDroid : redDroidList) {
            redDroid.setRandomPosition(MAP_SIZE);
        }
    }

    private void refreshMap() {
        Component[] components = mapPanel.getComponents();

        for (int i = 0; i < components.length; i++) {
            JPanel cellPanel = (JPanel) components[i];
            int row = i / MAP_SIZE;
            int col = i % MAP_SIZE;

            cellPanel.removeAll();

            if (Math.random() < 0.3) {
                cellPanel.setBackground(Color.BLACK);
            } else {
                cellPanel.setBackground(Color.WHITE);
            }

            if (row == redDroid.getX() && col == redDroid.getY()) {
                cellPanel.setBackground(redDroid.getColor());
            }

            if (row == greenDroid.getX() && col == greenDroid.getY()) {
                cellPanel.setBackground(greenDroid.getColor());
            }

            cellPanel.revalidate();
            cellPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Mavenproject1 frame = new Mavenproject1();
            frame.setVisible(true);
        });
    }
}

class Droid {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int MAP_SIZE = 8;

    private String name;
    private Color color;
    private int x;
    private int y;
    private boolean isMoving;
    private java.util.List<DroidMovedListener> listeners;

    public Droid(String name, Color color) {
        this.name = name;
        this.color = color;
        this.listeners = new java.util.ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setRandomPosition(int mapSize) {
        x = (int) (Math.random() * mapSize);
        y = (int) (Math.random() * mapSize);
    }

    public void startMoving(int targetX, int targetY) {
        if (!isMoving) {
            isMoving = true;
            move(targetX, targetY);
        }
    }

    public void stopMoving() {
        isMoving = false;
    }

    public void addDroidMovedListener(DroidMovedListener listener) {
        listeners.add(listener);
    }

    public void updateVisibility(int otherX, int otherY) {
        updateVisibility(otherX, otherY, 1);
    }

    public void updateVisibility(int otherX, int otherY, int visibility) {
        for (DroidMovedListener listener : listeners) {
            listener.droidMoved(x, y);
        }

        int dx = otherX - x;
        int dy = otherY - y;

        for (int[] direction : DIRECTIONS) {
            int nx = x + direction[0];
            int ny = y + direction[1];

            if (nx >= 0 && nx < MAP_SIZE && ny >= 0 && ny < MAP_SIZE) {
                double distance = Math.sqrt(dx * dx + dy * dy);
                double maxDistance = Math.sqrt(visibility * visibility);

                if (distance <= maxDistance) {
                    x = nx;
                    y = ny;
                    break;
                }
            }
        }
    }

    private void move(int targetX, int targetY) {
        int dx = targetX - x;
        int dy = targetY - y;

        if (dx == 0 && dy == 0) {
            isMoving = false;
            return;
        }

        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);

        int directionX = dx > 0 ? 1 : dx < 0 ? -1 : 0;
        int directionY = dy > 0 ? 1 : dy < 0 ? -1 : 0;

        if (absDx > absDy) {
            x += directionX;
        } else {
            y += directionY;
        }

        SwingUtilities.invokeLater(() -> {
            for (DroidMovedListener listener : listeners) {
                listener.droidMoved(x, y);
            }

            if (x == targetX && y == targetY) {
                isMoving = false;
            } else {
                move(targetX, targetY);
            }
        });
    }
}

interface DroidMovedListener {
    void droidMoved(int x, int y);
}
