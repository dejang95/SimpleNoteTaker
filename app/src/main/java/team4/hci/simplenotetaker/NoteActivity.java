package team4.hci.simplenotetaker;

/* created by Dejan */

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;

    private EditText titleView;
    private EditText contentView;
    private ImageView imageView;

    private String noteFileName;
    private String currentPhotoPath;
    private Note loadedNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //Defining the Views in "acticity_note.xml"
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

    // For selecting the Icons and back button in Action Bar.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_menu_saveNote:
                saveNote();
                break;

            case R.id.action_menu_delete:
                deleteNote();
                break;

            case android.R.id.home:
                finish();
                return true;

            case R.id.action_menu_camera:
                dispatchTakePictureIntent();
                break;

        }

        return true;
    }

    // Deleting the single Note.
    private void deleteNote() {
        if (loadedNote == null) {
            Toast.makeText(this, "Save the note first! Right now there is nothing to delete! :/ ", Toast.LENGTH_SHORT).show();
            return;
        } else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete Dialog")
                    .setMessage("You are about to delete the following note: ''" + titleView.getText().toString() + "''. Are you sure?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Utilities.deleteNote(getApplicationContext(), loadedNote.getDateTime() + Utilities.FILE_EXTENSION);
                            Toast.makeText(getApplicationContext()
                                    , titleView.getText().toString() + " The Note has been deleted.", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    })
                    .setNegativeButton("no", null)
                    .setCancelable(false);

            dialog.show();

        }
    }

    // For saving the single Note.
    private void saveNote() {
        Note note;

        if (titleView.getText().toString().trim().isEmpty() || contentView.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter something in your note first, than save it! :) ", Toast.LENGTH_SHORT).show();
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


    // Creates Image File from taken photo
    private File createImageFile () throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    //Takes the photo and saves it on storage
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create The File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                //Error occurred

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    //Function that selects the name of the picture based on the time of its creation, and than returns it's name.
    /*
     * private String getPictureName() {
     * SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
     * String timeStamp = sdf.format(new Date());
     * return "EintragBild" + timeStamp + ".jpg";
     * }
     */

    // For displaying the taken picture and storing it temporary inside an ImageView of UserEmpty Activity.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap(imageBitmap);
        }
    }
}
