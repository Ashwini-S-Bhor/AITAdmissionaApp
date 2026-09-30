package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Courses;

public interface CourseRepository extends JpaRepository<Courses,Integer>{

}
