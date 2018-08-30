package com.amrudesh.treble;


import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */

public class RootFragment extends Fragment {
     View rootView;
     RootDetect root=new RootDetect();
     Boolean status;
     String txt;
     TextView textView;
     ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        rootView = inflater.inflate(R.layout.fragment_root, container, false);
        imageView=(ImageView) rootView.findViewById(R.id.root);
        textView=(TextView) rootView.findViewById(R.id.rt2);
        status=root.FindRoot();
        txt=status.toString();
        if(status==true)
        {
            imageView.setImageResource(R.drawable.android_root);
            textView.setText("Your Device is Rooted,You're not Safe");
        }
        else
        {
            imageView.setImageResource(R.drawable.ic_launcher);
            textView.setText("Your Device is Not Rooted,You're Safe.");
        }
        ImageView imageView=(ImageView)rootView.findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.xda-developers.com/root";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        return rootView;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_root, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(rootView.getContext(),about_me.class));
    }
        return true;

    }



}



