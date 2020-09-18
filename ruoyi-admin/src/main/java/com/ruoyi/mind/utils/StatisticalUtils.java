package com.ruoyi.mind.utils;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.mind.registered.service.IDbPatientMessageService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller()
@RequestMapping("/statistical")
public class StatisticalUtils {
    private String prefix = "statistical";


    @Autowired
    private IDbPatientMessageService messageService;

    /*
     * 每日就诊人数趋势图
     * data：每日的人数量
     * startDate ：  开始的时间  （2006, 0, 1）时间格式      月份加1   0到11月
     * endDate ：  结束时间           月份减1
     * showStartDate  ： 默认展示开始的时间   月份加1
     * */
    @GetMapping("/trend")
    public String TrendDailyVisits(ModelMap map) {
//        结束时间
        String s = DateUtils.parseDateToStr("yyyy,MM,dd", new Date());
        String s1 = GetMonth(s, 1);
        map.put("endDate", s1);
        List<Map<String, Object>> maps = messageService.selectListByCreatTime();
        Map<String, Object> stringObjectMap = maps.get(0);
        Date time = (Date) stringObjectMap.get("time");
        String s3 = DateUtils.parseDateToStr("yyyy,MM,dd", time);
        String s4 = GetMonth(s3, -1);
        map.put("startDate", s4);
        List<String> objects = new ArrayList<>();
        Map<String, String> listArray = getListArray(time, new Date());

        maps.forEach(item -> {
            Date time1 = (Date) item.get("time");
            String s7 = DateUtils.parseDateToStr("yyyy,MM,dd", time1);
            if (listArray.get(s7)!=null){
                listArray.put(s7,item.get("num")+"");
            }
        });
        Set<String> strings = listArray.keySet();
        strings.forEach(item->{
            objects.add(listArray.get(item));
        });
        map.put("data", objects);
        return prefix + "/statistical";
    }

    public static String GetMonth(String data, int i) {
        int i2 = Integer.parseInt(data.split(",")[1]);
        i2 = i2 + i;
        String replace = data.replace(data.split(",")[1], i2 + "");
        return replace;

    }
    public static String GetDay(Date data, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        Date time = cal.getTime();
        String s7 = DateUtils.parseDateToStr("yyyy,MM,dd", time);
        return s7;
    }

    public static Map<String, String> getListArray(Date startDate, Date endDate) {
        String datePoor = DateUtils.getDatePoor(endDate,startDate );
        String day = datePoor.split("天")[0];
        int day2 = Integer.parseInt(day);
           Map<String, String> objectObjectHashMap = new LinkedMap<>();
        for (int i = 0; i < day2; i++) {
            String s = GetDay(startDate, i);
            objectObjectHashMap.put(s,"0");
        }
        return objectObjectHashMap;
    }



}
