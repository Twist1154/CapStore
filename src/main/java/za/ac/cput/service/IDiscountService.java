package za.ac.cput.service;

import za.ac.cput.domain.Discount;

public interface IDiscountService extends IService<Discount, Long>{
    void delete(Long id);
}
