package com.Santander.Santander;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/webapi")
public class NameRestController {

	
	@Autowired
	NameRepository servicio;
	//@Autowired
	//SanService service;
	
	@GetMapping("/Usuarios")
	public Iterable<Name> ObtenerDatos() {

		
		
		return servicio.findAll();
		
	}
	
@PostMapping(path="/nuevoUsuario", consumes="application/json")
 void NuevoCampeon(@RequestBody Name name) {
 
		servicio.save(name);
		
}

@DeleteMapping("/eliminarUsuario/{DNI}")	
public void DeleteChamp(@PathVariable long DNI) {
		if (servicio.findById(DNI) != null) {
			servicio.deleteById(DNI);;
			
		}
		
}

@PutMapping("/actualizarUsuario/{dni}")
public Optional<Name> updateUser(@RequestBody Name name, @PathVariable Long dni) {
	Optional<Name> Usuario = servicio.findById(dni);

	if (Usuario.isPresent()) {

		Usuario.get().setFirstName(name.getFirstName());
		Usuario.get().setLastName(name.getLastName());
		Usuario.get().setUsername(name.getFirstName()+"."+name.getLastName());
		servicio.save(Usuario.get());
		return Usuario;
	} else {
	
	return Usuario;}
}
		

}