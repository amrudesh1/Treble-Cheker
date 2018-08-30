package com.amrudesh.treble;


import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrebleFragment extends Fragment {
    Process p = null;
    String board_platform = "";
    View rootView;
    ImageView imageView;
    TextView textView;
    ClipData.Item item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        rootView = inflater.inflate(R.layout.fragment_treble, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.imageView1);
        textView=(TextView) rootView.findViewById(R.id.sup);
        try {
            p = new ProcessBuilder("/system/bin/getprop", "ro.treble.enabled").redirectErrorStream(true).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                board_platform = line;
            }
               if (board_platform.contains("true")) {

                imageView.setImageResource(R.drawable.tick);
                textView.setText("Supported");


               }
               else if(board_platform==null ||board_platform.contains("false"))
               {

                   imageView.setImageResource(R.drawable.cross);
                   textView.setText("Not Supported");
               }
               else
               {
                   imageView.setImageResource(R.drawable.cross);
                   textView.setText("Not Supported");
               }
          p.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootView;

    }

    // Inflate the layout for this fragment
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_treble, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(rootView.getContext(),about_me.class));

            }
        return true;
    }

}
