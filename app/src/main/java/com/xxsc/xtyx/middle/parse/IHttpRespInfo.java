package com.xxsc.xtyx.middle.parse;

/**
 * Created by xiaocoder on 2015/8/28.
 */
public interface IHttpRespInfo {

    String RESPONSE_CODE = "code";
    String RESPONSE_MSG = "msg";

    String getCode();

    String getMsg();

}
