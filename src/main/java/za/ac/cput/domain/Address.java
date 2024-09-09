package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;
    @Column(name = "userID")
    private Long userID;
    private String addressLine;
    private String city;
    private String country;
    private String zipCode;
    private Integer phoneNumber;
    private LocalDate createdAt;
    private LocalDate deletedAt;

    protected  Address() {
    }

    private Address (Builder builder){
        this.addressID = builder.addressID;
        this.userID = builder.userID;
        this.addressLine = builder.addressLine;
        this.city = builder.city;
        this.country = builder.country;
        this.zipCode = builder.zipCode;
        this.phoneNumber = builder.phoneNumber;
        this.createdAt = builder.createdAt;
        this.deletedAt = builder.deletedAt;
    }

    public Long getAddressID() {
        return addressID;
    }

    public Long getUserID() {
        return userID;
    }

    public String getAddressLine() {
        return addressLine;
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

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressID, address.addressID) &&
                Objects.equals(userID, address.userID); // Exclude nullable fields here
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressID, userID);  // Consider only key fields
    }



    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", userID=" + userID +
                ", addressLine='" + addressLine + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdAt=" + createdAt +
                ", deletedAt=" + (deletedAt != null ? deletedAt : "N/A") + // Handle null case
                '}';
    }


    public static class Builder{
        private Long addressID;
        private Long userID;
        private String addressLine;
        private String city;
        private String country;
        private String zipCode;
        private Integer phoneNumber;
        private LocalDate createdAt;
        private LocalDate deletedAt;

        public Builder setAddressID(Long addressID) {
            this.addressID = addressID;
            return this;
        }

        public Builder setUserID(Long userID) {
            this.userID = userID;
            return this;
        }

        public Builder setAddressLine(String addressLine) {
            this.addressLine = addressLine;
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
            this.phoneNumber = phoneNumber;return this;
        }

        public Builder setCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setDeletedAt(LocalDate deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public Builder copy (Address address){
            this.addressID = address.addressID;
            this.userID = address.userID;
            this.addressLine = address.addressLine;
            this.city = address.city;
            this.country = address.country;
            this.zipCode = address.zipCode;
            this.phoneNumber = address.phoneNumber;
            this.createdAt = address.createdAt;
            this.deletedAt = address.deletedAt;
            return this;
        }

        public Address build(){return new Address(this);}

    }
}
