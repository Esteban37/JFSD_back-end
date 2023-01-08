package com.mitocode.controller;

import com.mitocode.dto.ExamDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Exam;
import com.mitocode.service.IExamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private IExamService service;// = new ExamService();

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> findAll(){
        List<ExamDTO> list = service.findAll().stream().map(p -> mapper.map(p, ExamDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> findById(@PathVariable("id") Integer id){
        Exam obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, ExamDTO.class), HttpStatus.OK);
    }

    /*@PostMapping
    public ResponseEntity<Exam> save(@RequestBody Exam exam){
        Exam obj = service.save(exam);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }*/

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ExamDTO dto){
        Exam obj = service.save(mapper.map(dto, Exam.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExam()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Exam> update(@Valid @RequestBody ExamDTO dto){
        Exam obj = service.update(mapper.map(dto, Exam.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        Exam obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ExamDTO> findByIdHateoas(@PathVariable("id") Integer id){
        Exam obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        EntityModel<ExamDTO> resource = EntityModel.of(mapper.map(obj, ExamDTO.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("exam-info1"));
        resource.add(link2.withRel("exam-info2"));

        return resource;
    }



    /*public ExamController(ExamService service){
        this.service = service;
    }*/

    /*@GetMapping
    public String sayHello(){
        //Exam exam = new Exam(1, "mitocode");
        return service.sayHello(null);
    }*/
}
