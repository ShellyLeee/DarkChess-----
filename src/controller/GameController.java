package controller;

import view.Chessboard;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * 这个类主要完成由窗体上组件触发的动作。
 * 例如点击button等
 * ChessGameFrame中组件调用本类的对象，在本类中的方法里完成逻辑运算，将运算的结果传递至chessboard中绘制
 */

public class GameController {
    private Chessboard chessboard;

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    //这个方法是写  如何从文件中读取数据  ！！  sakai第介绍38分钟  我没有改这个方法，存档
    public List<String> loadGameFromFile(String path) {
        try {
            List<String> chessData = Files.readAllLines(Path.of(path));
            chessboard.loadGame(chessData);
            return chessData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void restartGame() {
        chessboard.initAllChessOnBoard(chessboard.ChushihuaQiZi());
        chessboard.setBlack_score(0);
        chessboard.setRed_score(0);
        chessboard.clickController.setProgress(1);
        chessboard.clickController.setShiFouYiHuiHe(false);
    }
}