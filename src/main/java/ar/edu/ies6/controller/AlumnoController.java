package ar.edu.ies6.controller;

import ar.edu.ies6.model.Alumno;
import ar.edu.ies6.service.AlumnoService;
import ar.edu.ies6.util.ListadoAlumnos;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlumnoController {
	@Autowired
	Alumno alu;
	
	@Autowired
	AlumnoService alumnoService;

	//Método para mostrar el formulario
	@GetMapping({"/index", "/", "/home", "/alumno"})
	public ModelAndView cargarAlumno() {
		
		//Alumno alu = new Alumno ();
		
		alu.setFechaNac(LocalDate.of(1988, 8, 20));
		System.out.println("Edad: "+alu.obtenerEdad());
		
		ModelAndView modelView = new ModelAndView ("index");
		
		modelView.addObject("alumno", alu);
		
		return modelView;	
	}
	
	//Método para procesar los datos del formulario
	@PostMapping("/cargarAlumno")
    public ModelAndView cargarAlumno(@ModelAttribute("alumno") Alumno alumno) {
		
		ListadoAlumnos.getListado().add(alumno);
 	
        ModelAndView modelView = new ModelAndView ("listadoAlumnos");
		
		modelView.addObject("listado", ListadoAlumnos.getListado());
		
		return modelView;	
	}
	
	//Método para eliminar un registro
	@GetMapping("/eliminarAlumno/{dni}")
	public ModelAndView eliminarAlumno(@PathVariable Integer dni) {
		
		for (int i=0; i<ListadoAlumnos.getListado().size(); i++) {
			if (ListadoAlumnos.getListado().get(i).getDni().equals(dni)) {
				//ListadoAlumnos.getListado().get(i).setEstado(false);
				ListadoAlumnos.getListado().remove(i);
			}
		}
		ModelAndView modelView = new ModelAndView ("listadoAlumnos");
		modelView.addObject("listado", ListadoAlumnos.getListado());
		
		return modelView;	
	}
	
	//Método para modificar un registro
	@GetMapping("/modificarAlumno/{dni}")
	public ModelAndView modificarAlumno(@PathVariable Integer dni) throws Exception {
		
		//modifica un alumno (dni)
		ModelAndView modificaAlumno = new ModelAndView("alumno");
		modificaAlumno.addObject("alumno", alumnoService.encontrarUnAlumno(dni));
		
		return modificaAlumno;	
	}
	
	@PostMapping("/modificarAlumno")
    public ModelAndView modificarUnAlumno(@ModelAttribute("alumno") Alumno alumno) {
		
        alumnoService.guardarAlumno(alumno);
        ModelAndView modelView = new ModelAndView ("listadoAlumnos");
		//modelView.addObject("listado", ListadoAlumnos.getListado());
        modelView.addObject("listado", alumnoService.buscarTodosAlumnos());
		return modelView;	
	}	
}
