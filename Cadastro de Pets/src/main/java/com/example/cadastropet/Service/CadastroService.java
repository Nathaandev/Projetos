package com.example.cadastropet.Service;
import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.Exceptions.AgeHigherThan19Exception;
import com.example.cadastropet.Exceptions.ExceptionsCheck;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import jakarta.persistence.Transient;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CadastroService {
    CadastroModel cadastroModel;
    static final String VALUE_NOT_INFORMED = "NOT INFORMED";
    @Autowired
    CadastroRepository repository;
    ExceptionsCheck exceptionsCheck = new ExceptionsCheck();

    public ResponseEntity<CadastroModel> saveProduct(@RequestBody @Valid CadastroRecordDTO cadastroRecordDTO){
        var cadastroModel = new CadastroModel();
        BeanUtils.copyProperties(cadastroRecordDTO, cadastroModel);
        if (cadastroModel.getRace().trim().isEmpty()){ cadastroModel.setRace(VALUE_NOT_INFORMED);}
        if (cadastroRecordDTO.number().trim().isEmpty()){ cadastroModel.setNumber(VALUE_NOT_INFORMED);}
        if (cadastroModel.getWeight().trim().isEmpty()){ cadastroModel.setWeight(VALUE_NOT_INFORMED);}
        if (cadastroModel.getAge().trim().isEmpty()){ cadastroModel.setAge(VALUE_NOT_INFORMED);}

        exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel);
        cadastroModel.setAddress(cadastroRecordDTO.street() + ", " + cadastroRecordDTO.number() + " - " + cadastroRecordDTO.city());
        String name = cadastroModel.getFirstname() + " " + cadastroModel.getLastname();
        cadastroModel.setName(name);
        cadastroModel.setFirstname(cadastroModel.getFirstname());
        cadastroModel.setLastname(cadastroModel.getLastname());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cadastroModel));

    }
    public ResponseEntity<List<CadastroModel>> getAll(){
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
    public ResponseEntity<Object> DeleteById(Long id){
        var pet = repository.findById(id);
        if (pet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Content deleted successfully");
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

    public ResponseEntity<List<CadastroModel>> GetPets(Long id, String firstname, String lastname, String address, String weight, String age, String gender, String type, String race){
        int active_filters = 0;
        List<CadastroModel> pets = repository.findAll();
        List<CadastroModel> filters = pets.stream()
                .filter(p -> id == null || p.getPetid().equals(id))
                .filter(p -> firstname == null || (p.getName() != null && p.getName().toLowerCase().contains(firstname.toLowerCase())))
                .filter(p -> lastname == null || (p.getName() != null && p.getName().toLowerCase().contains(lastname.toLowerCase())))
                .filter(p -> address == null || p.getAddress().equalsIgnoreCase(address))
                .filter(p -> weight == null || p.getWeight().equalsIgnoreCase(weight))
                .filter(p -> age == null || p.getAge().equalsIgnoreCase(age))
                .filter(p -> gender == null || p.getGender().name().equalsIgnoreCase(gender))
                .filter(p -> type == null || p.getType().name().equalsIgnoreCase(type))
                .filter(p -> race == null || p.getRace().equalsIgnoreCase(race))

                .collect(Collectors.toList());
        if(id != null){
            active_filters++;
        }
        if(firstname != null){
            active_filters++;
        }
        if(lastname != null){
            active_filters++;
        }
        if(address != null){
            active_filters++;
        }
        if(weight != null && !Objects.equals(weight, VALUE_NOT_INFORMED)){
            active_filters++;
        }
        if(age != null && !Objects.equals(age, VALUE_NOT_INFORMED)){
            active_filters++;
        }
        if(gender != null){
            active_filters++;
        }
        if(type != null){
            active_filters++;
        }
        if (race != null && !Objects.equals(race, VALUE_NOT_INFORMED)) {
            active_filters++;
        }
        exceptionsCheck.CheckExceptionsGet(active_filters);
        return ResponseEntity.ok(filters);

    }
}
