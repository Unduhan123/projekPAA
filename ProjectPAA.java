package com.mycompany.mavenproject1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Mavenproject1 extends JFrame {

    int[][] map = new int[][]{
        //map 15x15
        {0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1},
        {1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0},
        {1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0},
        {0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
        {0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1},
        {0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
        {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0},
        {1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
        {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0},
        {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0},
        {0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0},
        {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //0=Rute
};

    //Membuat tombol
    JButton Start;
    JButton Stop;
    JButton AcakPeta;
    JButton AcakDroidMerah;
    JButton AcakDroidHijau;
    JButton TambahDroidMerah;
    JButton KurangiDroidMerah;
    JButton PandanganDroidMerah;
    JButton PandanganDroidHijau;
    JButton Keluar;

    //Acak Map
    boolean repaint = false;

    private int radius = 0;

    public Mavenproject1() {
        //Pengaturan frame
        setTitle(" PROJECT GAME PAA");
        setSize(790, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        //Membuat nama tombol
        Start = new JButton("Start");
        Stop = new JButton("Stop");
        AcakPeta = new JButton("Shuffel Map");
        AcakDroidMerah = new JButton("Shuffel Red Droid");
        AcakDroidHijau = new JButton("Shuffel Green droid");
        TambahDroidMerah = new JButton("Add Red Droid");
        KurangiDroidMerah = new JButton("Reduce Red Droid");
        PandanganDroidMerah = new JButton("Show Red Droid Visibility");
        PandanganDroidHijau = new JButton("Show Green Droid Visibility");
        Keluar = new JButton("Quit");

        //Menambahkan tombol
        add(Start);
        add(Stop);
        add(AcakPeta);
        add(AcakDroidMerah);
        add(AcakDroidHijau);
        add(TambahDroidMerah);
        add(KurangiDroidMerah);
        add(PandanganDroidMerah);
        add(PandanganDroidHijau);
        add(Keluar);

        // Urutan penempatan tombol
        Start.setBounds(530, 75, 200, 30);
        Stop.setBounds(530, 110, 200, 30);
        AcakPeta.setBounds(530, 145, 200, 30);
        AcakDroidMerah.setBounds(530, 180, 200, 30);
        AcakDroidHijau.setBounds(530, 215, 200, 30);
        TambahDroidMerah.setBounds(530, 250, 200, 30);
        KurangiDroidMerah.setBounds(530, 285, 200, 30);
        PandanganDroidMerah.setBounds(530, 320, 200, 30);
        PandanganDroidHijau.setBounds(530, 355, 200, 30);
        Keluar.setBounds(530, 390, 200, 30);




        //Fungsi Tombol
        //  tombol Start
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMoving();
            }
        });

        // tombol Stop
        Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMoving();
            }
        });
        // tombol Shuffel Map
        AcakPeta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x[][] = AcakPeta();
                repaint = true;
                restore(x);
                repaint();
            }
        });

        // tombol Shuffel Red Droid
        AcakDroidMerah.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x[][] = AcakDroidMerah();
                restore(x);
                repaint();
            }
        });

        // tombol Shuffel Green droid
        AcakDroidHijau.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x[][] = AcakDroidHijau();
                restore(x);
                repaint();
            }
        });

        // tombol Add Red Droid
        TambahDroidMerah.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x[][] = TambahDroidMerah();
                restore(x);
                repaint();
            }
        });

        // tombol Reduce Red Droid
        KurangiDroidMerah.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x[][] = KurangiDroidMerah();
                restore(x);
                repaint();
            }
        });

        // tombol Show Red Droid Visibility
        PandanganDroidMerah.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x[][] = PandanganDroidMerah();
                restore(x);
                repaint();
            }
        });

        // tombol Show Green Droid Visibility
        PandanganDroidHijau.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] x = PandanganDroidHijau(radius);
                restore(x);
                repaint();
            }
        });

        // tombol Keluar
        Keluar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  //menutup aplikasi
            }
        });
    }

    //Bagian Peta
    public void restore(int[][] savedMap) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = savedMap[i][j];
            }
        }
    }

    //FUNGSI TOMBOL 
    //Fungsi Shuffel Map
    public int[][] AcakPeta() {
        int[][] arr = new int[15][15];
        Random rnd = new Random();
        int min = 0;
        int high = 1;

        //(1)
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = 1;
            }
        }

        //(0)
        for (int i = 0; i < arr.length; i += 2) {
            for (int j = 0; j < arr[0].length; j += 2) {
                arr[i][j] = 0;
            }
        }

        //(0)
        for (int i = 0; i < arr.length; i += 2) {
            for (int j = 0; j < arr[0].length; j += 2) {
                if (i > 0) {
                    arr[i - 1][j] = arr[i - 1][j] == 0 ? 0 : rnd.nextInt((high - min) + 1) + min;
                }
                if (j > 0) {
                    arr[i][j - 1] = arr[i][j - 1] == 0 ? 0 : rnd.nextInt((high - min) + 1) + min;
                }
                if (i < arr.length - 1) {
                    arr[i + 1][j] = arr[i + 1][j] == 0 ? 0 : rnd.nextInt((high - min) + 1) + min;
                }
                if (j < arr[0].length - 1) {
                    arr[i][j + 1] = arr[i][j + 1] == 0 ? 0 : rnd.nextInt((high - min) + 1) + min;
                }
            }
        }

        //posisi Droid_Merah
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 2) {
                    arr[i][j] = 2;
                }
            }
        }

        //Posisi droid_Hijau
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 3) {
                    arr[i][j] = 3;
                }
            }
        }
        return arr;
    }

    //Fungsi Shuffel Red Droid
    public int[][] AcakDroidMerah() {
        int[][] arr = new int[map.length][map.length];
        Random rand = new Random();
        int row, col;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                arr[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 2) {
                    row = i;
                    col = j;
                    arr[row][col] = 0; // hapus droid_merah 
                }
            }
        }

        //acak posisi droid_merah
        while (true) {
            row = rand.nextInt(map.length);
            col = rand.nextInt(map.length);

            if (arr[row][col] == 0) {
                arr[row][col] = 2;
                break;
            }
        }

        return arr;
    }

    //Fungsi Shuffel Green droid
    public int[][] AcakDroidHijau() {
        int[][] arr = new int[map.length][map.length];
        Random rand = new Random();
        int row = 0, col = 0;


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                arr[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 3) {
                    row = i;
                    col = j;
                    arr[row][col] = 0;
                }
            }
        }

        // random posisi baru untuk droid_hijau
        while (true) {
            int m = rand.nextInt(map.length);
            int n = rand.nextInt(map.length);

            if (arr[m][n] == 0 && (m != row || n != col)) {
                arr[m][n] = 3;
                break;
            }
        }

        return arr;
    }

    //Fungsi Add Red Droid
    public int[][] TambahDroidMerah() {
        int[][] arr = map.clone(); //
        Random rnd = new Random();

        // Posisi droid_merah yang baru
        int row = rnd.nextInt(arr.length);
        int col = rnd.nextInt(arr[0].length);

        while (arr[row][col] != 0) {
            row = rnd.nextInt(arr.length);
            col = rnd.nextInt(arr[0].length);
        }

       
        arr[row][col] = 2;

        return arr;
    }

    //Fungsi kurangi Droid Merah
    private final int MIN_DROID_MERAH = 1;

    public int[][] KurangiDroidMerah() {
        int[][] arr = map.clone(); 
        int countDroidMerah = 0;

        
        for (int[] row : arr) {
            for (int val : row) {
                if (val == 2) {
                    countDroidMerah++;
                }
            }
        }


        if (countDroidMerah > MIN_DROID_MERAH) {
            Random rnd = new Random();


            while (true) {
                int row = rnd.nextInt(arr.length);
                int col = rnd.nextInt(arr[0].length);

                if (arr[row][col] == 2) {
                    arr[row][col] = 0;
                    break;
                }
            }
        }

        return arr;
    }

    // Fungsi Start & Stop
    private boolean stopMoving = false;
    private boolean stopGreen = false;

    // Fungsi untuk menghentikan gerakan
    private void stopMoving() {
        stopMoving = true;
        stopGreen = true;
    }

    // Fungsi untuk memulai gerakan
    private void startMoving() {
        stopMoving = false;
        stopGreen = false;

        Thread moveThread = new Thread(() -> {
            // Temukan posisi awal droid_merah
            int startXRed = -1;
            int startYRed = -1;

            // Temukan posisi awal droid_hijau
            int startXGreen = -1;
            int startYGreen = -1;

            // Cari posisi awal droid_merah & hijau
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == 2) {
                        startXRed = i;
                        startYRed = j;
                    } else if (map[i][j] == 3) {
                        startXGreen = i;
                        startYGreen = j;
                    }

                    if (startXRed != -1 && startXGreen != -1) {
                        break;
                    }
                }

                if (startXRed != -1 && startXGreen != -1) {
                    break;
                }
            }


            if (startXRed == -1 || startYRed == -1 || startXGreen == -1 || startYGreen == -1) {
                System.out.println("Posisi awal droid tidak ditemukan");
                return;
            }


            boolean[][] visited = new boolean[map.length][map[0].length];
            visited[startXRed][startYRed] = true;
            visited[startXGreen][startYGreen] = true;
            dfs(startXRed, startYRed, startXGreen, startYGreen, visited);
        });
        moveThread.start();
    }

    private void dfs(int xRed, int yRed, int xGreen, int yGreen, boolean[][] visited) {
        // untuk arah pergerakan droid_merah
        int[] directionsRed = {0, 1, 2, 3};
        shuffleArray(directionsRed);

        // untuk arah pergerakan droid_hijau
        int[] directionsGreen = {0, 1, 2, 3};
        shuffleArray(directionsGreen);

        if (areOnSameStraightPath(xRed, yRed, xGreen, yGreen)) {
            
            int initialXRed = xRed;
            int initialYRed = yRed;

            
            while (!areAdjacent(xRed, yRed, xGreen, yGreen)) {
                
                if (stopMoving || stopGreen) {
                    return;
                }

                
                int direction = determineDirection(xRed, yRed, xGreen, yGreen);

                //Gerakan Droid_Merah
                if (direction == 0) { // Gerakan ke atas
                    xRed--;
                } else if (direction == 1) { // Gerakan ke kanan
                    yRed++;
                } else if (direction == 2) { // Gerakan ke bawah
                    xRed++;
                } else if (direction == 3) { // Gerakan ke kiri
                    yRed--;
                }

                
                if (isValidMove(xRed, yRed, visited)) {
                    visited[xRed][yRed] = true;

              
                    map[initialXRed][initialYRed] = 0;

                   
                    map[xRed][yRed] = 2;
                    repaint();

                    // jeda langkah berikutnya
                    try {
                        Thread.sleep(400); 
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    
                    initialXRed = xRed;
                    initialYRed = yRed;
                } else {
                    
                    xRed = initialXRed;
                    yRed = initialYRed;
                    break;
                }
            }

            // Droid merah & hijau menempel, hentikan pergerakan
            if (areAdjacent(xRed, yRed, xGreen, yGreen)) {
                stopMoving();
                System.out.println("GAME OVER!");
                return;
            }
        }

        // Pergerakan droid_merah
        for (int dirRed : directionsRed) {
           
            if (stopMoving) {
                return;
            }
            int newXRed = xRed;
            int newYRed = yRed;

            
            if (dirRed == 0) { // Gerakan ke atas
                newXRed--;
            } else if (dirRed == 1) { // Gerakan ke kanan
                newYRed++;
            } else if (dirRed == 2) { // Gerakan ke bawah
                newXRed++;
            } else if (dirRed == 3) { // Gerakan ke kiri
                newYRed--;
            }

            
            if (isValidMove(newXRed, newYRed, visited)) {
                visited[newXRed][newYRed] = true;

                
                map[xRed][yRed] = 0;
                map[newXRed][newYRed] = 2;
                repaint();

                
                try {
                    Thread.sleep(400); 
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                // Pergerakan droid_hijau
                for (int dirGreen : directionsGreen) {
           
                    if (stopMoving || stopGreen) {
                        return;
                    }
                    int newXGreen = xGreen;
                    int newYGreen = yGreen;

                    // Gerakan Droid_Hijau
                    if (dirGreen == 0) { // Gerakan ke atas
                        newXGreen--;
                    } else if (dirGreen == 1) { // Gerakan ke kanan
                        newYGreen++;
                    } else if (dirGreen == 2) { // Gerakan ke bawah
                        newXGreen++;
                    } else if (dirGreen == 3) { // Gerakan ke kiri
                        newYGreen--;
                    }

                    
                    if (isValidMove(newXGreen, newYGreen, visited)) {
                        visited[newXGreen][newYGreen] = true;

                  
                        map[xGreen][yGreen] = 0;
                        map[newXGreen][newYGreen] = 3;
                        repaint();

                    
                        dfs(newXRed, newYRed, newXGreen, newYGreen, visited);

                        
                        if (stopMoving || stopGreen) {
                            return;
                        }

                     
                        visited[newXGreen][newYGreen] = false;

                      
                        map[xGreen][yGreen] = 3;
                        map[newXGreen][newYGreen] = 0;
                        repaint();

                     
                        try {
                            Thread.sleep(400); // 
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    } else if (newXGreen == xRed && newYGreen == yRed) {
                        // 
                        continue;
                    }
                }

               
                visited[newXRed][newYRed] = false;

          
                map[xRed][yRed] = 2;
                map[newXRed][newYRed] = 0;
                repaint();

             
                try {
                    Thread.sleep(400); 
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * @param xRed
     * @param yRed
     * @param xGreen
     * @param yGreen
     * @return
     */
    private boolean areOnSameStraightPath(int xRed, int yRed, int xGreen, int yGreen) {
        if (xRed == xGreen) { 
            int minY = Math.min(yRed, yGreen);
            int maxY = Math.max(yRed, yGreen);

        
            for (int y = minY + 1; y < maxY; y++) {
                if (map[xRed][y] == 1) {
                    return false; 
                }
            }
            return true;
        } else if (yRed == yGreen) { // 
            int minX = Math.min(xRed, xGreen);
            int maxX = Math.max(xRed, xGreen);

            
            for (int x = minX + 1; x < maxX; x++) {
                if (map[x][yRed] == 1) {
                    return false; 
                }
            }
            return true;
        }

        return false; // 
    }

   
    private boolean areAdjacent(int xRed, int yRed, int xGreen, int yGreen) {
        return (Math.abs(xRed - xGreen) == 1 && yRed == yGreen) || (xRed == xGreen && Math.abs(yRed - yGreen) == 1);
    }

    // Fungsi pergerakan droid_merah menuju droid_hijau
    private int determineDirection(int xRed, int yRed, int xGreen, int yGreen) {
        if (xRed < xGreen) { 
            return 2; // Gerakan ke bawah
        } else if (xRed > xGreen) { 
            return 0; // Gerakan ke atas
        } else if (yRed < yGreen) { 
            return 1; // Gerakan ke kanan
        } else { 
            return 3; // Gerakan ke kiri
        }
    }

    private void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    // langkah pergerakan ke koordinat (x, y) dalam map
    private boolean isValidMove(int x, int y, boolean[][] visited) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length && !visited[x][y] && map[x][y] != 1;
    }

    //Fungsi Show Red Droid Visibility
    public int[][] PandanganDroidMerah() {
        int[][] arr = map.clone(); // clone map agar tidak merubah map asli

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                if (arr[row][col] != 2) {
                    arr[row][col] = (arr[row][col] == 1) ? 1 : 0; // Set rute atau tembok sesuai dengan map asli
                }
            }
        }

        return arr;
    }

    // Fungsi Show Green Droid Visibility
    public int[][] PandanganDroidHijau(int radius) {
        this.radius = radius; 
        int[][] arr = map.clone(); // clone map 
        int droidHijauRow = -1; // posisi baris droid_hijau
        int droidHijauCol = -1; // posisi kolom droid_hijau

        // posisi droid_hijau dalam map
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                if (arr[row][col] == 3) {
                    droidHijauRow = row;
                    droidHijauCol = col;
                    break;
                }
            }
            if (droidHijauRow != -1) {
                break;
            }
        }

        int startX = Math.max(0, droidHijauCol - radius);
        int endX = Math.min(arr[0].length - 1, droidHijauCol + radius);
        int startY = Math.max(0, droidHijauRow - radius);
        int endY = Math.min(arr.length - 1, droidHijauRow + radius);

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                if (arr[row][col] != 2 && arr[row][col] != 3) {
                    if (col < startX || col > endX || row < startY || row > endY) {
                        arr[row][col] = 4;
                    }
                }
            }
        }

        return arr;
    }

    @Override
    public void paint(Graphics g) {
        g.translate(30, 50);

        //Map
        if (repaint == true) {
            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map.length; col++) {
                    Color color;

                    switch (map[row][col]) {
                        case 1:
                            color = Color.black;
                            break;
                        case 2:
                            color = Color.red;
                            break;
                        case 3:
                            color = Color.green;
                            break;
                        case 4:
                            color = Color.white;
                            break;
                        default:
                            color = Color.blue;
                    }

                    g.setColor(color);
                    g.fillRect(30 * col, 30 * row, 30, 30);
                    g.setColor(Color.black);
                    g.drawRect(30 * col, 30 * row, 30, 30);

                }
            }
        }

        if (repaint == false) {
            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map.length; col++) {
                    Color color;

                    switch (map[row][col]) {
                        case 1:
                            color = Color.black;
                            break;
                        case 2:
                            color = Color.red;
                            break;
                        case 3:
                            color = Color.green;
                            break;
                        case 4:
                            color = Color.white;
                            break;
                        default:
                            color = Color.blue;
                    }

                    g.setColor(color);
                    g.fillRect(30 * col, 30 * row, 30, 30);
                    g.setColor(Color.black);
                    g.drawRect(30 * col, 30 * row, 30, 30);
                }
            }
        }
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
            new Mavenproject1();
            }
        });
    }
}
