package com.example.cadastropet.Controller;
import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.Exceptions.AgeHigherThan19Exception;
import com.example.cadastropet.Exceptions.ExceptionsCheck;
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
    @Autowired
    private CadastroService cadastroService;
    CadastroRecordDTO dto;

    //Indicates to SpringBoot that this method will receive a POST request
    @PostMapping("/cadastro")
    //Indicates that this method will be executed when the endpoint /products is called with the method HTTP Post
    public ResponseEntity<CadastroModel> save(@RequestBody @Valid CadastroRecordDTO cadastroRecordDTO){
        return service.saveProduct(cadastroRecordDTO);
    }
    //Get all
    @GetMapping("/GetAll")
    public ResponseEntity<List<CadastroModel>> getAll(){
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id){
        return service.DeleteById(id);
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<Object> Update(@PathVariable("id") Long id, @RequestBody @Valid CadastroRecordDTO cadastroRecordDTO){
        return service.Update(id, cadastroRecordDTO);
    }
    @GetMapping("/pets")
    public ResponseEntity<List<CadastroModel>> getPets(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String weight,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String race

    ) {
        return cadastroService.GetPets(id, firstname, lastname ,address, weight, age, gender, type, race);
    }
}
