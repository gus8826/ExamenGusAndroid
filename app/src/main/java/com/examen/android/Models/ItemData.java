package com.examen.android.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemData implements Parcelable {

    String code;
    boolean success;
    String message;
    private ItemUbicaciones itemUbicaciones;

    public ItemData(){}


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ItemUbicaciones getItemUbicaciones() {
        return itemUbicaciones;
    }

    public void setItemUbicaciones(ItemUbicaciones itemUbicaciones) {
        this.itemUbicaciones = itemUbicaciones;
    }
    protected ItemData(Parcel in) {
        code = in.readString();
        success = in.readByte() != 0;
        message = in.readString();
    }

    public static final Creator<ItemData> CREATOR = new Creator<ItemData>() {
        @Override
        public ItemData createFromParcel(Parcel in) {
            return new ItemData(in);
        }

        @Override
        public ItemData[] newArray(int size) {
            return new ItemData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeString(message);
    }
}
