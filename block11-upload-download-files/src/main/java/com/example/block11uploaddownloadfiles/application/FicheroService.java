package com.example.block11uploaddownloadfiles.application;

import com.example.block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.example.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import com.example.block11uploaddownloadfiles.domain.Fichero;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FicheroService {
    Fichero subirFichero(String ruta, MultipartFile file) throws Exception;

    Resource descargarFichero(String name) throws Exception;

    FicheroOutputDto getFicheroById(int id);

    List<FicheroOutputDto> getFicheroByName(String name);

}
