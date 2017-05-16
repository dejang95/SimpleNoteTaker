package team4.hci.simplenotetaker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;


    private EditText titleView;
    private EditText contentView;

    private ImageView imageView;

    private String noteFileName;
    private Note loadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);





        titleView = (EditText) findViewById(R.id.note_Title);
        contentView = (EditText) findViewById(R.id.note_Content);
        imageView = (ImageView) findViewById(R.id.note_Image);

        noteFileName = getIntent().getStringExtra("NOTE_FILE");
        if (noteFileName != null && !noteFileName.isEmpty()) {
            loadedNote = Utilities.getNoteByName(this, noteFileName);

            if (loadedNote != null) {
                titleView.setText(loadedNote.getTitle());
                contentView.setText(loadedNote.getContent());
            }
        }

    }

    // Inflating the menu from resources - Adding a new Item

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_new, menu);
        return true;
    }



    // Clicking on the button will call save Note

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_menu_saveNote:
                saveNote();
                break;

            case R.id.action_menu_delete:
                deleteNote();
                break;

            case R.id.home:
                finish();
                return true;

            case R.id.action_menu_camera:
                dispatchTakePictureIntent();
                break;
            case android.R.id.home:
                this.finish();
                break;


        }


        return true;
    }

    private void deleteNote() {
        if (loadedNote == null) {
            Toast.makeText(this, "Save the note first! Right now there is nothing to delete! :/ ", Toast.LENGTH_SHORT).show();
            return;
        } else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete Dialog")
                    .setMessage("You are about to delete " + titleView.getText().toString() + ", sure?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Utilities.deleteNote(getApplicationContext(), loadedNote.getDateTime() + Utilities.FILE_EXTENSION);
                            Toast.makeText(getApplicationContext()
                                    , titleView.getText().toString() + " Note was deleted.", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    })
                    .setNegativeButton("no", null)
                    .setCancelable(false);

            dialog.show();

        }
    }

    private void saveNote() {
        Note note;

        if (titleView.getText().toString().trim().isEmpty() || contentView.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter something in your title or your content :) ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (loadedNote == null) {
            note = new Note(System.currentTimeMillis(), titleView.getText().toString(),
                    contentView.getText().toString());
        } else {
            note = new Note(loadedNote.getDateTime(), titleView.getText().toString()
                    , contentView.getText().toString());
        }
        if (Utilities.saveNote(this, note)) {
            Toast.makeText(this, "Your note has been saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note couldn't be saved, please make sure u have enough space on your device! :)"
                    , Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
