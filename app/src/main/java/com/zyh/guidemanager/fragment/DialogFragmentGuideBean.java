package com.zyh.guidemanager.fragment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Bean for GuideDialogFragment can be extended.
 * <p>
 * Created by Oscar-Zhang on 2018/5/17.
 */
public class DialogFragmentGuideBean implements Parcelable {
    private int imgResId;

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public DialogFragmentGuideBean() {

    }

    protected DialogFragmentGuideBean(Parcel in) {
        imgResId = in.readInt();
    }

    public static final Creator<DialogFragmentGuideBean> CREATOR = new Creator<DialogFragmentGuideBean>() {
        @Override
        public DialogFragmentGuideBean createFromParcel(Parcel in) {
            return new DialogFragmentGuideBean(in);
        }

        @Override
        public DialogFragmentGuideBean[] newArray(int size) {
            return new DialogFragmentGuideBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgResId);
    }
}
