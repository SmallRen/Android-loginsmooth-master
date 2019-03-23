package com.wzh.study.history;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author sensor
 * @since 2019-03-14
 */
public class Sensor   {

    private Integer sId;

    /**
     * 心率
     */
    private String heartRate;

    /**
     * 体温
     */
    private String temperature;

    /**
     * 液位
     */
    private String liquidLevel;

    /**
     * 采集日期
     */
    private String collectDate;

    /**
     * 用户id
     */
    private Integer uId;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLiquidLevel() {
        return liquidLevel;
    }

    public void setLiquidLevel(String liquidLevel) {
        this.liquidLevel = liquidLevel;
    }

    public String getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(String collectDate) {
        this.collectDate = collectDate;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

}
