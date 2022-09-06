/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author NVS
 */
public interface IStatisticService {
    ArrayList<String> getPawnCouponStatistic(Date dateFrom, Date dateTo);
    ArrayList<String> getCustomerStatistic(Date dateFrom, Date dateTo);
    ArrayList<String> getTypeOfProductStatistic(Date dateFrom, Date dateTo);
}
