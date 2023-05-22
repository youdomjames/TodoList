package com.task.repository;

import com.task.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TaskRepository extends MongoRepository<Task, String> {
    @Query("{ $or: [ { 'taskTitle': { $regex: ?0, $options: 'i' } }, " +
            "{ 'taskPriority': { $regex: ?0, $options: 'i' } }, { 'taskDateAndTime': { $regex: ?0, $options: 'i' } }, { 'isAddedToCalendar': { $regex: ?0, $options: 'i' } } ] }")
    Page<Task> findAll(String filter, Pageable pageable);
}
