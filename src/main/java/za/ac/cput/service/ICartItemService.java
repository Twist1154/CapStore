package za.ac.cput.service;

import za.ac.cput.domain.CartItem;

/**
 * ICartItemService.java
 *
 * Author: Kinzonzi Mukoko
 * Student Num: 221477934
 * Date: 09-Sep-24
 */
public interface ICartItemService extends IService<CartItem, Long> {

    void deleteById(Long id);

}
