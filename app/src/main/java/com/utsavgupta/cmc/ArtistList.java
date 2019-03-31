package com.utsavgupta.cmc;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Belal on 2/26/2017.
 */

public class ArtistList extends ArrayAdapter<Artist> {
    private Activity context;
    List<Artist> artists;
    EditText mesg; Button send;

    public ArtistList(Activity context, List<Artist> artists) {
        super(context, R.layout.layout_artist_list, artists);
        this.context = context;
        this.artists = artists;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_artist_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.timestamp);
        send=(Button)listViewItem.findViewById(R.id.send_msg);
       // mesg=(EditText)listViewItem.findViewById(R.id.msg) ;

        Artist artist = artists.get(position);
        textViewName.setText(artist.getArtistName());
        textViewGenre.setText(artist.getDatex());
       // final String a=mesg.getText().toString();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"tt" , Toast.LENGTH_LONG).show();
                getContext().startActivity(new Intent(getContext(),sendingSMS.class));
            }
        });
        return listViewItem;
    }
}