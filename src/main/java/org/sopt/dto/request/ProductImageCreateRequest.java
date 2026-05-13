package org.sopt.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductImageCreateRequest(

    @NotBlank
    String imageUrl,

    @NotBlank
    String contentType,

    @NotNull
    @Min(1)
    Integer imageOrder,

    @NotNull
    Boolean isRepresentative
) {

}
