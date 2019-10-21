package com.jwt.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ImageItemOrder.
 */
@Entity
@Table(name = "image_item_order")
public class ImageItemOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_order")
    private Integer order;

    @OneToOne(mappedBy = "imageItemOrder")
    @JsonIgnore
    private ImageItem imageItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public ImageItemOrder order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public ImageItem getImageItem() {
        return imageItem;
    }

    public ImageItemOrder imageItem(ImageItem imageItem) {
        this.imageItem = imageItem;
        return this;
    }

    public void setImageItem(ImageItem imageItem) {
        this.imageItem = imageItem;
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
        ImageItemOrder imageItemOrder = (ImageItemOrder) o;
        if (imageItemOrder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imageItemOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImageItemOrder{" +
            "id=" + getId() +
            ", order=" + getOrder() +
            "}";
    }
}
