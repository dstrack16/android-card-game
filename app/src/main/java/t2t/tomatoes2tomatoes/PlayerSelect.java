package t2t.tomatoes2tomatoes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class PlayerSelect extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int playerCount = 2; // variable for number of players, starts at 1

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View PopUp = layoutInflater.inflate(R.layout.activity_player_select, null);
        final AlertDialog alertD = new AlertDialog.Builder(getActivity()).create();

        // get start, exit buttons
        ImageView btn1 = (ImageView) PopUp.findViewById(R.id.startButton);
        ImageView btn2 = (ImageView) PopUp.findViewById(R.id.exitButton);

        // get arrows
        ImageView left = (ImageView) PopUp.findViewById(R.id.leftarrow);
        ImageView right = (ImageView) PopUp.findViewById(R.id.rightarrow);

        // misc
        final ImageView plyrnum = (ImageView) PopUp.findViewById(R.id.tomatoNum);
        final TypedArray playerimgs = getResources().obtainTypedArray(R.array.player_imgs);
        final TextView playerNum = (TextView) PopUp.findViewById(R.id.playersNum);

        // when start button is clicked
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoardActivity.class);
                String currentVal = playerNum.getText().toString();
                intent.putExtra("playerNum", currentVal);
                startActivity(intent);
            }
        });

        // when exit button is clicked
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // nothing here yet
            }
        });

        // when left arrow is pressed
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // grab number from TextView
                String currentVal = playerNum.getText().toString();
                int num = Integer.parseInt(currentVal);

                // minimum of 2 players
                if (num > 2) {
                    num--;
                }

                // set TextView to new number
                playerNum.setText(String.valueOf(num));

                // iterate through typed array
                plyrnum.setImageResource(playerimgs.getResourceId(num - 1, -1));

            }
        });

        // when right arrow is pressed
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentVal = playerNum.getText().toString();
                int num = Integer.parseInt(currentVal);

                // maximum of 3 players
                if (num < 3)
                {
                    num++;
                }

                // set TextView to new number
                playerNum.setText(String.valueOf(num));

                // iterate through typed array
                plyrnum.setImageResource(playerimgs.getResourceId(num-1, -1));
            }
        });

        // show pop up
        alertD.setView(PopUp);
        alertD.show();
        return alertD;
    }
}