package com.funtravelapp.notif.model.notifStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notif_status")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class NotifStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "chaining_id")
    private String chainingId;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "seller_id")
    private Integer sellerId;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "seller_email")
    private String sellerEmail;
    // Sent, pending
    @Column(name = "status")
    private String status;
}
