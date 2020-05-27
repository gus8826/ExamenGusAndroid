package com.examen.android.Models;

public class ItemMaps {

    private Float text_1;
    private Float text_2;

    public ItemMaps(Float text_1, Float text_2) {
        this.text_1 = text_1;
        this.text_2 = text_2;
    }

    public ItemMaps() {
    }

    public Float getText_1() {
        return text_1;
    }

    public void setText_1(Float text_1) {
        this.text_1 = text_1;
    }

    public Float getText_2() {
        return text_2;
    }

    public void setText_2(Float text_2) {
        this.text_2 = text_2;
    }
}
