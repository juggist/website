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
    String[] telNums = {"021-65876552","免费试镜","报名咨询","经纪合作","机构合作","品牌合作","商务合作"};
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
        tv_tel_1.setText(sp1);
        tv_tel_1.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp2 = new SpannableString(telTypes[1] + telNums[1]);
        sp2.setSpan(new MySpanClick(2),6,sp2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_2.setText(sp2);
        tv_tel_2.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp3 = new SpannableString(telTypes[2] + telNums[2]);
        sp3.setSpan(new MySpanClick(3),6,sp3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_3.setText(sp3);
        tv_tel_3.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp4 = new SpannableString(telTypes[3] + telNums[3]);
        sp4.setSpan(new MySpanClick(4),6,sp4.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_4.setText(sp4);
        tv_tel_4.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp5 = new SpannableString(telTypes[4] + telNums[4]);
        sp5.setSpan(new MySpanClick(5),6,sp5.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_5.setText(sp5);
        tv_tel_5.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp6 = new SpannableString(telTypes[5] + telNums[5]);
        sp6.setSpan(new MySpanClick(6),6,sp6.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_6.setText(sp6);
        tv_tel_6.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp7 = new SpannableString(telTypes[6] + telNums[6]);
        sp7.setSpan(new MySpanClick(7),6,sp7.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_7.setText(sp7);
        tv_tel_7.setMovementMethod(LinkMovementMethod.getInstance());

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
