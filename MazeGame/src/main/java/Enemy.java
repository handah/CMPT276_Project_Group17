import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Enemy extends MovingCharacter{
    public Enemy(int x, int y) throws IOException
    {
        super(x, y);
        Image image = ImageIO.read(new File("src/ghost.png"));
        this.label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_FAST)));
    }


    private String getBestMove(Player player, BoardState boardState) {
        String[] moves = {"w", "e", "n", "s"};
        int[] squaredDistances = new int[4];

        int playerX = player.getX();
        int playerY = player.getY();
        int enemyX = this.getX();
        int enemyY = this.getY();

        int bestMoveDistance = Integer.MAX_VALUE;
        int bestMoveIndex = -1;

        // squared Distances from each cell to player
        squaredDistances[0] = boardState.calculateSquaredDistance(enemyX - 1, enemyY, playerX, playerY);
        squaredDistances[1] = boardState.calculateSquaredDistance(enemyX + 1, enemyY, playerX, playerY);
        squaredDistances[2] = boardState.calculateSquaredDistance(enemyX, enemyY - 1, playerX, playerY);
        squaredDistances[3] = boardState.calculateSquaredDistance(enemyX, enemyY + 1, playerX, playerY);

        for (int i = 0; i < 4; i++) {
            if (bestMoveDistance > squaredDistances[i]) {
                bestMoveDistance = squaredDistances[i];
                bestMoveIndex = i;
            }
        }

       return moves[bestMoveIndex];
    }

    public void move(Board board, BoardState boardState, Player player) {
        String bestMove = getBestMove(player, boardState);

        if (bestMove.equals("w")) {
            this.moveWest(board, boardState);
        } else if (bestMove.equals("e")) {
            this.moveEast(board, boardState);
        } else if (bestMove.equals("n")) {
            this.moveNorth(board, boardState);
        } else if (bestMove.equals("s")) {
            this.moveSouth(board, boardState);
        }
    }
}
