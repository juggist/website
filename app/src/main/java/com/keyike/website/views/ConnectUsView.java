package com.keyike.website.views;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.keyike.website.R;

import java.util.List;

public class ConnectUsView extends LinearLayout {
    String[] telTypes = {"咨询电话:","免费试镜:","报名咨询:","经纪合作:","机构合作:","品牌合作:","商务合作:"};
    String[] telNums = {"021-6587","免费试镜","报名咨询","经纪合作","机构合作","品牌合作","商务合作"};
    TextView tv_tel_1,tv_tel_2,tv_tel_3,tv_tel_4,tv_tel_5,tv_tel_6,tv_tel_7;
    public ConnectUsView(Context context) {
        super(context);
        init(context);
    }

    public ConnectUsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ConnectUsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.component_connect_us,this);
        tv_tel_1 = view.findViewById(R.id.tv_tel_1);
        tv_tel_2 = view.findViewById(R.id.tv_tel_2);
        tv_tel_3 = view.findViewById(R.id.tv_tel_3);
        tv_tel_4 = view.findViewById(R.id.tv_tel_4);
        tv_tel_5 = view.findViewById(R.id.tv_tel_5);
        tv_tel_6 = view.findViewById(R.id.tv_tel_6);
        tv_tel_7 = view.findViewById(R.id.tv_tel_7);

        SpannableString sp1 = new SpannableString(telTypes[0] + telNums[0]);
        sp1.setSpan(new MySpanClick(1),6,sp1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_1.setMovementMethod(LinkMovementMethod.getInstance());
        tv_tel_1.setText(sp1.toString());
    }

    class MySpanClick extends ClickableSpan{
        int type;

        public MySpanClick(int type) {
            this.type = type;
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setUnderlineText(true);
        }

        @Override
        public void onClick(@NonNull View view) {
            Log.i("keyike","SpanClick type = " +type);
        }
    }
}
