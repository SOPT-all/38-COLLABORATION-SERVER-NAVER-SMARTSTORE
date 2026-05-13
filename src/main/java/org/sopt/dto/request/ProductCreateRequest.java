package org.sopt.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record ProductCreateRequest(

    @NotBlank
    @Size(max = 100)
    String name,

    @NotNull
    Long categoryId,

    @NotNull
    Object price,

    @NotEmpty
    List<@Valid ProductImageCreateRequest> images
) {
}
