package com.example.block11uploaddownloadfiles.domain;
import com.example.block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.example.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fichero {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String fecha_subida;
    private String categoria;
    private String ruta;


    public Fichero(FicheroInputDto ficheroInputDto){
        this.id = ficheroInputDto.getId();
        this.name = ficheroInputDto.getName();
        this.fecha_subida = ficheroInputDto.getFecha_subida();
        this.categoria = ficheroInputDto.getCategoria();
        this.ruta = ficheroInputDto.getRuta();
    }

    public FicheroOutputDto ficheroToficheroOutputDto(){
        return new FicheroOutputDto(
                this.id,
                this.name,
                this.fecha_subida,
                this.categoria,
                this.ruta
        );
    }
}
