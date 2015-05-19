package com.gpzuestc.framework.serialize;

import java.io.Serializable;

public class CardVO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String CARD_STATUS_DISPACH = "CARD_STATUS_DISPATCH";

    public static final String CARD_STATUS_VERIFY_OK = "CARD_STATUS_VERIFY_OK";

    public int errcode;
    public String errmsg;
    public Card card;

    public class Card {
        public String card_type;
        public GeneralCoupon general_coupon;
        public Groupon groupon;
        public Gift gift;
        public Cash cash;
        public Discount discount;
        public MemberCard member_card;
        public ScenicTicket scenic_ticket;
        public MovieTicket movie_ticket;
        public BoardingPass boarding_pass;
        public LuckyMoney lucky_money;
        public MeetingTicke meeting_ticket;

        public class GeneralCoupon {
            public BaseInfo base_info;
            public String default_detail;
        }

        public class Groupon {
            public BaseInfo base_info;
            public String deal_detail;
        }

        public class Gift {
            public BaseInfo base_info;
            public String gift;
        }

        public class Cash {
            public BaseInfo base_info;
            public Integer least_cost;
            public Integer reduce_cost;
        }

        public class Discount {
            public BaseInfo base_info;
            public String discount;
        }

        public class MemberCard {
            public BaseInfo base_info;
            public String supply_bonus;
            public String supply_balance;
            public String bonus_cleared;
            public String bonus_rules;
            public String balance_rules;
            public String prerogative;
            public String bind_old_card_url;
            public String activate_url;
        }

        public class ScenicTicket {
            public BaseInfo base_info;
            public String ticket_class;
            public String guide_url;
        }

        public class MovieTicket {
            public BaseInfo base_info;
            public String detail;
        }

        public class BoardingPass {
            public BaseInfo base_info;
            public String from;
            public String to;
            public String flight;
            public String departure_time;
            public String landing_time;
            public String check_in_url;
            public String air_model;
        }

        public class LuckyMoney {
            public BaseInfo base_info;
        }

        public class MeetingTicke {
            public BaseInfo base_info;
            public String meeting_detail;
            public String map_url;
        }

       
    }


    public static BaseInfo getBaseInfo(CardVO cardVO) {
        String cardType = cardVO.card.card_type;
        if (cardType.equals("GENERAL_COUPON"))
            return cardVO.card.general_coupon.base_info;

        else if (cardType.equals("GROUPON"))
            return cardVO.card.groupon.base_info;


        else if (cardType.equals("DISCOUNT"))
            return cardVO.card.discount.base_info;

        else if (cardType.equals("GIFT"))
            return cardVO.card.gift.base_info;

        else if (cardType.equals("CASH"))
            return cardVO.card.cash.base_info;


        else if (cardType.equals("MEMBER_CARD"))
            return cardVO.card.member_card.base_info;

        else if (cardType.equals("SCENIC_TICKET"))
            return cardVO.card.scenic_ticket.base_info;


        else if (cardType.equals("MOVIE_TICKET"))
            return cardVO.card.movie_ticket.base_info;

        else if (cardType.equals("BOARDING_PASS"))
            return cardVO.card.boarding_pass.base_info;

        else if (cardType.equals("LUCKY_MONEY"))
            return cardVO.card.lucky_money.base_info;

        else if (cardType.equals("MEETING_TICKET"))
            return cardVO.card.meeting_ticket.base_info;

        return null;
    }	
}


