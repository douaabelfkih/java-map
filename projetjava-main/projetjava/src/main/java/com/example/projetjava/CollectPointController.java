package com.example.projetjava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/collect-points")
public class CollectPointController {

    @Autowired
    private CollectPointRepository collectPointRepository;

    // Affiche la liste des points
    @GetMapping
    public String listCollectPoints(Model model) {
        List<CollectPoint> collectPoints = collectPointRepository.findAll();
        model.addAttribute("collectPoints", collectPoints);
        return "collect-points";
    }

    // Affiche le formulaire d'ajout
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("collectPoint", new CollectPoint());
        return "add-collect-point";
    }

    // Enregistre un nouveau point
    @PostMapping("/add")
    public String addCollectPoint(@ModelAttribute CollectPoint collectPoint) {
        collectPointRepository.save(collectPoint);
        return "redirect:/admin/collect-points";
    }

    // Supprime un point
    @GetMapping("/delete/{id}")
    public String deleteCollectPoint(@PathVariable Long id) {
        collectPointRepository.deleteById(id);
        return "redirect:/admin/collect-points";
    }
}
