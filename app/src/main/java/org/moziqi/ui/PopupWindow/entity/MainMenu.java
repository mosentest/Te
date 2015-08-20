package org.moziqi.ui.PopupWindow.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by moziqi on 15-8-20.
 */
public class MainMenu implements Parcelable {
    private int imgId;//图片
    private String content;//内容

    public MainMenu() {
    }

    public MainMenu(Parcel in) {
        imgId = in.readInt();
        content = in.readString();
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgId);
        dest.writeString(content);
    }

    //public static final一个都不能少，内部对象CREATOR的名称也不能改变，必须全部大写。
    public static final Creator<MainMenu> CREATOR = new Creator<MainMenu>() {

        @Override
        public MainMenu createFromParcel(Parcel source) {
            return new MainMenu(source);
        }

        @Override
        public MainMenu[] newArray(int size) {
            return new MainMenu[size];
        }
    };
}
