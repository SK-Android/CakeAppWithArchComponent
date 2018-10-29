package com.androidapp.mcs.cakeappwitharchcomp.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Model class for both Room Database and Retrofit
//Room use ->Primary Key, @NonNull, @Entity
//Retrofit use -> @SerializeName,@Expose (generated automatically in pojo converter)

@Entity
public class Cakes {
        @PrimaryKey()
        @SerializedName("title")
        @Expose
        @NonNull
        private String title;

        @SerializedName("desc")
        @Expose
        private String desc;

        @SerializedName("image")
        @Expose
        private String image;


    public Cakes(@NonNull String title, String desc, String image) {
            this.title = title;
            this.desc = desc;
            this.image = image;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    @Override
    public String toString() {
        return "Cakes{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
