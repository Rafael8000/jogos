package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Fornecedor;
import application.model.fornRepository;

@Controller
@RequestMapping("/Fornecedores")
public class Fornecedores {
    @Autowired
    private FornecedorRepository fornRepo;

    @RequestMapping("/lista")
    public String list(Model model) {
        model.addAttribute("fornecedores", fornRepo.findAll());
        return "/Fornecedores/lista";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "/Fornecedores/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Fornecedor Fornecedor = new Fornecedor();
        Fornecedor.setFornecedor(Fornecedor);

        fornRepo.save(Fornecedor);

        return "redirect:/Fornecedores/list";
    }

    @RequestMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        Optional<Fornecedor> Fornecedor = fornRepo.findById(id);

        if(Fornecedor.isPresent()) {
            model.addAttribute("fornecedores", Fornecedor.get());
            return "/Fornecedores/update";
        }

        return "redirect:/Fornecedores/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
        @RequestParam("id") int id,
        @RequestParam("nome") String nome
    ) {
        Optional<Fornecedor> Fornecedor = fornRepo.findById(id);

        if(Fornecedor.isPresent()) {
            Fornecedor.get().setFornecedor(Fornecedor);

            fornRepo.save(Fornecedor.get());
        }
        
        return "redirect:/Fornecedores/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("id") int id) {
        Optional<Fornecedor> Fornecedor = fornRepo.findById(id);

        if(Fornecedor.isPresent()) {
            model.addAttribute("Fornecedor", Fornecedor.get());
            return "/Fornecedores/delete";
        }

        return "redirect:/Fornecedores/list";
    }

    @RequestMapping(value = "/deletar", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        FornRepo.deleteById(id);

        return "redirect:/genero/list";
    }
}