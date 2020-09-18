package com.ruoyi.mind.physical.utils;


import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.mind.physical.domain.DbDescriptionOrHint;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisInduced;
import com.ruoyi.mind.physical.service.IDbDescriptionOrHintService;

import java.util.List;

/*
 * 诱发电位工具类
 * */
public class InducedUtils {



    public   DbReportDiagnosisInduced getClusion(DbReportDiagnosisInduced dbReportDiagnosisInduced){
        dbReportDiagnosisInduced.setImpressionP300(getP30Conclusion(dbReportDiagnosisInduced));
        dbReportDiagnosisInduced.setImpressionMvn(getMMNConclusion(dbReportDiagnosisInduced));
        dbReportDiagnosisInduced.setImpressionP50(getP50Conclusion(dbReportDiagnosisInduced));
        dbReportDiagnosisInduced.setImpressionCnv(getCNVConclusion(dbReportDiagnosisInduced));
        return dbReportDiagnosisInduced;
    }

    /*
    * IF(AND
(J12>=378,J12<=402),Sheet2!N38,
IF(AND(J12>402,J12<=425),Sheet2!N41,
IF(J12>425,
Sheet2!N44,
IF(J12<378,
Sheet2!N47,Sheet1!O34))))
    * */
    /*
     * p30结论拼接
     * */
    public  String getP30Conclusion(DbReportDiagnosisInduced dbReportDiagnosisInduced) {
        StringBuilder stringBuilder = new StringBuilder();
        /*
        * 第一部分      =IF(J12<378,Sheet2!M35,IF(J12>=378,Sheet2!M38))
        *
        * p3b lat  <378     35行     能完成任务，靶刺激反应时间正常。
		            <=378     38行     能完成任务，靶刺激反应时间延长，
        *
        * */
        int p3bLat = Integer.parseInt(dbReportDiagnosisInduced.getP3bLat());
        DbDescriptionOrHint dbDescriptionOrHint = new DbDescriptionOrHint();
        if (p3bLat < 378) {
//            正常
            dbDescriptionOrHint.setName("p3bLat正常");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        } else {
            dbDescriptionOrHint.setName("p3bLat偏大");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        }
        /*
        * 第二部分   =IF(G12+K12>8,Sheet2!N50,IF(G12+K12<8,Sheet2!N51))
        *
        * N2 amp+N1 lat   >8   50行		P3b波幅未见异常。
				<=8   51行		P3b波幅降低，提示认知活动心理资源匮乏。
        *
        * */

        int n2Amp = Integer.parseInt(dbReportDiagnosisInduced.getN2Amp());
        int n1Lat = Integer.parseInt(dbReportDiagnosisInduced.getN1Lat());
        if ((n2Amp + n1Lat) > 8) {
//            正常
            dbDescriptionOrHint.setName("n2正常");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        } else {
            dbDescriptionOrHint.setName("n2偏大");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        }
        /*
         * 第三部分      =IF(
         * AND(J12>=378,J12<=402),Sheet2!N38,   P3b潜伏期轻度延长。
         * IF(AND(J12>402,J12<=425),Sheet2!N41,      P3b潜伏期中度延长。
         * IF(J12>425,Sheet2!N44,                P3b潜伏期重度延长。
         * IF(J12<378,Sheet2!N47,                P3b潜伏期未见异常。
         * Sheet1!O34))))                        空白
         *   J12  p3bLat
         *
         * */
        if (p3bLat >= 378 && p3bLat <= 402) {
            dbDescriptionOrHint.setName("P3b潜伏轻度");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        } else if (p3bLat >= 402 && p3bLat <= 425) {
            dbDescriptionOrHint.setName("P3b潜伏中度");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        } else if (p3bLat > 425) {
            dbDescriptionOrHint.setName("P3b潜伏重度");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        } else if (p3bLat < 378) {
            dbDescriptionOrHint.setName("P3b潜伏正常");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        } else {
//            空白
        }
        /*
         * 第四部分
         *=if（and(f12<300,h12<400),sheet2！41，   提示在完成选择性注意事件过程中，认知功能明显减退。
         * if(and(f12<300,h12>400),sheet2！42.       提示在完成选择性注意事件过程中，定向活动和认知功能明显减退。
         * if(and f12>300,h12>400),sheet2！43)）      提示在完成选择性注意事件过程中，知觉水平降低、定向活动和认知功能明显减退。
         *
         * f12  p300 n2 lat
         * h12  p300 p3a lat
         * */
        int n2lat = Integer.parseInt(dbReportDiagnosisInduced.getN2Lat());
        int p3alat = Integer.parseInt(dbReportDiagnosisInduced.getP3aLat());
        if (n2lat<300&p3alat<400){
            dbDescriptionOrHint.setName("认知功能减退");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        }else  if (n1Lat<300&&p3alat>400){
            dbDescriptionOrHint.setName("认知功能明显减退");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        } else if (n1Lat>300&&p3alat>400) {
            dbDescriptionOrHint.setName("知觉水平降低");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            stringBuilder.append(conclusion.getDescribe());
        }

        return stringBuilder.toString();
    }

    /*
     *
     *MMN结论 拼接
     * */
   public  String getMMNConclusion(DbReportDiagnosisInduced dbReportDiagnosisInduced) {
        /*
         * 一部分       =IF(J16<210,Sheet2!M13,        MMN 波形潜伏期未见异常，提示在无意识状态下，大脑对新奇刺激信息自动加工功能正常。
         * IF(AND(J16>=210,J16<=240),Sheet2!M14,    MMN 波形潜伏期轻度异常，提示在无意识状态下，大脑对新奇刺激信息自动加工功能减退。
         * IF(J16<=270,Sheet2!M15,                  MMN 波形潜伏期中度异常，提示在无意识状态下，大脑对新奇刺激信息自动加工功能明显减退。
         * IF(J16>270,Sheet2!M16))))                MMN 波形潜伏期重度异常，提示在无意识状态下，大脑对新奇刺激信息自动加工功能障碍。
         *J16  MMN
         *
         * */
        String conclusionString="";
        int mmn= Integer.parseInt(dbReportDiagnosisInduced.getMvn());
        DbDescriptionOrHint dbDescriptionOrHint = new DbDescriptionOrHint();
        if (mmn<210){
            dbDescriptionOrHint.setName("MMN潜伏期正常");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
             conclusionString = conclusion.getDescribe();
        }else if(mmn>=210&&mmn<=240){
            dbDescriptionOrHint.setName("MMN潜伏期轻度");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();
        }
        else if(mmn<=270){
            dbDescriptionOrHint.setName("MMN潜伏期中度");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();
        }
        else if(mmn>270){
            dbDescriptionOrHint.setName("MMN潜伏期重度");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();
        }

        return conclusionString;
    }
    /*
    *
    * p50结论凭借
    * */
    public  String getP50Conclusion(DbReportDiagnosisInduced dbReportDiagnosisInduced) {
        /*
         * 一部分      =IF(H17/F17>0.5,Sheet2!M24,     S2-P50/S1-P50﹥0.5，提示大脑感觉门控（抗干扰能力）缺失，大脑不能排除干扰。
         * IF(H17/F17=0.5,Sheet2!M25,               S2-P50/S1-P50=0.5，提示大脑感觉门控（抗干扰能力）轻度缺失。
         * IF(H17/F17<0.5,Sheet2!M23)))         S2-P50/S1-P50<0.5，未见大脑感觉门控（抗干扰能力）缺失征象。
         *
         * H17      p50s2
         * p50s1
         * */
        String conclusionString="";
        int cnvLaC = Integer.parseInt(dbReportDiagnosisInduced.getP50AmS250());
        int cnvs2 = Integer.parseInt(dbReportDiagnosisInduced.getP50AmS150());
        DbDescriptionOrHint dbDescriptionOrHint = new DbDescriptionOrHint();
        if (cnvLaC/cnvs2>0.5){
            dbDescriptionOrHint.setName("p50重度");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();
        }else if(cnvLaC/cnvs2==0.5){
            dbDescriptionOrHint.setName("p50轻度");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();
        } else if (cnvLaC/cnvs2==0.5){
            dbDescriptionOrHint.setName("p50正常");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();

        }


        return conclusionString;
    }

    /*
    *
    *CNV结论拼接
    *
    * */
    public String getCNVConclusion(DbReportDiagnosisInduced dbReportDiagnosisInduced) {
/*
*=if(and(b22<200,j22<15),sheet2!m27,    能完成作业，提示期待心理反应过程中，唤醒水平过度增高所致高警觉状态、伴注意维持障碍。
* if(and(b22<200,j22>15),sheet2!m28,    能完成作业，提示期待心理反应过程中，唤醒水平增高所致高警觉状态、伴注意维持障碍。
* if(and(b22>200,j22<15),sheet2！m29,    能完成作业，提示唤醒水平降低所致注意障碍。
* if(and b22>200,j22>30),sheet2!m30,    能完成作业，提示唤醒水平增高所致注意障碍。
* if(and(b22>200,i22>270),sheet2!31     能完成作业，提示唤醒水平过度增高所致注意障碍。
* ))
*
* b22   cnvla a
* j22  cnvamb
* i22 cnvs2c
*
*
* */
        String conclusionString="";
        int cnvLaA = Integer.parseInt(dbReportDiagnosisInduced.getCnvLaA());
        int cvnamB = Integer.parseInt(dbReportDiagnosisInduced.getCnvAmB());
        int cnvlas2c = Integer.parseInt(dbReportDiagnosisInduced.getCnvLaS2C());
        DbDescriptionOrHint dbDescriptionOrHint = new DbDescriptionOrHint();
        if (cnvLaA<200&&cvnamB<15){
            dbDescriptionOrHint.setName("唤醒水平过高");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();
        }else if (cnvLaA<200&&cvnamB>15){
            dbDescriptionOrHint.setName("唤醒水平增高");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();
        }else if (cnvLaA>200&&cvnamB<15){
            dbDescriptionOrHint.setName("提示唤醒水平降低");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();
        }
        else if (cnvLaA>200&&cvnamB>30){
            dbDescriptionOrHint.setName("提示唤醒水平增高");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();
        }
        else if (cnvLaA>200&&cnvlas2c>270){
            dbDescriptionOrHint.setName("提示唤醒水平过度");
            DbDescriptionOrHint conclusion = getConclusion(dbDescriptionOrHint);
            conclusionString = conclusion.getDescribe();
        }



        return conclusionString;
    }


    private DbDescriptionOrHint getConclusion(DbDescriptionOrHint dbDescriptionOrHint) {
        dbDescriptionOrHint.setTypeCode("induced");
        List<DbDescriptionOrHint> dbDescriptionOrHints = SpringUtils.getBean(IDbDescriptionOrHintService.class).selectDbDescriptionOrHintList(dbDescriptionOrHint);
        return dbDescriptionOrHints.get(0);
    }


}
