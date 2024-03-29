package kz.kbtu.sfcourse.domain.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountCreatedEvent {
    String aggregateObjectType;
    String aggregateObjectId;
    String messagePayload;
    LocalDateTime emittedDate;
}
