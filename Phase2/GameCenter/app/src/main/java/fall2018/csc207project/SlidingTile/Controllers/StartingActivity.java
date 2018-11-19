package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.BoardManager;
import fall2018.csc207project.models.DataStream;
import fall2018.csc207project.models.SaveManager;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {
    private String currentUser;
    private SaveManager saveManager;
    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedData = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        this.currentUser = sharedData.getString("currentUser", null);
        this.saveManager = new SaveManager(DataStream.getInstance(), currentUser,
                sharedData.getString("currentGame", null), this);
        setContentView(R.layout.tile_game_starting);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addGlobalScoreButtonListener();
    }

    /**
     * Activate the start button.
     */

    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToComplexity();
            }
        });
    }

    /**
     * Activate the myScore button.
     */

    private void addGlobalScoreButtonListener() {
        Button myScoreButton = findViewById(R.id.global_score_button);
        myScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();;
            }
        });
    }
    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        Button loadAutoButton = findViewById(R.id.LoadAuto);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(saveManager.readFromSlot(false));
            }
        });
        loadAutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(saveManager.readFromSlot(true));
            }
        });
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        final Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object save = saveManager.readFromSlot(true);
                if(save == null){
                    makeNotStartedText();
                } else {
                    saveManager.saveToSlot(save, false, getApplicationContext());
                    makeToastSavedText();
                }
            }
            });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a game that has no saves.
     */
    private void makeNoSavedText() {
        Toast.makeText(this, "No Save!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that the game hasn't started yet.
     */
    private void makeNotStartedText() {
        Toast.makeText(this, "Game is not started!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the complexity selection view.
     */
    private void switchToComplexity() {
        Intent tmp = new Intent(this, ComplexityActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the GameActivity view to play the game.
     * @param save the save to be restored.
     */
    private void switchToGame(Object save) {
        if(save == null){
            makeNoSavedText();
        } else {
            boardManager = (BoardManager)save;
            Intent tmp = new Intent(this, GameActivity.class);
            tmp.putExtra("save", boardManager);
            startActivity(tmp);
        }
    }

    private void switchToScoreboard() {
//        Intent tmp = new Intent(this, Scoreboard.class);
//        tmp = tmp.putExtra("GlobalCenter", globalCenter);
//        startActivity(tmp);
    }
}
