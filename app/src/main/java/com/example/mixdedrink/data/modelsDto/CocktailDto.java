package com.example.mixdedrink.data.modelsDto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CocktailDto implements Parcelable {
    private String idDrink;
    private String strDrink;
    private String strDrinkAlternate = null;
    private String strTags;
    private String strVideo = null;
    private String strCategory;
    private String strIBA;
    private String strAlcoholic;
    private String strGlass;
    private String strInstructions;
    private String strInstructionsES = null;
    private String strInstructionsDE;
    private String strInstructionsFR = null;
    private String strInstructionsIT;
    private String strDrinkThumb;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5 = null;
    private String strIngredient6 = null;
    private String strIngredient7 = null;
    private String strIngredient8 = null;
    private String strIngredient9 = null;
    private String strIngredient10 = null;
    private String strIngredient11 = null;
    private String strIngredient12 = null;
    private String strIngredient13 = null;
    private String strIngredient14 = null;
    private String strIngredient15 = null;
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4 = null;
    private String strMeasure5 = null;
    private String strMeasure6 = null;
    private String strMeasure7 = null;
    private String strMeasure8 = null;
    private String strMeasure9 = null;
    private String strMeasure10 = null;
    private String strMeasure11 = null;
    private String strMeasure12 = null;
    private String strMeasure13 = null;
    private String strMeasure14 = null;
    private String strMeasure15 = null;
    private String strImageSource;

    //* Constructor //
    public CocktailDto(String idDrink, String strDrink, String strDrinkAlternate,
                       String strTags, String strVideo, String strCategory,
                       String strIBA, String strAlcoholic, String strGlass,
                       String strInstructions, String strInstructionsES, String strInstructionsDE,
                       String strInstructionsFR, String strInstructionsIT, String strDrinkThumb,
                       String strIngredient1, String strIngredient2, String strIngredient3,
                       String strIngredient4, String strIngredient5, String strIngredient6,
                       String strIngredient7, String strIngredient8, String strIngredient9,
                       String strIngredient10, String strIngredient11, String strIngredient12,
                       String strIngredient13, String strIngredient14, String strIngredient15,
                       String strMeasure1, String strMeasure2, String strMeasure3,
                       String strMeasure4, String strMeasure5, String strMeasure6,
                       String strMeasure7, String strMeasure8, String strMeasure9,
                       String strMeasure10, String strMeasure11, String strMeasure12,
                       String strMeasure13, String strMeasure14, String strMeasure15,
                       String strImageSource) {
        this.idDrink = idDrink;
        this.strDrink = strDrink;
        this.strDrinkAlternate = strDrinkAlternate;
        this.strTags = strTags;
        this.strVideo = strVideo;
        this.strCategory = strCategory;
        this.strIBA = strIBA;
        this.strAlcoholic = strAlcoholic;
        this.strGlass = strGlass;
        this.strInstructions = strInstructions;
        this.strInstructionsES = strInstructionsES;
        this.strInstructionsDE = strInstructionsDE;
        this.strInstructionsFR = strInstructionsFR;
        this.strInstructionsIT = strInstructionsIT;
        this.strDrinkThumb = strDrinkThumb;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strMeasure1 = strMeasure1;
        this.strMeasure2 = strMeasure2;
        this.strMeasure3 = strMeasure3;
        this.strMeasure4 = strMeasure4;
        this.strMeasure5 = strMeasure5;
        this.strMeasure6 = strMeasure6;
        this.strMeasure7 = strMeasure7;
        this.strMeasure8 = strMeasure8;
        this.strMeasure9 = strMeasure9;
        this.strMeasure10 = strMeasure10;
        this.strMeasure11 = strMeasure11;
        this.strMeasure12 = strMeasure12;
        this.strMeasure13 = strMeasure13;
        this.strMeasure14 = strMeasure14;
        this.strMeasure15 = strMeasure15;
        this.strImageSource = strImageSource;
    }

    //* Getter Methods *//

    public String getIdDrink() {
        return idDrink;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public String getStrTags() {
        return strTags;
    }

    public String getStrVideo() {
        return strVideo;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrIBA() {
        return strIBA;
    }

    public String getStrAlcoholic() {
        return strAlcoholic;
    }

    public String getStrGlass() {
        return strGlass;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public String getStrInstructionsES() {
        return strInstructionsES;
    }

    public String getStrInstructionsDE() {
        return strInstructionsDE;
    }

    public String getStrInstructionsFR() {
        return strInstructionsFR;
    }

    public String getStrInstructionsIT() {
        return strInstructionsIT;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public String getStrImageSource() {
        return strImageSource;
    }


    protected CocktailDto(Parcel in) {
        idDrink = in.readString();
        strDrink = in.readString();
        strDrinkAlternate = in.readString();
        strTags = in.readString();
        strVideo = in.readString();
        strCategory = in.readString();
        strIBA = in.readString();
        strAlcoholic = in.readString();
        strGlass = in.readString();
        strInstructions = in.readString();
        strInstructionsES = in.readString();
        strInstructionsDE = in.readString();
        strInstructionsFR = in.readString();
        strInstructionsIT = in.readString();
        strDrinkThumb = in.readString();
        strIngredient1 = in.readString();
        strIngredient2 = in.readString();
        strIngredient3 = in.readString();
        strIngredient4 = in.readString();
        strIngredient5 = in.readString();
        strIngredient6 = in.readString();
        strIngredient7 = in.readString();
        strIngredient8 = in.readString();
        strIngredient9 = in.readString();
        strIngredient10 = in.readString();
        strIngredient11 = in.readString();
        strIngredient12 = in.readString();
        strIngredient13 = in.readString();
        strIngredient14 = in.readString();
        strIngredient15 = in.readString();
        strMeasure1 = in.readString();
        strMeasure2 = in.readString();
        strMeasure3 = in.readString();
        strMeasure4 = in.readString();
        strMeasure5 = in.readString();
        strMeasure6 = in.readString();
        strMeasure7 = in.readString();
        strMeasure8 = in.readString();
        strMeasure9 = in.readString();
        strMeasure10 = in.readString();
        strMeasure11 = in.readString();
        strMeasure12 = in.readString();
        strMeasure13 = in.readString();
        strMeasure14 = in.readString();
        strMeasure15 = in.readString();
        strImageSource = in.readString();
    }

    public static final Creator<CocktailDto> CREATOR = new Creator<CocktailDto>() {
        @Override
        public CocktailDto createFromParcel(Parcel in) {
            return new CocktailDto(in);
        }

        @Override
        public CocktailDto[] newArray(int size) {
            return new CocktailDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(idDrink);
        parcel.writeString(strDrink);
        parcel.writeString(strDrinkAlternate);
        parcel.writeString(strTags);
        parcel.writeString(strVideo);
        parcel.writeString(strCategory);
        parcel.writeString(strIBA);
        parcel.writeString(strAlcoholic);
        parcel.writeString(strGlass);
        parcel.writeString(strInstructions);
        parcel.writeString(strInstructionsES);
        parcel.writeString(strInstructionsDE);
        parcel.writeString(strInstructionsFR);
        parcel.writeString(strInstructionsIT);
        parcel.writeString(strDrinkThumb);
        parcel.writeString(strIngredient1);
        parcel.writeString(strIngredient2);
        parcel.writeString(strIngredient3);
        parcel.writeString(strIngredient4);
        parcel.writeString(strIngredient5);
        parcel.writeString(strIngredient6);
        parcel.writeString(strIngredient7);
        parcel.writeString(strIngredient8);
        parcel.writeString(strIngredient9);
        parcel.writeString(strIngredient10);
        parcel.writeString(strIngredient11);
        parcel.writeString(strIngredient12);
        parcel.writeString(strIngredient13);
        parcel.writeString(strIngredient14);
        parcel.writeString(strIngredient15);
        parcel.writeString(strMeasure1);
        parcel.writeString(strMeasure2);
        parcel.writeString(strMeasure3);
        parcel.writeString(strMeasure4);
        parcel.writeString(strMeasure5);
        parcel.writeString(strMeasure6);
        parcel.writeString(strMeasure7);
        parcel.writeString(strMeasure8);
        parcel.writeString(strMeasure9);
        parcel.writeString(strMeasure10);
        parcel.writeString(strMeasure11);
        parcel.writeString(strMeasure12);
        parcel.writeString(strMeasure13);
        parcel.writeString(strMeasure14);
        parcel.writeString(strMeasure15);
        parcel.writeString(strImageSource);
    }

}
