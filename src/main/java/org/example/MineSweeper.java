package org.example;
import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
     int rowSize;
     int colSize;
     int mineCount;
     int[][] gameBoard;
     boolean[][] mines;

    public MineSweeper(int rowSize, int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.mineCount = (rowSize * colSize) / 4;
        this.gameBoard = new int[rowSize][colSize];
        this.mines = new boolean[rowSize][colSize];
    }

    public void placeMines() {
        Random random = new Random();
        int count = 0;

        while (count < mineCount) {
            int randRow = random.nextInt(rowSize);
            int randCol = random.nextInt(colSize);

            if (!mines[randRow][randCol]) {
                mines[randRow][randCol] = true;
                count++;
            }
        }
    }

    public void printBoard() {
        System.out.println("Mayın Tarlası Oyunu");
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (gameBoard[i][j] == -1) {
                    System.out.print("* ");
                } else {
                    System.out.print(gameBoard[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean isValidPoint(int row, int col) {
        return row >= 0 && row < rowSize && col >= 0 && col < colSize;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int remainingCells = rowSize * colSize - mineCount;

        while (remainingCells > 0) {
            printBoard();

            System.out.print("Satır seçin (0-" + (rowSize - 1) + "): ");
            int selectedRow = scanner.nextInt();

            System.out.print("Sütun seçin (0-" + (colSize - 1) + "): ");
            int selectedCol = scanner.nextInt();

            if (!isValidPoint(selectedRow, selectedCol)) {
                System.out.println("Geçersiz nokta. Lütfen tekrar seçin.");
                continue;
            }

            if (mines[selectedRow][selectedCol]) {
                System.out.println("Mayına bastınız! Oyunu kaybettiniz.");
                return;
            } else {
                int adjacentMines = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int newRow = selectedRow + i;
                        int newCol = selectedCol + j;

                        if (isValidPoint(newRow, newCol) && mines[newRow][newCol]) {
                            adjacentMines++;
                        }
                    }
                }

                gameBoard[selectedRow][selectedCol] = adjacentMines;
                remainingCells--;
            }
        }

        System.out.println("Tebrikler! Tüm noktaları seçtiniz. Oyunu kazandınız.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Satır sayısını girin: ");
        int rowSize = scanner.nextInt();

        System.out.print("Sütun sayısını girin: ");
        int colSize = scanner.nextInt();

        MineSweeper game = new MineSweeper(rowSize, colSize);
        game.placeMines();
        game.playGame();
    }
}
