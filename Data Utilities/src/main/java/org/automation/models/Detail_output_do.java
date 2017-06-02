package org.automation.models;

import lombok.Data;

import java.util.Date;

/**
 * Created by ssarker on 1/15/2017.
 */
@Data
public class Detail_output_do {


    public static final String[] FILE_HEADERS = {
            "channel_name",
            "weekday_name",
            "daypart_date",
            "daypart_name",
            "unit_count",
            "unit_cost",
            "gross_cost",
            "household_impression",
            "target_impression",
            "target_cpm",
            "household_cpm"
    };

    public static final String column_1= "channel_name";
    public static final String column_2="weekday_name";
    public static final String column_3= "daypart_date";
    public static final String column_4 ="daypart_name";
    public static final String column_5="unit_count";
    public static final String column_6 ="unit_cost";
    public static final String column_7 ="gross_cost";
    public static final String column_8 ="household_impression";
    public static final String column_9 ="target_impression";
    public static final String column_10 ="target_cpm";
    public static final String column_11 ="household_cpm";

    private String channel_name;
    private String weekday_name;
    private Date daypart_date;
    private String daypart_name;
    private  Long unit_count;
    private Double unit_cost;
    private Double gross_cost;
    private  Long household_impression;
    private  Long target_impression;
    private Double target_cpm;
    private Double household_cpm;

    public Detail_output_do(){}
    public Detail_output_do(final String channel_name,final  String weekday_name,
                            final Date daypart_date,final  String daypart_name,
                            final Long unit_count, final Double unit_cost,
                            final Double gross_cost, final Long household_impression,
                            final Long target_impression, final Double target_cpm, final Double household_cpm) {
        this.channel_name = channel_name;
        this.weekday_name = weekday_name;
        this.daypart_date = daypart_date;
        this.daypart_name = daypart_name;
        this.unit_count = unit_count;
        this.unit_cost = unit_cost;
        this.gross_cost = gross_cost;
        this.household_impression = household_impression;
        this.target_impression = target_impression;
        this.target_cpm = target_cpm;
        this.household_cpm = household_cpm;
    }
}
