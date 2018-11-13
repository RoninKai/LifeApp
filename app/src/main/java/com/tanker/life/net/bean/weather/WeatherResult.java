package com.tanker.life.net.bean.weather;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/06
 * @describe : 聚合数据天气返回
 */
public class WeatherResult {

    private WeatherSK sk;
    private ToDayWeather today;
    private String currentCityImage;

    public WeatherSK getSk() {
        return sk;
    }

    public ToDayWeather getToday() {
        return today;
    }

    public String getCurrentCityImage() {
        return "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541998899617&di=65184d2666b6e3eeaa13847d10ec6c57&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F48540923dd54564e48036f00b8de9c82d1584fa1.jpg";
    }

    @NonNull
    @Override
    public String toString() {
        return sk.toString() + today.toString();
    }

    public class WeatherSK {
        private String temp;
        @SerializedName(value = "wind_direction")
        private String windDirection;
        @SerializedName(value = "wind_strength")
        private String windStrength;
        private String humidity;
        private String time;

        public String getTemp() {
            return temp;
        }

        public String getWindDirection() {
            return windDirection;
        }

        public String getWindStrength() {
            return windStrength;
        }

        public String getHumidity() {
            return humidity;
        }

        public String getTime() {
            return time;
        }

        @NonNull
        @Override
        public String toString() {
            return "温度  =  " + temp + "\n"
                    + "风向  =  " + windDirection + "\n"
                    + "风力  =  " + windStrength + "\n"
                    + "湿度  =  " + humidity + "\n"
                    + "时间  =  " + time + "\n";
        }
    }

    public class ToDayWeather {
        private String temperature;
        private String weather;
        private String wind;
        private String week;
        private String city;
        @SerializedName(value = "date_y")
        private String dateY;
        @SerializedName(value = "dressing_advice")
        private String dressingAdvice;

        public String getTemperature() {
            return temperature;
        }

        public String getWeather() {
            return weather;
        }

        public String getWind() {
            return wind;
        }

        public String getWeek() {
            return week;
        }

        public String getCity() {
            return city;
        }

        public String getDateY() {
            return dateY;
        }

        public String getDressingAdvice() {
            return dressingAdvice;
        }

        @NonNull
        @Override
        public String toString() {
            return "气温  =  " + temperature + "\n"
                    + "天气  =  " + weather + "\n"
                    + "风速  =  " + wind + "\n"
                    + "周内  =  " + week + "\n"
                    + "城市  =  " + city + "\n"
                    + "日期  =  " + dateY + "\n"
                    + "衣着  =  " + dressingAdvice;
        }
    }

}