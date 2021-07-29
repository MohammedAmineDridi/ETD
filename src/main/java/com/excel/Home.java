package com.excel ;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;

//it's the main 'rest controller' .

// @CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/api/v1")
public class Home {

	/*

	@Autowired
	private TodoService todoService ;
	
	*/
	 
	// route {/} simple
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value={"/etudiant"} )
	public ArrayList<Etudiant> test() {
		
		ArrayList<Etudiant> list1 = new ArrayList<Etudiant>();
		Etudiant e1 = new Etudiant(1,"amine");
		Etudiant e2 = new Etudiant(2,"yesine");
		
		list1.add(e1);
		
		list1.add(e2);
		
		return list1 ;
		
	}
	
	// route test 2
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value={"/etudiant2"} )
	public ArrayList<String> test2() {
		ArrayList<String> a = new ArrayList<String>();
		String nom ="amineee";
		a.add(nom);
		return a ; 
		
	}
	
	// @CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value={"","/"} )
	public ResponseEntity<String> greeting() {
		return new ResponseEntity<String>("welcome to spring boot",HttpStatus.OK) ;
	}
	
	// route {/name} paramétré .
	
	@GetMapping(value="/{name}")
	public String greeting_with_name(@PathVariable String name) {
		return "welcome " + name + " to spring boot application" ;
	}
	
	// to do route 
	
	
	
	// excel file part 
	

	// test repo in readexcel class
	
	@Autowired 
	ReadExcel ex ;
	
	/*
	
	@GetMapping("/excel_file_headers")
	public ArrayList<Object> excel_header() {
		
		 // ex.read_excel_file();
		
		ArrayList<Object> list_of_headers = ex.get_excel_file_header(); // get excel file header 
				
		return list_of_headers ;		
	}
	
	*/
	
	@GetMapping("/excel_file_values")
		
	public ArrayList<Object> excel_values(){
		
		// ReadExcel excel = new ReadExcel();
		
		ArrayList<Object> list_of_values = ex.get_excel_file_values() ; // get excel file values .
		
		return list_of_values ; 
	}
	
	
	@GetMapping("/values_extract")
	
	public void extract_values() {
		 // ReadExcel excel = new ReadExcel();
		ex.values_extract();
	}
	
	// test mysql connexion
	
	
	
	
	
	@GetMapping("/testt")
	public List<String> get_testtt() {
		return ex.getNames_from_db();
	}
	
	@GetMapping("/testtt")
	public void func() {
		ex.test_jdbc2();
	}
	
	@GetMapping("/testttt")
	public List<String> func2() {
		return ex.testttt();
	}
	
	// route = create the table + add colonnes .
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/test_create_table/{name}")
	
	public ArrayList<Object> create_table_test(@PathVariable String name) {
		
	
		System.out.println("file name ==> " + name ) ;
		
		ex.read_excel_file(name);
		
		ex.test_create_table(name) ;

		ArrayList<Object> list_of_headers = ex.get_excel_file_header(name); // get excel file header -> set headers as columns .
		
		// last part : insert the values to database
		
		String sql = ex.insert_from_excel_to_database(name) ;
		
		System.out.println(sql) ;
				
		return list_of_headers ;
	}

	
	
}
