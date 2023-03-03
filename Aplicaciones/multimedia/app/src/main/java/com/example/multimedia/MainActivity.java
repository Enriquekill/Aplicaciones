package com.example.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayList<String> playlist = new ArrayList<>();


    private Spinner spn;
    private String video, videoAuxImp;
    private VideoView reproductor;

    private Button btn_import;
    private static final int REQUEST_CODE_PICK_VIDEO = 1;

    private static final int REQUEST_PICK_VIDEO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_PICK_VIDEO);

        video = "android.resource://"+getPackageName()+"/"+R.raw.amor;
        reproductor = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse(video);
        reproductor.setVideoURI(uri);

        MediaController mediaController =  new MediaController(this);
        reproductor.setMediaController(mediaController);
        mediaController.setAnchorView(reproductor);

        //cambiarVideo();
        //Playlist
        spn = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.playlist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(this);

        Button btnImport = findViewById(R.id.btn_import);
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("video/*");
                startActivityForResult(pickIntent, REQUEST_PICK_VIDEO);
            }
        });

    }

    public void cambiarVideo(){
        Uri uri = Uri.parse(video);
        reproductor.setVideoURI(uri);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                video = "android.resource://"+getPackageName()+"/"+R.raw.amor;
                break;
            case 1:
                video = "android.resource://"+getPackageName()+"/"+R.raw.microsoft;
                break;
            case 2:
                video = "android.resource://"+getPackageName()+"/"+R.raw.vaca;
                break;
            case 3:
                video = "android.resource://"+getPackageName()+"/"+R.raw.pixel;
                break;
            case 4:
                video = "android.resource://"+getPackageName()+"/"+R.raw.vscode;
                break;
            case 5:
                video = "android.resource://"+getPackageName()+"/"+R.raw.demons;
                break;
            case 6:
                video = videoAuxImp; // obtener el último video agregado a la lista de reproducción
                setTitle("Video " + (playlist.size()));
                break;
            default:
                video = "android.resource://"+getPackageName()+"/"+R.raw.amor;
        }
        cambiarVideo();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PICK_VIDEO:
                    try {
                        Uri uri = data.getData();
                        //reproductor.setVideoURI(uri);
                        videoAuxImp = getRealPathFromUri(uri);

                        updatePlaylist(videoAuxImp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }


    private void updatePlaylist(String videoPath) {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> playlist = preferences.getStringSet("playlist", new HashSet<>());
        playlist.add(videoPath);
        editor.putStringSet("playlist", playlist);
        editor.apply();

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spn.getAdapter();
        adapter.add(videoPath);
        adapter.notifyDataSetChanged();

        playlist.add(videoPath);
    }


    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    private String getFileNameFromPath(String path) {
        String[] pathSegments = path.split("/");
        String fileName = pathSegments[pathSegments.length - 1];
        Toast.makeText(this, fileName, Toast.LENGTH_SHORT).show();
        return fileName;
    }


}


