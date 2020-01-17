package cpr.castelao.aplicacinbasica.ui.fragment03;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import cpr.castelao.aplicacinbasica.R;


public class VideoFragment extends Fragment {

    TextView txt;
    VideoView video;
    ImageButton btnPlay, btnPause, btnRev;

    String url = "";
    public static final String VIDEO_URL = "url_del_video_a_mostrar";
    public VideoFragment() {
    }

    public static VideoFragment newInstance(String urlVideo) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(VIDEO_URL, urlVideo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(VIDEO_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frg_video, container, false);;

        initWidgets(view);
        initData();

        return view;
    }

    private void initWidgets(View view) {
        txt = view.findViewById(R.id.frg_video_lbl);
        video = view.findViewById(R.id.frg_video_video);

        btnPlay = view.findViewById(R.id.video_play_btn);
        btnPause = view.findViewById(R.id.video_play_btn);
        btnRev = view.findViewById(R.id.video_play_btn);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video.canPause()){
                    video.resume();
                }else{
                    video.start();
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video.isPlaying()){
                    video.pause();
                }
            }
        });

        btnRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.seekTo(0);
            }
        });

    }

    private void initData() {
        video.setVideoPath(url);
        txt.setText(url);



    }

}
