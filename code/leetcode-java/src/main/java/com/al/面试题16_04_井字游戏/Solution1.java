package com.al.面试题16_04_井字游戏;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author duval
 * @date 2019-10-13 14:43
 */
public class Solution1 {

    public String tictactoe(String[] board) {
        int size = board.length;

        boolean XnaWin = true, OpieWin = true, OnaWin = true, XpieWin = true;
        boolean isFull = true;

        for (int i = 0; i < size; i++) {
            if (board[i].contains(" ")) {
                isFull = false;
            }

            boolean XrowWin = true, OrowWin = true, XcolWin = true, OcolWin = true;


            for (int j = 0; j < size; j++) {
                if (XrowWin) XrowWin = XrowWin && ('X' == board[i].charAt(j));
                if (OrowWin) OrowWin = OrowWin && ('O' == board[i].charAt(j));
                if (XcolWin) XcolWin = XcolWin && ('X' == board[j].charAt(i));
                if (OcolWin) OcolWin = OcolWin && ('O' == board[j].charAt(i));
            }

            if (XrowWin || XcolWin) return "X";
            if (OrowWin || OcolWin) return "O";

            if (XnaWin) XnaWin = XnaWin && ('X' == board[i].charAt(i));
            if (OnaWin) OnaWin = OnaWin && ('O' == board[i].charAt(i));
            if (OpieWin) OpieWin = OpieWin && ('O' == board[size - 1 - i].charAt(i));
            if (XpieWin) XpieWin = XpieWin && ('X' == board[size - 1 - i].charAt(i));

        }

        if (XnaWin || XpieWin) return "X";

        if (OpieWin || OnaWin) return "O";


        if (isFull) return "Draw";
        else return "Pending";

    }

    public String tictactoe1(String[] board) {

        int size = board.length;

        String winStatus;

        boolean isEnd;
        // 逐行比较
        for (int i = 0; i < size; i++) {
            isEnd = true;
            winStatus = "" + board[i].charAt(0);
            for (int j = 1; j < size; j++) {
                if (board[i].charAt(j) != board[i].charAt(0)) {
                    isEnd = false;
                    break;
                }
            }

            if (isEnd && !winStatus.equals(" ")) {
                return winStatus;
            }
        }

        // 逐列比较
        for (int i = 0; i < size; i++) {
            isEnd = true;
            winStatus = "" + board[0].charAt(i);
            for (int j = 1; j < size; j++) {
                if (board[j].charAt(i) != board[0].charAt(i)) {
                    isEnd = false;
                    break;
                }
            }
            if (isEnd && !winStatus.equals(" ")) {
                return winStatus;
            }
        }


        // 对角线1
        isEnd = true;
        winStatus = "" + board[0].charAt(0);
        for (int i = 1; i < size; i++) {
            if (board[i].charAt(i) != board[0].charAt(0)) {
                isEnd = false;
                break;
            }
        }

        if (isEnd && !winStatus.equals(" ")) {
            return winStatus;
        }

        // 对角线2
        isEnd = true;
        winStatus = "" + board[size - 1].charAt(0);
        for (int i = 1; i < size; i++) {
            if (board[size - 1 - i].charAt(i) != board[size - 1].charAt(0)) {
                isEnd = false;
                break;
            }
        }

        if (isEnd && !winStatus.equals(" ")) {
            return winStatus;
        }

        winStatus = "Draw";
        for (int i = 0; i < size; i++) {
            if (board[i].contains(" ")) {
                winStatus = "Pending";
            }
        }

        return winStatus;
    }

    @Test
    public void test() {
        Assert.assertEquals(tictactoe(new String[]{"OOX", "XXO", "OXO"}), "Draw");
        Assert.assertEquals(tictactoe(new String[]{"O X", " XO", "X O"}), "X");
        Assert.assertEquals(tictactoe(new String[]{"OOX", "XXO", "OX "}), "Pending");
        Assert.assertEquals(tictactoe(new String[]{"   X O  O ", " X X    O ", "X  X    O ", "X    OX O ", "X   XO  O ", "X  X O  O ", "O  X O  O ", "     O  OX", "     O  O ", "   X XXXO "}), "O");
    }
}
