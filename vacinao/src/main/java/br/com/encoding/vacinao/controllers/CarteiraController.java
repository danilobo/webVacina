package br.com.encoding.vacinao.controllers;

import br.com.encoding.vacinao.models.Vacina;
import br.com.encoding.vacinao.repositories.CarteiraRepository;
import br.com.encoding.vacinao.repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CarteiraController {
    @Autowired
    private VacinaRepository vacinaRepository;
    private CarteiraRepository carteiraRepository;

    @GetMapping("/carteira")
    public ModelAndView index(){
        return null;
    }

    @GetMapping("carteira/new")
    public ModelAndView carteiraNovo() {
        List<Vacina> vacinas = this.vacinaRepository.findAll();

        ModelAndView mv = new ModelAndView("carteira/new");
        mv.addObject("vacinas", vacinas);
        return mv;
    }
}
