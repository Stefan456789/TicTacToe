package me.stefan.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TableLayout layout;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game(this::getLayout, this::sendToast, this::resetBoard);
        layout = findViewById(R.id.TableLayout);
        Arrays.stream(tableToButtonArray(layout))
                .forEach(row -> Arrays.stream(row)
                        .forEach(bt -> bt.setOnClickListener(view -> {
                            Button button = (Button)view;
                            if (button.getText().toString().isEmpty()){
                                button.setText(String.valueOf(game.getPlayer()));
                                game.resume();
                            }else{
                                sendToast("Dieses Feld wurde bereits belegt!");
                            }
                        })));
    }

    private Button[][] tableToButtonArray(TableLayout layout) {
        Button[][] field = new Button[3][3];
        for (int i = 1; i < field.length+1; i++) {
            TableRow row = (TableRow) layout.getChildAt(i);
            for (int j = 0; j < field.length; j++) {
                Button butt = (Button) row.getChildAt(j);
                field[i-1][j] = butt;
            }
        }
        return field;
    }

    private int[][] tableToIntArray(Button[][] buttons) {
        int[][] field = new int[3][3];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                Button butt = buttons[i][j];
                switch (butt.getText().toString()){
                    case "":
                        field[i][j] = 0;
                        break;
                    case "X":
                        field[i][j] = 1;
                        break;
                    case "O":
                        field[i][j] = 2;
                        break;
                }
            }
        }
        return field;
    }

    private int[][] getLayout() {
        return tableToIntArray(tableToButtonArray(layout));
    }

    private void sendToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void resetBoard(){
        for (Button[] i : tableToButtonArray(layout)) {
            for (Button j : i) {
                j.setText("");
            }
        }
    }
}