package com.mitocode.controller;

import com.mitocode.dto.VitalSignDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.VitalSign;
import com.mitocode.service.IVitalSignService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vitalsigns")
public class VitalSignController {

    @Autowired
    private IVitalSignService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VitalSignDTO>> findAll(){
        List<VitalSignDTO> list = service.findAll().stream().map(p -> mapper.map(p, VitalSignDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSignDTO> findById(@PathVariable("id") Integer id){
        VitalSign obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, VitalSignDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody VitalSignDTO dto){
        VitalSign obj = service.save(mapper.map(dto, VitalSign.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVitalSign()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<VitalSign> update(@Valid @RequestBody VitalSignDTO dto){
        VitalSign obj = service.update(mapper.map(dto, VitalSign.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        VitalSign obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<VitalSignDTO>> listPage(Pageable pageable){
        Page<VitalSignDTO> page = service.listPage(pageable).map(p->mapper.map(p, VitalSignDTO.class));

        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
