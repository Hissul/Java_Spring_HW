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
	
	Consumer consumer = null;
	
	
	@GetMapping("/addPurchase")
    public String add() {
		
		if(consumer ==  null) {
			return "login";
		}
		
		return "addPurchase";
	}
	
	@PostMapping("/addOnePurchase")
	public String newPurchase(@ModelAttribute Product newBuy,Model model) {	
		
		newBuy.setConsumer(consumer);
		
		model.addAttribute("myPurchase", newBuy);
		
		servise.saveToDB(newBuy);		
		
		System.out.println(newBuy);
		return "index";
	}
	
	
	@GetMapping("/allPurchases")
    public String all(@RequestParam(name="my_param",required=false,defaultValue="absent") String from_url,Model model) {
		
		if(consumer ==  null) {
			return "login";
		}
		
		model.addAttribute("my_param", from_url);
	    model.addAttribute("products", servise.getProductByConsumerEmail(consumer.getEmail()));
		return "allPurchases";
	}

	
	@GetMapping("/registration")
	public String registr(WebRequest request,Model model) {

		Consumer userDto = new Consumer();
		model.addAttribute("myUser", userDto);	
		 
		return "registration";
	}
	
	
	@PostMapping("/postRegistr")
	public String createNewUser(@ModelAttribute Consumer newUser, Model model) {
		//model.addAttribute("myUser", newUser);
		servise.saveUserToDB(newUser);
		
		consumer = newUser;
		
		return "login";
	}

	@GetMapping("/login")
	public String login( @RequestParam(name="my_param", required=false, defaultValue="absent") String from_url, Model model) {
//		model.addAttribute("my_param", from_url);
//	    model.addAttribute("myUser", new UserDTO("q@q.ru"));		
		return "login";
	}
	
	@PostMapping("/postLogin")
	public String postLogin(@ModelAttribute Consumer newUser, Model model) {
		
		System.out.println(newUser);
		
		Consumer user = servise.findByEmail(newUser.getEmail());
		
		newUser.setFirstName(" ");
		newUser.setLastName(" ");
		
		System.out.println(user);
		
		if(user == null) {
			System.out.println("NULL");
			model.addAttribute("myUser", newUser);
			return "registration";
		}
		
		if(newUser.getPassword().equals(user.getPassword())) {
			System.out.println("MATCH");
			consumer = user;
			return "index";
		}
		else{
			System.out.println("NOT MATCH");
			return "login";
		}			
	}
	
	
	@GetMapping("/logout")
	public String logout() {
		consumer = null;
		return "index";
	}
	
	@GetMapping("/main")
	public String index() {
		return "index";
	}
	
	
	   @GetMapping("/test_url")
	    public String getTestResponse() {
	        return "Test Response String";
	    }
	
	
}
