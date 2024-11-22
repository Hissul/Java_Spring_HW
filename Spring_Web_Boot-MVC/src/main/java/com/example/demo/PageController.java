package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;


@Controller
//@RestController
public class PageController {
	
	@Autowired
	private ShopService servise;
	
	@GetMapping("/addPurchase")
    public String add() {
		return "addPurchase";
	}
	
	@GetMapping("/allPurchases")
    public String all(@RequestParam(name="my_param",required=false,defaultValue="absent") String from_url,Model model) {
		model.addAttribute("my_param", from_url);
	    model.addAttribute("products", servise.getAllProducts());
		return "allPurchases";
	}
	
	@GetMapping("/main")
    public String main() {
		return "index";
	}

	
	
	@PostMapping("/addOnePurchase")
	public String newPurchase(@ModelAttribute Product newBuy,Model model) {
		
		model.addAttribute("myPurchase", newBuy);
		
		servise.saveToDB(newBuy);
		//servise.saveToDB(newBuy.getName(), newBuy.getPrise());
		
		System.out.println(newBuy);
		return "index";
	}
	
	@GetMapping("/registration")
	public String registr(WebRequest request,Model model) {

		UserDTO userDto = new UserDTO();
		model.addAttribute("myUser", userDto);
		 
		return "registration";
	}
	
	
	@PostMapping("/postRegistr")
	public String createNewUser(@ModelAttribute UserDTO newUser, Model model) {
		//model.addAttribute("myUser", newUser);
		servise.saveUserToDB(newUser);
		System.out.println(newUser);
		return "index";
	}

}
