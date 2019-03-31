package com.utsavgupta.cmc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArtistActivity extends AppCompatActivity {
String trackName,status;
    TextView buttonAddTrack,complete, name, age, mob, date,ref,test,gender,pnam,stat;
    EditText pathologist,statust ;int i;
    SeekBar seekBarRating;
    TextView textViewRating, textViewArtist;
    ListView listViewTracks;
    LinearLayout llpath,llperf,llstat,llstst;
    DatabaseReference databaseTracks;
    String iddddst;
    List<Track> tracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        Intent intent = getIntent();

        /*
        * this line is important
        * this time we are not getting the reference of a direct node
        * but inside the node track we are creating a new child with the artist id
        * and inside that node we will store all the tracks with unique ids
        * */
        stat=findViewById(R.id.ctxtstat);
        llstst=findViewById(R.id.llstst);
        llpath=findViewById(R.id.llpath);
        llstat=findViewById(R.id.llstat);
        pnam=findViewById(R.id.pname);
        llperf=findViewById(R.id.llperf);
        databaseTracks = FirebaseDatabase.getInstance().getReference("tracks").child(intent.getStringExtra(MainActivity.ARTIST_ID));

        buttonAddTrack =  findViewById(R.id.start);
        complete =  findViewById(R.id.delete);
        pathologist = (EditText) findViewById(R.id.name);
        statust = (EditText) findViewById(R.id.cstatus);
        seekBarRating = (SeekBar) findViewById(R.id.seekBarRating);
        textViewRating = (TextView) findViewById(R.id.textViewRating);
        textViewArtist = (TextView) findViewById(R.id.textViewArtist);
        listViewTracks = (ListView) findViewById(R.id.listViewTracks);
        age=findViewById(R.id.cage);
        gender=findViewById(R.id.cgender);
        ref=findViewById(R.id.cref);
        test=findViewById(R.id.ctest);
        date=findViewById(R.id.cdate);

        tracks = new ArrayList<>();

        textViewArtist.setText(intent.getStringExtra(MainActivity.ARTIST_NAME));
        age.setText(intent.getStringExtra("age"));
        gender.setText(intent.getStringExtra("gender"));
        ref.setText(intent.getStringExtra("ref"));
        test.setText(intent.getStringExtra("test"));
        date.setText(intent.getStringExtra("date"));

        seekBarRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //textViewRating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tracks.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Track track = postSnapshot.getValue(Track.class);
                    tracks.add(track);
                    iddddst=postSnapshot.getKey();
                    i=track.getI();
                }
                TrackList trackListAdapter = new TrackList(ArtistActivity.this, tracks);
                listViewTracks.setAdapter(trackListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        buttonAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTrack();
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=102;
                if(llpath.getVisibility()==View.VISIBLE) {
                    llstst.setVisibility(View.GONE);
                    llstat.setVisibility(View.VISIBLE);
                    stat.setText(statust.getText().toString().trim());
                    String id  = databaseTracks.push().getKey();
                    Track track = new Track(iddddst, trackName, status,i);
                    databaseTracks.child(iddddst).setValue(track);
                    complete.setVisibility(View.GONE);
                    buttonAddTrack.setBackgroundColor(Color.GREEN);
                    buttonAddTrack.setTextColor(Color.WHITE);
                    buttonAddTrack.setClickable(false);
                    buttonAddTrack.setText("COMPLETED");
                }
                else {
                    Toast.makeText(getApplicationContext(), "First Click the START Buton", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tracks.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Track track = postSnapshot.getValue(Track.class);
                    tracks.add(track);
                    i=track.getI();
                    trackName=track.getTrackName();
                    status=track.getStatus();
                  // idddd=postSnapshot.getKey();
                }
                Toast.makeText(getApplicationContext(), i+"\n"+status, Toast.LENGTH_LONG).show();
                TrackList trackListAdapter = new TrackList(ArtistActivity.this, tracks);
                listViewTracks.setAdapter(trackListAdapter);
                if (i==53){
                    if(llpath.getVisibility()==View.INVISIBLE)
                    {
                        llpath.setVisibility(View.VISIBLE);
                    }
                    pnam.setText(trackName);
                    llperf.setVisibility(View.GONE);
                    buttonAddTrack.setText("STARTED");
                    buttonAddTrack.setBackgroundColor(Color.BLUE);
                    buttonAddTrack.setTextColor(Color.WHITE);
                    buttonAddTrack.setClickable(false);
                }
                else if(i==102){
                    if(llpath.getVisibility()==View.INVISIBLE)
                    {
                        llpath.setVisibility(View.VISIBLE);
                    }
                    pnam.setText(trackName);
                    stat.setText(status);
                    llperf.setVisibility(View.GONE);
                    complete.setVisibility(View.GONE);
                    buttonAddTrack.setBackgroundColor(Color.GREEN);
                    buttonAddTrack.setTextColor(Color.WHITE);
                    buttonAddTrack.setClickable(false);
                    buttonAddTrack.setText("COMPLETED");
                    llstat.setVisibility(View.VISIBLE);
                    llstst.setVisibility(View.GONE );
                    //stat.setText(statust.getText().toString().trim());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveTrack() {

        trackName = pathologist.getText().toString().trim();

        status=statust.getText().toString().trim();
        int rating = seekBarRating.getProgress();

        if (!TextUtils.isEmpty(trackName)) {
            i=53;
            String id  = databaseTracks.push().getKey();
            Track track = new Track(id, trackName, status,i);
            databaseTracks.child(id).setValue(track);
            Toast.makeText(this, "Track saved", Toast.LENGTH_LONG).show();
            //pathologist.setText("");
            if(llpath.getVisibility()==View.INVISIBLE)
            {
                llpath.setVisibility(View.VISIBLE);
            }
            pnam.setText(trackName);
            llperf.setVisibility(View.GONE);
            buttonAddTrack.setBackgroundColor(Color.BLUE);
            buttonAddTrack.setText("STARTED");
            buttonAddTrack.setTextColor(Color.WHITE);
            buttonAddTrack.setClickable(false);
        } else {
            Toast.makeText(this, "Please enter track name", Toast.LENGTH_LONG).show();
        }

    }
}
