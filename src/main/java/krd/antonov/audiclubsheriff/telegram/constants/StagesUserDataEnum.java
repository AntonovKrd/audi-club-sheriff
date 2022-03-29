package krd.antonov.audiclubsheriff.telegram.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StagesUserDataEnum {

    USER_PHONE_STAGE(1),
    USER_NAME_STAGE(2),
    USER_DATE_BIRTH_STAGE(3),
    VEHICLE_LICENCE_PLATE_STAGE(4),
    VEHICLE_MODEL_STAGE(5),
    VEHICLE_YEAR_STAGE(6);

    private final Integer stage;
}
