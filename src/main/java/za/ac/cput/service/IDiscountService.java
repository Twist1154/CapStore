package za.ac.cput.service;

import za.ac.cput.domain.Discount;

import java.util.List;

public interface IDiscountService extends IService<Discount, Long>{
    void delete(Long id);
    List<Discount> findByIdAndMaxUses(Long id, int max_uses);
    List<Discount> findByCode(String code);
    List<Discount> findByDiscountPercentGreaterThan(double percentage);
}
