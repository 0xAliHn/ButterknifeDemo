package com.android.butterknifedemo.view;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.butterknifedemo.R;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_SHORT;

//Eliminate findViewById calls by using @BindView on fields:
public class MainActivity extends AppCompatActivity {

    // Automatically finds each field by the specified ID.
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;

    /*Improved Resource Lookups*/
    //Eliminate resource lookups in your Java code by using resource annotations on fields:
    //The following resource types are available: BindArray, BindBitmap, BindBool,BindColor,BindDimen,BindDrawable,BindInt,BindString.
    // Here are example
    @BindString(R.string.app_name)
    String title;
    @BindDrawable(R.mipmap.ic_launcher)
    Drawable graphic;
    @BindColor(R.color.colorPrimary)
    int red; // int or ColorStateList field


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /*Improved Listener Attachments*/

    //Eliminate anonymous inner-classes for listeners by annotating methods with @OnClick and others:
    //The following event listeners are supported out of the box: OnClick, OnLongClick, OnEditorAction, OnFocusChange, OnItemClick, OnItemLongClick,
    // OnItemSelected, OnPageChange, OnTextChanged, OnTouch, OnCheckedChanged.
    // Here are example
    @OnClick(R.id.button1)
    public void sayHi(Button button) {
        Toast.makeText(this, "Submitted", LENGTH_SHORT).show();
    }

    //We can attach multiple views to the same listener with:
    @OnClick({R.id.button2, R.id.button3})
    public void multipleButtonHandler(Button btn) {
        Toast.makeText(this, "Multiple Button Handler", LENGTH_SHORT).show();
    }

}
