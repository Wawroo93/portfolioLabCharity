package pl.coderslab.charity.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Controller
public class DonationController {

    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    public DonationController(DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }
    @GetMapping("/donations/new")
    public String showForm(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("donation", new Donation());
        return "donationForm";
    }

    @PostMapping("/donations/new")
    public String handleForm(@ModelAttribute Donation donation) {
        Donation savedDonation = donationRepository.save(donation);
        return "redirect:/donations/summary/" + savedDonation.getId();
    }
    @GetMapping("/donations/summary/{id}")
    public String showSummary(@PathVariable("id") Long id, Model model) {
        Optional<Donation> donationOptional = donationRepository.findById(id);
        if (donationOptional.isPresent()) {
            Donation donation = donationOptional.get();
            LocalTime pickUpTime = donation.getPickUpTime();
            Instant instant = pickUpTime.atDate(LocalDate.of(1970, 1, 1)).
                    atZone(ZoneId.systemDefault()).toInstant();
            Date date = Date.from(instant);
            model.addAttribute("pickUpTime", date);
            model.addAttribute("donation", donation);
            return "donationFormSummary";
        } else {
            return "redirect:/error";
        }
    }
}
