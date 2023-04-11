import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int DOT_SIZE = 10;
    private static final int RAND_POS = 49;
    private static final int DELAY = 100;

    private ArrayList<Point> snake;
    private Point food;
    private int score = 0;
    private boolean inGame = true;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    public SnakeGame() {
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        
        initGame();
    }
    
    private void initGame() {
        snake = new ArrayList<Point>();
        snake.add(new Point(50, 50));
        snake.add(new Point(40, 50));
        snake.add(new Point(30, 50));
        
        createFood();
        
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }
    
    private void createFood() {
        Random rand = new Random();
        int x = rand.nextInt(RAND_POS) * DOT_SIZE;
        int y = rand.nextInt(RAND_POS) * DOT_SIZE;
        food = new Point(x, y);
    }
    
    private void move() {
        Point head = snake.get(0);
        int x = (int) head.getX();
        int y = (int) head.getY();
        
        if (leftDirection) {
            x -= DOT_SIZE;
        } else if (rightDirection) {
            x += DOT_SIZE;
        } else if (upDirection) {
            y -= DOT_SIZE;
        } else if (downDirection) {
            y += DOT_SIZE;
        }
        
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            inGame = false;
        }
        
        Point newHead = new Point(x, y);
        
        if (newHead.equals(food)) {
            score++;
            createFood();
        } else {
            snake.remove(snake.size() - 1);
        }
        
        if (snake.contains(newHead)) {
            inGame = false;
        }
        
        snake.add(0, newHead);
    }
    
    private void doDrawing(Graphics g) {
        if (inGame) {
            g.setColor(Color.RED);
            g.fillOval(food.x, food.y, DOT_SIZE, DOT_SIZE);
            
            for (Point p : snake) {
                g.setColor(Color.GREEN);
                g.fillOval(p.x, p.y, DOT_SIZE, DOT_SIZE);
            }
            
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 20);
            
            g.setColor(Color.WHITE);
            g.drawString("Press Q to quit", WIDTH - 100, 20);
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Game Over!", WIDTH / 2 - 30, HEIGHT / 2);
            g.drawString("Score: " + score, WIDTH / 2 - 30, HEIGHT / 2 + 20);
        }
    }
    
    @Override
