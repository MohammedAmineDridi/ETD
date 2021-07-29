import { Component, OnInit } from '@angular/core';

import {Service} from '../app/service' ;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'EtdSystemAngular';
  

  extension_test  ;
  file_name ;

  ngOnInit(){
  
  }

  constructor(private service:Service ) { }

  // file function 

  onFileSelected(event){
    if(event.target.files.length > 0) 
  {
    alert("you choose : " + event.target.files[0].name);

    var file = event.target.files[0].name ;

    var file_length = file.length ;

    var extension = file.substring(file_length-4,file_length) ;   


    // alert("extension = " + extension) ;

    if(extension == 'xlsx'){
      //  alert("yes");
      
    }
    else{
        alert("sorry , the file extension should be : .xlsx ");
    }

      this.extension_test = extension ;

      this.file_name = file.substring(0,file_length-5);
  }

  }


  // btn convert to database function 

  convert_to_mysql(){
   
   // alert("extension = " + this.extension_test ) ;

    if(this.extension_test == "xlsx"){
        
      // convert excel to mysql 

      alert("extension = " + this.extension_test) ;

      alert("file name = " + this.file_name ) ;

      this.service.convert_excel_to_mysql(this.file_name).subscribe((headers)=>{
          console.log(headers) ;
      });

    }

    else{
        alert("you have to choose an 'xlsx' file ");
    }

  }





  btn_test(){
    this.service.test().subscribe((data)=>{
      console.log(data) ;
    });
  }


}
