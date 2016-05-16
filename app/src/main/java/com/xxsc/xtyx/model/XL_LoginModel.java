package com.xxsc.xtyx.model;

import com.xxsc.xtyx.middle.parse.BaseModel;

/**
 * @version xilinch on 2016/4/7.
 * @modifier xilinch 2016/4/7 14:52.
 * @description
 */
public class XL_LoginModel extends BaseModel{

    /**
     * code : -1
     * msg : 用户名和密码不匹配或短信验证码不对
     * datas : {"user_token":"","user_id":"","img":"http://120.25.240.120/shop/"}
     */

    private String code;
    private String msg;
    /**
     * user_token :
     * user_id :
     * img : http://120.25.240.120/shop/
     */

    private DatasBean datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String user_token;
        private String user_id;
        private String img;

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
