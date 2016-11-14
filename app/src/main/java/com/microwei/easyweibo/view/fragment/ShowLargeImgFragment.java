package com.microwei.easyweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.microwei.easyweibo.R;

/**
 * Created by Administrator on 2016-05-04 .
 */
public class ShowLargeImgFragment extends Fragment implements View.OnClickListener{
    private String largePicUrl;
    public void setLargePicUrl(String largePicUrl){
        this.largePicUrl=largePicUrl;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View showLargeImgFragmentView=inflater.inflate(R.layout.fragment_show_large_img, null);
        SimpleDraweeView showLargeImageView=(SimpleDraweeView)showLargeImgFragmentView.findViewById(R.id.showLargeImageView);
        showLargeImageView.setImageURI(largePicUrl);
        showLargeImageView.setOnClickListener(this);
        return showLargeImgFragmentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showLargeImageView:
                getActivity().finish();
        }
    }
}
