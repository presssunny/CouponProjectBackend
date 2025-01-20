package com.sunny.Data.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(unique = true, updatable = false)
    @JdbcType(CharJdbcType.class)
    private UUID uuid;

    @Column(nullable = false, name = "first_name")
    private final String firstName;

    @Column(nullable = false, name = "last_name")
    private final String lastName;

    @Column(nullable = false)
    private final String email;

    @Column(nullable = false)
    private final String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "customer_coupon",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id")
    )
    private Set<Coupon> PurchasedCoupons;
    @Version
    private Long version;
}
