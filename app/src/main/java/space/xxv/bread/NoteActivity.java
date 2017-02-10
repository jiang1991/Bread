package space.xxv.bread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import io.realm.RealmResults;
import space.xxv.bread.model.Note;

import io.realm.Realm;


public class NoteActivity extends AppCompatActivity {

    private EditText titleText;
    private Realm realm;
    private TextView status;

    private String noteTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        titleText = (EditText) findViewById(R.id.noteTitle);
        status = (TextView) findViewById(R.id.status);

        realm = Realm.getDefaultInstance();

        showNote();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void saveButtonClick(View view) {
        addNote();

        showNote();
        titleText.setText("");
    }


    public void addNote(){

        noteTitle = titleText.getText().toString();

        final Note note = new Note();
        note.setTitle(noteTitle);
        note.setDate(new Date());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(note);
            }
        });

    }

    public void showNote() {
//        final RealmResults<Note> notes = realm.where(Note.class).findAll();
        status.setText("number of notes: " + realm.where(Note.class).count());
    }
}
