package view;


import chessComponent.*;
//import chessComponent.EmptySlotComponent;
//import chessComponent.SoldierChessComponent;
//import chessComponent.SquareComponent;
import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 这个类表示棋盘组建，其包含：
 * SquareComponent[][]: 4*8个方块格子组件
 */
public class Chessboard extends JComponent {
    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 4;

    private final SquareComponent[][] squareComponents = new SquareComponent[ROW_SIZE][COL_SIZE];
    //todo: you can change the initial player
    private ChessColor currentColor = ChessColor.BLACK;

    //all chessComponents in this chessboard are shared only one model controller
    public final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;


    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width + 2, height);
        CHESS_SIZE = (height - 6) / 8;
        SquareComponent.setSpacingLength(CHESS_SIZE / 12);
        System.out.printf("chessboard [%d * %d], chess size = %d\n", width, height, CHESS_SIZE);
        //初始化棋子
        initAllChessOnBoard(ChushihuaQiZi());
    }

    public SquareComponent[][] getChessComponents() {
        return squareComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    /**
     * 将SquareComponent 放置在 ChessBoard上。里面包含移除原有的component及放置新的component
     *
     * @param squareComponent
     */
    public void putChessOnBoard(SquareComponent squareComponent) {
        int row = squareComponent.getChessboardPoint().getX(), col = squareComponent.getChessboardPoint().getY();
        if (squareComponents[row][col] != null) {
            remove(squareComponents[row][col]);
        }
        add(squareComponents[row][col] = squareComponent);
    }

    /**
     * 交换chess1 chess2的位置
     *
     * @param chess1
     * @param chess2
     */

    //在这里加积分
    private int Red_score=0;
    private int Black_score=0;

    public void setBlack_score(int black_score) {
        Black_score = black_score;
    }

    public void setRed_score(int red_score) {
        Red_score = red_score;
    }
    //你要用到的方法
    public int getRed_score() {
        return Red_score;
    }

    public int getBlack_score() {
        return Black_score;
    }

    private int AddHowMuchScore(SquareComponent chessBeEaten){
        if (chessBeEaten.getRank()==1){
            return 1;
        }
        else if (chessBeEaten.getRank()==2){
            return 5;
        }
        else if (chessBeEaten.getRank()==3){
            return 5;
        }else if (chessBeEaten.getRank()==4){
            return 5;
        }else if (chessBeEaten.getRank()==5){
            return 5;
        }else if (chessBeEaten.getRank()==6){
            return 10;
        }else{
            return 30;
        }
    }

    //被吃的是什么棋子
    private SquareComponent ChessBeEaten;

    public SquareComponent getChessBeEaten() {
        return ChessBeEaten;
    }
    public void swapChessComponents(SquareComponent chess1, SquareComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            if (chess2.getChessColor()==ChessColor.BLACK){
                Red_score+=AddHowMuchScore(chess2);
            }
            if (chess2.getChessColor()==ChessColor.RED){
                Black_score+=AddHowMuchScore(chess2);
            }
            ChessBeEaten=chess2;
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        squareComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        squareComponents[row2][col2] = chess2;

        //只重新绘制chess1 chess2，其他不变
        chess1.repaint();
        chess2.repaint();

    }

    //增加一个方法，先弄出一个list，再按list中的数字给棋子初始化
    public List<String> ChushihuaQiZi() {
        List<Integer> ChushihuaQiZi2 = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            ChushihuaQiZi2.add(i);
        }
        Collections.shuffle(ChushihuaQiZi2);
        List<String> ChushihuaQiZi = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            if (ChushihuaQiZi2.get(i) < 5) {
                ChushihuaQiZi.add("R1");
            } else if (ChushihuaQiZi2.get(i) < 10) {
                ChushihuaQiZi.add("B1");
            } else if (ChushihuaQiZi2.get(i) < 12) {
                ChushihuaQiZi.add("R2");
            } else if (ChushihuaQiZi2.get(i) < 14) {
                ChushihuaQiZi.add("B2");
            } else if (ChushihuaQiZi2.get(i) < 16) {
                ChushihuaQiZi.add("R3");
            } else if (ChushihuaQiZi2.get(i) < 18) {
                ChushihuaQiZi.add("B3");
            } else if (ChushihuaQiZi2.get(i) < 20) {
                ChushihuaQiZi.add("R4");
            } else if (ChushihuaQiZi2.get(i) < 22) {
                ChushihuaQiZi.add("B4");
            } else if (ChushihuaQiZi2.get(i) < 24) {
                ChushihuaQiZi.add("R5");
            } else if (ChushihuaQiZi2.get(i) < 26) {
                ChushihuaQiZi.add("B5");
            } else if (ChushihuaQiZi2.get(i) < 28) {
                ChushihuaQiZi.add("R6");
            } else if (ChushihuaQiZi2.get(i) < 30) {
                ChushihuaQiZi.add("B6");
            } else if (ChushihuaQiZi2.get(i) < 31) {
                ChushihuaQiZi.add("R7");
            } else ChushihuaQiZi.add("B7");
        }
        return ChushihuaQiZi;
    }


    //这个是初始化棋子
    //!!!   我把这个方法从private改成了public！！！Restart要用到这个方法
    public void initAllChessOnBoard(List<String> ChushihuaQiZi) {
        for (int i = 0; i < squareComponents.length; i++) {
            for (int j = 0; j < squareComponents[i].length; j++) {
                ChessColor color;
                SquareComponent squareComponent = null;
                if (ChushihuaQiZi.get(4*i + j).charAt(0) == 'R') {
                    color = ChessColor.RED;
                } else color = ChessColor.BLACK;
                if (ChushihuaQiZi.get(4*i + j).charAt(1) == '1') {
                    squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                } else if (ChushihuaQiZi.get(4*i + j).charAt(1) == '2') {
                    squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                } else if (ChushihuaQiZi.get(4*i + j).charAt(1) == '3') {
                    squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                } else if (ChushihuaQiZi.get(4*i + j).charAt(1) == '4') {
                    squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                } else if (ChushihuaQiZi.get(4*i + j).charAt(1) == '5') {
                    squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                } else if (ChushihuaQiZi.get(4*i + j).charAt(1) == '6') {
                    squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                } else if (ChushihuaQiZi.get(4*i + j).charAt(1) == '7') {
                    squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                }
                squareComponent.setVisible(true);
                putChessOnBoard(squareComponent);
            }
        }
        repaint();
    }

    /**
     * 绘制棋盘格子
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 将棋盘上行列坐标映射成Swing组件的Point
     *
     * @param row 棋盘上的行
     * @param col 棋盘上的列
     * @return
     */
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE + 3, row * CHESS_SIZE + 3);
    }

    /**
     * 通过GameController调用该方法
     *
     * @param chessData
     */
    public void loadGame(List<String> chessData) {
        chessData.forEach(System.out::println);
    }
}
