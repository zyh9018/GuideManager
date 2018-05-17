package com.zyh.guidemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zyh.guidemanager.dialog.DialogGuideBean;
import com.zyh.guidemanager.dialog.GuideDialog;
import com.zyh.guidemanager.fragment.DialogFragmentGuideBean;
import com.zyh.guidemanager.fragment.GuideDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<DialogGuideBean> mGuideBeanList = new ArrayList<>();
    private List<DialogFragmentGuideBean> mFragmentGuideBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init dialog data
        DialogGuideBean guideBean1 = new DialogGuideBean();
        guideBean1.setImgResId(R.drawable.dialog_guide_1);
        guideBean1.setTextResId(R.string.dialog_guide_text_1);
        mGuideBeanList.add(guideBean1);

        DialogGuideBean guideBean2 = new DialogGuideBean();
        guideBean2.setImgResId(R.drawable.dialog_guide_2);
        guideBean2.setTextResId(R.string.dialog_guide_text_2);
        mGuideBeanList.add(guideBean2);

        DialogGuideBean guideBean3 = new DialogGuideBean();
        guideBean3.setImgResId(R.drawable.dialog_guide_3);
        guideBean3.setTextResId(R.string.dialog_guide_text_3);
        mGuideBeanList.add(guideBean3);

        // init fragment data
        DialogFragmentGuideBean fragmentGuideBean1 = new DialogFragmentGuideBean();
        fragmentGuideBean1.setImgResId(R.drawable.fullscene_guide_1);
        mFragmentGuideBeanList.add(fragmentGuideBean1);

        DialogFragmentGuideBean fragmentGuideBean2 = new DialogFragmentGuideBean();
        fragmentGuideBean2.setImgResId(R.drawable.fullscene_guide_2);
        mFragmentGuideBeanList.add(fragmentGuideBean2);

        DialogFragmentGuideBean fragmentGuideBean3 = new DialogFragmentGuideBean();
        fragmentGuideBean3.setImgResId(R.drawable.fullscene_guide_3);
        mFragmentGuideBeanList.add(fragmentGuideBean3);
    }

    public void showDialogGuide(View view) {
        new GuideDialog(this, mGuideBeanList).show();
    }

    public void showFragmentGuide(View view) {
        GuideDialogFragment fragment = GuideDialogFragment.newInstance((ArrayList<DialogFragmentGuideBean>) mFragmentGuideBeanList);
        fragment.show(getSupportFragmentManager(), GuideDialogFragment.TAG);
    }
}
