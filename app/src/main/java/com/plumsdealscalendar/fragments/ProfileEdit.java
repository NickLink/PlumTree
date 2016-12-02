package com.plumsdealscalendar.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.plumsdealscalendar.Const;
import com.plumsdealscalendar.R;
import com.plumsdealscalendar.database.UserDataImp;
import com.plumsdealscalendar.http.HttpRequest;
import com.plumsdealscalendar.http.RequestType;
import com.plumsdealscalendar.interfaces.UI_Interfaces;
import com.plumsdealscalendar.interfaces.UserDataInterfaces;
import com.plumsdealscalendar.models.login.Payload;
import com.plumsdealscalendar.utils.Converters;
import com.plumsdealscalendar.utils.Images;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;

import static android.app.Activity.RESULT_OK;

/**
 * Created by NickNb on 30.11.2016.
 */
public class ProfileEdit extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener , HttpRequest {
    private String TAG = getClass().getSimpleName();
    UI_Interfaces UIInterfaces;
    UserDataInterfaces userDataInterfaces;
    private Realm realm;
    private Payload payload;
    DatePickerDialog datePickerDialog;

    ImageView profile_photo, profile_imagebutton;
    EditText profile_name, profile_email, profile_phone;

    Button setdate_button;
    boolean new_image;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            UIInterfaces = (UI_Interfaces) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fr_profile_edit, container, false);

        if (savedInstanceState != null) {
            //Restoring fragment state
        }
        Log.d(TAG, "Data ProfileView onCreateView");

        profile_photo = (ImageView)view.findViewById(R.id.profile_photo);
        profile_imagebutton = (ImageView)view.findViewById(R.id.profile_imagebutton);
        profile_imagebutton.setOnClickListener(this);
        profile_name = (EditText)view.findViewById(R.id.profile_name);
        profile_email = (EditText)view.findViewById(R.id.profile_email);
        profile_phone = (EditText)view.findViewById(R.id.profile_phone);

        setdate_button = (Button) view.findViewById(R.id.setdate_button);
        setdate_button.setOnClickListener(this);
        ImageButton edit_button = (ImageButton)view.findViewById(R.id.edit_ok_button);
        edit_button.setOnClickListener(this);
        ImageButton back_button = (ImageButton)view.findViewById(R.id.back_button);
        back_button.setOnClickListener(this);

        userDataInterfaces = new UserDataImp();
        payload = userDataInterfaces.getPayload();
        payload.addChangeListener(new RealmChangeListener<RealmModel>() {
            @Override
            public void onChange(RealmModel element) {
                SetData();
            }
        });

        SetData();

        return view;
    }

    void SetData(){
        if(payload.getSaved_image() != null) {
            profile_photo.setImageBitmap(Images.decodeBase64(payload.getSaved_image()));
        }
        if(payload.getName() != null && !payload.getName().trim().isEmpty()) {
            profile_name.setText(payload.getName());
        }
        if(payload.getEmail() != null && !payload.getEmail().trim().isEmpty()){
            profile_email.setText(payload.getEmail());
        }
        if(payload.getPhone() != null && !payload.getPhone().trim().isEmpty()){
            profile_phone.setText(payload.getPhone());
        }
        if(payload.getGender() != null && !payload.getGender().trim().isEmpty()){
            if(payload.getGender().equals("f")) {
                //profile_gender.setText("Female");
            } else if(payload.getGender().equals("m")){
                //profile_gender.setText("Male");
            }
        } else {
            //profile_gender.setText("Not set");
        }

    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        setdate_button.setText(" " + day + " " + Converters.getMonth(month) + " " + year);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setdate_button:
                datePickerDialog = new DatePickerDialog(getActivity(), this, 2000, 11, 11);
                datePickerDialog.show();
                break;
            case R.id.edit_ok_button:
                //Save
                SavePayload();
                break;
            case R.id.back_button:
                //Exit
                UIInterfaces.onBackPressed();
                break;
            case R.id.profile_imagebutton:
                //Go to get Image
                GetImage();
                break;
        }
    }

    void SavePayload(){
        HashMap params = new HashMap<String, String>();
        params.put("email", "max@max.com");
        params.put("password", "123123");

        StringBuilder sb = new StringBuilder();
        sb.append(Const.API_Save_Profile);
        sb.append(payload.getUserId());
        sb.append(Const.API_Acess_token);
        sb.append(payload.getApiHash());

        RequestType login = new RequestType(getActivity(), 1, sb.toString(), this);
        login.StringPostRequest(params);


        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        payload.setName(profile_name.getText().toString());
        payload.setEmail(profile_email.getText().toString());
        payload.setPhone(profile_phone.getText().toString());

        realm.insertOrUpdate(payload);
        realm.commitTransaction();
    }

    void GetImage() {
        final String[] items = new String[]{"Take from camera",
                "Select from gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Image");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {
                    //pick from camera
                    PickFromCamera();
                } else {
                    //pick from file
                    PickFromFile();
                }
            }
        });
        builder.show();
    }

    //==========================================================================
    private String upload_file = "temp.jpg";
    private Uri mImageCaptureUri;
    private static final int PICK_FROM_CAMERA = 1;
    //private static final int CROP_FROM_CAMERA = 2;
    private static final int PICK_FROM_FILE = 3;
    String patch;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //upload_image = true;
        if (resultCode != getActivity().RESULT_OK)
            return;
        switch (requestCode) {
            case PICK_FROM_CAMERA:
                mImageCaptureUri = data.getData();
                DoCrop();
                break;
            case PICK_FROM_FILE:
                mImageCaptureUri = data.getData();
                DoCrop();
                //patch = getRealPathFromURI(mImageCaptureUri);
                break;
            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    profile_photo.setImageURI(result.getUri());
                    new_image = true;
                    //Uri resultUri = result.getUri();
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }

                break;
        }
        //photo_added = true;
    }

    private void PickFromFile(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, "Complete action using"),
                PICK_FROM_FILE);
    }

    private void PickFromCamera(){
        File upload = new File(upload_file);
        mImageCaptureUri = Uri.fromFile(upload);
        Intent intent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                mImageCaptureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    private void DoCrop(){
        CropImage.activity(mImageCaptureUri)
                .setAspectRatio(1, 1)
                .setAllowRotation(false)
                .setRequestedSize(200, 200)
                .start(getContext(), this);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getActivity(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void string_result(int type, String result) {

    }

    @Override
    public void image_result(int type, Bitmap bitmap) {

    }

    @Override
    public void http_error(int type, String error) {

    }
}
