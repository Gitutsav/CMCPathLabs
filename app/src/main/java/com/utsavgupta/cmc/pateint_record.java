package com.utsavgupta.cmc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class pateint_record extends ArrayAdapter<Artist> {
    private Activity context;
    List<Artist> artists;String paname,stst;int i;
    EditText mesg; Button send;
    private DatabaseReference databaseTracks;
    ProgressBar pb;

    public pateint_record(@NonNull Context context, int resource, Activity context1, List<Artist> artists, String paname, String stst, int i, EditText mesg, Button send, DatabaseReference databaseTracks, ProgressBar pb) {
        super(context, resource);
        this.context = context1;
        this.artists = artists;
        this.paname = paname;
        this.stst = stst;
        this.i = i;
        this.mesg = mesg;
        this.send = send;
        this.databaseTracks = databaseTracks;
        this.pb = pb;
    }

    public pateint_record(Activity context, List<Artist> artists) {
        super(context, R.layout.activity_pateint_record, artists);
        this.context = context;
        this.artists = artists;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_pateint_record, null, true);
        pb=listViewItem.findViewById(R.id.progressBar);
        TextView name = (TextView) listViewItem.findViewById(R.id.cname);
        TextView date = (TextView) listViewItem.findViewById(R.id.cdate);
        TextView age = (TextView) listViewItem.findViewById(R.id.cage);
        TextView gender = (TextView) listViewItem.findViewById(R.id.cgender);
        TextView ref = (TextView) listViewItem.findViewById(R.id.cref);
        TextView test = (TextView) listViewItem.findViewById(R.id.ctest);
        TextView pname = (TextView) listViewItem.findViewById(R.id.pname);
        TextView cstat = (TextView) listViewItem.findViewById(R.id.cstat);
        TextView ts2 = (TextView) listViewItem.findViewById(R.id.s2);
        TextView ts3 = (TextView) listViewItem.findViewById(R.id.s3);
       // send=(Button)listViewItem.findViewById(R.id.send_msg);
        // mesg=(EditText)listViewItem.findViewById(R.id.msg) ;

        Artist artist = artists.get(position);
        name.setText(artist.getArtistName());
        date.setText(artist.getDatex());
        age.setText(artist.getAgex());
        gender.setText(artist.getAgend());
        ref.setText(artist.getRefx());
        test.setText(artist.getTestx());
        String id=artist.getArtistId();


      /*  databaseTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);
        databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //tracks.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Track track = postSnapshot.getValue(Track.class);
                   // tracks.add(track);
                   // iddddst=postSnapshot.getKey();
                    i=track.getI();
                    stst=track.getStatus();
                    paname=track.getTrackName();
                }
               // TrackList trackListAdapter = new TrackList(ArtistActivity.this, tracks);
               // listViewTracks.setAdapter(trackListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        // final String a=mesg.getText().toString();
      /*  send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"tt" , Toast.LENGTH_LONG).show();
                getContext().startActivity(new Intent(getContext(),sendingSMS.class));
            }
        });*/
      if(i==53){
          pb.setProgress(50);
          ts2.setText(String.valueOf(50));
      }
      else if(i==102){
          pb.setProgress(100);
          ts2.setText(String.valueOf(100));
      }
      pname.setText(paname);
      cstat.setText(stst);
      ts2.setText(String.valueOf(i));
      return listViewItem;
    }
}
