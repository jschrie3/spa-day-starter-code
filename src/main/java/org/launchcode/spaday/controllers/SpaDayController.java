package org.launchcode.spaday.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

// CHAPTER 13 STUDIO: SPA DAY

@Controller
public class SpaDayController {

    // The owners gave us this method to help us figure out which facial treatments are appropriate for which skin type.
    public boolean checkSkinType(String skinType, String facialType) {
        // basic structure:
        // takes in skintype and facial type
        // if skinType is THIS, then THESE are the facialType options

        if (skinType.equals("oily")) {
            return facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating");
        }
        else if (skinType.equals("combination")) {
            return facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating") || facialType.equals("Enzyme Peel");
        }
        else if (skinType.equals("dry")) {
            return facialType.equals("Rejuvenating") || facialType.equals("Hydrofacial");
        }
        else {
            return true;
        }
    }

    @GetMapping(value="")
    @ResponseBody
    public String customerForm () {
        String html = "<form method = 'post'>" +
                "Name: <br>" +
                "<input type = 'text' name = 'name'>" +
                "<br>Skin type: <br>" +
                "<select name = 'skintype'>" +
                "<option value = 'oily'>Oily</option>" +
                "<option value = 'combination'>Combination</option>" +
                "<option value = 'normal'>Normal</option>" +
                "<option value = 'dry'>Dry</option>" +
                "</select><br>" +
                "Manicure or Pedicure? <br>" +
                "<select name = 'manipedi'>" +
                "<option value = 'manicure'>Manicure</option>" +
                "<option value = 'pedicure'>Pedicure</option>" +
                "</select><br>" +
                "<input type = 'submit' value = 'Submit'>" +
                "</form>";
        return html;
    }

    // The spaMenu() method, which we will use to bring in our Thymeleaf template, menu.html.
    @PostMapping(value="")
    public String spaMenu(@RequestParam String name, @RequestParam String skintype, @RequestParam String manipedi, Model model) {

        // Also, our teammates already set up code in our spaMenu() method to fill an ArrayList<String>
        // with facial treatments that would work for the user’s skin type.
        //Now, we just need to use Thymeleaf to display the appropriate facial treatments
        // (stored in the ArrayList, appropriateFacials)!
        // Let’s head back to menu.html and checkout the empty div with the id, servicesMenu.

        // adding facial menu options to array list called facials
        ArrayList<String> facials = new ArrayList<>();
        facials.add("Microdermabrasion");
        facials.add("Hydrofacial");
        facials.add("Rejuvenating");
        facials.add("Enzyme Peel");

        // adding only appropriate facials for skinType options to each facial menu item
        ArrayList<String> appropriateFacials = new ArrayList<>();

        // for each facial menu item in facials array list
        for (int i = 0; i < facials.size(); i ++) {

            // call check checkSkinType to check user input for skinType
            // then add the returned values (appropriate facials for that skinType)
            // to the appropriateFacials arraylist

            if (checkSkinType(skintype,facials.get(i))) {
                appropriateFacials.add(facials.get(i));
            }
        }

        // need to create models to bring data into the view
        model.addAttribute("name", name);
        model.addAttribute("skintype", skintype);
        model.addAttribute("manipedi", manipedi);
        model.addAttribute("facials", facials);
        model.addAttribute("appropriateFacials", appropriateFacials);

        return "menu";
    }
}
