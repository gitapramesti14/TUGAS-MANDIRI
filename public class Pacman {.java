public class Pacman {
    package Pacman;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import javax.swing.ImageIcon;
    import javax.swing.JPanel;
    import javax.swing.Timer;
        public class Model extends JPanel implements ActionListener {
            private Dimension d;
            private final Font smallFont = new Font("Arial", Font.BOLD, 14);
            private boolean inGame = false;
            private boolean dying = false;
            private final int BLOCK_SIZE = 24;
            private final int N_BLOCKS = 15;
            private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
            private final int MAX_GHOSTS = 12;
            private final int PACMAN_SPEED = 6;
            private int N_GHOSTS = 6;
            private int lives, score;
            private int[] dx, dy;
            private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;
            private Image heart, ghost;
            private Image up, down, left, right;
            private int pacman_x, pacman_y, pacmand_x, pacmand_y;
            private int req_dx, req_dy;
            private final short levelData[] = {
                    19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
                    17, 16, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16, 16, 16, 20,
                    25, 24, 24, 24, 28, 0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
                    0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
                    19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20,
                    17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
                    17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
                    17, 16, 16, 16, 24, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
                    17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 18, 18, 18, 18, 20,
                    17, 24, 24, 28, 0, 25, 24, 24, 16, 16, 16, 16, 16, 16, 20,
                    21, 0,  0,  0,  0,  0,  0,   0, 17, 16, 16, 16, 16, 16, 20,
                    17, 18, 18, 22, 0, 19, 18, 18, 16, 16, 16, 16, 16, 16, 20,
                    17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
                    17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
                    25, 24, 24, 24, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
            };
            private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
            private final int maxSpeed = 6;
            private int currentSpeed = 3;
            private short[] screenData;
            private Timer timer;
            public Model() {
                loadImages();
                initVariables();
                addKeyListener(new TAdapter());
                setFocusable(true);
                initGame();
            }
            private void loadImages() {
                down = new ImageIcon("C:\\Users\\KEL KALKUL\\IdeaProjects\\Pacman\\images\\down.gif").getImage();
                up = new ImageIcon("C:\\Users\\KEL KALKUL\\IdeaProjects\\Pacman\\images\\up.gif").getImage();
                left = new ImageIcon("C:\\Users\\KEL KALKUL\\IdeaProjects\\Pacman\\images\\left.gif").getImage();
                right = new ImageIcon("C:\\Users\\KEL KALKUL\\IdeaProjects\\Pacman\\images\\right.gif").getImage();
                ghost = new ImageIcon("C:\\Users\\KEL KALKUL\\IdeaProjects\\Pacman\\images\\ghost.gif").getImage();
                heart = new ImageIcon("C:\\Users\\KEL KALKUL\\IdeaProjects\\Pacman\\images\\heart.png").getImage();
            }
            private void initVariables() {
                screenData = new short[N_BLOCKS * N_BLOCKS];
                d = new Dimension(400, 400);
                ghost_x = new int[MAX_GHOSTS];
                ghost_dx = new int[MAX_GHOSTS];
                ghost_y = new int[MAX_GHOSTS];
                ghost_dy = new int[MAX_GHOSTS];
                ghostSpeed = new int[MAX_GHOSTS];
                dx = new int[4];
                dy = new int[4];
                timer = new Timer(40, this);
                timer.start();
            }
            private void playGame(Graphics2D g2d) {
                if (dying) {
                    death();
                } else {
                    movePacman();
                    drawPacman(g2d);
                    moveGhosts(g2d);
                    checkMaze();
                }
            }
            private void showIntroScreen(Graphics2D g2d) {
                String start = "Press SPACE to start";
                g2d.setColor(Color.yellow);
                g2d.drawString(start, (SCREEN_SIZE)/4, 150);
            }
            private void drawScore(Graphics2D g) {
                g.setFont(smallFont);
    
                ;
    
                g.setColor(new Color(5, 181, 79));
                String s = "Score: " + score;
                g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);
                for (int i = 0; i < lives; i++) {
                    g.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
                }
            }
            private void checkMaze() {
                int i = 0;
                boolean finished = true;
                while (i < N_BLOCKS * N_BLOCKS && finished) {
                    if ((screenData[i]) != 0) {
                        finished = false;
                    }
                    i++;
                }
                if (finished) {
                    score += 50;
                    if (N_GHOSTS < MAX_GHOSTS) {
                        N_GHOSTS++;
                    }
                    if (currentSpeed < maxSpeed) {
                        currentSpeed++;
                    }
                    initLevel();
                }
            }
