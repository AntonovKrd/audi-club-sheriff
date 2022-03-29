package krd.antonov.audiclubsheriff.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
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

    @Column(name = "chat_id", nullable = false)
    private String chatId;

    @Column(name = "date_birth", nullable = false)
    private LocalDate dateBirth;

    @Column(name = "tg_nickname", unique = true)
    private String tgNickname;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "date_registration", nullable = false)
    private LocalDate dateRegistration;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getChatId(), user.getChatId()) && Objects.equals(getDateBirth(), user.getDateBirth()) && Objects.equals(getTgNickname(), user.getTgNickname()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getDateRegistration(), user.getDateRegistration()) && Objects.equals(getVehicles(), user.getVehicles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getChatId(), getDateBirth(), getTgNickname(), getPhone(), getDateRegistration(), getVehicles());
    }
}
