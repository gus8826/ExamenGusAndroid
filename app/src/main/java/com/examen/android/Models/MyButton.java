package com.examen.android.Models;

import com.examen.android.Layouts.MyListener;

public class MyButton {
    MyListener ml;

    public MyButton(MyListener ml) {
        this.ml = ml;
    }

    public void MyLogicToIntimateOthers() {
        ml.callback( "success");
    }
}