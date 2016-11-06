package com.example.adiad.profileanalyser;

import android.util.Log;

public class health {

    float weight,heightft,heightinch,smoke,active,diet,diabetic,bp;
    float height,bmi;
    health(){
        this.weight=0;
        this.heightft=1;
        this.heightinch=1;
        this.height=0;
        this.bmi=0;
        this.smoke=0;
        this.active=0;
        this.diet=0;
        this.diabetic=0;
        this.bp=0;
    }
    float bmical(int w, float hf, float hi){
        float rbmi=1;
        float a,b,c;
        weight=w;
        heightft=hf;
        heightinch=hi;
        a=heightft*12;
        b=heightinch+a;
        c = (float) (b * 2.54);
        height=c/100;
        bmi= (weight/(height*height));
        Log.e("health.java","dhb"+bmi);
        if(bmi<19)
            rbmi=(float)0.55;
        else if(bmi>=19&&bmi<=25)
            rbmi=1;
        else if(bmi>25&&bmi<=30)
            rbmi=(float)0.45;
        else if(bmi>30)
            rbmi=0;
        return rbmi;
    }
}