package com.sjm.cardiomems.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.sjm.cardiomems.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN   = "com.sjm.cardiomems.android.geoquiz.answer_shown";
    private static final String KEY_CHEAT_SHOWN_BOOL = "cheat_bool";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private boolean mAnswerWasShown;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null) {
            mAnswerWasShown = savedInstanceState.getBoolean(KEY_CHEAT_SHOWN_BOOL, false);
            setAnswerShownResult(mAnswerWasShown);
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.answer_text_view);

        Button showAnswerButton = (Button)findViewById(R.id.show_answer_button);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button_string);
                } else {
                    mAnswerTextView.setText(R.string.false_button_string);
                }
                setAnswerShownResult(true);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CHEAT_SHOWN_BOOL, mAnswerWasShown);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        mAnswerWasShown = isAnswerShown;

        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

}
