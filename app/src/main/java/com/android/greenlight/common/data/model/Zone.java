package com.android.greenlight.common.data.model;

/**
 * @author Vivek
 * This model is common module used for Zone,area & region to store and display in app
 */

public class Zone {
    private int zoneId;
    private String zoneName;
    private String zoneTerritory;

    public Zone(int zoneId, String zoneName, String zoneTerritory) {
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.zoneTerritory = zoneTerritory;
    }

    public Zone(String zoneName, int zoneId) {
        this.zoneName = zoneName;
        this.zoneId = zoneId;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneTerritory() {
        return zoneTerritory;
    }

    public void setZoneTerritory(String zoneTerritory) {
        this.zoneTerritory = zoneTerritory;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "zoneId=" + zoneId +
                ", zoneName='" + zoneName + '\'' +
                ", zoneTerritory='" + zoneTerritory + '\'' +
                '}';
    }

}
