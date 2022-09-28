package com.yh.store.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OrderItemVO implements Serializable {
    private Integer oid;
    private String recvName;
    private Date orderTime;
    private Integer status;
    private String title;
    private String image;
    private Long price;
    private Long totalPrice;
    private Integer num;

    @Override
    public String toString() {
        return "OrderItemVO{" +
                "oid=" + oid +
                ", recvName='" + recvName + '\'' +
                ", orderTime=" + orderTime +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", num=" + num +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemVO that = (OrderItemVO) o;
        return Objects.equals(oid, that.oid) && Objects.equals(recvName, that.recvName) && Objects.equals(orderTime, that.orderTime) && Objects.equals(status, that.status) && Objects.equals(title, that.title) && Objects.equals(image, that.image) && Objects.equals(price, that.price) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oid, recvName, orderTime, status, title, image, price, totalPrice, num);
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
