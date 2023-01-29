import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;

public class SparklesAnimator {
    private static final int NUM_SPARKLES = 100;
    private static final int SPARKLE_SIZE = 5;
    private static final int WIDTH = PacmanGame.WIDTH;
    private static final int HEIGHT = PacmanGame.HEIGHT;

    private Point[] sparkles;

    public SparklesAnimator() {
        sparkles = new Point[NUM_SPARKLES];
        for (int i = 0; i < NUM_SPARKLES; i++) {
            sparkles[i] = new Point((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
        }
    }

    public void update() {
        for (int i = 0; i < NUM_SPARKLES; i++) {
            sparkles[i].x += (int) (Math.random() * 3) - 1;
            sparkles[i].y += (int) (Math.random() * 3) - 1;

            if (sparkles[i].x < 0) {
                sparkles[i].x = WIDTH;
            }
            if (sparkles[i].x > WIDTH) {
                sparkles[i].x = 0;
            }
            if (sparkles[i].y < 0) {
                sparkles[i].y = HEIGHT;
            }
            if (sparkles[i].y > HEIGHT) {
                sparkles[i].y = 0;
            }
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < NUM_SPARKLES; i++) {
            g.setColor(randomColor());
            g.fillOval(sparkles[i].x, sparkles[i].y, SPARKLE_SIZE, SPARKLE_SIZE);
        }
    }

    public Color randomColor() {
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        return new Color(r, g, b);
    }
}
