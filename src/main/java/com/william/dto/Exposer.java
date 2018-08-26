package com.william.dto;

/**
 * Created by Baozhikuan on 2018/8/19.
 */
public class Exposer {
    private boolean exposed;

    private String md5;

    private long goodId;

    private long now;

    private long start;

    private long end;

    public Exposer(boolean exposed, String md5, long goodId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.goodId = goodId;
    }

    public Exposer(boolean exposed, long goodId, long now, long start, long end) {
        this.exposed = exposed;
        this.goodId=goodId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long goodId) {
        this.exposed = exposed;
        this.goodId = goodId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", goodId=" + goodId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
