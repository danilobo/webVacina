package br.com.encoding.vacinao.controllers;


import br.com.encoding.vacinao.dto.CarteiraRequisicao;
import br.com.encoding.vacinao.dto.VacinaRequisicao;
import br.com.encoding.vacinao.models.Carteira;
import br.com.encoding.vacinao.models.Vacina;
import br.com.encoding.vacinao.repositories.CarteiraRepository;
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
public class CarteiraController {
    @Autowired
    private CarteiraRepository carteiraRepository;
    @Autowired
    private VacinaRepository vacinaRepository;


    @GetMapping("/carteiras")
    public ModelAndView index(){

        List<Carteira> carteiras = this.carteiraRepository.findAll();
        ModelAndView mv = new ModelAndView("carteira/index");

        mv.addObject("carteiras", carteiras);
        return mv;
    }

    @GetMapping("carteiras/new")
    public ModelAndView carteiraNovo(CarteiraRequisicao requisicao) {
        List<Vacina> vacinas = this.vacinaRepository.findAll();

        ModelAndView mv = new ModelAndView("carteira/new");
        mv.addObject("vacinas", vacinas);
        return mv;
    }
//
    @PostMapping("/carteiras")
    public ModelAndView create(@Valid CarteiraRequisicao requisicao, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("carteira/new");
            return mv;
        }
        else {
            Carteira carteira = requisicao.toCarteria();
            this.carteiraRepository.save(carteira);
            return new ModelAndView("redirect:/carteiras");
        }
    }

    @GetMapping("/carteiras/{id}")
    public ModelAndView show(@PathVariable Long id){
        Optional<Carteira> optional =  this.carteiraRepository.findById(id);

        if (optional.isPresent()){
            Carteira carteira = optional.get();
            ModelAndView mv = new ModelAndView("carteira/show");
            mv.addObject("carteira", carteira);
            return mv;
        }
        else{
            return new ModelAndView("redirect:/carteira");
        }
    }

    @GetMapping("/carteiras/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, CarteiraRequisicao requisicao){
        Optional<Carteira> optional =  this.carteiraRepository.findById(id);
        if (optional.isPresent()){
            Carteira carteira = optional.get();
            requisicao.fromCarteira(carteira);

            ModelAndView mv = new ModelAndView("carteira/edit");
            mv.addObject("carteiraId", carteira.getId());

            return mv;
        }
        else{
            return new ModelAndView("redirect:/carteiras");
        }
    }


    @PostMapping("/carteiras/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid CarteiraRequisicao requisicao, BindingResult bindingResult){
        requisicao.toString();
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("carteira/new");
            return mv;
        }
        else {
            Optional<Carteira> optional =  this.carteiraRepository.findById(id);

            if (optional.isPresent()){
                Carteira carteira = optional.get();
                carteira.setIdUsuario(requisicao.getIdUsuario());
                carteira.setIdVacinas(requisicao.getIdVacinas());
                carteira.setAplicacaoData(requisicao.getAplicacaoData());
                this.carteiraRepository.save(carteira);

                return new ModelAndView("redirect:/carteiras/" + carteira.getId());
            }
            else{
                return new ModelAndView("redirect:/carteiras");
            }

        }
    }

    @GetMapping("/carteiras/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/carteiras");

        try {
            this.carteiraRepository.deleteById(id);
            mv.addObject("mensagem", "Carteira #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        }
        catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroCarteira("DELETE ERROR: Carteira #" + id + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroCarteira(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/carteiras");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
