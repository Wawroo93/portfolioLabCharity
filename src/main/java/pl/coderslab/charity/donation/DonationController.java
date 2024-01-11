package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.InstitutionRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/donations")
public class DonationController {

    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

//    @GetMapping("/new")
//    String showForm(Model model) {
//        model.addAttribute("categories", categoryRepository.findAll());
//        model.addAttribute("institutions", institutionRepository.findAll());
//        model.addAttribute("donation", new Donation());
//        return "donationForm";
//    }
//    @PostMapping("/new")
//    String handleForm(@ModelAttribute Donation donation) {
//        Donation savedDonation = donationRepository.save(donation);
//        return "redirect:/donations/summary/" + savedDonation.getId();
//    }
    @GetMapping("/step")
    String step(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("donation", new Donation());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "formStep";
    }
    @PostMapping("/step")
    String stepSave(@ModelAttribute Donation donation) {
        donationRepository.save(donation);
        return "redirect:/donations/formConfirmation";
    }
    @GetMapping("/formConfirmation")
    String showFormConfirmation() {
        return "formConfirmation";
    }

//    @GetMapping("/summary/{id}")
//    String showSummary(@PathVariable("id") Long id, Model model) {
//        Optional<Donation> donationOptional = donationRepository.findById(id);
//        if (donationOptional.isPresent()) {
//            Donation donation = donationOptional.get();
//            LocalTime pickUpTime = donation.getPickUpTime();
//            Instant instant = pickUpTime.atDate(LocalDate.of(1970, 1, 1)).
//                    atZone(ZoneId.systemDefault()).toInstant();
//            Date date = Date.from(instant);
//            model.addAttribute("pickUpTime", date);
//            model.addAttribute("donation", donation);
//            return "donationFormSummary";
//        } else {
//            return "redirect:/";
//        }
//    }
}
