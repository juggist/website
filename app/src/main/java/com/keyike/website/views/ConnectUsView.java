package com.keyike.website.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.keyike.website.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

public class ConnectUsView extends LinearLayout {
    Context context;
    String[] telTypes = {"咨询电话:", "免费试镜:", "试镜地址:","了解我们", "报名咨询:","经纪合作:", "机构合作:", "品牌合作:", "商务合作:","邮箱:"};
    String[] telNums = {"021-65876552", "15802169501", "上海飞虹路525号8楼5258艺术空间", "","15802169501", "13774445688", "17765130994", "13918909462","021-65876552","84509288@qq.com"};
    TextView tv_tel_1, tv_tel_2, tv_tel_3, tv_tel_4, tv_tel_5, tv_tel_6, tv_tel_7,tv_tel_8,tv_tel_9,tv_tel_10;

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

    void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.component_connect_us, this);
        tv_tel_1 = view.findViewById(R.id.tv_tel_1);
        tv_tel_2 = view.findViewById(R.id.tv_tel_2);
        tv_tel_3 = view.findViewById(R.id.tv_tel_3);
        tv_tel_4 = view.findViewById(R.id.tv_tel_4);
        tv_tel_5 = view.findViewById(R.id.tv_tel_5);
        tv_tel_6 = view.findViewById(R.id.tv_tel_6);
        tv_tel_7 = view.findViewById(R.id.tv_tel_7);
        tv_tel_8 = view.findViewById(R.id.tv_tel_8);
        tv_tel_9 = view.findViewById(R.id.tv_tel_9);
        tv_tel_10 = view.findViewById(R.id.tv_tel_10);

        SpannableString sp1 = new SpannableString(telTypes[0] + telNums[0]);
        sp1.setSpan(new MySpanClick(0), 5, sp1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_1.setText(sp1);
        tv_tel_1.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp2 = new SpannableString(telTypes[1] + telNums[1]);
        sp2.setSpan(new MySpanClick(1), 5, sp2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_2.setText(sp2);
        tv_tel_2.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp3 = new SpannableString(telTypes[2] + telNums[2]);
        tv_tel_3.setText(sp3);

        SpannableString sp4 = new SpannableString(telTypes[3] + telNums[3]);
        sp4.setSpan(new MySpanClick(3), 0, sp4.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_4.setText(sp4);
        tv_tel_4.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp5 = new SpannableString(telTypes[4] + telNums[4]);
        sp5.setSpan(new MySpanClick(4), 5, sp5.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_5.setText(sp5);
        tv_tel_5.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp6 = new SpannableString(telTypes[5] + telNums[5]);
        sp6.setSpan(new MySpanClick(5), 5, sp6.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_6.setText(sp6);
        tv_tel_6.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp7 = new SpannableString(telTypes[6] + telNums[6]);
        sp7.setSpan(new MySpanClick(6), 5, sp7.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_7.setText(sp7);
        tv_tel_7.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp8 = new SpannableString(telTypes[7] + telNums[7]);
        sp8.setSpan(new MySpanClick(7), 5, sp8.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_8.setText(sp8);
        tv_tel_8.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp9 = new SpannableString(telTypes[8] + telNums[8]);
        sp9.setSpan(new MySpanClick(8), 5, sp9.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_9.setText(sp9);
        tv_tel_9.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp10 = new SpannableString(telTypes[9] + telNums[9]);
        sp10.setSpan(new MySpanClick(9), 3, sp10.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_tel_10.setText(sp10);
        tv_tel_10.setMovementMethod(LinkMovementMethod.getInstance());
    }

    class MySpanClick extends ClickableSpan {
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

            if(type == 0 || type == 1 || type == 4|| type == 5 ||type == 6||type == 7||type == 8){
                AndPermission.with(context).runtime().permission(Permission.CALL_PHONE).onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telNums[type]));
                        context.startActivity(intent);
                    }
                }).onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Toast.makeText(context,"请打开电话权限",Toast.LENGTH_LONG).show();
                    }
                }).start();
            }else if(type == 2){

            }else if(type == 9){
                sendEmail(context,"","",telNums[type]);
            }

        }


        /**
         * 邮件分享
         *
         * @param context 上下文
         * @param title   邮件主题
         * @param content 邮件内容
         * @param address 邮件地址
         */
        public void sendEmail(Context context, String title, String content, String address) {
            Uri uri = Uri.parse("mailto:" + address);
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
            // 设置对方邮件地址
            emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
            // 设置标题内容
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, title);
            // 设置邮件文本内容
            emailIntent.putExtra(Intent.EXTRA_TEXT, content);
            context.startActivity(Intent.createChooser(emailIntent, "选择邮箱"));
        }
    }
}
