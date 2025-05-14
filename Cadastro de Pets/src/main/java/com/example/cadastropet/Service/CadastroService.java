package com.example.cadastropet.Service;
import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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
        String sex = null;
        int sexlenght = sex.length();
        List<CadastroModel> pets = repository.findAll();
        if(pets.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pets);
    }
    public ResponseEntity<?> getById(Long id){
        var pet = repository.findById(id);
        if(pet.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no pet with such id");
        }
        return ResponseEntity.ok(pet.get());
    }
    public ResponseEntity<List<CadastroModel>> getByGender(MascOrFem gender){
        List<CadastroModel> pets = repository.findByGender(gender);
        if(pets.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(pets);
    }
    public ResponseEntity<List<CadastroModel>> getByType(CatOrDog type){
        List<CadastroModel> pets = repository.findByType(type);
        if(pets.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } return ResponseEntity.ok(pets);
    }
    public ResponseEntity<?> Delete(Long id){
        var pet = repository.findById(id);
        if(pet.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no pet with such id");
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Content deleted successfully.");
    }
    public ResponseEntity<Object> Update(@PathVariable("id") Long id, @RequestBody @Valid CadastroRecordDTO cadastroRecordDTO){
        Optional<CadastroModel> pet = repository.findById(id);
        if(pet.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var atualizar = pet.get();
        BeanUtils.copyProperties(cadastroRecordDTO, atualizar);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(atualizar));
    }
}
