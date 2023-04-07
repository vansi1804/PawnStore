/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.List;
import java.util.Date;

/**
 *
 * @author NVS
 */
public interface IStatisticService {
    List<String> getPawnCouponStatistic(Date dateFrom, Date dateTo);
    List<String> getCustomerStatistic(Date dateFrom, Date dateTo);
    List<String> getTypeOfProductStatistic(Date dateFrom, Date dateTo);
}
