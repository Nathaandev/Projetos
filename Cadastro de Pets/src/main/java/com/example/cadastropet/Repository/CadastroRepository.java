package com.example.cadastropet.Repository;

import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.Model.CadastroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CadastroRepository extends JpaRepository<CadastroModel, Long> {
    List<CadastroModel> findByGender(MascOrFem gender);
    List<CadastroModel> findByType(CatOrDog type);
}
