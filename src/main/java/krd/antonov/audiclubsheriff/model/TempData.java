package krd.antonov.audiclubsheriff.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "temp_data")
@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class TempData {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "chat_id", nullable = false)
    private String chatId;

    @Column(name = "stage", nullable = false)
    private Integer stage;

    @Column(name = "value")
    private String value;

    @Column(name = "date_event", nullable = false)
    @CreationTimestamp
    private LocalDateTime dateEvent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TempData tempData = (TempData) o;
        return Objects.equals(getId(), tempData.getId()) && Objects.equals(getChatId(), tempData.getChatId()) && Objects.equals(getStage(), tempData.getStage()) && Objects.equals(getValue(), tempData.getValue()) && Objects.equals(getDateEvent(), tempData.getDateEvent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getChatId(), getStage(), getValue(), getDateEvent());
    }
}
