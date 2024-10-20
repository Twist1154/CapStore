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
    private double discount_percent;
    private LocalDate start_date;
    private LocalDate end_date;
    private int max_uses;

    public Discount() {
    }

    public Discount(Builder builder) {
        this.id = builder.id;
        this.code = builder.code;
        this.description = builder.description;
        this.discount_percent = builder.discount_percent;
        this.start_date = builder.start_date;
        this.end_date = builder.end_date;
        this.max_uses = builder.max_uses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return id == discount.id && Double.compare(discount.discount_percent, discount_percent) == 0 && max_uses == discount.max_uses && Objects.equals(code, discount.code) && Objects.equals(description, discount.description) && Objects.equals(start_date, discount.start_date) && Objects.equals(end_date, discount.end_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, discount_percent, start_date, end_date, max_uses);
    }

    @Override
    public String toString() {
        return "Discounts{" +
                "discountId=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", discount_percent=" + discount_percent +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", max_uses=" + max_uses +
                '}';
    }

    public static class Builder {
        private Long id;
        private String code;
        private String description;
        private double discount_percent;
        private LocalDate start_date;
        private LocalDate end_date;
        private int max_uses;

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

        public Builder setDiscount_percent(double discount_percent) {
            this.discount_percent = discount_percent;
            return this;
        }

        public Builder setStart_date(LocalDate start_date) {
            this.start_date = start_date;
            return this;
        }

        public Builder setEnd_date(LocalDate end_date) {
            this.end_date = end_date;
            return this;
        }

        public Builder setMax_uses(int max_uses) {
            this.max_uses = max_uses;
            return this;
        }

        public Builder copy(Discount discount) {
            this.id = discount.getId();
            this.code = discount.getCode();
            this.description = discount.getDescription();
            this.discount_percent = discount.getDiscount_percent();
            this.start_date = discount.getStart_date();
            this.end_date = discount.getEnd_date();
            this.max_uses = discount.getMax_uses();
            return this;
        }

        public Discount build() {
            return new Discount(this);
        }
    }
}
