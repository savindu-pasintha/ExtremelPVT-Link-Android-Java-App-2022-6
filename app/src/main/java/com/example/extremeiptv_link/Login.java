
package com.example.extremeiptv_link;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {

    private String username, password;
    TextInputLayout usernameTextInputLayout,passwordTextInputLayout;
    TextInputEditText usernameEditText, passwordEditText;
    Button loginBtn;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firestore = FirebaseFirestore.getInstance();


        usernameTextInputLayout =(TextInputLayout)findViewById(R.id.usernameTextinputLayout);
        passwordTextInputLayout= (TextInputLayout)findViewById(R.id.passwordTextinputLayout);
        usernameEditText=(TextInputEditText)findViewById(R.id.usernameEditText);
        passwordEditText=(TextInputEditText)findViewById(R.id.passwordEditText);
        loginBtn = (Button)findViewById(R.id.lbtn);
        loginBtn.setClickable(true);

    }

    public void requestFocus(View view){
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public boolean validate_username(){
        if(usernameEditText.getText().toString().trim().isEmpty()){
            usernameTextInputLayout.setError("Enter User Name !");
            requestFocus(usernameEditText);
            return false;
        }else{
            if(usernameEditText.getText().toString().trim().length()<3){
                usernameTextInputLayout.setError("Minimum 3 characters !");
                requestFocus(usernameEditText);
                return false;
            }else {
                usernameTextInputLayout.setErrorEnabled(false);
            }
        }
        return true;
    }

    public boolean validate_password(){
        if(passwordEditText.getText().toString().trim().isEmpty()){
            passwordTextInputLayout.setError("Enter Password !");
            requestFocus(usernameEditText);
            return false;
        }else{
            if(passwordEditText.getText().toString().trim().length()<6){
                passwordTextInputLayout.setError("Minimum 6 characters !");
                requestFocus(usernameEditText);
                return false;
            }else {
                passwordTextInputLayout.setErrorEnabled(false);
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void loginBtnClick(View view){
        loginBtn.setTextColor(getColor(R.color.black));
        Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_LONG).show();
        if(validate_password() && validate_username()){
            if((usernameEditText.getText().toString().trim()).equals("admin123")&&(passwordEditText.getText().toString().trim()).equals("admin123")){
                alertMethod("Login","Your Login Was Successful");
                usernameEditText.setText("");
                passwordEditText.setText("");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }else{
                firestore_data_read();
            }
        }else{
            loginBtn.setTextColor(getColor(R.color.white));
        }
    }


    public void submitForm(String resName,String resPass,String resStatus,int shopId, String deviceKey){
        String status = "SUCCESS";

        if(!validate_password()){return;}
        if(!validate_username()){return;}

        username = usernameEditText.getText().toString().trim();
        password=passwordEditText.getText().toString().trim();
        if(username.equals(resName)&&password.equals(resPass)){
            alertMethod("Login","Success !");
            if(status.equals(resStatus)){
                //profile_add(shopId,deviceKey);
            }else{
                alertMethod("Insert Failed", "");
            }
        }else{
            alertMethod("Login","Failed !");
        }

    }

    public void firestore_data_save(){
        Map<String,Object> users=new HashMap<>();
        users.put("userName",usernameEditText.getText().toString().trim());
        users.put("password",passwordEditText.getText().toString().trim());
        firestore.collection("accounts").document(usernameEditText.getText().toString().trim())
                .set(users)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"User Details Successfully saved",Toast.LENGTH_LONG).show();
                        usernameEditText.setText("");
                        passwordEditText.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    }
                });

        //save auto generated doc id with
        /*
        firestore.collection("users").add(users).addOnSuccessListener(
                new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                    }
                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_LONG).show();
            }
        });
*/
    }
    public void firestore_data_read(){
        DocumentReference docRef = firestore.collection("accounts").document(usernameEditText.getText().toString().trim());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if((passwordEditText.getText().toString().trim()).equals(document.getData().get("password").toString())){
                            alertMethod("Login","Your Login Was Successful");
                            usernameEditText.setText("");
                            passwordEditText.setText("");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 2000);
                        }else{
                            alertMethod("Login","Your Login Was Failed");
                        }

                    } else {
                        Toast.makeText(getApplicationContext(),"No Such Username",Toast.LENGTH_LONG).show();
                    }
                } else {
                    alertMethod("Login","Error Or Incorrect Username");
                }
            }
        });
    }
    public void alertMethod(String title,String msg){
        try{
            //1-builder to a set alert values
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(title);
            alertDialogBuilder.setMessage(msg);
            //2- execute alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }catch(Exception e){
            Log.e("error alertMethod()",e.getMessage().toString());
        }

    }



}