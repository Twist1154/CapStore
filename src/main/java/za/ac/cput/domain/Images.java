package za.ac.cput.domain;

import jakarta.persistence.Embeddable;

/**
 * Images.java
 *
 * Represents multiple image URLs for a Product.
 */
@Embeddable
public class Images {
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String imageUrl4;


    public Images() {}

    public Images(String imageUrl1, String imageUrl2, String imageUrl3, String imageUrl4) {
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
        this.imageUrl4 = imageUrl4;
    }


    public String getImageUrl1() {
        return imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public String getImageUrl4() {
        return imageUrl4;
    }

    // Setters
    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public void setImageUrl4(String imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }

    @Override
    public String toString() {
        return "Images{" +
                "\n imageUrl1='" + imageUrl1 + '\'' +
                "\n imageUrl2='" + imageUrl2 + '\'' +
                "\n imageUrl3='" + imageUrl3 + '\'' +
                "\n imageUrl4='" + imageUrl4 + '\'' +
                '}';
    }
}
