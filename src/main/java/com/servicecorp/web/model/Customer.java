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
public class Customer {
    @Id
    private long id;
    private String name;
    private String contact_email;
    private String contact_phone;
    private String contact_address;
    private String created_at;
}
