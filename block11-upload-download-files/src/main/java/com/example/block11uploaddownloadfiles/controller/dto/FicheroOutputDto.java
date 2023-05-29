package com.example.block11uploaddownloadfiles.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheroOutputDto{
        private int id;
        private String name;
        private String fecha_subida;
        private String categoria;
        private String ruta;
}
