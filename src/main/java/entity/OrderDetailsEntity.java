package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name =  "order_details")
public class OrderDetailsEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "orderId")

    private OrderEntity order;
    private String itemCode;
    private Integer qty;
    private Double discount;

    }
