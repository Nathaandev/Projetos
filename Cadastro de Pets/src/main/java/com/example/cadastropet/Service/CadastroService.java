package com.example.cadastropet.Service;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CadastroService {

    @Autowired
    CadastroRepository repository;


    public ResponseEntity<CadastroModel> saveProduct(@RequestBody @Valid CadastroRecordDTO cadastroRecordDTO){
        //The ResponseEntity Contains the HTTP response code and the body of the response
        //The RequestBody annotation will take the JSON and transform it into a ProductRecordDTO object (Name and Price)
        //The Valid annotation activates the @NotNull and @NotBlank annotations on the ProductRecordDTO class
        var cadastroModel = new CadastroModel();
        BeanUtils.copyProperties(cadastroRecordDTO, cadastroModel); //Will copy the values from the ProductRecordDTO to the ProductModel (Name and Price)
        String endereço = cadastroRecordDTO.street() + ", " + cadastroRecordDTO.number() + " - " + cadastroRecordDTO.city();
        cadastroModel.setEndereço(endereço);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cadastroModel));
        //Returns an HTTP response code 201 (Created) and the ProductModel object saved in the database
    }
    public ResponseEntity<List<CadastroModel>> getAll(){
        List<CadastroModel> pets = repository.findAll();
        if(pets.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pets);
    }
}
