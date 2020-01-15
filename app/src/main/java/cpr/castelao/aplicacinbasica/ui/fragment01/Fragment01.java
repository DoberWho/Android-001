package cpr.castelao.aplicacinbasica.ui.fragment01;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return inflater.inflate(R.layout.frg_fragment01, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FrgViewModel.class);
        // TODO: Use the ViewModel
    }

}
