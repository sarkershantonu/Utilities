package org.automation.models; 

package com.att.blueprint.csv;

import lombok.Data;

/**
 * Created by ssarker on 1/16/2017.
 */
@Data
public class Summary_output_do {

    public static final String[] FILE_HEADERS = {
            "SUM_HH_IMP",
            "SUM_TGT_IMP",
            "SUM_TGT_CPM",
            "SUM_HH_CPM"
    };

    public static final String column_1= "SUM_HH_IMP";
    public static final String column_2= "SUM_TGT_IMP";
    public static final String column_3= "SUM_TGT_CPM";
    public static final String column_4 ="SUM_HH_CPM";

    private  Long SUM_HH_IMP;
    private  Long SUM_TGT_IMP;
    private  Double	SUM_TGT_CPM;
    private  Double	SUM_HH_CPM;

    public Summary_output_do(){}
    public Summary_output_do(Long SUM_HH_IMP, Long SUM_TGT_IMP, Double SUM_TGT_CPM, Double SUM_HH_CPM) {
        this.SUM_HH_IMP = SUM_HH_IMP;
        this.SUM_TGT_IMP = SUM_TGT_IMP;
        this.SUM_TGT_CPM = SUM_TGT_CPM;
        this.SUM_HH_CPM = SUM_HH_CPM;
    }
}
