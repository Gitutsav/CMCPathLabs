package com.utsavgupta.cmc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/*import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;*/
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class BlockPruebaAdapter extends RecyclerView.Adapter<BlockPruebaAdapter.PruebaViewHolder> {
    //private Block_submit_tables_teachers bstt;
    List<String> block_names,school_names,cluster_names,dates,remarks,presents,absents,leaves,attendence_id,flag,
            name,age,mob,is,ststs,panamex,testx,refx;
    AlertDialog.Builder builder1;
    String[] escrito;
    ProgressDialog dialog;
    private Context context;

    public BlockPruebaAdapter(List<String> names, List<String> age, List<String> mob, List<String> is, List<String> ststs, List<String> panamex, List<String> refx, List<String> testx) {
    this.name=names;
    this.age=age;
    this.mob=mob;
    this.is=is;
    this.ststs=ststs;
    this.panamex=panamex;
    this.refx=refx;
    this.testx=testx;
      //escrito = new String[lista.size()];
    }

    @Override
    public PruebaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pateint_record,parent,false);
        //this.bstt=new Block_submit_tables_teachers(v.getContext());
        context=v.getContext();
        return new PruebaViewHolder(v);

    }

    @Override
    public void onBindViewHolder(PruebaViewHolder holder, int position) {
         String blockname=name.get(position);
         String schoolname=age.get(position);
         String clustername=mob.get(position);
         String i=is.get(position);
         String stst=ststs.get(position);
         String paname=panamex.get(position);
         String test=testx.get(position);
         String ref=refx.get(position);


         holder.bindProducto(blockname,schoolname,clustername,i,stst,paname,test,ref);
    }

    @Override
    public int getItemCount() {
        return is.size();
    }

    public String[] getEscrito() {
        return escrito;
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{


        TextView block_names,school_names,cluster_names,dates,remarks,presents,absents,leaves,itv,ststv,panamtv,ts1,ts2,ts3,reftv,testtv;
        Button sync; ProgressBar pb;
        int accuracyt,slot_idt,taken_by_idt,school_idt,marked_by_id,marked_type=1;
        String remarkt,datett,marked_ont,datet;String message,status;
        double lattitudet,longitudet;
        String user_idt="",attendencestatust="";
        public PruebaViewHolder( View itemView) {
            super(itemView);
            block_names = (TextView) itemView.findViewById(R.id.cname);
            school_names = (TextView) itemView.findViewById(R.id.cage);
            cluster_names = (TextView) itemView.findViewById(R.id.cgender);
           // itv = (TextView) itemView.findViewById(R.id.cname);
            ststv = (TextView) itemView.findViewById(R.id.cstat);
            panamtv = (TextView) itemView.findViewById(R.id.pname);
            pb=itemView.findViewById(R.id.progressBar);
           // dates = (TextView) itemView.findViewById(R.id.date);
            ts2=itemView.findViewById(R.id.s2);
            ts3=itemView.findViewById(R.id.s3);
            reftv=itemView.findViewById(R.id.cref);
            testtv=itemView.findViewById(R.id.ctest);




            //   dialog=new ProgressDialog(itemView.getContext());
  /*          sync.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder1 = new AlertDialog.Builder(view.getContext());

                    builder1.setCancelable(false);
                    Boolean isconnected= ConnectivityReceiver.isConnected();
                    if(isconnected){
                        builder1.setTitle("Synced Successfully");
                        builder1.setPositiveButton(
                                "ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                        dialog.cancel();
                                        sync.setClickable(false);
                                        sync.setText("Done");
                                        sync.setBackgroundColor(44);


                                    }
                                });
                    }
                    else
                        {
                        builder1.setTitle("No Internet Connection");
                        builder1.setMessage("Do you want to update attendance via mobile network?");

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                    dialog.cancel();
                                    sync.setClickable(false);
                                    sync.setText("Done");
                                    sync.setBackgroundColor(44);


                                }
                            });

       builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
}
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });*/
        }

        public void bindProducto(String blockname, String schoolname, String clustername, String i, String stst, String paname, String test, String ref){



           block_names.setText(blockname);
           school_names.setText(schoolname);
           cluster_names.setText(clustername);
           ststv.setText(stst);
           reftv.setText(ref);
           testtv.setText(test);
           panamtv.setText(paname);
            if(Integer.parseInt(i)==53){
                pb.setProgress(50);
                ts2.setText("Started");
                ts3.setText(" ");
            }
            else if(Integer.parseInt(i)==102){
                pb.setProgress(100);
                ts2.setText(" ");
                ts3.setText("Completed");
              //  ts2.setText(String.valueOf(100));
            }


        }

    }
}
