package com.sunny.Data.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false)
    @JdbcType(CharJdbcType.class)
    private UUID uuid;

    @Column(nullable = false)
    private final String category;

    @Column(nullable = false)
    private final String title;

    @Column(nullable = false, name = "start_date")
    private final LocalDate startDate;

    @Column(nullable = false, name = "end_date")
    private final LocalDate endDate;

    @Column
    private int amount;

    @Column(nullable = false)
    private final String description;

    @Column(nullable = false)
    private final double price;

    private String image;


    @JoinColumn(name = "company_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "customer_coupon",
            joinColumns = @JoinColumn(name = "coupon_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customer> customers;

    @Version
    private Long version;
}

