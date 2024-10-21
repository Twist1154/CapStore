package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Discount;
import za.ac.cput.repository.IDiscountRepository;

import java.util.List;

@Service
public class DiscountService implements IDiscountService {
    private final IDiscountRepository discountRepository;

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
        if(discountRepository.existsById(discount.getId())){
            return discountRepository.save(discount);
        }
        return null;
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
