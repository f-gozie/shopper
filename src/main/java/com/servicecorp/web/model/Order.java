package com.servicecorp.web.model;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private int id;
    private int user_id;
    private int company_id;
    private int quantity;
    private float price_total;
    private float unit_price;
    private String created_at;
    private String status;
}
