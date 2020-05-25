package com.example.travel.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class Train{
  @SerializedName("duration")
  @Expose
  private Integer duration;
  @SerializedName("train_num")
  @Expose
  private Integer train_num;
  @SerializedName("src")
  @Expose
  private String src;
  @SerializedName("train_name")
  @Expose
  private String train_name;
  @SerializedName("classes")
  @Expose
  private List<Integer> classes;
  @SerializedName("days")
  @Expose
  private String days;
  @SerializedName("arr_time")
  @Expose
  private String arr_time;
  @SerializedName("dest_time")
  @Expose
  private String dest_time;
  @SerializedName("dest")
  @Expose
  private String dest;
  public Train(){
  }
  public Train(Integer duration,Integer train_num,String src,String train_name,List<Integer> classes,String days,String arr_time,String dest_time,String dest){
   this.duration=duration;
   this.train_num=train_num;
   this.src=src;
   this.train_name=train_name;
   this.classes=classes;
   this.days=days;
   this.arr_time=arr_time;
   this.dest_time=dest_time;
   this.dest=dest;
  }
  public void setDuration(Integer duration){
   this.duration=duration;
  }
  public Integer getDuration(){
   return duration;
  }
  public void setTrain_num(Integer train_num){
   this.train_num=train_num;
  }
  public Integer getTrain_num(){
   return train_num;
  }
  public void setSrc(String src){
   this.src=src;
  }
  public String getSrc(){
   return src;
  }
  public void setTrain_name(String train_name){
   this.train_name=train_name;
  }
  public String getTrain_name(){
   return train_name;
  }
  public List<Integer> getClasses(){
   return classes;
  }
  public void setDays(String days){
   this.days=days;
  }
  public String getDays(){
   return days;
  }
  public void setArr_time(String arr_time){
   this.arr_time=arr_time;
  }
  public String getArr_time(){
   return arr_time;
  }
  public void setDest_time(String dest_time){
   this.dest_time=dest_time;
  }
  public String getDest_time(){
   return dest_time;
  }
  public void setDest(String dest){
   this.dest=dest;
  }
  public String getDest(){
   return dest;
  }
}