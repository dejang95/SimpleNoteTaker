package team4.hci.simplenotetaker;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListViewNotes;
    Button pink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i= getIntent();

        getIntent().getStringExtra("color");



        mListViewNotes = (ListView) findViewById(R.id.main_notesListView);
    }

    // Inflating the menu from resources - Adding a new Item

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Intent i= getIntent();

        getIntent().getStringExtra("color");
        return true;
    }

    // Clicking on the button will call Note Activity


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_newNote:
                // start NoteActivity
                Intent newNoteActivity = new Intent(this, NoteActivity.class);
                startActivity(newNoteActivity);
                break;

            case R.id.change_color:
                // start NoteActivity
                Intent newNoteActivity2 = new Intent(this, ChangeColor.class);
                startActivity(newNoteActivity2);
                break;

            case R.id.settings:
                // start NoteActivity
                Intent newNoteActivity3 = new Intent(this, Settings.class);
                startActivity(newNoteActivity3);
                break;

            //start Color

        }




        return true;
    }




    @Override
    protected void onResume() {
        super.onResume();
        mListViewNotes.setAdapter(null);

        ArrayList<Note> notes = Utilities.getSavedNotes(this);

        if(notes == null || notes.size() == 0) {
            Toast.makeText(this, "You haven't saved any Notes yet!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            NoteAdapter noteAdapter = new NoteAdapter(this, R.layout.item_note, notes);
            mListViewNotes.setAdapter(noteAdapter);

            mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    String fileName = ((Note)mListViewNotes.getItemAtPosition(position)).getDateTime()
                            + Utilities.FILE_EXTENSION;

                    Intent viewNoteIntent = new Intent(getApplicationContext(), NoteActivity.class );
                    viewNoteIntent.putExtra("NOTE_FILE", fileName);
                    startActivity(viewNoteIntent);
                }
            });
        }
    }
}
