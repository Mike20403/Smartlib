package com.app.smartlibhost.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.smartlibhost.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.microblink.MicroblinkSDK;
import com.microblink.entities.recognizers.Recognizer;
import com.microblink.entities.recognizers.RecognizerBundle;
import com.microblink.entities.recognizers.blinkid.documentface.DocumentFaceRecognizer;
import com.microblink.entities.recognizers.blinkid.generic.BlinkIdCombinedRecognizer;
import com.microblink.entities.recognizers.blinkid.imageresult.FullDocumentImageResult;
import com.microblink.intent.IntentDataTransferMode;
import com.microblink.uisettings.ActivityRunner;
import com.microblink.uisettings.DocumentUISettings;
import com.microblink.uisettings.UISettings;
import com.microblink.uisettings.options.BeepSoundUIOptions;
import com.microblink.uisettings.options.HelpIntentUIOptions;
import com.microblink.uisettings.options.OcrResultDisplayMode;
import com.microblink.uisettings.options.OcrResultDisplayUIOptions;
import com.squareup.picasso.Picasso;

public class MemberCardActivity extends AppCompatActivity  {

    Toolbar tb;
    ImageView cmnd,facereg;
    FirebaseStorage mstorage = FirebaseStorage.getInstance();
    StorageReference storageReference = mstorage.getReference();
    String Uid = FirebaseAuth.getInstance().getUid();

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_card);
        Anhxa();
        ActionToolbar();
        SetData();






    }

    private void SetData() {
        storageReference.child("UserInfo").child(Uid+"cmnd.png").getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                Picasso.with(getApplicationContext()).load( task.getResult().toString())
                        .placeholder(R.drawable.no_img)
                        .error(R.drawable.no_img)
                        .into(cmnd);
            }
        });
        storageReference.child("UserInfo").child(Uid+"face.png").getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                Picasso.with(getApplicationContext()).load( task.getResult().toString())
                        .placeholder(R.drawable.no_img)
                        .error(R.drawable.no_img)
                        .into(facereg);
            }
        });

    }




    public void ActionToolbar() {
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
    private void Anhxa() {
        tb = (Toolbar) findViewById(R.id.toolbar);
        cmnd =(ImageView) findViewById(R.id.CMND);
        facereg =(ImageView)  findViewById(R.id.faceregconize);
        facereg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*AlertDialog.Builder mBuilder = new AlertDialog.Builder(MemberCardActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.photoview);
                Picasso.with(getApplicationContext()).load(sach.getImg_sach()).
                        into(photoView);

                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();


                mDialog.show();
                // mDialog.getWindow().setLayout(1250, 1800);

*/
            }
        });
    }



}
