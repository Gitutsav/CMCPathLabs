package com.utsavgupta.cmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.security.AccessController.getContext;

public class RecyclerPatientRecord extends AppCompatActivity {
BlockPruebaAdapter adapter;
    private DatabaseReference databaseArtists,databaseTracks;
    private RecyclerView recyclerview;String id;
    List<String> names=new ArrayList<>(),ages=new ArrayList<>(),mobs=new ArrayList<>(),refx=new ArrayList<>(),testx=new ArrayList<>(),
            is=new ArrayList<>(),ststs=new ArrayList<>(),panamex=new ArrayList<>(),ids=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_patient_record);
        recyclerview = (android.support.v7.widget.RecyclerView) findViewById(R.id.rvPrueba);
        android.support.v7.widget.LinearLayoutManager llm = new android.support.v7.widget.LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(llm);
        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");
        //iterating through all the nodes
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
               // artists.clear();
           names.clear();ages.clear();mobs.clear();refx.clear();testx.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Artist artist = postSnapshot.getValue(Artist.class);
                    if(artist.getAmob().equals("+919751999978")){
                    names.add(artist.getArtistName());
                    ages.add(artist.getAgex());
                    mobs.add(artist.getAmob());
                    refx.add(artist.getRefx());
                    testx.add(artist.getTestx());
                    id=artist.getArtistId();

                    }
                    //adding artist to the list
                   // artists.add(artist);
                }
                Collections.reverse(names);
                Collections.reverse(ages);
                Collections.reverse(mobs);
                Collections.reverse(testx);
                Collections.reverse(refx);


                //creating adapter
                databaseTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);
                databaseTracks.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //tracks.clear();
                        is.clear();
                        ststs.clear();
                        panamex.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Track track = postSnapshot.getValue(Track.class);
                            // tracks.add(track);
                            // iddddst=postSnapshot.getKey();
                           int i=track.getI();
                           String stst=track.getStatus();
                           String paname=track.getTrackName();
                           is.add(String.valueOf(i));
                           ststs.add(stst);
                           panamex.add(paname);

                        }
                        Collections.reverse(is);
                        Collections.reverse(ststs);
                        Collections.reverse(panamex);
                        adapter = new BlockPruebaAdapter(names,ages,mobs,is,ststs,panamex,refx,testx);
                        recyclerview.setAdapter(adapter);
                        // TrackList trackListAdapter = new TrackList(ArtistActivity.this, tracks);
                        // listViewTracks.setAdapter(trackListAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
