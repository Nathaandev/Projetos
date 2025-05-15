package com.example.cadastropet.Controller;
import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.Exceptions.AgeHigherThan19;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.Service.CadastroService;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CadastroController {

    @Autowired
    CadastroService service;

    //Indicates to SpringBoot that this method will receive a POST request
    @PostMapping("/cadastro")
    //Indicates that this method will be executed when the endpoint /products is called with the method HTTP Post
    public ResponseEntity<CadastroModel> save(@RequestBody @Valid CadastroRecordDTO cadastroRecordDTO){
        if (cadastroRecordDTO.age() >= 20) {
            throw new AgeHigherThan19("The pet can't be older than 19.");
        }
        return service.saveProduct(cadastroRecordDTO);
    }
    //Get all
    @GetMapping("/GetAll")

    public ResponseEntity<List<CadastroModel>> getAll(){
        return service.getAll();
    }
    //Get by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return service.getById(id);
    }
    //Get by gender
    @GetMapping("/GetByGender/{gender}")
    public ResponseEntity<List<CadastroModel>> getByGender(@PathVariable MascOrFem gender){
        return service.getByGender(gender);
    }
    @GetMapping("/GetByType/{type}")
    public ResponseEntity<List<CadastroModel>> getByType(@PathVariable CatOrDog type){
        return service.getByType(type);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id){
        return service.Delete(id);
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<Object> Update(@PathVariable("id") Long id, @RequestBody @Valid CadastroRecordDTO cadastroRecordDTO){
        return service.Update(id, cadastroRecordDTO);
    }

}
