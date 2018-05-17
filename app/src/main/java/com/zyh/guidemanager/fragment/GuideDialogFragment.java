package com.zyh.guidemanager.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyh.guidemanager.R;
import com.zyh.guidemanager.dialog.DialogGuideBean;
import com.zyh.guidemanager.uitls.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Guide DialogFragment
 * <p>
 * Created by Oscar-Zhang on 2018/05/17.
 */
public class GuideDialogFragment extends DialogFragment {
    public static final String TAG = "GuideDialogFragment";
    private static final String ARGUMENT_KEY_DATA_LIST = "argument_key_data_list";
    private Context mContext;
    private ViewPager mGuideVp;
    private LinearLayout mSwitchLl;
    private List<ImageView> mSwitchIvList = new ArrayList<>();
    private List<View> mGuideViewList = new ArrayList<>();
    private static final int sMARGIN = DisplayUtils.dp(5);

    public static GuideDialogFragment newInstance(ArrayList<DialogFragmentGuideBean> guideBeanList) {
        GuideDialogFragment guideDialogFragment = new GuideDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARGUMENT_KEY_DATA_LIST, guideBeanList);
        guideDialogFragment.setArguments(bundle);
        return guideDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = this.getContext();
        Bundle arguments = getArguments();
        if (arguments != null) {
            ArrayList<DialogFragmentGuideBean> dataList = arguments.getParcelableArrayList(ARGUMENT_KEY_DATA_LIST);
            initData(dataList);
        }

        mGuideVp = view.findViewById(R.id.guide_vp);
        mSwitchLl = view.findViewById(R.id.guide_switch_ll);
        initSwitchIcon();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mGuideViewList);
        mGuideVp.setAdapter(viewPagerAdapter);
        mGuideVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectSwitchIcon(position); //select switch icon
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    private void initData(ArrayList<DialogFragmentGuideBean> fragmentGuideBeanList) {

        for (int i = 0, size = fragmentGuideBeanList.size(); i < size; i ++) {
            DialogFragmentGuideBean guideBean = fragmentGuideBeanList.get(i);
            int imgResId = guideBean.getImgResId();
            View guideView = getLayoutInflater().inflate(R.layout.fragment_guide_view_container, null);
            ImageView containerIv = guideView.findViewById(R.id.fragment_guide_container_iv);
            containerIv.setImageResource(imgResId);
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
                imageView.setImageResource(R.drawable.shape_circle_black);  // black is the selected one
            } else {
                imageView.setImageResource(R.drawable.shape_circle_gray);   // others are gray
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
