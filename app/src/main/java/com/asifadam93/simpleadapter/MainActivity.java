package com.asifadam93.simpleadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextLastName, editTextEmail;
    private List<HashMap<String, String>> hashMapList;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        // Default users
        User user1 = new User("Adam", "Asif", "asifadam93@hotmail.fr");
        User user2 = new User("Asharf", "Ali", "asharfali@hotmail.com");
        User user3 = new User("Babu", "Miah", "miah@gmail.com");

        addHashMapToAdapterList(createHashMapWithUser(user1));
        addHashMapToAdapterList(createHashMapWithUser(user2));
        addHashMapToAdapterList(createHashMapWithUser(user3));

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String email = editTextEmail.getText().toString();

                if (!name.isEmpty() && !lastName.isEmpty() && !email.isEmpty()) {
                    User tmpUser = new User(name, lastName, email);
                    addHashMapToAdapterList(createHashMapWithUser(tmpUser));
                    simpleAdapter.notifyDataSetChanged();
                }
            }
        });

        simpleAdapter = new SimpleAdapter(this, hashMapList, android.R.layout.simple_list_item_2, new String[]{"name", "email"}, new int[]{android.R.id.text1, android.R.id.text2});

        ListView listView = (ListView) findViewById(R.id.llv);
        listView.setAdapter(simpleAdapter);
    }

    private HashMap<String, String> createHashMapWithUser(User myUser) {
        HashMap<String, String> tempHashMap = new HashMap<>();
        tempHashMap.put("name", myUser.getFirstName() + " " + myUser.getLastName());
        tempHashMap.put("email", myUser.getEmail());
        return tempHashMap;
    }

    private void addHashMapToAdapterList(HashMap<String, String> hashMap) {

        if (hashMapList == null) {
            hashMapList = new ArrayList<>();
        }

        hashMapList.add(hashMap);
    }
}
