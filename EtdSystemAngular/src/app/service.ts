import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// import { Observable } from 'rxjs';


@Injectable({
    providedIn: 'root',  // le service partagable dans tt l'app .
  })


  export class Service {


    constructor(private http: HttpClient) { }

     // test route 
     test(){

      const url_test_route = "http://localhost:8088/api/v1/etudiant2" ;
  
        return this.http.get<any>(url_test_route) ; // observable object
     }

     convert_excel_to_mysql(nom_file:String){
       
      const convert_route = "http://localhost:8088/api/v1/test_create_table/"+nom_file ;
  
      return this.http.get<any>(convert_route) ; 

     }


        
    
        }


  

