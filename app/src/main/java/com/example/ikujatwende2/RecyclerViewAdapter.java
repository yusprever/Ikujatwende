package com.example.ikujatwende2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {

    private ArrayList activity,location,date,time,reporter;
    private ArrayList<String> everything;
    private Context context;
    DatabaseHelper DB;


    public RecyclerViewAdapter(Context context, ArrayList activity,ArrayList location,ArrayList date,ArrayList time, ArrayList reporter ) {
        this.activity = activity;
        this.location = location;
        this.date = date;
        this.time = time;
        this.reporter  = reporter ;
        this.context = context;
        this.everything = new ArrayList<>(activity);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myrows,parent,false);
        return new ViewHolder(view).myDelFunc(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            holder.myActivity.setText(activity.get(position).toString());
            holder.myLocation.setText(location.get(position).toString());
            holder.myDate.setText(date.get(position).toString());
            holder.myTime.setText(time.get(position).toString());
            holder.myReporter.setText(reporter.get(position).toString());

            DB = new DatabaseHelper(context);

    }

    @Override
    public int getItemCount() {

        return activity.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList filteredList = new ArrayList();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(everything);
            }
            else{
                for (String event: everything) {
                    if(event.toLowerCase().contains(charSequence.toString().toLowerCase())){

                        filteredList.add(event);
                    }
                    
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            activity.clear();
            activity.addAll((Collection) filterResults.values);
            notifyDataSetChanged();


        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myActivity,myLocation,myDate,myTime,myReporter;
        private  RecyclerViewAdapter recyclerViewAdapter;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            myActivity  = itemView.findViewById(R.id.myActivity);
            myLocation  = itemView.findViewById(R.id.myLoc);
            myDate  = itemView.findViewById(R.id.myDate);
            myTime  = itemView.findViewById(R.id.myTime);
            myReporter = itemView.findViewById(R.id.myReporter);

            itemView.findViewById(R.id.myDel).setOnClickListener(view -> {

                String act = recyclerViewAdapter.activity.get(getAdapterPosition()).toString();
                String dat = recyclerViewAdapter.date.get(getAdapterPosition()).toString();
                String rep= recyclerViewAdapter.reporter.get(getAdapterPosition()).toString();

                Boolean checkdeletedata = DB.deleteuserdata(act,dat,rep);
                if(checkdeletedata==true)
                    Toast.makeText(context, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "Entry Not Deleted", Toast.LENGTH_SHORT).show();

                recyclerViewAdapter.activity.remove(getAdapterPosition());
                recyclerViewAdapter.location.remove(getAdapterPosition());
                recyclerViewAdapter.date.remove(getAdapterPosition());
                recyclerViewAdapter.time.remove(getAdapterPosition());
                recyclerViewAdapter.reporter.remove(getAdapterPosition());

                recyclerViewAdapter.notifyItemRemoved(getAdapterPosition());

            });
            itemView.findViewById(R.id.gen_Rep).setOnClickListener(view -> {
                Button btnCancel, btnCreate;
                EditText etReport;

                String myAct = recyclerViewAdapter.activity.get(getAdapterPosition()).toString();
                String my_date = recyclerViewAdapter.date.get(getAdapterPosition()).toString();
                String my_reporter= recyclerViewAdapter.reporter.get(getAdapterPosition()).toString();

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.create_report);
                btnCancel = dialog.findViewById(R.id.editCancel);
                btnCreate = dialog.findViewById(R.id.editCreate);
                etReport = dialog.findViewById(R.id.etReport);
                dialog.show();

                btnCancel.setOnClickListener(view1 -> {
                    dialog.dismiss();

                });
                btnCreate.setOnClickListener(view1 -> {

                    Boolean checkReport = DB.updateuserdata(myAct,my_date,my_reporter,etReport.getText().toString());
                    if(checkReport==true)
                        Toast.makeText(context, "Report Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "Entry Not Inserted", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();

                });


            });
            itemView.findViewById(R.id.view_REP).setOnClickListener(view -> {

                Intent intent = new Intent(context, MainActivity5.class);

                Bundle bd = new Bundle();


                bd.putString("myActivity",recyclerViewAdapter.activity.get(getAdapterPosition()).toString());
                bd.putString("myLocation",recyclerViewAdapter.location.get(getAdapterPosition()).toString());
                bd.putString("myDate",recyclerViewAdapter.date.get(getAdapterPosition()).toString());
                bd.putString("myTime",recyclerViewAdapter.time.get(getAdapterPosition()).toString());
                bd.putString("myReporter",recyclerViewAdapter.reporter.get(getAdapterPosition()).toString());

                intent.putExtras(bd);
                context.startActivity(intent);

            });



        }
        public ViewHolder myDelFunc(RecyclerViewAdapter recyclerViewAdapter){
            this.recyclerViewAdapter = recyclerViewAdapter;
            return this;

        }
    }
}
