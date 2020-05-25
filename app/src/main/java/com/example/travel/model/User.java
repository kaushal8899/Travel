package com.example.travel.model;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("_id")
    @Expose
    /* renamed from: id */
    private String f99id;
    @SerializedName("isvalid")
    @Expose
    private String isvalid;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("type")
    @Expose
    private String type;


    public User() {
    }


    public User(String id, String name2, String email2, String mobile2, String password2, String type2, String isvalid2, String path) {
        this.f99id = id;
        this.name = name2;
        this.email = email2;
        this.mobile = mobile2;
        this.password = password2;
        this.type = type2;
        this.isvalid = isvalid2;
    }

    public String getId() {
        return this.f99id;
    }

    public void setId(String id) {
        this.f99id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email2) {
        this.email = email2;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile2) {
        this.mobile = mobile2;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password2) {
        this.password = password2;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type2) {
        this.type = type2;
    }

    public String getIsvalid() {
        return this.isvalid;
    }

    public void setIsvalid(String isvalid2) {
        this.isvalid = isvalid2;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", (Object) this.f99id).append("name", (Object) this.name).append("email", (Object) this.email).append("mobile", (Object) this.mobile).append("password", (Object) this.password).append("type", (Object) this.type).append("isvalid", (Object) this.isvalid).toString();
    }
}
