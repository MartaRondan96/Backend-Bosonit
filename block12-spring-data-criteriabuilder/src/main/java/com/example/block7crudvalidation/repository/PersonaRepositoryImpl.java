package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonaRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<PersonaOutputDto> getCustomQuery(
            HashMap<String, Object>  conditions , String order, int pageNumber, int pageSize) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field, value) -> {
            switch (field) {
                case "usuario":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    break;
                case "name":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    break;
            }
        });
        //Comprueba los campos date_min y date_max del HashMap
        //Si tiene ambos datos devuelve los registros con create_date entre las fechas dadas
        //si solo tiene uno de los dos devuelve los que tengan la misma fecha de creacion o bien mayor o menor en funcion del caso
        //si tiene date_min registros con create_date menor o igual y si tiene date_min mayor o igual
        if (conditions.get("date_min") != null && conditions.get("date_max") != null) {
            predicates.add(cb.between(root.get("create_date"), (LocalDate) conditions.get("date_min"), (LocalDate) conditions.get("date_max")));
        }else if (conditions.get("date_min") != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("creationDate"), (LocalDate) conditions.get("date_min")));
        } else if (conditions.get("date_max") != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("creationDate"), (LocalDate) conditions.get("date_max")));
        }
        //Comprueba el tipo de orden que quiere establecer en los resultados de la consulta
        if (order.equals("u")) {
            query.orderBy(cb.asc(root.get("usuario")));
        }
        if (order.equals("n")) {
            query.orderBy(cb.desc(root.get("name")));
        }

        query.select(root)
                .where(predicates.toArray(new Predicate[predicates.size()]));

        //setFirstResult((pageNumber - 1) * pageSize)-> posicion inicial de los resultados
        //setMaxResults(pageSize)-> nº max de resultados por pagina
        //getResultList()-> obtiene la lista de resultados de la consulta.
        return entityManager
                .createQuery(query)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList()
                .stream()
                .map(Persona::personaToPersonaOutputDTO)
                .toList();

    }
}
