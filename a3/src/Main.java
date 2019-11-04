import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.application.Application;
import javax.swing.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import java.awt.Graphics;
import javafx.event.*;
import javafx.animation.*;
import javafx.util.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class Main extends Application {
    static int width = 48;
    static int height = 32;
    static int cellSize = 25;
    static int level = 1;
    static int currScore = 0;
    static int highScore = 0;
    static boolean isPaused = false;

    static int currentTime = 30;

    static List<Cell> snake = new ArrayList<>(); // array of points snake is on
    static List<Cell> apples = new ArrayList<>(); // array of points apples are on
    static Dir direction = Dir.right;
    static boolean gameOver = false;
    static Timeline timeline;


    public static class Cell {
        double x;
        double y;
        public Cell(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public enum Dir {
        left, right, up, down
    }

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void init() throws Exception {
        //super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //super.start();
        try {
            // scene1
            VBox rootPane1 = new VBox(5);
            Label prompt_label = new Label("PRESS SPACE TO START\n\n");
            Label name_label = new Label("Nathan Hung");
            Label id_label = new Label("nhung");
            StringBuilder msg = new StringBuilder();
            //msg.append("\n");
            msg.append("\n\nHOW TO PLAY:\n");
            msg.append("\nThe game screen displays a snake (chain of blocks) always in motion on the screen.\n");
            msg.append("\nThe direction of the snake can be controlled by the arrows keys (left/right/up/down), which sets the direction to what arrow key was pressed.\n");
            msg.append("\nThe goal of the game is for the snake to eat the apples (red circles).\n");
            msg.append("\nThe snake can die by eating itself (when it collides with itself) or by hitting the bounds or obstacles.\n");
            msg.append("\nA timer ticks down on each level. When the timer runs out, the next level is loaded, with increasingly more fruit.\n");
            msg.append("\nThe snake can die by eating itself (when it collides with itself) or by hitting the bounds or obstacles.\n");
            msg.append("\nThe final level runs until the snake dies.\n");
            Label description_label = new Label();
            description_label.setText(msg.toString());
            rootPane1.getChildren().addAll(prompt_label, name_label, id_label, description_label);
            rootPane1.setAlignment(Pos.CENTER);
            Scene scene1 = new Scene(rootPane1, 1200, 800);


            // SCENE 2 (the gameplay screen)
            VBox root = new VBox();
            Canvas canvas = new Canvas(width * cellSize, height * cellSize);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            root.getChildren().add(canvas);

            // timer for the refreshing of the animation
            timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    handleAnimation(gc);
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);


            // timer for level clock, every second, decrement timer
            Timeline timelineClock = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    if (level != 3) {
                        currentTime -= 1;
                    }
                }
            }));
            timelineClock.setCycleCount(Timeline.INDEFINITE);

            Scene scene2 = new Scene(root, 1200, 800);

            // EVENT HANDLERS
            scene1.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if (key.getCode() == KeyCode.SPACE) {
                    //System.out.println("You pressed SPACE");
                    level = 1;
                    initLevel(level);
                    direction = Dir.right;
                    gameOver = false;
                    timeline.play();
                    timelineClock.play();
                    // clear snake if any still exists
                    snake.clear();
                    // initial snake (length = 1)
                    snake.add(new Cell(width / 2, height / 2));
                    stage.setScene(scene2);
                }
                if (key.getCode() == KeyCode.DIGIT1) {
                    // clear any previous game
                    timeline.stop();
                    timelineClock.stop();
                    currScore = 0;
                    currentTime = 0;
                    gameOver = false;
                    // start new game from level 1
                    level = 1;
                    initLevel(level);
                    direction = Dir.right;
                    timeline.play();
                    timelineClock.play();
                    // clear snake if any still exists
                    snake.clear();
                    // initial snake (length = 1)
                    snake.add(new Cell(width / 2, height / 2));
                    stage.setScene(scene2);
                }
                if (key.getCode() == KeyCode.DIGIT2) {
                    // clear any previous game
                    timeline.stop();
                    timelineClock.stop();
                    currScore = 0;
                    currentTime = 0;
                    gameOver = false;
                    // start new game from level 1
                    level = 2;
                    initLevel(level);
                    direction = Dir.right;
                    timeline.play();
                    timelineClock.play();
                    // clear snake if any still exists
                    snake.clear();
                    // initial snake (length = 1)
                    snake.add(new Cell(width / 2, height / 2));
                    stage.setScene(scene2);
                }
                if (key.getCode() == KeyCode.DIGIT3) {
                    // clear any previous game
                    timeline.stop();
                    timelineClock.stop();
                    currScore = 0;
                    currentTime = 0;
                    gameOver = false;
                    // start new game from level 1
                    level = 3;
                    initLevel(level);
                    direction = Dir.right;
                    timeline.play();
                    timelineClock.play();
                    // clear snake if any still exists
                    snake.clear();
                    // initial snake (length = 1)
                    snake.add(new Cell(width / 2, height / 2));
                    stage.setScene(scene2);
                }
            });

            scene2.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (key.getCode() == KeyCode.UP) {
                    direction = Dir.up;
                }
                if (key.getCode() == KeyCode.LEFT) {
                    direction = Dir.left;
                }
                if (key.getCode() == KeyCode.DOWN) {
                    direction = Dir.down;
                }
                if (key.getCode() == KeyCode.RIGHT) {
                    direction = Dir.right;
                }
                if (key.getCode() == KeyCode.DIGIT1) {
                    // clear any previous game
                    timeline.stop();
                    timelineClock.stop();
                    currScore = 0;
                    currentTime = 0;
                    gameOver = false;
                    // start new game from level 1
                    level = 1;
                    initLevel(level);
                    direction = Dir.right;
                    timeline.play();
                    timelineClock.play();
                    // clear snake if any still exists
                    snake.clear();
                    // initial snake (length = 1)
                    snake.add(new Cell(width / 2, height / 2));
                }
                if (key.getCode() == KeyCode.DIGIT2) {
                    // clear any previous game
                    timeline.stop();
                    timelineClock.stop();
                    currScore = 0;
                    currentTime = 0;
                    gameOver = false;
                    // start new game from level 1
                    level = 2;
                    initLevel(level);
                    direction = Dir.right;
                    timeline.play();
                    timelineClock.play();
                    // clear snake if any still exists
                    snake.clear();
                    // initial snake (length = 1)
                    snake.add(new Cell(width / 2, height / 2));
                }
                if (key.getCode() == KeyCode.DIGIT3) {
                    // clear any previous game
                    timeline.stop();
                    timelineClock.stop();
                    currScore = 0;
                    currentTime = 0;
                    gameOver = false;
                    // start new game from level 1
                    level = 3;
                    initLevel(level);
                    direction = Dir.right;
                    timeline.play();
                    timelineClock.play();
                    // clear snake if any still exists
                    snake.clear();
                    // initial snake (length = 1)
                    snake.add(new Cell(width / 2, height / 2));
                }
                if (key.getCode() == KeyCode.P) {
                    if (isPaused) {
                        timeline.play();
                        timelineClock.play();
                    } else {
                        timeline.pause();
                        timelineClock.pause();
                    }
                    isPaused = !isPaused;
                }
                if (key.getCode() == KeyCode.R) {
                    timeline.stop();
                    timelineClock.stop();
                    currScore = 0;
                    currentTime = 0;
                    gameOver = false;
                    stage.setScene(scene1);
                }
                if (key.getCode() == KeyCode.Q) {
                    gameOver = true;
                    //timeline.stop();
                    //timelineClock.stop();
                    //currScore = 0;
                    //currentTime = 0;
                }
            });


            stage.setScene(scene1);
            stage.setTitle("Snake");
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {

        }
    }

    public static void handleAnimation(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("", 75));
            gc.fillText("GAME OVER", 425, 425);

            gc.setFill(Color.WHITE);
            gc.setFont(new Font("", 50));
            gc.fillText("Your Score: " + currScore, 475, 500);

            gc.setFill(Color.WHITE);
            gc.setFont(new Font("", 50));
            gc.fillText("High Score: " + highScore, 475, 575);

            return;
        }

        if (currentTime == 0 && level != 3) {
            level++;
            initLevel(level);
        }

        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction) {
            case up:
                snake.get(0).y -= 1;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
                break;
            case down:
                snake.get(0).y += 1;
                if (snake.get(0).y > height) {
                    gameOver = true;
                }
                break;
            case left:
                snake.get(0).x -= 1;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
                break;
            case right:
                snake.get(0).x += 1;
                if (snake.get(0).x > width) {
                    gameOver = true;
                }
                break;
        }

        // eat food (loop through fruit array)
        boolean hitApple = false;
        double appleX = 0;
        double appleY = 0;
        for (Cell c : apples) {
            if (c.x == snake.get(0).x && c.y == snake.get(0).y) {
                snake.add(new Cell(-1, -1));
                hitApple = true;
                appleX = c.x;
                appleY = c.y;
            }
        }
        if (hitApple == true) {
            replaceApple(appleX, appleY);
            updateScore();
        }

        // snake landed on itself
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
            }
        }

        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, width * cellSize, height * cellSize);

        // score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + currScore, 10, 30);

        // high score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("High Score: " + highScore, 210, 30);

        // Current Level
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Level: " + level, 610, 30);

        // timer:
        if (level != 3) {
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("", 30));
            gc.fillText("Timer: " + currentTime, 1000, 30);
        }

        for (Cell c : apples) {
            gc.setFill(Color.RED);
            gc.fillOval(c.x * cellSize, c.y * cellSize, cellSize, cellSize);
        }

        // snake
        for (Cell c : snake) {
            gc.setFill(Color.BLUE);
            gc.fillRect(c.x * cellSize, c.y * cellSize, cellSize - 1, cellSize - 1);
            gc.setFill(Color.BLUE);
            gc.fillRect(c.x * cellSize, c.y * cellSize, cellSize - 2, cellSize - 2);

        }
    }

    public static void updateScore() {
        currScore++;
        if (currScore > highScore) {
            highScore = currScore;
        }
    }

    // apple was hit, now make a new one
    public static void replaceApple(double x, double y) {
        for (int i = 0; i < apples.size(); i++) {
            if (apples.get(i).x == x && apples.get(i).y == y) {
                apples.remove(i);
            }
        }
        Random random = new Random();
        start: while (true) {
            int newAppleX = random.nextInt(width);
            int newAppleY = random.nextInt(height);

            for (Cell c : snake) {
                // loop through fruits array
                if (c.x == newAppleX && c.y == newAppleY) {
                    continue start;
                }
            }

            for (Cell c : apples) {
                if (c.x == newAppleX && c.y == newAppleY) {
                    continue start;
                }
            }

            apples.add(new Cell(newAppleX, newAppleY));
            break;

        }
    }

    // initialize fruits on the board
    // only called when a new level is started
    public static void initLevel(int level) {
        // set speed
        if (level == 1) {
            timeline.setRate(1);
        } else if (level == 2) {
            timeline.setRate(1.7);
        } else if (level == 3) {
            timeline.setRate(2.5);
        }
        // set fruits
        apples.clear();
        if (level == 1) {
            for (int i = 0; i < 5; i++) {
                apples.add(new Cell(10 + i, 10 + i));
            }
        } else if (level == 2) {
            for (int i = 0; i < 10; i++) {
                apples.add(new Cell(10 + i, 10 + i));
            }
        } else if (level == 3) {
            for (int i = 0; i < 15; i++) {
                apples.add(new Cell(10 + i, 10 + i));
            }
        }

        // reset timer to 30 seconds
        if (level != 3) {
            currentTime = 30;
        }

    }

    @Override
    public void stop() throws Exception {
        //super.stop();
    }
}

