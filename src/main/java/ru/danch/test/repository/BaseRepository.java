package ru.danch.test.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Long>, QuerydslPredicateExecutor<T> {

    default Stream<T> findAllByPredicate(Predicate predicate) {
        return StreamSupport.stream(this.findAll(predicate).spliterator(), false);
    }

}
