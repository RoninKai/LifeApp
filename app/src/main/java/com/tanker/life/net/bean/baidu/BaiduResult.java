package com.tanker.life.net.bean.baidu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/08
 * @describe : TODO
 */
public class BaiduResult {
    private int totalNum;
    @SerializedName(value = "start_index")
    private int startIndex;
    @SerializedName(value = "return_number")
    private int returnNumber;
    @SerializedName(value = "data")
    private List<BaiduGirl> imageData;

    public List<BaiduGirl> getImageData() {
        return imageData;
    }

    public class BaiduGirl {
        @SerializedName(value = "image_url")
        private String imageUrl;
        @SerializedName(value = "image_width")
        private int imageWidth;
        @SerializedName(value = "image_height")
        private int imageHeight;

        public String getImageUrl() {
            return imageUrl;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public int getImageHeight() {
            return imageHeight;
        }

    }

}