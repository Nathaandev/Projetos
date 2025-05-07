package com.example.cadastropet.Controller;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CadastroController {

    @Autowired
    CadastroRepository repository;

    //Indicates to SpringBoot that this method will receive a POST request
    @PostMapping("/cadastro")
    //Indicates that this method will be executed when the endpoint /products is called with the method HTTP Post

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
    @GetMapping("/cadastro")
    public ResponseEntity<Iterable<CadastroModel>> findAll(){ //Create a method called findAll that returns an Iterable of ProductModel objects
        return ResponseEntity.ok(repository.findAll()); //Returns an HTTP response code 200 (OK) and all the objects saved in the database
    }

}
