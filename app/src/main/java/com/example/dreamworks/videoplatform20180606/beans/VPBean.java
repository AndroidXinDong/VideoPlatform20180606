package com.example.dreamworks.videoplatform20180606.beans;

import java.util.List;

public class VPBean {

    /**
     * msg : 成功
     * data : [{"nodeName":"整机动画","videoUrl":"1","ID":1,"parentID":0},{"nodeName":"前面板","videoUrl":"1","ID":2,"parentID":1},{"nodeName":"上面板","videoUrl":"1","ID":3,"parentID":1},{"nodeName":"路板组件","videoUrl":"1","ID":4,"parentID":2},{"nodeName":"显示屏组件","videoUrl":"1","ID":5,"parentID":4},{"nodeName":"孔板流量计组件","videoUrl":"1","ID":6,"parentID":3},{"nodeName":"温度传感器罩","videoUrl":"1","ID":7,"parentID":3},{"nodeName":"上面板组件.mp4","videoUrl":"http://49.4.11.118:8080/VideoPlateform/videos/上面板组件.mp4","ID":8,"parentID":3},{"nodeName":"孔板流量计组件.mp4","videoUrl":"http://49.4.11.118:8080/VideoPlateform/videos/孔板流量计组件.mp4","ID":9,"parentID":6},{"nodeName":"温度传感器罩.mp4","videoUrl":"http://49.4.11.118:8080/VideoPlateform/videos/温度传感器罩.mp4","ID":10,"parentID":7},{"nodeName":"前面板.mp4","videoUrl":"http://49.4.11.118:8080/VideoPlateform/videos/前面板.mp4","ID":11,"parentID":2},{"nodeName":"路板组件.mp4","videoUrl":"http://49.4.11.118:8080/VideoPlateform/videos/路板组件.mp4","ID":12,"parentID":4},{"nodeName":"显示屏组件.mp4","videoUrl":"http://49.4.11.118:8080/VideoPlateform/videos/显示屏组件.mp4","ID":13,"parentID":5},{"nodeName":"整机装配.mp4","videoUrl":"http://49.4.11.118:8080/VideoPlateform/videos/整机装配.mp4","ID":14,"parentID":1}]
     * state : 200
     */

    private String msg;
    private int state;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * nodeName : 整机动画
         * videoUrl : 1
         * ID : 1
         * parentID : 0
         */

        private String nodeName;
        private String videoUrl;
        private int ID;
        private int parentID;

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getParentID() {
            return parentID;
        }

        public void setParentID(int parentID) {
            this.parentID = parentID;
        }
    }
}
