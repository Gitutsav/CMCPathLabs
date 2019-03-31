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
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sendingSMS extends AppCompatActivity {
    EditText etPhone, etMessage;
    Button btnSendSMS;
String msg,phn;
String status;
    private final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    private final String SENT = "SMS_SENT";
    private final String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;

    public sendingSMS() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_sms);

        etPhone = (EditText) findViewById(R.id.phone);
        etMessage = (EditText) findViewById(R.id.omessage);

        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);

        sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(DELIVERED), 0);



        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("phone_details", MODE_PRIVATE);
                // String restoredText = prefs.getString("text", null);
                //   if (restoredText != null) {
                //String messagem= prefs.getString("phn", "No name defined");//"No name defined" is the default value.
                //String telNrm  = prefs.getString("msg", "");
                String telNrm="9751999978";
                String messagem="hello";


                if (ContextCompat.checkSelfPermission(sendingSMS.this, Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(sendingSMS.this, new String[] {Manifest.permission.SEND_SMS},
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
    }
public void sendsms()
{
   // SharedPreferences prefs = getSharedPreferences("phone_details", MODE_PRIVATE);
    // String restoredText = prefs.getString("text", null);
    //   if (restoredText != null) {
   // String messagem= prefs.getString("phn", "No name defined");//"No name defined" is the default value.
   // String telNrm  = prefs.getString("msg", "");
    String num = "7521079555";
    String msg = "KANDHAMAL *1*694*2*4*5578,5582,5585,5587,5590,5592,5595*1,1,1,1,1,1,1*26.5121413*80.2243174*23.150999069213867*2018-06-21*1*2018-06-21 10:28*All present";


    if (ContextCompat.checkSelfPermission(sendingSMS.this, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED)
    {
        ActivityCompat.requestPermissions(sendingSMS.this, new String[] {Manifest.permission.SEND_SMS},
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
        sms.sendTextMessage(num, null, msg, sentPI, deliveredPI);
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
                        status="SMS sent successfully!";
                        break;

                    //Something went wrong and there's no way to tell what, why or how.
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(context, "Generic failure!", Toast.LENGTH_SHORT).show();
                        status="Generic failure!";
                        break;

                    //Your device simply has no cell reception. You're probably in the middle of
                    //nowhere, somewhere inside, underground, or up in space.
                    //Certainly away from any cell phone tower.
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(context, "No service!", Toast.LENGTH_SHORT).show();
                        status="No service!";
                        break;

                    //Something went wrong in the SMS stack, while doing something with a protocol
                    //description unit (PDU) (most likely putting it together for transmission).
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(context, "Null PDU!", Toast.LENGTH_SHORT).show();
                        status="Null PDU!";
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
                editor.putString("status",status );
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
