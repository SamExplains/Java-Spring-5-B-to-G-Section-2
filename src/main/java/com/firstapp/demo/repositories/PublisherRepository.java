package com.firstapp.demo.repositories;

import com.firstapp.demo.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long > {


}
