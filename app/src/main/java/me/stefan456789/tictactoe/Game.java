package me.stefan456789.tictactoe;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;

public class Game {
    private char player = 'X';
    private final GetIntArray field;
    private final Messenger sendMessage;
    private final Runnable resetBoard;

    public Game(GetIntArray getField, Messenger sendMessage, Runnable resetBoard) {
        this.field = getField;
        this.sendMessage = sendMessage;
        this.resetBoard = resetBoard;
    }


    public char getPlayer() {
        return player;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void resume() {
        if (player == 'X'){
            player = 'O';
        } else {
            player = 'X';
        }
        switch (winner(field.get())){
            case 0:
                return;
            case 1:
                sendMessage.send("Player X wins");
                break;
            case 2:
                sendMessage.send("Player O wins");
                break;
            case 3:
                sendMessage.send("It's a tie");
                break;
        }
        resetBoard.run();



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static int winner(int[][] gameboard){

        //Horizontal Win
        if(gameboard[0][0] == 1&& gameboard[0][1] == 1 && gameboard [0][2] == 1 ){
            return 1;
        }
        if(gameboard[0][0] == 2&& gameboard[0][1] == 2 && gameboard [0][2] == 2 ){
            return 2;
        }
        if(gameboard[1][0] == 1&& gameboard[1][1] == 1 && gameboard [1][2] == 1 ){
            return 1;
        }
        if(gameboard[1][0] == 2&& gameboard[1][1] == 2 && gameboard [1][2] == 2 ){
            return 2;
        }
        if(gameboard[2][0] == 1&& gameboard[2][1] == 1 && gameboard [2][2] == 1 ){
            return 1;
        }
        if(gameboard[2][0] == 2&& gameboard[2][1] == 2 && gameboard [2][2] == 2) {
            return 2;
        }

        //Vertical Wins
        if(gameboard[0][0] == 1&& gameboard[1][0] == 1 && gameboard [2][0] == 1 ){
            return 1;
        }
        if(gameboard[0][0] == 2&& gameboard[1][0] == 2 && gameboard [2][0] == 2 ){
            return 2;
        }
        if(gameboard[0][1] == 1&& gameboard[1][1] == 1 && gameboard [2][1] == 1 ){
            return 1;
        }
        if(gameboard[0][1] == 2&& gameboard[1][1] == 2 && gameboard [2][1] == 2 ){
            return 2;
        }
        if(gameboard[0][2] == 1&& gameboard[1][2] == 1 && gameboard [2][2] == 1 ){
            return 1;
        }
        if(gameboard[0][2] == 2&& gameboard[1][2] == 2 && gameboard [2][2] == 2 ){
            return 2;
        }

        //Diagonal Wins
        if(gameboard[0][0] == 1&& gameboard[1][1] == 1 && gameboard [2][2] == 1 ){
            return 1;
        }
        if(gameboard[0][0] == 2&& gameboard[1][1] == 2 && gameboard [2][2] == 2 ){
            return 2;
        }
        if(gameboard[2][0] == 1&& gameboard[1][1] == 1 && gameboard [0][2] == 1){
            return 1;
        }
        if(gameboard[2][0] == 2&& gameboard[1][1] == 2 && gameboard [0][2] == 2 ){
            return 2;
        }

        if(Arrays.stream(gameboard).allMatch(x -> Arrays.stream(x).allMatch(y -> y != 0))){
            return 3;
        }

        return 0;
    }
}


interface GetIntArray {
    int[][] get();
}


interface Messenger {
    void send(String msg);
}
