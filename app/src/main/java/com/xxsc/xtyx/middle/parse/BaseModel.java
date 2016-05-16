package com.xxsc.xtyx.middle.parse;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by xiaocoder on 2015/7/29.
 */
public abstract class BaseModel implements IHttpRespInfo, Serializable {

    private static final long serialVersionUID = 2161633826093329307L;

    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * 浅克隆
     */
    public Object simpleClone() {
        return clone();
    }

    /**
     * 深克隆
     */
    public Object deepClone() {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            //将对象写到流里
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            oos = new ObjectOutputStream(byteArrayOutputStream);

            oos.writeObject(this);

            //从流里读出来
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            ois = new ObjectInputStream(byteArrayInputStream);

            return ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (oos != null) {
                        try {
                            oos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
