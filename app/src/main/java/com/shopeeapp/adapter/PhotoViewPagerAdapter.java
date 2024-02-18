package com.shopeeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.shopeeapp.R;
import com.shopeeapp.entity.slide;

import java.util.List;

public class PhotoViewPagerAdapter extends PagerAdapter {
    private List<slide> mListphoto;

    public PhotoViewPagerAdapter(List<slide> mListphoto) {
        this.mListphoto = mListphoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide,container,false);
        ImageView imgSlide=view.findViewById(R.id.img_slide);
         slide slide=mListphoto.get(position);
         imgSlide.setImageResource(slide.getResouceId());
         container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(mListphoto!=null){
            return mListphoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
