package com.gpzuestc.framework.serialize;

import java.util.List;

public class BaseInfo {
    public String id;
    public String logo_url;
    public DateInfo date_info;

    public class DateInfo {
        public String type;
        public String begin_timestam;
        public String end_timestamp;
        public String fixed_term;
        public String fixed_begin_term;
    }

    public String color;
    public String notice;
    public String service_phone;
    public String description;
    public List<String> location_id_list;
    public String get_limit;
    public String can_share;
    public String can_give_friend;
    public String status;
    public Sku sku;

    public class Sku {
        public int quantity;
    }

    public String create_time;
    public String update_time;

    public List<String> js_oauth_uin_list;

    public String code_type;
    public String brand_name;
    public String title;
    public String use_limit;
    public String use_custom_code;
    public String bind_openid;


    public String url_name_type;
}