package com.espe.crud.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.espe.crud.model.DocenteExterno;
import com.espe.crud.repository.docenteExternoRepository;

@CrossOrigin(origins = "*")
@RestController

public class DocenteExternoController {

	public static final Logger logger = LoggerFactory.getLogger(DocenteExternoController.class);

	@Autowired
	docenteExternoRepository repository;

	// **MUÃ‰STRA TODAS LOS CONVENIOS EXISTENTES EN LA BASE DE DADTOS**

    @GetMapping("/DocentesExternos")
    public List<DocenteExterno> getAllConvocatorias() {
      System.out.println("Get all DocentesExternos ...");
   
      List<DocenteExterno> DocenteExterno = new ArrayList<>();
      repository.findAll().forEach(DocenteExterno::add);
   
      return DocenteExterno;
    }

	// **MUESTRA UN CONVENIO ESPECIFICA

    
    @GetMapping(value = "DocenteExterno/{id}")
    public List<DocenteExterno> findById(@PathVariable int id) {
   
      List<DocenteExterno> DocenteExterno = repository.findById(id);
      return DocenteExterno;
    }

	// **CREA UNA NUEVO CONVENIO**

	@PostMapping(value = "/DocenteExterno")
	public DocenteExterno postDocenteExterno(@RequestBody DocenteExterno DocenteExterno) {
		DocenteExterno _DocenteExterno = repository.save(new DocenteExterno(
				 DocenteExterno.getId(),  DocenteExterno.getApellido(),  DocenteExterno.getNombre(),  DocenteExterno.getCargo(),
				 DocenteExterno.getIdentificacion(),  DocenteExterno.getTelefono(),  DocenteExterno.getEmail(),  DocenteExterno.getCarrera(), 
				 DocenteExterno.getDepartamento(),  DocenteExterno.getUsuario_crea(),  DocenteExterno.getFecha_crea(),  DocenteExterno.getUsuario_mod(), 
				 DocenteExterno.getFecha_mod(),  DocenteExterno.getPidm()));
		return _DocenteExterno;
	}

	// **EDITA UN CONVENIO DE ACUERDO A SU ID**

	@PutMapping("/DocenteExterno/{id}")
	public ResponseEntity<DocenteExterno> updateConvenio(@PathVariable("id") Long id, @RequestBody DocenteExterno DocenteExterno) {
		System.out.println("Update DocenteExterno with ID = " + id + "...");
		Optional<DocenteExterno> DocenteExternoData = repository.findById(id);

		if (DocenteExternoData.isPresent()) {
			DocenteExterno _DocenteExterno = DocenteExternoData.get();
			_DocenteExterno.setApellido(DocenteExterno.getApellido());
			_DocenteExterno.setNombre(DocenteExterno.getNombre());
			_DocenteExterno.setCargo(DocenteExterno.getCargo());
			_DocenteExterno.setIdentificacion(DocenteExterno.getIdentificacion());
			_DocenteExterno.setTelefono(DocenteExterno.getTelefono());
			_DocenteExterno.setEmail(DocenteExterno.getEmail());
			_DocenteExterno.setCarrera(DocenteExterno.getCarrera());
			_DocenteExterno.setDepartamento(DocenteExterno.getDepartamento());
			_DocenteExterno.setUsuario_crea(DocenteExterno.getUsuario_crea());
			_DocenteExterno.setFecha_crea(DocenteExterno.getFecha_crea());
			_DocenteExterno.setUsuario_mod(DocenteExterno.getUsuario_mod());
			_DocenteExterno.setFecha_mod(DocenteExterno.getFecha_mod());
			_DocenteExterno.setPidm(DocenteExterno.getPidm());

			return new ResponseEntity<>(repository.save(_DocenteExterno), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
