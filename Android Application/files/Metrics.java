package com.example.test1;

public class Metrics {
    private String air_humidity;
    private String air_temp;
    private String basil_moist;
    private String lettuce_moist;
    private String onion_moist;
    private String water_ph;
    private String water_temp;

    public Metrics() {
    }

    public Metrics(String air_humidity, String air_temp, String basil_moist, String lettuce_moist, String onion_moist, String water_ph, String water_temp) {
        this.air_humidity = air_humidity;
        this.air_temp = air_temp;
        this.basil_moist = basil_moist;
        this.lettuce_moist = lettuce_moist;
        this.onion_moist = onion_moist;
        this.water_ph = water_ph;
        this.water_temp = water_temp;
    }

    public String getAir_humidity() {
        return air_humidity;
    }

    public void setAir_humidity(String air_humidity) {
        this.air_humidity = air_humidity;
    }

    public String getAir_temp() {
        return air_temp;
    }

    public void setAir_temp(String air_temp) {
        this.air_temp = air_temp;
    }

    public String getBasil_moist() {
        return basil_moist;
    }

    public void setBasil_moist(String basil_moist) {
        this.basil_moist = basil_moist;
    }

    public String getLettuce_moist() {
        return lettuce_moist;
    }

    public void setLettuce_moist(String lettuce_moist) {
        this.lettuce_moist = lettuce_moist;
    }

    public String getOnion_moist() {
        return onion_moist;
    }

    public void setOnion_moist(String onion_moist) {
        this.onion_moist = onion_moist;
    }

    public String getWater_ph() {
        return water_ph;
    }

    public void setWater_ph(String water_ph) {
        this.water_ph = water_ph;
    }

    public String getWater_temp() {
        return water_temp;
    }

    public void setWater_temp(String water_temp) {
        this.water_temp = water_temp;
    }

}
