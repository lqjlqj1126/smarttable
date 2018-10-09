package com.fst.smarttable;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class Scrthrt implements Parcelable
{
    private String sid;

    private String prjid;

    private String name;

    private String subtp;

    private String desp;

    private String jcyj;

    private String level;

    private BigDecimal val;
    private boolean checked;
    public boolean getChecked()
    {
        return checked;
    }
    public void setChecked(boolean ck)
    {
        checked=ck;
    }
    public Scrthrt(){}
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getPrjid() {
        return prjid;
    }

    public void setPrjid(String prjid) {
        this.prjid = prjid == null ? null : prjid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSubtp() {
        return subtp;
    }

    public void setSubtp(String subtp) {
        this.subtp = subtp == null ? null : subtp.trim();
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp == null ? null : desp.trim();
    }

    public String getJcyj() {
        return jcyj;
    }

    public void setJcyj(String jcyj) {
        this.jcyj = jcyj == null ? null : jcyj.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public BigDecimal getVal() {
        return val;
    }

    public void setVal(BigDecimal val) {
        this.val = val;
    }
    @Override
    public int describeContents()
    {
        return 0;
    }
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(sid);
        out.writeString(prjid);
        out.writeString(name);
        out.writeString(subtp);
        out.writeString(jcyj);
        out.writeString(level);
        out.writeByte((byte) (checked ? 1 : 0));
        out.writeDouble(val.doubleValue());
    }
    public static final Creator<Scrthrt> CREATOR = new Creator<Scrthrt>()
     {
         public Scrthrt createFromParcel(Parcel in)
         {
             return new Scrthrt(in);
         }

         public Scrthrt[] newArray(int size)
         {
             return new Scrthrt[size];
         }
     };

     private Scrthrt(Parcel in)
     {

         sid=in.readString();
         prjid=in.readString();
         name=in.readString();
         subtp=in.readString();
         desp=in.readString();
         jcyj=in.readString();
         level=in.readString();
         double d=in.readDouble();
         val=new BigDecimal(d);
         checked=in.readByte() != 0;
     }
}