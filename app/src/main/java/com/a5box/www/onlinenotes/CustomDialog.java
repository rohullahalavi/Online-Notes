package com.a5box.www.onlinenotes;


import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class CustomDialog {




    public  void customDialog(final int position, final String what, final int lastId) {
        final Dialog dialog = new Dialog(G.currentActivity);
        dialog.setContentView(R.layout.dialog_add_edit);
        Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
        final EditText edtSetTitle = (EditText) dialog.findViewById(R.id.edtSetTitle);
        final EditText edtSetDesc = (EditText) dialog.findViewById(R.id.edtSetDesc);

        if (what=="update") {
            StrucTask task = ActivityMain.tasks.get(position);
            edtSetTitle.setText(task.getTitle());
            edtSetDesc.setText(task.getDescription());
        }


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String title = edtSetTitle.getText().toString();
                String desc = edtSetDesc.getText().toString();

                switch (what){

                    case "insert":
                        StrucTask note = new StrucTask();
                        note.setTitle(title);
                        note.setDescription(desc);
                        note.setDone(false);
                        note.setId(lastId+1);

                        ActivityMain.tasks.add(note);

                        addNewTask(title,desc,String.valueOf(lastId+1));
                        break;

                    case "update":

                        StrucTask task = ActivityMain.tasks.get(position);

                        String id = String.valueOf(task.getId());

                        task.setTitle(title);
                        task.setDescription(desc);

                        editTask(id,title,desc,"0");
                        break;



                }
                ActivityMain.adapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    private  void addNewTask(String title, String desc,String lastID){
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", lastID);
        params.put("title", title);
        params.put("desc", desc);

        G.service.connection(ActivityMain.URL,"insert",params);
    }


    private void editTask(String id,String title,String desc,String done){
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        params.put("title", title);
        params.put("desc", desc);
        params.put("done", done);

        G.service.connection(ActivityMain.URL,"update",params);
    }

    public void removeItem(String id){

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        G.service.connection(ActivityMain.URL,"delete",params);
    }



    public void setDone(String id,String title,String desc,String done){
        Map<String, String> params = new HashMap<String, String>();

        params.put("id", id);
        params.put("title", title);
        params.put("desc", desc);
        params.put("done", done);

        G.service.connection(ActivityMain.URL,"update",params);
    }


}
