package com.example.cadastropet.Exceptions;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Service.CadastroService;
import com.example.cadastropet.dtos.CadastroRecordDTO;

public class ExceptionsCheck {
    public ExceptionsCheck() {
    }
    public void CheckExceptionsSave(CadastroRecordDTO cadastroRecordDTO, CadastroModel cadastroModel){
        if (!cadastroRecordDTO.age().trim().isEmpty()) {
            if (Integer.parseInt(cadastroModel.getAge()) >= 20) {
                throw new AgeHigherThan19Exception("The pet can't be older than 19.");
            }
        }
        if (!cadastroRecordDTO.weight().trim().isEmpty()){
            if (Integer.parseInt(cadastroModel.getWeight()) > 60 || Integer.parseInt(cadastroModel.getWeight()) < 0.5) {
                throw new WeightOutOfBoundsException("The pet's weight HAS to be between 0.5 kg and 60kg.");
            }
        }
        if (cadastroRecordDTO.firstname() == null || !cadastroRecordDTO.firstname().matches("[a-zA-Z]+") || cadastroRecordDTO.firstname().trim().isEmpty()){
            throw new IllegalArgumentException("The name cannot be empty or blank, and only alphabetic characters (A–Z or a–z) are allowed.");
        }
        if (cadastroRecordDTO.firstname() == null || !cadastroRecordDTO.firstname().matches("[a-zA-Z]+")|| cadastroRecordDTO.lastname().trim().isEmpty()){
            throw new IllegalArgumentException("The name cannot be empty or blank, and only alphabetic characters (A–Z or a–z) are allowed.");
        }
        if (!cadastroModel.getRace().matches("[\\p{L} ]*")){
            throw new IllegalArgumentException("The race can't have numbers or special characters");
        }
        if (!cadastroRecordDTO.number().matches("[0-9 ]*")){throw new IllegalArgumentException("This field can't have letters or special characters");}
    }
}