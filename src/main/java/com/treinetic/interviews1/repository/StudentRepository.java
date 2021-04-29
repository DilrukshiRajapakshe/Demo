package com.treinetic.interviews1.repository;

import com.treinetic.interviews1.dto.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    @Query("{Student: { $regex: ?0 } })")
    List<Student> findNot_approved(String status);
}
