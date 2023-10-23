package cat.itacademy.barcelonactiva.abdellaoui.fethi.s05.t01.n01.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cat.itacademy.barcelonactiva.abdellaoui.fethi.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.abdellaoui.fethi.s05.t01.n01.model.repository.SucursalRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/sucursal")
public class SucursalController {

	@Autowired
	SucursalRepository sucursalRepository;

	@GetMapping("/getAll")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "size", defaultValue = "5") int s,
			@RequestParam(name = "pc", defaultValue = "") String keyword) {

		// Page<Sucursal> pageSucursals = sucursalRepository.findAll(PageRequest.of(p,
		// s));
		Page<Sucursal> pageSucursals = sucursalRepository.search("%" + keyword + "%", PageRequest.of(p, s));

		model.addAttribute("llistaSucursals", pageSucursals.getContent());
		int[] pages = new int[pageSucursals.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("paginaActual", p);
		model.addAttribute("pc", keyword);
		return "sucursals";

	}

	@GetMapping("/delete")
	public String delete(Integer id, int page, int size, @RequestParam(name = "pc", defaultValue = "") String keyword) {

		sucursalRepository.deleteById(id);
		return "redirect:/sucursal/getAll?page=" + page + "&size=" + size + "&pc=" + keyword;

	}

	@GetMapping("/add")
	public String addSucursal(Model model) {
		model.addAttribute("sucursal", new Sucursal());
		return "formSucursal";
	}
	@GetMapping("/edit")
	public String editSucursal(Model model,Integer id) {
		Optional<Sucursal> s = sucursalRepository.findById(id);
		model.addAttribute("sucursal", s.get());
		return "formEditSucursal";
	}

	@PostMapping("/guardar")
	public String guardar(Model model, @Valid Sucursal sucursal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formSucursal";
		}
		System.out.println(sucursal+ "noterror");
		sucursalRepository.save(sucursal);
		return "confirmacio";
	}

//	@PostMapping("/add")
//	public ResponseEntity<Sucursal> createSucursal(@RequestBody Sucursal sucursal) {
//		Sucursal tmp = new Sucursal(sucursal.getNomSucursal(), sucursal.getPaisSucursal());
//		sucursalRepository.save(tmp);
//		return new ResponseEntity<>(tmp, HttpStatus.CREATED);
//	}
//
//	// update
//	@PutMapping
//	public ResponseEntity<Sucursal> updateSucursal(@RequestBody Sucursal sucursal) {
//
//		sucursalRepository.save(sucursal);
//		// Optional<Sucursal> sucursal = sucursalRepository.findById(id);
////        if (!(sucursal.isPresent())){
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }        
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//
//	// delete
//	@DeleteMapping("/{id}")
//	public ResponseEntity<HttpStatus> deleteSucursal(@PathVariable("id") Integer id) {
//		Optional<Sucursal> sucursal = sucursalRepository.findById(id);
//		if (!(sucursal.isPresent())) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		sucursalRepository.deleteById(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//
//	// getOne/{id}
//	@GetMapping("/getOne/{id}")
//	public ResponseEntity<Sucursal> getOneById(@PathVariable("id") Integer id) {
//		Optional<Sucursal> sucursal = sucursalRepository.findById(id);
//		if (!(sucursal.isPresent())) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(sucursal.get(), HttpStatus.OK);
//	}
//	@RestController
//	List<Sucursal> sucursals = new ArrayList<>();
//	sucursalRepository.findAll().forEach(sucursals::add);
//	if (sucursals.isEmpty()) {
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//	return new ResponseEntity<>(sucursals, HttpStatus.OK);

//	@RequestMapping("/test")
//	public String test() {
//		return "test.html";
//	}

//	List<Sucursal> sucursals = sucursalRepository.findAll();
//	model.addAttribute("llistaSucursals",sucursals);
//	return "sucursals";

}
