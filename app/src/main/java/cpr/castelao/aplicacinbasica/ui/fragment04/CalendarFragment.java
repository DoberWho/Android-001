package cpr.castelao.aplicacinbasica.ui.fragment04;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import cpr.castelao.aplicacinbasica.R;


public class CalendarFragment extends Fragment {

    TextView txt;
    CalendarView cal;
    EditText edtDate, edtDate2;
    Button btn;

    public CalendarFragment() {
    }

    public static CalendarFragment newInstance(String urlVideo) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frg_calendar, container, false);;

        initWidgets(view);
        initData();

        return view;
    }

    private void initWidgets(View view) {
        cal     = view.findViewById(R.id.frg_calendar_cal);
        edtDate = view.findViewById(R.id.frg_calendar_edt);
        edtDate2 = view.findViewById(R.id.frg_calendar_edt2);
        txt     = view.findViewById(R.id.frg_calendar_txt);
        btn     = view.findViewById(R.id.frg_calendar_btn);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d("CALENDAR","Year:"+year+"-"+(1+month)+"-"+dayOfMonth);
            }
        });

        edtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("CALENDAR","FOCUS:"+hasFocus);
            }
        });

        edtDate2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    handled = true;
                }
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    String txt = edtDate2.getText().toString();

                }
                return handled;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edtDate.getText().toString();
                txt.setText(str);
            }
        });


    }

    private void initData() {


    }

}
