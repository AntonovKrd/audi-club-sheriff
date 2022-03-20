package krd.antonov.audiclubsheriff.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Table(name = "vehicles")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Vehicle {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "base64_generated_id")
    private Long id;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
