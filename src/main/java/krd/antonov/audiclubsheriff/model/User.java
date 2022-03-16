package krd.antonov.audiclubsheriff.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class User {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "base64_generated_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dateBirth", nullable = false)
    private Date dateBirth;

    @Column(name = "tgNickname", nullable = false, unique = true)
    private String tgNickname;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Fetch(value = FetchMode.SELECT)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vehicle> vehicles;

    public User addVehicle(Vehicle vehicle) {
        if (vehicle == null) vehicles = new HashSet<>();
        vehicles.add(vehicle.setUser(this));
        return this;
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicle.setUser(null);
        if (vehicles == null) return;
        vehicles.remove(vehicle);
    }
}
