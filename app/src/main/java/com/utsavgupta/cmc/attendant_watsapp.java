package com.utsavgupta.cmc;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
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

public class attendant_watsapp extends Activity {
    String trackName,status;
    TextView buttonAddTrack,complete, name, age, mob, date,ref,test,gender,pnam,stat,wataspp,mesg;
    EditText pathologist,statust ;int i;
    SeekBar seekBarRating;
    TextView textViewRating, textViewArtist;
    ListView listViewTracks;
    LinearLayout llpath,llperf,llstat,llstst;
    DatabaseReference databaseTracks;
    String iddddst;
    List<Track> tracks;
/////
EditText etPhone, etMessage;
    Button btnSendSMS;
    String msg,phn;
    String statusss;
    private final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    private final String SENT = "SMS_SENT";
    private final String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendant_watsapp);
        sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(DELIVERED), 0);

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
        mesg=findViewById(R.id.msg);
        wataspp=findViewById(R.id.watsapp);
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
mesg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //SharedPreferences prefs = getSharedPreferences("phone_details", MODE_PRIVATE);
        // String restoredText = prefs.getString("text", null);
        //   if (restoredText != null) {
        //String messagem= prefs.getString("phn", "No name defined");//"No name defined" is the default value.
       // String telNrm  = prefs.getString("msg", "");
        String messagem = "Your report is ready UTSAV GUPTA 9751999978";
        String telNrm  ="+919751999978";



        if (ContextCompat.checkSelfPermission(attendant_watsapp.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(attendant_watsapp.this, new String [] {Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        else
        {
            SmsManager sms = SmsManager.getDefault();

            //phone - Recipient's phone number
            //address - Service Center Address (null for default)
            //message - SMS message to be sent
            //piSent - Pending intent to be invoked when the message is sent
            //piDelivered - Pending intent to be invoked when the message is delivered to the recipient
            sms.sendTextMessage(telNrm, null, messagem, sentPI, deliveredPI);
        }
    }
});
        wataspp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        if (isWhatsappInstalled) {
               /*    Uri uri = Uri.parse("smsto:" + "916388141630");
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setData(uri);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hai Good Morning");
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp");

                  //  startActivity(Intent.createChooser(sendIntent, ""));
                    startActivity(sendIntent);*/
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=+916388141630&text=Your%20report%20is%20ready%20UTSAV%20GUPTA%2097519999978"
                    )));
        } else {
            Toast.makeText(attendant_watsapp.this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goToMarket);

        }
                /*Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"utsavgupta0397@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }*/
    }
});
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
                TrackList trackListAdapter = new TrackList(attendant_watsapp.this, tracks);
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
    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
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
                TrackList trackListAdapter = new TrackList(attendant_watsapp.this, tracks);
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
    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //The deliveredPI PendingIntent does not fire in the Android emulator.
        //You have to test the application on a real device to view it.
        //However, the sentPI PendingIntent works on both, the emulator as well as on a real device.

        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
                        statusss="SMS sent successfully!";
                        break;

                    //Something went wrong and there's no way to tell what, why or how.
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(context, "Generic failure!", Toast.LENGTH_SHORT).show();
                        statusss="Generic failure!";
                        break;

                    //Your device simply has no cell reception. You're probably in the middle of
                    //nowhere, somewhere inside, underground, or up in space.
                    //Certainly away from any cell phone tower.
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(context, "No service!", Toast.LENGTH_SHORT).show();
                        statusss="No service!";
                        break;

                    //Something went wrong in the SMS stack, while doing something with a protocol
                    //description unit (PDU) (most likely putting it together for transmission).
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(context, "Null PDU!", Toast.LENGTH_SHORT).show();
                        statusss="Null PDU!";
                        break;

                    //You switched your device into airplane mode, which tells your device exactly
                    //"turn all radios off" (cell, wifi, Bluetooth, NFC, ...).
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(context, "Radio off!", Toast.LENGTH_SHORT).show();
                        status="Radio off!";
                        break;

                }
                SharedPreferences loginData = getSharedPreferences("sms_status", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = loginData.edit();
                editor.putString("status",statusss );
                editor.apply();

            }
        };

        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch(getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "SMS delivered!", Toast.LENGTH_SHORT).show();
                        break;

                    case Activity.RESULT_CANCELED:
                        Toast.makeText(context, "SMS not delivered!", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        };

        //register the BroadCastReceivers to listen for a specific broadcast
        //if they "hear" that broadcast, it will activate their onReceive() method
        registerReceiver(smsSentReceiver, new IntentFilter(SENT));
        registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));
    }
}
