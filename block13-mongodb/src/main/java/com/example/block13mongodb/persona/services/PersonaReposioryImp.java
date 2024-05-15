package com.example.block13mongodb.persona.services;

import com.example.block13mongodb.persona.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonaReposioryImp{
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Persona> getPersonaCustomQuery(String ordenar, String paramOrdenar, HashMap<String, Object> parametros, int pagina, int cantidad) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();

        Set<String> keys = parametros.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            criteriaList.add(Criteria.where(key).regex(".*" + parametros.get(key) + ".*"));
        }

        criteria.andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
        query.addCriteria(criteria);

        if (ordenar.equals("asc")) {
            query.with(Sort.by(Sort.Direction.ASC, paramOrdenar));
        } else if (ordenar.equals("desc")) {
            query.with(Sort.by(Sort.Direction.DESC, paramOrdenar));
        }
        int saltar = (pagina - 1) * cantidad;
        query.skip(saltar).limit(cantidad);

        return mongoTemplate.find(query, Persona.class);
    }

}
