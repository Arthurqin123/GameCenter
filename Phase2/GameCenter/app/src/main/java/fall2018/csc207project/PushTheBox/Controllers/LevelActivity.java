package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc207project.PushTheBox.Models.MapManager;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Controllers.GameActivity;
import fall2018.csc207project.SlidingTile.Models.BoardManager;

public class LevelActivity extends AppCompatActivity {

    GridView gridView;

    private List<Button> levelButtons = new ArrayList<>();

    LevelFactory levelFactory = new LevelFactory();

    private int level;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_levels);
        gridView = findViewById(R.id.boxLevelGrid);
        createLevelButtons();
        display();
    }

    public void createLevelButtons(){
        for (int i = 0; i < levelFactory.getLevelAmount(); i++){
            Button tmp = new Button(getApplicationContext());
            tmp.setTextSize(40);
            tmp.setText(Integer.toString(i+1));
            tmp.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    level = Integer.parseInt(button.getText().toString());
                    switchToGame(level);
                }
            });
            levelButtons.add(tmp);
        }
    }


    private void switchToGame(int level) {
        Intent tmp = new Intent(this, BoxGameActivity.class);
        tmp.putExtra("save", new MapManager(level));
        startActivity(tmp);
        finish();
    }


    public void display(){
        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return levelButtons.size();
            }

            @Override
            public Object getItem(int position) {
                return levelButtons.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Button button;

                if (convertView == null) {
                    button = levelButtons.get(position);
                } else {
                    button = (Button) convertView;
                }
                return button;
            }
        });
    }


}
