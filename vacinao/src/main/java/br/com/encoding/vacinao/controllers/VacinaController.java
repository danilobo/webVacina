package br.com.encoding.vacinao.controllers;

import br.com.encoding.vacinao.dto.VacinaRequisicao;
import br.com.encoding.vacinao.models.Vacina;
import br.com.encoding.vacinao.repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class VacinaController {
    @Autowired
    private VacinaRepository vacinaRepository;

    @GetMapping("/vacinas")
    public ModelAndView index(){
        List<Vacina> vacinas = this.vacinaRepository.findAll();

        ModelAndView mv = new ModelAndView("vacina/index");
        mv.addObject("vacinas", vacinas);
        return mv;
    }

    @GetMapping("vacinas/new")
    public String novo(VacinaRequisicao requisicao) {
        return "vacina/new";
    }

    @PostMapping("/vacinas")
    public ModelAndView create(@Valid VacinaRequisicao requisicao, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("vacina/new");
            return mv;
        }
        else {
            Vacina vacina = requisicao.toVacina();
            this.vacinaRepository.save(vacina);
            return new ModelAndView("redirect:/vacinas");
        }
    }

    @GetMapping("/vacinas/{id}")
    public ModelAndView show(@PathVariable Long id){
        Optional<Vacina> optional =  this.vacinaRepository.findById(id);

        if (optional.isPresent()){
            Vacina vacina = optional.get();
            ModelAndView mv = new ModelAndView("vacina/show");
            mv.addObject("vacina", vacina);
            return mv;
        }
        else{
            return new ModelAndView("redirect:/vacinas");
        }
    }

    @GetMapping("/vacinas/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, VacinaRequisicao requisicao){
        Optional<Vacina> optional =  this.vacinaRepository.findById(id);
        if (optional.isPresent()){
            Vacina vacina = optional.get();
            requisicao.fromVacina(vacina);

            ModelAndView mv = new ModelAndView("vacina/edit");
            mv.addObject("vacinaId", vacina.getId());

            return mv;
        }
        else{
            return new ModelAndView("redirect:/vacinas");
        }
    }


    @PostMapping("/vacinas/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid VacinaRequisicao requisicao, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("vacina/new");
            return mv;
        }
        else {
            Optional<Vacina> optional =  this.vacinaRepository.findById(id);

            if (optional.isPresent()){
                Vacina vacina = optional.get();
                vacina.setNome(requisicao.getNome());
                vacina.setDoenca(requisicao.getDoenca());
                this.vacinaRepository.save(vacina);

                return new ModelAndView("redirect:/vacinas/" + vacina.getId());
            }
            else{
                return new ModelAndView("redirect:/vacinas");
            }

        }
    }

    @GetMapping("/vacinas/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/vacinas");

        try {
            this.vacinaRepository.deleteById(id);
            mv.addObject("mensagem", "Vacina #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        }
        catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroProfessor("DELETE ERROR: Vacina #" + id + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroProfessor(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/professores");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
