package com.liz.myapplication.enity;

public class Lists {
    private int dt;
    private Main main;
    private Weather weather;

    public Lists() {
    }

    public Lists(int dt, Main main, Weather weather) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Lists{" +
                "dt=" + dt +
                ", main=" + main +
                ", weather=" + weather +
                '}';
    }
}
