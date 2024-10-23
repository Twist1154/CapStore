package za.ac.cput.service;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Discount;
import za.ac.cput.repository.IDiscountRepository;

import java.util.List;

@Slf4j
@Transactional
@Service
public class DiscountService implements IDiscountService {
    private final IDiscountRepository discountRepository;
private final Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(this.getClass());
    @Autowired
    public DiscountService(IDiscountRepository discountRepository){
        this.discountRepository = discountRepository;
    }

    @Override
    public Discount create(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public Discount read(Long id) {
        return discountRepository.findById(id).orElse(null);
    }

    @Override
    public Discount update(Discount discount) {
        Discount existingDiscount = discountRepository.findById(discount.getId()).orElse(null);
        if (existingDiscount != null) {
            Discount updatedDiscount = new Discount.Builder()
                    .copy(existingDiscount)
                    .setId(existingDiscount.getId())
                    .setCode(discount.getCode())
                    .setDescription(discount.getDescription())
                    .setDiscountPercent(discount.getDiscountPercent())
                    .setStartDate(discount.getStartDate())
                    .setEndDate(discount.getEndDate())
                    .setMaxUses(discount.getMaxUses())
                    .build();
            return discountRepository.save(updatedDiscount);
        }
        logger.info("Discount not found.");
        return null;
    }

    @Override
    public List<Discount> findByIdAndMaxUses(Long id, int max_uses) {
        return discountRepository.findByIdAndMaxUses(id, max_uses);
    }

    @Override
    public List<Discount> findByCode(String code) {
        return discountRepository.findByCode(code);
    }

    @Override
    public List<Discount> findByDiscountPercentGreaterThan(double percentage) {
        return discountRepository.findByDiscountPercentGreaterThan(percentage);
    }

    @Override
    public void delete(Long id) {
        this.discountRepository.deleteById(id);
    }

    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }
}
