package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Plataforma;
import application.model.PlataformaRepository;
import application.model.Fornecedor;
import application.model.FornecedorRepository;

@Controller
@RequestMapping("/Plataformas")
public class PlataformaController {
    @Autowired
    private PlataformaRepository PlatRepo;
    @Autowired
    private FornecedorRepository FornRepo;

    @RequestMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("Nome", PlatRepo.findAll());
        return "/Plataformas/lista";
    }

    @RequestMapping("/inserir")
    public String insert(Model model) {
        model.addAttribute("Fornecedor", FornRepo.findAll());
        return "/Plataformas/inserir";
    }

    @RequestMapping(value = "/inserir", method = RequestMethod.POST)
    public String insert(
        @RequestParam("Nome") String Nome,
        @RequestParam("Fornecedor") String Fornecedor)
         {
        Plataforma Plataforma = new Plataforma();
        Plataforma.setNome(Nome);

        PlatRepo.save(Plataforma);
        return "redirect:/Plataformas/lista";
    }

    @RequestMapping("/Atualizar")
    public String update(Model model, @RequestParam("id") int id) {
        Optional<Plataforma> livro = PlatRepo.findById(id);
        model.addAttribute("Fornecedor", FornRepo.findAll());

        if(Plataforma.isPresent()) {
            model.addAttribute("Plataforma", Plataforma.get());
            return "/Plataformas/Atualizar";
        }

        return "redirect:/Plataformas/lista";
    }

    @RequestMapping(value = "/Atualizar", method = RequestMethod.POST)
    public String update(
        @RequestParam("id") int id,
        @RequestParam("Nome") String Nome,
        @RequestParam("Fornecedor") String Fornecedor) {
        Optional<Plataforma> Plataforma = PlatRepo.findById(id);

        if(Plataforma.isPresent()) {
            Plataforma.get().setNome(Nome);
            Fornecedor.get().setFornecedor(Fornecedor);
            PlatRepo.save(Plataforma.get());
        }

        return "redirect:/livro/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("id") int id) {
        Optional<Livro> livro = livroRepo.findById(id);

        if(livro.isPresent()) {
            model.addAttribute("livro", livro.get());
            return "/livro/delete";
        }

        return "redirect:/livro/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        livroRepo.deleteById(id);

        return "redirect:/livro/list";
    }
}