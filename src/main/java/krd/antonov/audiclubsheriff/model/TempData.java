package krd.antonov.audiclubsheriff.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table(name = "temp_data")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class TempData {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "base64_generated_id")
    private Long id;

    @Column(name = "chat_id", nullable = false)
    private String chatId;

    @Column(name = "stage", nullable = false)
    private int stage;

    @Column(name = "value")
    private String value;

    @Column(name = "date_event", nullable = false)
    private LocalDate dateEvent;

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
