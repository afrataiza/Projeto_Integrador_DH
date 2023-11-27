package com.br.digital_hoteis.app.api.dto.request;

import com.br.digital_hoteis.domain.entity.Characteristics;
import com.br.digital_hoteis.domain.entity.Images;
import com.br.digital_hoteis.domain.entity.Policy;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.Set;
import java.util.UUID;

public record CreateHotelRequest(
        @Schema(example = "My Hotel") @NotBlank String trading_name,
        @Schema(example = "50.950.077/0001-09") @CNPJ @NotBlank String cnpj,
        @Schema(example = "An amazing hotel with pools and jacuzzis") @NotBlank String description,
        @Schema(description = "The category of the hotel") CreateCategoryRequest category,
        @Schema(description = "The contact information of the hotel") CreateContactRequest contact,
        @Schema(description = "The city information of the hotel") CreateCityRequest city,
        Set<UUID> hosts,
        @Schema(description = "The policy conduct of the hotel") Policy policy,
        Set<Characteristics> characteristics,
        Set<UUID> rooms,
        Set<Images> images // images DÚVIDA: será preciso adicionar imagens aqui??
) {
}
