package com.yh.store.pojo;

import java.io.Serializable;
import java.util.Objects;

//收藏类
public class Collect extends BaseEntity implements Serializable {

    private Integer id;
    private Integer uid;
    private Integer pid;
    private Long price;
    private String title;
    private String image;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Collect collect = (Collect) o;
        return Objects.equals(id, collect.id) && Objects.equals(uid, collect.uid) && Objects.equals(pid, collect.pid) && Objects.equals(price, collect.price) && Objects.equals(title, collect.title) && Objects.equals(image, collect.image) && Objects.equals(status, collect.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, uid, pid, price, title, image, status);
    }
}
