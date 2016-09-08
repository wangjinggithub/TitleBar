package com.trywang.titlebar.views;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.trywang.titlebar.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TitleBar模板
 * Created by Try on 2016/9/5.
 * @description 可以设置字体颜色，图片，背景以及点击事件
 * Email wjfang_email@163.com
 * GitHub https://github.com/wangjinggithub
 * 使用方法如下：
 * 1、默认显示左边的图片返回键和中间文字：Try
 * <com.trywang.titlebar.views.XTitleBar
 *  android:id="@+id/title_bar"
 *  android:layout_width="match_parent"
 *  android:layout_height="wrap_content"/>
 *  2、设置了中间字体文字为：带有点击事件；标题左边默认返回键；标题右边显示并设置图片；添加点击事件
 *  <com.trywang.titlebar.views.XTitleBar
 *  xmlns:custom="http://schemas.android.com/apk/res-auto"
 *  android:layout_width="match_parent"
 *  android:layout_height="wrap_content"
 *  custom:TextCenter="带有点击事件"
 *  custom:isShowRight="true"
 *  custom:srcRight="@mipmap/icon_share"
 *  custom:onClickLeft="onClickLeft"
 *  custom:onClickCenter="onClickCenter"
 *  custom:onClickRight="onClickRight"/>
 *  更多的属性设置查看styles.xml中TitleBar
 *  {@link R.styleable#TitleBar}
 *  {@link R.style#TitleBarDefaultStyle}
 */
public class XTitleBar extends FrameLayout {
    @BindView(R.id.tv_title_left)
    TextView mTxtLeft;
    @BindView(R.id.tv_title_center)
    TextView mTxtCenter;
    @BindView(R.id.tv_title_right)
    TextView mTxtRight;
    @BindView(R.id.fl_title_left)
    FrameLayout mLayLeft;
    @BindView(R.id.fl_title_center)
    FrameLayout mLayCenter;
    @BindView(R.id.fl_title_right)
    FrameLayout mLayRight;
    @BindView(R.id.iv_title_bg)
    ImageView mImgBg;
    @BindView(R.id.iv_title_left)
    ImageView mImgLeft;
    @BindView(R.id.iv_title_right)
    ImageView mImgRight;

    private Context mContext;
    private boolean isShowImgLeft = true;
    private boolean isShowImgRight = true;
    private boolean isShowLeft = true;
    private boolean isShowRight = false;
    private Drawable mDrawLeft;
    private Drawable mDrawRight;
    private Drawable mDrawBg;
    /** 标题左边点击监听事件 */
    TitleBarLeftListener mTitleBarLeftListener;
    /** 标题中间点击监听事件 */
    TitleBarCenterListener mTitleBarCenterListener;
    /** 标题右边点击监听事件 */
    TitleBarRightListener mTitleBarRightListener;

    public TitleBarLeftListener getmTitleBarLeftListener() {
        return mTitleBarLeftListener;
    }

    public void setmTitleBarLeftListener(TitleBarLeftListener mTitleBarLeftListener) {
        this.mTitleBarLeftListener = mTitleBarLeftListener;
    }

    public TitleBarCenterListener getmTitleBarCenterListener() {
        return mTitleBarCenterListener;
    }

    public void setmTitleBarCenterListener(TitleBarCenterListener mTitleBarCenterListener) {
        this.mTitleBarCenterListener = mTitleBarCenterListener;
    }

    public TitleBarRightListener getmTitleBarRightListener() {
        return mTitleBarRightListener;
    }

    public void setmTitleBarRightListener(TitleBarRightListener mTitleBarRightListener) {
        this.mTitleBarRightListener = mTitleBarRightListener;
    }

    public XTitleBar(Context context) {
        this(context, null);
    }
    public XTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public XTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        String mTextLeft = null;
        String mTextCenter = null;
        String mTextRight = null;

        int mTextSizeLeft = 12;
        int mTextSizeCenter = 18;
        int mTextSizeRight = 12;

        int mTextColorLeft = Color.WHITE;
        int mTextColorCenter = Color.WHITE;
        int mTextColorRight = Color.WHITE;
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, R.style.TitleBarDefaultStyle);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.TitleBar_TextLeft:
                    mTextLeft = a.getString(attr);
                    break;
                case R.styleable.TitleBar_TextCenter:
                    mTextCenter = a.getString(attr);
                    break;
                case R.styleable.TitleBar_TextRight:
                    mTextRight = a.getString(attr);
                    break;
                case R.styleable.TitleBar_TextColorLeft:
                    mTextColorLeft = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TitleBar_TextColorCenter:
                    mTextColorCenter = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TitleBar_TextColorRight:
                    mTextColorRight = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TitleBar_TextSizeLeft:
                    mTextSizeLeft = a.getDimensionPixelSize(attr,12);
                    break;
                case R.styleable.TitleBar_TextSizeCenter:
                    mTextSizeCenter = a.getDimensionPixelSize(attr,18);
                    break;
                case R.styleable.TitleBar_TextSizeRight:
                    mTextSizeRight = a.getDimensionPixelSize(attr,12);
                    break;
                case R.styleable.TitleBar_isShowImgLeft:
                    isShowImgLeft = a.getBoolean(attr,true);
                    break;
                case R.styleable.TitleBar_isShowImgRight:
                    isShowImgRight = a.getBoolean(attr,true);
                    break;
                case R.styleable.TitleBar_isShowLeft:
                    isShowLeft = a.getBoolean(attr,true);
                    break;
                case R.styleable.TitleBar_isShowRight:
                    isShowRight = a.getBoolean(attr,false);
                    break;
                case R.styleable.TitleBar_srcLeft:
                    mDrawLeft = a.getDrawable(attr);
                    break;
                case R.styleable.TitleBar_srcRight:
                    mDrawRight = a.getDrawable(attr);
                    break;
                case R.styleable.TitleBar_bg:
                    mDrawBg = a.getDrawable(attr);
                    break;
                case R.styleable.TitleBar_onClickLeft:
                    if (context.isRestricted()) {
                        throw new IllegalStateException("The xxxxx:onClickLeft attribute cannot "
                                + "be used within a restricted context");
                    }

                    final String handlerNameLeft = a.getString(attr);
                    if (handlerNameLeft != null) {
                        setmTitleBarLeftListener(new TitleBarLeftListener() {
                            @Override
                            public void onClickTitleLeftListener(View v) {
                                new DeclaredOnClickListener(mTxtLeft, handlerNameLeft).onClick(v);
                            }
                        });
                    }
                    break;
                case R.styleable.TitleBar_onClickCenter:
                    if (context.isRestricted()) {
                        throw new IllegalStateException("The xxxxx:onClickCenter attribute cannot "
                                + "be used within a restricted context");
                    }

                    final String handlerNameCenter = a.getString(attr);
                    if (handlerNameCenter != null) {
                        setmTitleBarCenterListener(new TitleBarCenterListener() {
                            @Override
                            public void onClickTitleCenterListener(View v) {
                                new DeclaredOnClickListener(mTxtCenter, handlerNameCenter).onClick(v);
                            }
                        });
                    }
                    break;
                case R.styleable.TitleBar_onClickRight:
                    Log.d("TAG","TitleBar_onClickRight");
                    if (context.isRestricted()) {
                        throw new IllegalStateException("The xxxxx:onClickRight attribute cannot "
                                + "be used within a restricted context");
                    }

                    final String handlerName = a.getString(attr);
                    Log.d("TAG","TitleBar_onClickRight handlerName = " + handlerName);
                    if (handlerName != null) {
                        setmTitleBarRightListener(new TitleBarRightListener() {

                            @Override
                            public void onClickTitleRightListener(View v) {
                                Log.d("TAG","onClickTitleRightListener DeclaredOnClickListener");
                                new DeclaredOnClickListener(mTxtRight, handlerName).onClick(v);
                            }
                        });
                    }
                    break;
            }
        }

        a.recycle();
        init(context);
        setTextLeft(mTextLeft);
        setTextCentent(mTextCenter);
        setTextRight(mTextRight);
        setTextColorLeft(mTextColorLeft);
        setTextColorCentent(mTextColorCenter);
        setTextColorRight(mTextColorRight);
        setTextSizeLeft(mTextSizeLeft);
        setTextSizeCenter(mTextSizeCenter);
        setTextSizeRight(mTextSizeRight);
    }

    public void init(Context c){
        this.mContext = c;
        LayoutInflater.from(mContext).inflate(R.layout.activity_title,this,true);
        ButterKnife.bind(this);
        //设置是否显示title左右两边
        mLayLeft.setVisibility(isShowLeft ? VISIBLE : GONE);
        mLayRight.setVisibility(isShowRight ? VISIBLE : GONE);

        setShowImgLeft();
        setShowImgRight();

        mImgBg.setImageDrawable(mDrawBg);
        mImgLeft.setImageDrawable(mDrawLeft);
        mImgRight.setImageDrawable(mDrawRight);

        initListener();
    }

    /**
     * 初始化点击监听
     */
    private void initListener(){
        mLayLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTitleBarLeftListener != null) {

                    mTitleBarLeftListener.onClickTitleLeftListener(v);
                }
            }
        });
        mLayCenter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTitleBarCenterListener != null) {
                    mTitleBarCenterListener.onClickTitleCenterListener(v);
                }
            }
        });
        mLayRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTitleBarRightListener != null) {
                    Log.d("TAG","mTitleBarRightListener.onClickTitleRightListener ");
                    mTitleBarRightListener.onClickTitleRightListener(v);
                }
            }
        });
    }

    public boolean isShowImgLeft() {
        return isShowImgLeft;
    }

    public void setShowImgLeft(boolean showImgLeft) {
        isShowImgLeft = showImgLeft;
        setShowImgLeft();
    }

    public boolean isShowImgRight() {
        return isShowImgRight;
    }

    public void setShowImgRight(boolean showImgRight) {
        isShowImgRight = showImgRight;
        setShowImgRight();
    }

    public boolean isShowLeft() {
        return isShowLeft;
    }

    public void setShowLeft(boolean showLeft) {
        isShowLeft = showLeft;
        //设置是否显示title左边
        mLayLeft.setVisibility(isShowLeft ? VISIBLE : GONE);
    }

    public boolean isShowRight() {
        return isShowRight;
    }

    public void setShowRight(boolean showRight) {
        isShowRight = showRight;
        //设置是否显示title右边
        mLayRight.setVisibility(isShowRight ? VISIBLE : GONE);
    }

    /**
     * title左边显示文字还是图片
     */
    private void setShowImgLeft() {
        if (isShowImgLeft) {
            mImgLeft.setVisibility(VISIBLE);
            mTxtLeft.setVisibility(GONE);
        }else{
            mImgLeft.setVisibility(GONE);
            mTxtLeft.setVisibility(VISIBLE);
        }
    }

    /**
     * title右边显示文字还是图片
     */
    private void setShowImgRight() {
        if (isShowImgRight) {
            mImgRight.setVisibility(VISIBLE);
            mTxtRight.setVisibility(GONE);
        }else{
            mImgRight.setVisibility(GONE);
            mTxtRight.setVisibility(VISIBLE);
        }
    }

    public void setTextLeft(String textLeft){
        mTxtLeft.setText(isEmpty(textLeft) ? "" : textLeft);
    }
    public void setTextCentent(String textCenter){
        mTxtCenter.setText(isEmpty(textCenter) ? "" : textCenter);
    }
    public void setTextRight(String textRight){
        mTxtRight.setText(isEmpty(textRight) ? "" : textRight);
    }
    public void setTextColorLeft(int colorLeft){
        mTxtLeft.setTextColor(colorLeft);
    }
    public void setTextColorCentent(int colorCenter){
        mTxtCenter.setTextColor(colorCenter);
    }
    public void setTextColorRight(int colorRight){
        mTxtRight.setTextColor(colorRight);
    }
    public void setTextSizeLeft(int sizeLeft){
        mTxtLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX,sizeLeft);
    }
    public void setTextSizeCenter(int sizeCenter){
        mTxtCenter.setTextSize(TypedValue.COMPLEX_UNIT_PX,sizeCenter);
    }
    public void setTextSizeRight(int sizeRight){
        mTxtRight.setTextSize(TypedValue.COMPLEX_UNIT_PX,sizeRight);
    }
    public void setBg(int resource){
        try {
            mDrawBg = mContext.getResources().getDrawable(resource);
        } catch (Exception e) {
            mDrawBg = null;
            Log.d("TAG","资源没有找到！");
        }
        mImgBg.setImageDrawable(mDrawBg);
    }
    public void setImgLeft(int resource){
        try {
            mDrawLeft = mContext.getResources().getDrawable(resource);
        } catch (Exception e) {
            mDrawLeft = null;
            Log.d("TAG","资源没有找到！");
        }
        mImgLeft.setImageDrawable(mDrawLeft);
    }
    public void setImgRight(int resource){
        try {
            mDrawRight = mContext.getResources().getDrawable(resource);
        } catch (Exception e) {
            mDrawRight = null;
            Log.d("TAG","资源没有找到！");
        }
        mImgRight.setImageDrawable(mDrawRight);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public boolean isEmpty(String str){
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    public interface TitleBarLeftListener{
        void onClickTitleLeftListener(View v);
    }
    public interface TitleBarCenterListener{
        void onClickTitleCenterListener(View v);
    }
    public interface TitleBarRightListener{
        void onClickTitleRightListener(View v);
    }

    /**
     * An implementation of OnClickListener that attempts to lazily load a
     * named click handling method from a parent or ancestor context.
     */
    private static class DeclaredOnClickListener implements OnClickListener {
        private final View mHostView;
        private final String mMethodName;

        private Method mMethod;

        public DeclaredOnClickListener(View hostView, String methodName) {
            mHostView = hostView;
            mMethodName = methodName;
        }

        @Override
        public void onClick( View v) {
            if (mMethod == null) {
                mMethod = resolveMethod(mHostView.getContext(), mMethodName);
            }

            try {
                mMethod.invoke(mHostView.getContext(), v);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(
                        "Could not execute non-public method for xxxxx:onClickxxxx", e);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(
                        "Could not execute method for xxxxx:onClickxxxx", e);
            }
        }

        private Method resolveMethod(Context context, String name) {
            while (context != null) {
                try {
                    if (!context.isRestricted()) {
                        return context.getClass().getMethod(mMethodName, View.class);
                    }
                } catch (NoSuchMethodException e) {
                    // Failed to find method, keep searching up the hierarchy.
                }

                if (context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                } else {
                    // Can't search up the hierarchy, null out and fail.
                    context = null;
                }
            }

            final int id = mHostView.getId();
            final String idText = id == NO_ID ? "" : " with id '"
                    + mHostView.getContext().getResources().getResourceEntryName(id) + "'";
            throw new IllegalStateException("Could not find method " + mMethodName
                    + "(View) in a parent or ancestor Context for xxxxx:onClickxxxx "
                    + "attribute defined on view " + mHostView.getClass() + idText);
        }
    }
}
