package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userId;

    private String title;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String zipCode;
    private Integer phoneNumber;
    private LocalDate createdAt;
    private LocalDate updatedAt;


    public Address() {
    }

    private Address(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.title = builder.title;
        this.addressLine1 = builder.addressLine1;
        this.addressLine2 = builder.addressLine2;
        this.city = builder.city;
        this.country = builder.country;
        this.zipCode = builder.zipCode;
        this.phoneNumber = builder.phoneNumber;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(userId, address.userId) &&
                Objects.equals(title, address.title) &&
                Objects.equals(addressLine1, address.addressLine1) &&
                Objects.equals(addressLine2, address.addressLine2) &&
                Objects.equals(city, address.city) &&
                Objects.equals(country, address.country) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(phoneNumber, address.phoneNumber) &&
                Objects.equals(createdAt, address.createdAt) &&
                Objects.equals(updatedAt, address.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, addressLine1, addressLine2, city, country, zipCode, phoneNumber, createdAt, updatedAt);
    }

    public static class Builder {
        private Long id;
        private Long userId;
        private String title;
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String country;
        private String zipCode;
        private Integer phoneNumber;
        private LocalDate createdAt;
        private LocalDate updatedAt;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder setPhoneNumber(Integer phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDate updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder copy(Address address) {
            this.id = address.getId();
            this.userId = address.getUserId();
            this.title = address.getTitle();
            this.addressLine1 = address.getAddressLine1();
            this.addressLine2 = address.getAddressLine2();
            this.city = address.getCity();
            this.country = address.getCountry();
            this.zipCode = address.getZipCode();
            this.phoneNumber = address.getPhoneNumber();
            this.createdAt = address.getCreatedAt();
            this.updatedAt = address.getUpdatedAt();
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
