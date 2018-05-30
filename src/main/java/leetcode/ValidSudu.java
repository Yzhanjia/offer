package leetcode;

import java.util.HashMap;
import java.util.Map;
/*
找出有效数独
 */
public class ValidSudu {
    static Map<Character, Integer> map = new HashMap<Character, Integer>();
    private static void initMap(){
        for (char i = 0; i < '9'; i++) {
            map.put(i,0);
        }
    }
    public boolean isValidSudoku(char[][] board) {


        return rowValid(board)&&conlumnValid(board)&&squareValid(board);
    }
    private static boolean rowValid(char[][] board){

        for (int i = 0; i < 9; i++) {
            initMap();
            for (int j = 0; j < 9; j++) {
                if (board[i][j]>'0'&&board[i][j]<='9'){
                    if (map.get(board[i][j])>0){
                        return false;
                    }else {
                        map.put(board[i][j],1);
                    }
                }else if (board[i][j]!='.'){
                    return false;
                }

            }

        }
        return true;
    }
    private static boolean conlumnValid(char[][]board){
        for (int i = 0; i < 9; i++) {
            initMap();
            for (int j = 0; j < 9; j++) {
                if (board[j][i]>'0'&&board[j][i]<='9'){
                    if (map.get(board[j][i])>0){
                        return false;
                    }else {
                        map.put(board[j][i],1);
                    }
                }
            }

        }
        return  true;
    }

    private static boolean squareValid(char[][]board){
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                initMap();
                for (int k = i; k < i+3; k++) {
                    for (int l = j; l < j+3; l++) {
                        if (board[k][l]>'0'&&board[k][l]<='9') {
                            if (map.get(board[k][l]) > 0) {
                                return false;
                            } else {
                                map.put(board[k][l], 1);
                            }
                        }
                    }

                }
            }

        }
        return true;
    }
}
