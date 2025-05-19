package com.example.cadastropet.dtos;
import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import jakarta.validation.constraints.NotBlank;

public record CadastroRecordDTO(String firstname, String lastname, String race, String street, String number, String city, String weight, CatOrDog type, MascOrFem gender, String age) {


}
