package com.example.cadastropet.Controller;
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
        return service.saveProduct(cadastroRecordDTO);
    }
    @GetMapping("/cadastro")
    public ResponseEntity<List<CadastroModel>> getAll(){
        return service.getAll();
    }


}
