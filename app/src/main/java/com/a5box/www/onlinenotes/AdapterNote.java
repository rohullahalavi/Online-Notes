package com.a5box.www.onlinenotes;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterNote extends ArrayAdapter<StrucTask> {

        public AdapterNote (ArrayList<StrucTask> array){

            super(G.context,R.layout.adapter_note,array);
        }

    public static class ViewHolder{

        TextView txtTitle;
        TextView txtDesc;
        CheckBox chkDone;
        LinearLayout clickLayout;
        Button btnClose;
        public ViewHolder(View view){


            txtTitle = (TextView)view.findViewById(R.id.txtTitle);
            txtDesc  = (TextView)view.findViewById(R.id.txtDesc);
            chkDone  = (CheckBox) view.findViewById(R.id.chkDone);
            clickLayout = (LinearLayout) view.findViewById(R.id.layoutClick);
            btnClose = (Button) view.findViewById(R.id.btnClose);
        }
        public void fill(final ArrayAdapter<StrucTask> adapter, final StrucTask item, final int position){



            txtTitle.setText(item.getTitle());
            txtDesc.setText(item.getDescription());
            chkDone.setChecked(item.isDone());
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityMain.tasks.remove(position);
                    adapter.notifyDataSetChanged();
                    ActivityMain.cDialog.removeItem(String.valueOf(item.getId()));
                }
            });

            clickLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   ActivityMain.cDialog.customDialog(position,"update",0);
                }
            });

            // ClickListener for CheckBox ( UPDATE : ListView and Database )
            chkDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, final boolean b) {

                    compoundButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            item.setDone(b);
                            ActivityMain.tasks.set(position,item);

                            String sDone="0";
                            if (b) {
                                sDone = "1";
                            }

                            ActivityMain.cDialog.setDone(String.valueOf(item.getId()),item.getTitle(),item.getDescription(),sDone);

                        }
                    });



                }




            });

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        StrucTask item= getItem(position);
        if (convertView==null) {
            convertView = G.inflater.inflate(R.layout.adapter_note, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.fill(this,item,position);
        return convertView;
    }
}
