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
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false)
    @JdbcType(CharJdbcType.class)
    public UUID uuid;

    @Column(nullable = false)
    public final String name;

    @Column(nullable = false)
    public final String email;

    @Column(nullable = false)
    public final String password;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Coupon> coupons;

    @Version
    private Long version;
}
