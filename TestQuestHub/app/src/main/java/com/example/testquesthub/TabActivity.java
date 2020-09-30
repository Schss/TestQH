package com.example.testquesthub;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testquesthub.Models.Quest;
import com.example.testquesthub.adapter.QuestAdapter;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TabActivity extends AppCompatActivity {

    Button add_quest;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference quests;
    DatabaseReference mPostReference;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseListAdapter mAdapter;
    ScrollView scrollView;
    List<String> keys;
    RecyclerView questRec;
    QuestAdapter questAdapter;

    private static final String TAG = "TabActivity";

    LinearLayout root;
    ListView ListUserTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);

        add_quest = findViewById(R.id.addQuest);

        root = findViewById(R.id.root_element2);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        quests = db.getReference("Quests");

        add_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewQuest();
            }
        });
        questRec = findViewById(R.id.questList);
        questAdapter = new QuestAdapter();
        questRec.setAdapter(questAdapter);
        questRec.setLayoutManager(new LinearLayoutManager(this));


        quests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Quest> questList = new ArrayList<Quest>();
                for (DataSnapshot elem : snapshot.getChildren()) {
                    if (elem != null) {
                        questList.add(elem.getValue(Quest.class));
                    }
                }
                questAdapter.refresh(questList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void createNewQuest() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Создать квест");
        dialog.setMessage("Введите все данные для создания квеста");

        LayoutInflater inflater = LayoutInflater.from(this);
        View quest_window = inflater.inflate(R.layout.new_record_create, null);
        dialog.setView(quest_window);

        final MaterialEditText address = quest_window.findViewById(R.id.addressField);
        final MaterialEditText info = quest_window.findViewById(R.id.infoField);
        final MaterialEditText codeword = quest_window.findViewById(R.id.codewordField);
        final MaterialEditText name = quest_window.findViewById(R.id.nameQField);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Готово", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(address.getText().toString())) {
                    Snackbar.make(root, "Введите адресс", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(codeword.getText().toString())) {
                    Snackbar.make(root, "Введите кодовое слово", Snackbar.LENGTH_SHORT).show();
                    return;
                }


                Quest quest = new Quest();
                quest.setAd(address.getText().toString());
                quest.setInfo(info.getText().toString());
                quest.setCodeword(codeword.getText().toString());
                quest.setName(name.getText().toString());
                quest.setUserId(user.getUid());

                quests.child(UUID.randomUUID() + "").setValue(quest);

            }
        });
        dialog.show();
    }

    private void openOSM() {
        Intent intent = new Intent(this, com.example.testquesthub.ui.osm.OSM.class);
        startActivity(intent);
    }
}
