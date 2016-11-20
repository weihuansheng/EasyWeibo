package com.microwei.easyweibo.util;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016-11-20 .
 */

public class TextViewHelper {
    private Pattern mTopicPattern=Pattern.compile("#.*?#");
    private Pattern mExpressionPattern=Pattern.compile("\\[.*?\\]");

    private ForegroundColorSpan mColorSpan=new ForegroundColorSpan(Color.BLUE);
    private TextViewHelper(){
    }
    public static TextViewHelper newInstance(){
        return new TextViewHelper();
    }
    public  void showTextBySpan(TextView textView, String text){
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder(text);
        Matcher mTopicMatcher=mTopicPattern.matcher(text);
        Matcher mExpressionMatcher=mExpressionPattern.matcher(text);
        updateSpannableSpan(spannableStringBuilder,mTopicMatcher);
        updateSpannableSpan(spannableStringBuilder,mExpressionMatcher);
        textView.setText(spannableStringBuilder);
    }
    private SpannableStringBuilder updateSpannableSpan(SpannableStringBuilder spannableStringBuilder,Matcher matcher){
        while (matcher.find()){
            spannableStringBuilder.setSpan(
                    mColorSpan,matcher.start(),matcher.end(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableStringBuilder;
    }
}
