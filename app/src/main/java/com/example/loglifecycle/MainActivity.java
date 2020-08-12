package com.example.loglifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    /* Constant values for the names of each respective lifecycle callback */
    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    private static final String SAVE_LIFE_CYCLE_TEXTVIEW_CONTENT_KEY="LifeCycleCallbacks";
    private static final ArrayList<String> mLifecycleCallbacksList = new ArrayList<>();



    private TextView mLifecycleDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLifecycleDisplay = findViewById(R.id.tv_lifecycle_events_display);

       if(savedInstanceState!=null){
            if(savedInstanceState.containsKey(SAVE_LIFE_CYCLE_TEXTVIEW_CONTENT_KEY)){
                String allPreviousLifeCycleState=savedInstanceState
                .getString(SAVE_LIFE_CYCLE_TEXTVIEW_CONTENT_KEY);
                mLifecycleDisplay.setText("\nall previous instance state before onDestroy\n"+ allPreviousLifeCycleState);
                Log.d(TAG, "all previous instance state " + allPreviousLifeCycleState);
            }
        }


       Log.d(TAG, ON_CREATE);
       mLifecycleDisplay.append("Enter in onCreate \n"+ ON_CREATE+"\n" );

    }

   @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        logAndAppend(ON_SAVE_INSTANCE_STATE);
        String storeLifeCycleDisplayContent=mLifecycleDisplay.getText().toString();
        outState.putString(SAVE_LIFE_CYCLE_TEXTVIEW_CONTENT_KEY,storeLifeCycleDisplayContent);

    }



    private void logAndAppend(String lifecycleName) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleName);

        mLifecycleDisplay.append(lifecycleName + "\n");
    }

    /**
     * This method resets the contents of the TextView to its default text of "Lifecycle callbacks"
     *
     * @param view The View that was clicked. In this case, it is the Button from our layout.
     */
    public void buttonClick(View view) {
        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
    }



    @Override
    protected void onPause() {
        super.onPause();
        logAndAppend(ON_PAUSE);

    }


    @Override
    protected void onStop() {
        super.onStop();
        logAndAppend(ON_STOP);

    }

    // COMPLETED (6) Override onRestart, call super.onRestart, and call logAndAppend with ON_RESTART
    /**
     * Called after your activity has been stopped, prior to it being started again.
     *
     * Always followed by onStart()
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        logAndAppend(ON_RESTART);

    }

    // COMPLETED (7) Override onDestroy, call super.onDestroy, and call logAndAppend with ON_DESTROY
    /**
     * The final call you receive before your activity is destroyed. This can happen either because
     * the activity is finishing (someone called finish() on it, or because the system is
     * temporarily destroying this instance of the activity to save space. You can distinguish
     * between these two scenarios with the isFinishing() method.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        logAndAppend(ON_DESTROY);

    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndAppend(ON_RESUME);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndAppend(ON_START);
    }
}
