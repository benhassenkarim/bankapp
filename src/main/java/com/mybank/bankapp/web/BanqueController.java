package com.mybank.bankapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mybank.bankapp.entities.Compte;
import com.mybank.bankapp.entities.Operation;
import com.mybank.bankapp.metier.IBanqueMetier;

@Controller
public class BanqueController {
	@Autowired
	private IBanqueMetier banqueMetier;
	@RequestMapping("/operations")
	public String index() {
		return "index";
	}
	@GetMapping("/consulterCompte")
	public String consulter(Model model,String codeCompte,
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "4")int size
			) {
		
		try {
			model.addAttribute("codeCompte",codeCompte);
			Compte cp=banqueMetier.ConsulterCompte(codeCompte);
			model.addAttribute("compte",cp);
			
			Page<Operation> pageOperations= banqueMetier.listOperations(codeCompte,page,size);
			model.addAttribute("listOperations",pageOperations.getContent());
			int[] pages=new int[pageOperations.getTotalPages()];
			model.addAttribute("pages",pages);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		return "index";
	}
	@RequestMapping(value="/saveOperation",method = RequestMethod.POST)
	public String saveOperation(Model model,String typeOperation,
			String codeCompte,String codeCompte2 ,double montant) {
		try {
			if(typeOperation.equals("RET")) {banqueMetier.retirer(codeCompte, montant);}
			else if(typeOperation.equals("VER")) {banqueMetier.verser(codeCompte, montant);}
			else if(typeOperation.equals("VIR")) {banqueMetier.virement(codeCompte, codeCompte2, montant);}
		} catch (Exception e) {
			model.addAttribute("error",e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
	@GetMapping("/show")
	public String ShowPageFromList(Model model,String codeCompte,int page) {
		try {
			model.addAttribute("codeCompte",codeCompte);
			Compte cp=banqueMetier.ConsulterCompte(codeCompte);
			model.addAttribute("compte",cp);
			
			Page<Operation> pageOperations= banqueMetier.listOperations(codeCompte,page,4);
			model.addAttribute("listOperations",pageOperations.getContent());
			int[] pages=new int[pageOperations.getTotalPages()];
			model.addAttribute("pages",pages);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		
		
	}
		return "index";
}
	}
