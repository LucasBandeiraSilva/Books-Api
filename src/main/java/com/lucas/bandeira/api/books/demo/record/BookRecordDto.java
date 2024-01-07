package com.lucas.bandeira.api.books.demo.record;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookRecordDto(@NotBlank String title, @NotBlank String author, @NotNull  @Min(20) int totalPages) {

}
