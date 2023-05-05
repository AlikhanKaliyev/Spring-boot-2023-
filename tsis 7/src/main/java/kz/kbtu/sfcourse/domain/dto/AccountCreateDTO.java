package kz.kbtu.sfcourse.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountCreateDTO {
    @Schema(description = "valid person name", example = "John")
    private String username;

    private String password;

    @Schema(description = "valid email address", example = "john_wick@mail.ru")
    private String email;


    @Schema(description = "loginDate", type = "string", example = "2023-04-01T05:37:26.123+0600")
    private ZonedDateTime loginDate;

}
