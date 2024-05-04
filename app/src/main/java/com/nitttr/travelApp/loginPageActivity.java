package com.nitttr.travelApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginPageActivity extends AppCompatActivity {
    EditText username_edit_text,password_edit_text;
    Button signupButton,login_button;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        username_edit_text=findViewById(R.id.username_edit_text);
        password_edit_text=findViewById(R.id.password_edit_text);
        signupButton= findViewById(R.id.nxtPgBtn);
        login_button=findViewById(R.id.login_button);
        login_button.setOnClickListener(v->{
            String username=username_edit_text.getText().toString();
            String password=password_edit_text.getText().toString();
            if(username.isEmpty() && password.isEmpty()){
                Toast.makeText(loginPageActivity.this, "Not Entered Username or password",Toast.LENGTH_LONG).show();
            }
            else{
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //check if username exists in firebase database
                        if(snapshot.hasChild(username)){
                            //if exists in firebase
                            //getting password of user from firebase database and match it with user entered password
                            String getPassword=snapshot.child(username).child("password").getValue(String.class);
                            if(getPassword.equals(password)){
                                Toast.makeText(loginPageActivity.this,"Successfully Loged In",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(loginPageActivity.this,formPageActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(loginPageActivity.this,"Wrong Password",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(loginPageActivity.this,"SignUp user not registered",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open sign-up page here
                Intent intent = new Intent(loginPageActivity.this, signupPageActivity.class);
                startActivity(intent);
            }
        });


    }
}