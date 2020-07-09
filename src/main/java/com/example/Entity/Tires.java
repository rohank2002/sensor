package com.example.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;
@Entity
public class Tires {
    /*
    "tires": {
      "frontLeft": 34,
      "frontRight": 36,
      "rearLeft": 29,
      "rearRight": 34
   }
     */
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(columnDefinition = "VARCHAR(2)")
    private int  frontLeft;
    @Column(columnDefinition = "VARCHAR(2)")
    private int  frontRight;
    @Column(columnDefinition = "VARCHAR(2)")
    private int rearLeft;
    @Column(columnDefinition = "VARCHAR(2)")
    private int rearRight;

    public Tires(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public int getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(int frontLeft) {
        this.frontLeft = frontLeft;
    }

    public int getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(int frontRight) {
        this.frontRight = frontRight;
    }

    public int getRearLeft() {
        return rearLeft;
    }

    public void setRearLeft(int rearLeft) {
        this.rearLeft = rearLeft;
    }

    public int getRearRight() {
        return rearRight;
    }

    public void setRearRight(int rearRight) {
        this.rearRight = rearRight;
    }

    @Override
    public String toString() {
        return "Tires{" +
                "frontLeft=" + frontLeft +
                ", frontRight=" + frontRight +
                ", rearLeft=" + rearLeft +
                ", rearRight=" + rearRight +
                '}';
    }
}
