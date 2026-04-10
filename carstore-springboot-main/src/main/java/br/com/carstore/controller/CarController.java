package br.com.carstore.controller;

import br.com.carstore.dto.CarDTO;
import br.com.carstore.service.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping({"/", "/index"})
    public String exibirFormulario(Model model) {
        model.addAttribute("carDTO", new CarDTO());
        return "index";
    }

    @PostMapping("/carros")
    public String salvarCarro(@Valid CarDTO carDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }

        service.save(carDTO);
        return "redirect:/sucesso";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("cars", service.findAll());
        return "dashboard";
    }

    @GetMapping("/cars/edit/{id}")
    public String editarCarro(@PathVariable String id, Model model) {
        CarDTO carDTO = service.findById(id);
        if (carDTO == null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("carDTO", carDTO);
        return "index";
    }

    @GetMapping("/cars/delete/{id}")
    public String deletarCarro(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/cars/update/{id}")
    public String atualizarCarro(@PathVariable String id, @Valid CarDTO carDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }

        carDTO.setId(id);
        service.update(id, carDTO);
        return "redirect:/dashboard";
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso";
    }
}
