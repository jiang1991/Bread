package space.xxv.bread;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "space.xxv.bread.MESSAGE";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = (EditText) findViewById(R.id.edit_message);
    }

    /** Called when the user clicks the send button */
    public void httpRequest(View view){
        Intent intent = new Intent(this, NetworkActivity.class);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void startHome(View view){

        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }


    public void saveMessage(View view){
        String messOne = editText.getText().toString();

        SharedPreferences sharedPre = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor messEdit = sharedPre.edit();
        messEdit.putString(getString(R.string.saved_user_name), messOne);
        messEdit.commit();
    }


    public void showMessage(View view) {
        SharedPreferences userNamePre = getPreferences(Context.MODE_PRIVATE);
        String userName = userNamePre.getString(getString(R.string.saved_user_name), getString(R.string.apple_of_my_eye));

        editText.setText(userName);
    }

    public void StartNote(View view){
        Intent noteIntent = new Intent(this, NoteActivity.class);
        startActivity(noteIntent);
    }


}
