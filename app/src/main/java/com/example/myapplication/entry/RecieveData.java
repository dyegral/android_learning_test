package com.example.myapplication.entry;

import java.util.List;

public class RecieveData {
    private int code;
    private String message;
    private List<Image> result;

    public static class Image {
        private String id;
        private String time;
        private String img;

        public Image(String id, String time, String img) {
            this.id = id;
            this.time = time;
            this.img = img;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Image> getResult() {
        return result;
    }

    public void setResult(List<Image> result) {
        this.result = result;
    }
}
