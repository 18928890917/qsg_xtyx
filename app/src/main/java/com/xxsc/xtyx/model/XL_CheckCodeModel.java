package com.xxsc.xtyx.model;

import com.xxsc.xtyx.middle.parse.BaseModel;

/**
 * @version xilinch on 2016/4/18.
 * @modifier xilinch 2016/4/18 18:39.
 * @description
 */
public class XL_CheckCodeModel extends BaseModel{


    /**
     * code : 1
     * datas : {"captcha":123456}
     * msg :
     */

    private String code;
    /**
     * captcha : 123456
     */

    private DatasBean datas;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DatasBean {
        private int captcha;

        public int getCaptcha() {
            return captcha;
        }

        public void setCaptcha(int captcha) {
            this.captcha = captcha;
        }
    }
}
