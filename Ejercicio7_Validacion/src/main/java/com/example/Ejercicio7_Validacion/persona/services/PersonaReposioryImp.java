package com.example.Ejercicio7_Validacion.persona.services;

import com.example.Ejercicio7_Validacion.persona.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonaReposioryImp{
    @PersistenceContext
    private EntityManager entityManager;
    public List getPersonaCustomQuery(String ordenar,String paramOrdenar, HashMap<String, Object> parametros, int pagina, int cantidad) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);
        List<Predicate> predicates = new ArrayList<>();
        Set keys = parametros.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            predicates.add(cb.like(root.get(key), "%" + parametros.get(key) + "%"));
        }
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        if (ordenar.equals("asc")) {
            query.orderBy(cb.asc(root.get(paramOrdenar)));
        } else if (ordenar.equals("desc")) {
            query.orderBy(cb.desc(root.get(paramOrdenar)));
        }
        int firstResult = (pagina - 1) * cantidad;
        return entityManager.createQuery(query)
                .setFirstResult(firstResult)
                .setMaxResults(cantidad)
                .getResultList();
    }
}
