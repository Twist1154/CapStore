package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Discount.java
 *
 * @author Zachariah Matsimella
 * Student Num: 220097429
 * @date 08-Sep-24
 */
@Getter
@Entity
public class Discount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String description;
    private double discountPercent;
    private LocalDate startDate;
    private LocalDate endDate;
    private int maxUses;

    public Discount() {
    }

    public Discount(Builder builder) {
        this.id = builder.id;
        this.code = builder.code;
        this.description = builder.description;
        this.discountPercent = builder.discountPercent;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.maxUses = builder.maxUses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return id == discount.id && Double.compare(discount.discountPercent, discountPercent) == 0 && maxUses == discount.maxUses && Objects.equals(code, discount.code) && Objects.equals(description, discount.description) && Objects.equals(startDate, discount.startDate) && Objects.equals(endDate, discount.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, discountPercent, startDate, endDate, maxUses);
    }

    @Override
    public String toString() {
        return "Discounts{" +
                "discountId=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", discount_percent=" + discountPercent +
                ", start_date=" + startDate +
                ", end_date=" + endDate +
                ", max_uses=" + maxUses +
                '}';
    }

    public static class Builder {
        private Long id;
        private String code;
        private String description;
        private double discountPercent;
        private LocalDate startDate;
        private LocalDate endDate;
        private int maxUses;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDiscountPercent(double discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setMaxUses(int maxUses) {
            this.maxUses = maxUses;
            return this;
        }

        public Builder copy(Discount discount) {
            this.id = discount.getId();
            this.code = discount.getCode();
            this.description = discount.getDescription();
            this.discountPercent = discount.getDiscountPercent();
            this.startDate = discount.getStartDate();
            this.endDate = discount.getEndDate();
            this.maxUses = discount.getMaxUses();
            return this;
        }

        public Discount build() {
            return new Discount(this);
        }
    }
}
