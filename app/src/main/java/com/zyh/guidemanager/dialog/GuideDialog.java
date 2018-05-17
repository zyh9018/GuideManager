package com.zyh.guidemanager.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyh.guidemanager.R;
import com.zyh.guidemanager.uitls.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Guide Dialog
 * <p>
 * Created by Oscar-Zhang on 2018/5/17.
 */
public class GuideDialog extends Dialog {

    private Context mContext;
    private ViewPager mGuideVp;
    private ImageView mCloseIv;
    private LinearLayout mSwitchLl;
    private List<ImageView> mSwitchIvList = new ArrayList<>();
    private List<View> mGuideViewList = new ArrayList<>();
    private static final int sMARGIN = DisplayUtils.dp(5);

    public GuideDialog(@NonNull Context context, List<DialogGuideBean> guideBeanList) {
        super(context);
        mContext = context;
        initData(guideBeanList);
        init();
    }

    private void init() {
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
        getWindow().getAttributes().gravity = Gravity.CENTER;

        setContentView(R.layout.dialog_guide);
        mGuideVp = findViewById(R.id.guide_vp);
        mSwitchLl = findViewById(R.id.guide_switch_ll);
        mCloseIv = findViewById(R.id.guide_close_iv);
        initSwitchIcon();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mGuideViewList);
        mGuideVp.setAdapter(viewPagerAdapter);
        mGuideVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectSwitchIcon(position); //选中底部圆点icon
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mCloseIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GuideDialog.this.isShowing()) {
                    dismiss();
                }
            }
        });
    }

    private void initData(List<DialogGuideBean> guideBeanList) {

        for (int i = 0, size = guideBeanList.size(); i < size; i ++) {
            DialogGuideBean guideBean = guideBeanList.get(i);
            int imgResId = guideBean.getImgResId();
            int textResId = guideBean.getTextResId();
            View guideView = getLayoutInflater().inflate(R.layout.guide_view_container, null);
            ImageView containerIv = guideView.findViewById(R.id.share_video_guide_container_iv);
            containerIv.setImageResource(imgResId);
            TextView containerTv = guideView.findViewById(R.id.share_video_guide_container_tv);
            containerTv.setText(textResId);
            mGuideViewList.add(guideView);
        }
    }

    private void initSwitchIcon() {
        if (mGuideViewList == null || mGuideViewList.size() == 0) {
            return;
        }
        for (int i = 0; i < mGuideViewList.size(); i ++) {
            ImageView iconIv = new ImageView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(sMARGIN, 0, sMARGIN, 0);
            iconIv.setLayoutParams(layoutParams);
            if (i == 0) {
                iconIv.setImageResource(R.drawable.shape_circle_black); //default select the first one
            } else {
                iconIv.setImageResource(R.drawable.shape_circle_gray);
            }
            mSwitchIvList.add(iconIv);
        }

        for (int i = 0; i < mSwitchIvList.size(); i ++) {
            mSwitchLl.addView(mSwitchIvList.get(i));
        }
    }

    private void selectSwitchIcon(int position) {
        if (position >= mSwitchIvList.size()) {
            return;
        }
        for (int i = 0, size = mSwitchIvList.size(); i < size; i ++) {
            ImageView imageView = mSwitchIvList.get(i);
            if (i == position) {
                imageView.setImageResource(R.drawable.shape_circle_black);  // 选中项为黑色
            } else {
                imageView.setImageResource(R.drawable.shape_circle_gray);   // 其余为灰色
            }
        }
    }

    class ViewPagerAdapter extends PagerAdapter {
        private List<View> mViewList;
        public ViewPagerAdapter(List<View> viewList) {
            mViewList = viewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
