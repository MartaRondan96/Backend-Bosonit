package com.example.block11uploaddownloadfiles.application;

import com.example.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import com.example.block11uploaddownloadfiles.domain.Fichero;
import com.example.block11uploaddownloadfiles.repository.FicheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FicheroServiceImpl implements FicheroService {

    @Autowired
    private FicheroRepository ficheroRepository;

    @Override
    public Fichero subirFichero(String ruta, MultipartFile file) throws Exception {
    //Comprueba si el fichero que se ha pasado no este vacio
        if (file.isEmpty()) {
            throw new Exception();
        }
        // Guarda el archivo en la ruta
        Path directorioDestino = Paths.get(ruta);
        File directorio = directorioDestino.toFile();

        // Crear el directorio si no existe
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        file.transferTo(Path.of(directorio + "/" + file.getOriginalFilename()));
        //Crea el objeto Fichero para añadirlo a la BD
        Fichero archivoDatos = new Fichero();
        archivoDatos.setName(file.getOriginalFilename());
        archivoDatos.setFecha_subida(LocalDateTime.now().toString());
        archivoDatos.setCategoria(StringUtils.getFilenameExtension(archivoDatos.getName()));
        archivoDatos.setRuta(ruta);
        ficheroRepository.save(archivoDatos);
        return archivoDatos;
    }

    @Override
    public Resource descargarFichero(String nombreFichero) throws Exception {
        Fichero file = new Fichero();

        for (Fichero f : ficheroRepository.findByName(nombreFichero)) {
            if (f.getName().equals(nombreFichero)) {
                file.setName(f.getName());
                file.setId(f.getId());
                file.setRuta(f.getRuta());
                file.setCategoria((f.getCategoria()));
            }
        }
        // Obtener el archivo del sistema de archivos
        Resource resource = new FileSystemResource(file.getRuta() + "\\" + nombreFichero);
        // Verificar si el archivo existe
        if (!resource.exists())
            throw new Exception();
        return resource;
    }

    @Override
    public FicheroOutputDto getFicheroById(int id) {
        Optional<Fichero> f = ficheroRepository.findById(id);
        Fichero file = f.orElseThrow(() -> new RuntimeException("No existe fichero con ese ID"));
        FicheroOutputDto ficheroOutputDto = file.ficheroToficheroOutputDto();
        return ficheroOutputDto;
    }

    @Override
    public List<FicheroOutputDto> getFicheroByName(String name) {
        List<FicheroOutputDto> lista = new ArrayList<>();

        for (Fichero f : ficheroRepository.findByName(name)) {
            if (f.getName().equals(name)) {
                lista.add(f.ficheroToficheroOutputDto());
            }
        }
        return lista;
    }
}
