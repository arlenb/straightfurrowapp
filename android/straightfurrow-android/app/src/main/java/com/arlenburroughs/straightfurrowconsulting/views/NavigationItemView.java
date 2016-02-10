package com.arlenburroughs.straightfurrowconsulting.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.arlenburroughs.straightfurrowconsulting.R;

/**
 * Created by Arlen on 2/10/16.
 */
public class NavigationItemView extends FrameLayout {

    ImageView leftImage;
    TextView itemTitle;
    ImageView rightImage;

    public NavigationItemView(Context context) {
        super(context);
        sharedConstructor(context);
    }

    public NavigationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context);
    }

    public void sharedConstructor(Context context){
        LayoutInflater.from(context).inflate(R.layout.item_navigation_drawer,this,true);

        leftImage = (ImageView) findViewById(R.id.left_image);
        itemTitle = (TextView) findViewById(R.id.item_title);
        rightImage = (ImageView) findViewById(R.id.right_image);
    }

    public void setTitle(String title){
        itemTitle.setText(title);
    }

    public void setAsSelectedItem(boolean shouldSet){
        if(shouldSet){
            int primaryColor = getResources().getColor(R.color.primary_color);
            itemTitle.setTextColor(primaryColor);
            leftImage.setColorFilter(primaryColor);
        }else{
            itemTitle.setTextColor(Color.BLACK);
            leftImage.setColorFilter(Color.TRANSPARENT);
        }
    }

}
