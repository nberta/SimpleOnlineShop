package edu.miu.simpleshop.domain;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String imgName;

    private MultipartFile image;

    public ProductImage() {
    }

    public ProductImage(long id, String imgName) {
        this.id = id;
        this.imgName = imgName;
    }

    public String getImgName() {
        return imgName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
