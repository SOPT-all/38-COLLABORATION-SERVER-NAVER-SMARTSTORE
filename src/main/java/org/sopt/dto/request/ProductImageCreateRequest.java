package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductImageCreateRequest(

    @NotBlank
    String imageUrl,

    @NotBlank
    String contentType,

    @NotNull
    Integer imageOrder,

    @NotNull
    Boolean isRepresentative
) {

}
