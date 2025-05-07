package com.example.cadastropet.dtos;
import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import jakarta.validation.constraints.NotBlank;

public record CadastroRecordDTO(@NotBlank String name, @NotBlank String race, String street, int number, String city, double weight, CatOrDog type, MascOrFem gender, int age) {
}
