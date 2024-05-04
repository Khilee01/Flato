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

public class signupPageActivity extends AppCompatActivity {
    //create object of Datarefernce class to access firebase database
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://travelapp-1a989-default-rtdb.firebaseio.com/");
    EditText full_name_edit_text,email_edit_text,password_edit_text,conpassword_edit_text;

    Button register_button,loginnow_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        full_name_edit_text=findViewById(R.id.full_name_edit_text);
        email_edit_text=findViewById(R.id.email_edit_text);
        password_edit_text=findViewById(R.id.password_edit_text);
        conpassword_edit_text=findViewById(R.id.con_password_edit_text);
        register_button=findViewById(R.id.register_button);
        loginnow_button=findViewById(R.id.loginnow_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full_name=full_name_edit_text.getText().toString();
                String email=email_edit_text.getText().toString();
                String password=password_edit_text.getText().toString();
                String conpassword=conpassword_edit_text.getText().toString();
                if(full_name.isEmpty()||email.isEmpty()||password.isEmpty()||conpassword.isEmpty()){
                    Toast.makeText(signupPageActivity.this,"Please fill all the details",Toast.LENGTH_LONG).show();
                }
                else if (!password.equals(conpassword)) {
                    Toast.makeText(signupPageActivity.this,"Passwords are not matching",Toast.LENGTH_LONG).show();
                }
                else{
                    //sending data to firebase database
                    databaseReference.child("users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(full_name)){
                                Toast.makeText(signupPageActivity.this,"Username is already registered",Toast.LENGTH_LONG).show();
                                loginnow_button.setOnClickListener(v->{
                                    Intent intent=new Intent(signupPageActivity.this,homeScreenActivity.class);
                                    startActivity(intent);
                                });
                            }
                            else{
                                databaseReference.child("users").child(full_name).child("email").setValue(email);
                                databaseReference.child("users").child(full_name).child("password").setValue(password);
                                full_name_edit_text.setText("");
                                email_edit_text.setText("");
                                password_edit_text.setText("");
                                conpassword_edit_text.setText("");
                                Toast.makeText(signupPageActivity.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
                                //show intent of next page
                                Intent intent=new Intent(signupPageActivity.this,formPageActivity.class);
                                startActivity(intent);
                                loginnow_button.setOnClickListener(v->{
                                    Intent intent2=new Intent(signupPageActivity.this,formPageActivity.class);
                                    startActivity(intent2);
                                });
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        loginnow_button.setOnClickListener(v->{
            Intent intent2=new Intent(signupPageActivity.this,formPageActivity.class);
            startActivity(intent2);
        });

    }
}