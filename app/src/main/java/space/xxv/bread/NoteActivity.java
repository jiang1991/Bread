package space.xxv.bread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import io.realm.RealmResults;
import space.xxv.bread.model.Note;

import io.realm.Realm;


public class NoteActivity extends AppCompatActivity {

    private EditText titleText;
    private Realm realm;
    private TextView status;

    private RealmResults<Note> notes;

    private String noteTitle;

    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        setupViews();

        realm = Realm.getDefaultInstance();


        updateNote();
    }

    protected void setupViews() {
        titleText = (EditText) findViewById(R.id.noteTitle);
        status = (TextView) findViewById(R.id.status);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.note_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteAdapter = new NoteAdapter(noteClickListener);

        recyclerView.setAdapter(noteAdapter);
    }

    public void updateNote() {
        notes = realm.where(Note.class).findAll();

        status.setText("Notes Number:" + notes.size());

        noteAdapter.setNote(notes);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void saveButtonClick(View view) {
        addNote();

        titleText.setText("");

        updateNote();
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

    NoteAdapter.NoteClickListener noteClickListener = new NoteAdapter.NoteClickListener() {
        @Override
        public void onNoteClick(int position) {
            Note note = noteAdapter.getNote(position);



            updateNote();
        }
    };

}
