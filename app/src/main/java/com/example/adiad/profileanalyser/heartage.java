package com.example.adiad.profileanalyser;

import android.util.Log;

public class heartage {
    float smoke,bmi,cholesterol,bp,height,heightft,heightinch,weight;
    int age,bm,smk,habp,ch,heage;
    heartage(){
        this.smoke=0;
        this.bmi=0;
        this.cholesterol=0;
        this.bp=0;
        this.height=0;
        this.heightft=0;
        this.heightinch=0;
        this.age=0;
    }
    int bmi(int w, float hf, float hi){
        int rbmi=1;
        float a,b,c,d,e;
        weight=w;
        heightft=hf;
        heightinch=hi;
        a=heightft*12;
        b=heightinch+a;
        c = (float) (b * 2.54);
        height=c/100;
        bmi= (weight/(height*height));
// Log.e("health.java","dhb"+bmi);
        if(bmi>=20&&bmi<=24)
            rbmi=0;
        else if(bmi<20) {
            d = (bmi - 20) / 4;
            rbmi=Math.round(d);
        }
        else if(bmi>24)
        {
            e=(bmi-24)/4;
            rbmi=Math.round(e);
        }
        Log.e("health.java","bmi : "+rbmi);
        return rbmi;
    }
    int smoking(int a,float smke){
        int i=0;
        float q,w=0;
        if(smke==1)
            w=0;
        else if(smke>=0.2&&smke<=0.3)
            w= (float) 0.78;
        else if(smke>=0.3&&smke<=0.4)
            w=(float)0.66;
        else if(smke>=0.6&&smke<=0.7)
            w=(float)0.33;
        else if(smke==0)
            w = 1;
        q= (float) (0.24*a*w);
        i=Math.round(q);
        Log.e("health.java","smoke "+i);
        Log.e("health.java","smke "+smke);
        Log.e("health.java","w "+w);
        return i;
    }
    int bp(int a){
        int i=0;
        float x,y;
        if(a<120){
            x=(120-a)/7;
            i=Math.round(x);
        }
        else if(a>=120&&a<=127)
            i=0;
        else if(a>127)
        {
            y=(a-127)/7;
            i=Math.round(y);
        }
        Log.e("health.java","bp : "+i);
        return i;
    }
    int totalch(int b,int c){
        int i=0,j=0,k;
        if(b>=187&&b<=207)
            i=0;
        else if(b>=208&&b<=229)
            i=1;
        else if(b>=230&&b<=252)
            i=2;
        else if(b>=253&&b<=278)
            i=3;
        else if(b>=279&&b<=305)
            i=4;
        else if(b>=306&&b<=333)
            i=5;
        else if(b>=334&&b<=364)
            i=6;
        else if(b>=365&&b<=396)
            i=7;
        else if(b>=397&&b<=430)
            i=8;
        else if(b>=431&&b<=466)
            i=9;
        else if(b>=467&&b<=504)
            i=10;
        else if(b>=505)
            i=11;
        else if(b>=168&&b<=186)
            i=-1;
        else if(b>=150&&b<=167)
            i=-2;
        else if(b>=134&&b<=149)
            i=-3;
        else if(b>=119&&b<=133)
            i=-4;
        else if(b>=105&&b<=118)
            i=-5;
        else if(b>=92&&b<=104)
            i=-6;
        else if(b>=80&&b<=91)
            i=-7;
        else if(b>=70&&b<=79)
            i=-8;
        else if(b<=70)
            i=-9;
        if(c>=40&&c<=50)
            j=-1;
        else if(c>=51&&c<=60)
            j=-2;
        else if(c>=61&&c<=70)
            j=-3;
        else if(c>=71&&c<=80)
            j=-4;
        else if(c>=81&&c<=90)
            j=-5;
        else if(c>=91&&c<=100)
            j=-6;
        else if(c>=101)
            j=-7;
        else if(c>=35&&c<=39)
            j=1;
        else if(c>=30&&c<=34)
            j=2;
        else if(c>=26&&c<=29)
            j=3;
        else if(c>=22&&c<25)
            j=4;
        else if(c<22)
            j=5;
        k=i+j;
        Log.e("health.java","ch :"+k);
        return k;
    }
    int heart(int a,int b,int c,int d,int e)
    {
        heage=a+b+c+d+e;
        return heage;
    }
}
