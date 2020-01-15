package cpr.castelao.aplicacinbasica.ui.fragment01;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import cpr.castelao.aplicacinbasica.R;

public class Fragment01 extends Fragment {

    private FrgViewModel mViewModel;

    public static Fragment01 newInstance() {
        return new Fragment01();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frg_fragment01, container, false);
        initButtons(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FrgViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initButtons(View view) {
        Button btn = view.findViewById(R.id.frg_fragment01_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "FRG01", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
