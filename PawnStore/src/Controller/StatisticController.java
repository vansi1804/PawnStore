/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Service.IStatisticService;
import Service.impl.StatisticService;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author NVS
 */
public class StatisticController {

    private static final Logger LOG = Logger.getLogger(StatisticController.class.getName());

    private static StatisticController instance;

    public static StatisticController getCurrentInstance() {
        if (instance == null) {
            instance = new StatisticController();
        }
        return instance;
    }
    
    private final IStatisticService statisticService = new StatisticService();

    public ArrayList<String> getPawnCouponStatistic(Date dateFrom, Date dateTo) {
        return statisticService.getPawnCouponStatistic(dateFrom, dateTo);
    }

    public ArrayList<String> getCustomerStatistic(Date dateFrom, Date dateTo) {
        return statisticService.getCustomerStatistic(dateFrom, dateTo);
    }

    public ArrayList<String> getTypeOfProductStatistic(Date dateFrom, Date dateTo){
        return statisticService.getTypeOfProductStatistic(dateFrom, dateTo);
    }

}
