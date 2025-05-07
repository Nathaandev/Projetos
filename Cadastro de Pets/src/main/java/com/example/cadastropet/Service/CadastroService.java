package com.example.cadastropet.Service;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

// @Service
public class CadastroService {


    private final CadastroRepository repository;

    public CadastroService(CadastroRepository repository) {
        this.repository = repository;
    }

   // public ResponseEntity<CadastroModel> save(@RequestBody CadastroRecordDTO cadastroRecordDTO) {
    //    var cadastroModel = new CadastroModel();
    //    BeanUtils.copyProperties(cadastroRecordDTO, cadastroModel);
     //   return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cadastroModel));
    //}
}
