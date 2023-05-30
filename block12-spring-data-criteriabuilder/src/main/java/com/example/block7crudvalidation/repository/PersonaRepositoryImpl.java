package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PersonaRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<PersonaOutputDto> getCustomQuery(
            HashMap<String, Object>  conditions , String order) {

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
        if (conditions.get("date_min") != null && conditions.get("date_max") != null) {
            predicates.add(cb.between(root.get("create_date"), (LocalDate) conditions.get("date_min"), (LocalDate) conditions.get("date_max")));
        }

        if (order.equals("u")) {
            System.out.println(conditions.get("order"));
            query.orderBy(cb.asc(root.get("usuario")));
        }
        if (order.equals("n")) {
            query.orderBy(cb.desc(root.get("name")));
        }
        query.select(root)
                .where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager
                .createQuery(query)
                .getResultList()
                .stream()
                .map(Persona::personaToPersonaOutputDTO)
                .toList();

    }
}
