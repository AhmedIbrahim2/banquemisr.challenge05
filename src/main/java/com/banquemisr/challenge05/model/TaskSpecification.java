package com.banquemisr.challenge05.model;

import com.banquemisr.challenge05.model.enums.Status;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta. persistence. criteria. Predicate;

public class TaskSpecification {

    public static Specification<Task> getTasksByCriteria(String title, String description, Status status, LocalDateTime dueDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }
            if (description != null && !description.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + description + "%"));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (dueDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("dueDate"), dueDate));
            }

            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction(); // This means "no filter", returning all results
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
