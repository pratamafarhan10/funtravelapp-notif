package com.funtravelapp.notif.model.notifStatus;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "notif_status")
public class NotifStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "email")
    private String email;
    // Sent, pending
    @Column(name = "status")
    private String status;

    public NotifStatus() {
    }

    public NotifStatus(Integer orderId, Integer userId, String email, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.email = email;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
