package com.jwt.model;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ImageItem.
 */
@Entity
@Table(name = "image_item")
public class ImageItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(unique = true)
    private ImageItemOrder imageItemOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ImageItem imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageItemOrder getImageItemOrder() {
        return imageItemOrder;
    }

    public ImageItem imageItemOrder(ImageItemOrder imageItemOrder) {
        this.imageItemOrder = imageItemOrder;
        return this;
    }

    public void setImageItemOrder(ImageItemOrder imageItemOrder) {
        this.imageItemOrder = imageItemOrder;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImageItem imageItem = (ImageItem) o;
        if (imageItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageItem{" +
            "id=" + getId() +
            ", imageUrl='" + getImageUrl() + "'" +
            "}";
    }
}
