package com.utsavgupta.cmc;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;


public class BlockTab2Fragment extends Fragment {


    public static final String ARTIST_NAME = "net.simplifiedcoding.firebasedatabaseexample.artistname";
    public static final String ARTIST_ID = "net.simplifiedcoding.firebasedatabaseexample.artistid";

    //EditText editTextName;
    //Spinner spinnerGenre;
    Button buttonAddArtist;

    EditText pname, age, gender, address,ref,mob,test;

    ListView listViewArtists;
    //a list to store all the artist from firebase database
    List<Artist> artists;

    //our database reference object
    DatabaseReference databaseArtists;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_block_tab2, container, false);



      



        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");
        // listViewArtists = (ListView) view.findViewById(R.id.listViewArtists);
        artists = new ArrayList<>();
        //getting views
        //editTextName = (EditText) view.findViewById(R.id.editTextName);
        //spinnerGenre = (Spinner) view.findViewById(R.id.spinnerGenres);

        buttonAddArtist = (Button) view.findViewById(R.id.submitx);
        pname= (EditText) view.findViewById(R.id.name);
        age= (EditText) view.findViewById(R.id.age);
        gender= (EditText) view.findViewById(R.id.gender);
        address= (EditText) view.findViewById(R.id.addr);
        ref= (EditText) view.findViewById(R.id.ref_doc);
        mob= (EditText) view.findViewById(R.id.mob);
        test= (EditText) view.findViewById(R.id.test);
        //list to store artists

        //adding an onclicklistener to button
        buttonAddArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                addArtist();
            }
        });

        //attaching listener to listview
      /* listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                Artist artist = artists.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), ArtistActivity.class);

                //putting artist name and id to intent
                intent.putExtra(ARTIST_ID, artist.getArtistId());
                intent.putExtra(ARTIST_NAME, artist.getArtistName());

                //starting the activity with intent
                startActivity(intent);
            }
        });

        listViewArtists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Artist artist = artists.get(i);
                showUpdateDeleteDialog(artist.getArtistId(), artist.getArtistName());
                return true;
            }
        });*/



return view;
    }

  /*  private void showUpdateDeleteDialog(final String artistId, String artistName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        // final EditText editTextName = (EditText) dialogView.view.findViewById(R.id.editTextName);
        //  final Spinner spinnerGenre = (Spinner) dialogView.view.findViewById(R.id.spinnerGenres);
        final Button buttonUpdate = (Button) dialogView.view.findViewById(R.id.buttonUpdateArtist);
        final Button buttonDelete = (Button) dialogView.view.findViewById(R.id.buttonDeleteArtist);

        dialogBuilder.setTitle(artistName);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String name = editTextName.getText().toString().trim();
                String name ="name";
                // String genre = spinnerGenre.getSelectedItem().toString();
                String genre ="genere";
                if (!TextUtils.isEmpty(name)) {
                    updateArtist(artistId, name, genre);
                    b.dismiss();
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteArtist(artistId);
                b.dismiss();
            }
        });
    }
*/
    private boolean updateArtist(String id, String name, String genre) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("artists").child(id);

        //updating artist
        Artist artist = new Artist(id, name, genre,"","","", "", "", "");
        dR.setValue(artist);
     //   Toast.makeText(getApplicationContext(), "Artist Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteArtist(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("artists").child(id);

        //removing artist
        dR.removeValue();

        //getting the tracks reference for the specified artist
        DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        //removing all tracks
        drTracks.removeValue();
      //  Toast.makeText(getApplicationContext(), "Artist Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

    /* @Override
     protected void onStart() {
         super.onStart();
         //attaching value event listener
         databaseArtists.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
 
                 //clearing the previous artist list
                 artists.clear();
 
                 //iterating through all the nodes
                 for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                     //getting artist
                     Artist artist = postSnapshot.getValue(Artist.class);
                     //adding artist to the list
                     artists.add(artist);
                 }
                 Collections.reverse(artists);
                 //creating adapter
                 ArtistList artistAdapter = new ArtistList(attendant.this, artists);
                 //attaching adapter to the listview
                 listViewArtists.setAdapter(artistAdapter);
             }
 
             @Override
             public void onCancelled(DatabaseError databaseError) {
 
             }
         });
     }
 
 */
    /*
     * This method is saving a new artist to the
     * Firebase Realtime Database
     * */
    private void addArtist() {
        //getting the values to save
        String name = pname.getText().toString().trim();
        String mobc= mob.getText().toString().trim();
        String gend =gender.getText().toString().trim();
        String add = address.getText().toString().trim();
        //String genre = spinnerGenre.getSelectedItem().toString();
        String refd =ref.getText().toString().trim();
        String agex =age.getText().toString().trim();
        String testx =test.getText().toString().trim();


        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseArtists.push().getKey();
            SimpleDateFormat datew = new SimpleDateFormat("dd MMM,yyyy HH:mm");
            String datewt =datew.format(new Date());
            //creating an Artist Object
            Artist artist = new Artist(id, name, mobc,gend,add,refd,testx,agex,datewt);

            //Saving the Artist
            databaseArtists.child(id).setValue(artist);

            //setting edittext to blank again
            // editTextName.setText("");

            //displaying a success toast
            Toast.makeText(getActivity(), "Artist added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(getActivity(), "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }


}
