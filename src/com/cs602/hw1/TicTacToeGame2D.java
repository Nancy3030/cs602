package com.cs602.hw1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

class TicTacToeGame2D {

    public static Mark whoFirst() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter words starts with me for you play first, or enter any other words for pc does:");
        String input = scan.nextLine();
        Mark firstPlayer;
        if (!input.startsWith("me")) {
            firstPlayer = Mark.X;
            System.out.println("PC play first!\n");
        } else {
            firstPlayer = Mark.O;
            System.out.println("You play first!\n");
        }
        return firstPlayer;
    }

    public static void main(String[] args) {
        Game game = new Game();

        Mark mark = whoFirst();
        if (mark == Mark.O) {
            game.userPlay();
        } else {
            game.pcPlay();
        }
    }
}

class Game {

    private Board board;

    public Game() {
        this.board = new Board();
    }

    public void userPlay() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your play position index as xy:");
        String input = scan.nextLine();

        if (input.length() != 2) {
            System.out.println("Invalid input, please enter again:");
            userPlay();
        } else {
            int a = Integer.parseInt(input.substring(0, 1));
            int b = Integer.parseInt(input.substring(1, 2));


            if (a > 2 | b > 2 ) {
                System.out.println("index outbound, please enter again:");
                userPlay();
            }

            Cell cell = board.getCell(a, b);
            if (cell.getMark() != Mark.EMPTY) { // determine if this is not an empty cell
                System.out.println("Occupied here, please put other place:");
                userPlay();
            } else { // this is an empty cell
                cell.setMark(Mark.O);
                // update lineMarkCount
                board.incLineMarkCount(cell, Mark.O);
                System.out.println("You have played: \n");
                board.printplane();
                // check win
                if (board.isWin(Mark.O)) {
                    System.out.println("You !!wins!!, congrats!!!");
                }
                // check board is full
                else if (board.isFull()) {
                    System.out.println("Not bad, it's a draw!");
                }
                // let pc move
                else {
                    pcPlay();
                }
            }
        }
    }

    public void pcPlay() {
        // native rule: if there is 2 X in a Line, then put the 3th X and win;
        // if there is 2 O in a line, then put X there so not lose;
        // Otherwise, random put.

        Cell winCell = board.findNextWinCell(Mark.X);
        Cell loseCell = board.findNextWinCell(Mark.O);

        if (winCell != null) {
            winCell.setMark(Mark.X);
            board.incLineMarkCount(winCell, Mark.X);
            board.printplane();
            System.out.println("PC wins. Don't be upset, just try again!");
        } else {
            if (loseCell != null) {
                loseCell.setMark(Mark.X);
                board.incLineMarkCount(loseCell, Mark.X);
            } else {
                board.randomPick(Mark.X);
            }

            System.out.println("PC have played: \n");
            board.printplane();

            if (board.isFull()) {
                System.out.println("Not bad, it's a draw!");
            } else {
                userPlay();
            }
        }
    }
}


enum Mark {X, O, EMPTY} //Represents the value of a spot on the board. user O; pc X.

class Cell {
    private Mark mark = Mark.EMPTY;
    private int[] index = new int[2];

    public Cell(int x, int y) {
        index[0] = x;
        index[1] = y;

    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public int[] getIndex() {
        return index;
    }
}

class Line {
    private Cell[] cells = new Cell[3];

    public Line(Cell[] cells) {
        this.cells = cells;
    }

    public Cell[] getCells() {
        return cells;
    }
}

class Board {

    private HashMap<Cell, HashSet<Line>> cellLineMap = new HashMap<>();
    private HashMap<Line, CountPair> lineMarkCount = new HashMap<>();
    private Cell[][] plane = new Cell[3][3];

    public Board() {
        // initiate plane
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                plane[i][j] = new Cell(i, j);

            }
        }
        // fill lines
        fillLines();
        // fill cellLineMap
        fillCellLineMap();
    }

    public Cell getCell(int x, int y) {
        return plane[x][y];
    }


    void printplane() {
//
            for (int b = 2; b >= 0; b--) {
                for (int i = 0; i < b; i++) {
                    System.out.print(" ");
                }
//
                System.out.print(b + "  ");
                for (int c = 0; c < 3; c++) {
                    if (plane[b][c].getMark() == Mark.EMPTY) {
                        System.out.print("_ ");
                    } else if (plane[b][c].getMark() == Mark.O) {
                        System.out.print("O ");
                    } else
                        System.out.print("X ");
                }
                System.out.println();
            }
            System.out.println();
//        }
        System.out.println("  0 1 2 \n");
        System.out.println(" - - - - - - - - - - ");

    }

    private void fillCellLineMap() {
        for (Line line : lineMarkCount.keySet()) {
            for (Cell cell : line.getCells()) {
                if (cell != null) {
                    if (!cellLineMap.containsKey(cell)) {
                        cellLineMap.put(cell, new HashSet<>());
                    }
                    cellLineMap.get(cell).add(line);
                }
            }
        }
    }

    private void fillLines() {
        final int[][][] linesIndexes = {
                {{0, 0}, {0, 1}, {0, 2}},//rows
                {{1, 0}, {1, 1}, {1, 2}},
                {{2, 0}, {2, 1}, {2, 2}},
                {{0, 0}, {1, 0}, {2, 0}},//columns
                {{0, 1}, {1, 1}, {2, 1}},
                {{0, 2}, {1, 2}, {2, 2}},
                {{0, 0}, {1, 1}, {2, 2}},//diagonal line
                {{0, 2}, {1, 1}, {2, 0}}
          };


        for (int x = 0; x < linesIndexes.length; x++) {
            Cell[] cells = new Cell[3];
            for (int y = 0; y < 3; y++) {
                int i = linesIndexes[x][y][0];
                int j = linesIndexes[x][y][1];

                cells[y] = plane[i][j];
            }
            Line line = new Line(cells);
            lineMarkCount.put(line, new CountPair()); // fill lineMarkCount
        }
    }


        public void incLineMarkCount(Cell cell, Mark mark) {

            for (Line line : cellLineMap.get(cell)) {
                CountPair countPair = lineMarkCount.get(line);
                countPair.inc(mark);
            }
        }

        public boolean isWin(Mark mark) {
            boolean result = false;
            for (CountPair countPair : lineMarkCount.values()) {
                if (countPair.getCount(mark) == 3) {
                    result = true;
                }
            }
            return result;
        }

        public boolean isFull() {
            boolean result = true;
            for (CountPair countPair : lineMarkCount.values()) {
                if (countPair.getCount(Mark.O) + countPair.getCount(Mark.X) < 3) {
                    result = false;
                }
            }
            return result;
        }

        public Cell findNextWinCell(Mark mark) {
            Cell target = null;
            for (Line line : lineMarkCount.keySet()) {
                if (lineMarkCount.get(line).getCount(mark) == 2
                        && lineMarkCount.get(line).getCount((mark == Mark.O) ? Mark.X : Mark.O) == 0) {
                    for (Cell cell : line.getCells()) {
                        if (cell.getMark() == Mark.EMPTY) {
                            target = cell;
                        }
                    }
                }
            }
            return target;
        }

        public void randomPick(Mark mark) {
            Random random = new Random();
            int a = random.nextInt(3);
            int b = random.nextInt(3);


            if (plane[a][b].getMark() == Mark.EMPTY) {
                plane[a][b].setMark(mark);
                incLineMarkCount(plane[a][b], mark);
            } else
                randomPick(mark);
        }

    }

    class CountPair {
        private int oCount = 0;
        private int xCount = 0;

        public void inc(Mark mark) {
            if (mark == Mark.O) {
                oCount += 1;
            }
            if (mark == Mark.X) {
                xCount += 1;
            }
        }

        public int getCount(Mark mark) {
            int count = 0;
            if (mark == Mark.O) {
                count = oCount;
            }
            if (mark == Mark.X) {
                count = xCount;
            }
            return count;
        }
    }
