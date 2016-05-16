package com.xxsc.xtyx.model;

import com.google.gson.annotations.SerializedName;
import com.xxsc.xtyx.middle.parse.BaseModel;

import java.util.List;

/**
 * @author 赖善琦
 * @description
 */
public class SK_IndexModel extends BaseModel{

    /**
     * code : 1
     * datas : {"slide":[{"img":"http://120.25.240.120/shop/upload/2011/06/07/20110607105300491.png","type":"1"},{"img":"http://120.25.240.120/shop/upload/2011/06/07/20110607105300463.png","type":"1"}],"activity":[{"img":"http://120.25.240.120/shop/upload/2016/04/06/20160406095155732.jpg","type":"2"},{"img":"http://120.25.240.120/shop/upload/2016/04/06/20160406095155801.jpg","type":"2"},{"img":"http://120.25.240.120/shop/upload/2016/04/06/20160406095155648.jpg","type":"2"},{"img":"http://120.25.240.120/shop/upload/2016/04/06/20160406095155828.jpg","type":"2"}],"goods":{"new":[{"name":"衬衫","description":"推荐都说好","sale":"0","favorite":"0","share":"0","sell_price":"123.00","img":"http://120.25.240.120/shop/"},{"name":"高度白酒茅","description":"","sale":"0","favorite":"1","share":"0","sell_price":"450.00","img":"http://120.25.240.120/shop/upload/2016/03/14/20160314101659519.jpg"}],"special":[{"name":"衬衫","description":"推荐都说好","sale":"0","favorite":"0","share":"0","sell_price":"123.00","img":"http://120.25.240.120/shop/"},{"name":"高度白酒茅","description":"","sale":"0","favorite":"1","share":"0","sell_price":"450.00","img":"http://120.25.240.120/shop/upload/2016/03/14/20160314101659519.jpg"}],"praise":[{"name":"衬衫","description":"推荐都说好","sale":"0","favorite":"0","share":"0","sell_price":"123.00","img":"http://120.25.240.120/shop/"},{"name":"高度白酒茅","description":"","sale":"0","favorite":"1","share":"0","sell_price":"450.00","img":"http://120.25.240.120/shop/upload/2016/03/14/20160314101659519.jpg"}]}}
     * msg :
     */

    private String code;
    /**
     * slide : [{"img":"http://120.25.240.120/shop/upload/2011/06/07/20110607105300491.png","type":"1"},{"img":"http://120.25.240.120/shop/upload/2011/06/07/20110607105300463.png","type":"1"}]
     * activity : [{"img":"http://120.25.240.120/shop/upload/2016/04/06/20160406095155732.jpg","type":"2"},{"img":"http://120.25.240.120/shop/upload/2016/04/06/20160406095155801.jpg","type":"2"},{"img":"http://120.25.240.120/shop/upload/2016/04/06/20160406095155648.jpg","type":"2"},{"img":"http://120.25.240.120/shop/upload/2016/04/06/20160406095155828.jpg","type":"2"}]
     * goods : {"new":[{"name":"衬衫","description":"推荐都说好","sale":"0","favorite":"0","share":"0","sell_price":"123.00","img":"http://120.25.240.120/shop/"},{"name":"高度白酒茅","description":"","sale":"0","favorite":"1","share":"0","sell_price":"450.00","img":"http://120.25.240.120/shop/upload/2016/03/14/20160314101659519.jpg"}],"special":[{"name":"衬衫","description":"推荐都说好","sale":"0","favorite":"0","share":"0","sell_price":"123.00","img":"http://120.25.240.120/shop/"},{"name":"高度白酒茅","description":"","sale":"0","favorite":"1","share":"0","sell_price":"450.00","img":"http://120.25.240.120/shop/upload/2016/03/14/20160314101659519.jpg"}],"praise":[{"name":"衬衫","description":"推荐都说好","sale":"0","favorite":"0","share":"0","sell_price":"123.00","img":"http://120.25.240.120/shop/"},{"name":"高度白酒茅","description":"","sale":"0","favorite":"1","share":"0","sell_price":"450.00","img":"http://120.25.240.120/shop/upload/2016/03/14/20160314101659519.jpg"}]}
     */

    private DatasEntity datas;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DatasEntity {
        private GoodsEntity goods;
        /**
         * img : http://120.25.240.120/shop/upload/2011/06/07/20110607105300491.png
         * type : 1
         */

        private List<SlideEntity> slide;
        /**
         * img : http://120.25.240.120/shop/upload/2016/04/06/20160406095155732.jpg
         * type : 2
         */

        private List<ActivityEntity> activity;

        public GoodsEntity getGoods() {
            return goods;
        }

        public void setGoods(GoodsEntity goods) {
            this.goods = goods;
        }

        public List<SlideEntity> getSlide() {
            return slide;
        }

        public void setSlide(List<SlideEntity> slide) {
            this.slide = slide;
        }

        public List<ActivityEntity> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityEntity> activity) {
            this.activity = activity;
        }

        public static class GoodsEntity {
            /**
             * name : 衬衫
             * description : 推荐都说好
             * sale : 0
             * favorite : 0
             * share : 0
             * sell_price : 123.00
             * img : http://120.25.240.120/shop/
             */

            @SerializedName("new")
            private List<NewEntity> newX;
            /**
             * name : 衬衫
             * description : 推荐都说好
             * sale : 0
             * favorite : 0
             * share : 0
             * sell_price : 123.00
             * img : http://120.25.240.120/shop/
             */

            private List<SpecialEntity> special;
            /**
             * name : 衬衫
             * description : 推荐都说好
             * sale : 0
             * favorite : 0
             * share : 0
             * sell_price : 123.00
             * img : http://120.25.240.120/shop/
             */

            private List<PraiseEntity> praise;

            public List<NewEntity> getNewX() {
                return newX;
            }

            public void setNewX(List<NewEntity> newX) {
                this.newX = newX;
            }

            public List<SpecialEntity> getSpecial() {
                return special;
            }

            public void setSpecial(List<SpecialEntity> special) {
                this.special = special;
            }

            public List<PraiseEntity> getPraise() {
                return praise;
            }

            public void setPraise(List<PraiseEntity> praise) {
                this.praise = praise;
            }

            public static class NewEntity {
                private String name;
                private String description;
                private String sale;
                private String favorite;
                private String share;
                private String sell_price;
                private String img;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getSale() {
                    return sale;
                }

                public void setSale(String sale) {
                    this.sale = sale;
                }

                public String getFavorite() {
                    return favorite;
                }

                public void setFavorite(String favorite) {
                    this.favorite = favorite;
                }

                public String getShare() {
                    return share;
                }

                public void setShare(String share) {
                    this.share = share;
                }

                public String getSell_price() {
                    return sell_price;
                }

                public void setSell_price(String sell_price) {
                    this.sell_price = sell_price;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }

            public static class SpecialEntity {
                private String name;
                private String description;
                private String sale;
                private String favorite;
                private String share;
                private String sell_price;
                private String img;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getSale() {
                    return sale;
                }

                public void setSale(String sale) {
                    this.sale = sale;
                }

                public String getFavorite() {
                    return favorite;
                }

                public void setFavorite(String favorite) {
                    this.favorite = favorite;
                }

                public String getShare() {
                    return share;
                }

                public void setShare(String share) {
                    this.share = share;
                }

                public String getSell_price() {
                    return sell_price;
                }

                public void setSell_price(String sell_price) {
                    this.sell_price = sell_price;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }

            public static class PraiseEntity {
                private String name;
                private String description;
                private String sale;
                private String favorite;
                private String share;
                private String sell_price;
                private String img;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getSale() {
                    return sale;
                }

                public void setSale(String sale) {
                    this.sale = sale;
                }

                public String getFavorite() {
                    return favorite;
                }

                public void setFavorite(String favorite) {
                    this.favorite = favorite;
                }

                public String getShare() {
                    return share;
                }

                public void setShare(String share) {
                    this.share = share;
                }

                public String getSell_price() {
                    return sell_price;
                }

                public void setSell_price(String sell_price) {
                    this.sell_price = sell_price;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }
        }

        public static class SlideEntity {
            private String img;
            private String type;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class ActivityEntity {
            private String img;
            private String type;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
