package za.ac.cput.service;

import za.ac.cput.domain.OrderItem;

import java.util.Optional;

/**
 * IOrderItemService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 09-Sep-24
 */

public interface IOrderItemService  extends IService<OrderItem, Long>  {

    void deleteById(Long id);


}
