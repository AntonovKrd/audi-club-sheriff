package krd.antonov.audiclubsheriff.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getId(), vehicle.getId()) && Objects.equals(getLicensePlate(), vehicle.getLicensePlate()) && Objects.equals(getModel(), vehicle.getModel()) && Objects.equals(getYear(), vehicle.getYear()) && Objects.equals(getUser(), vehicle.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLicensePlate(), getModel(), getYear(), getUser());
    }
}
