package com.amrudesh.treble;


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
public class SeamlessFragment extends Fragment {
    Process p = null;
    String board_platform = "";
    View rootView;
    ImageView imageView;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        rootView = inflater.inflate(R.layout.fragment_seamless, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.imageView2);
        textView=(TextView) rootView.findViewById(R.id.sup2);
        try {
            p = new ProcessBuilder("/system/bin/getprop", "getprop ro.build.ab_update").redirectErrorStream(true).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                board_platform = line;
            }
            if (board_platform.contains("true")) {

                imageView.setImageResource(R.drawable.a_b);
                textView.setText("Supported");


            }
            else if(board_platform==null ||board_platform.contains("false"))
            {

                imageView.setImageResource(R.drawable.vec);
                textView.setText("Your device is not configured to be capable of Seamless Updates.\nSeamless System Updates Require A/B Partitions.");
            }
            else
            {
                imageView.setImageResource(R.drawable.vec);
                textView.setText("Your device is not configured to be capable of Seamless Updates.\nSeamless System Updates Require A/B Partitions.");
            }
            p.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootView;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_seamless, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(rootView.getContext(),about_me.class));
        }
        return true;
    }
}
