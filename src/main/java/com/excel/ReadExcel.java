package com.excel ;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class ReadExcel {
	
	   @Autowired
	    JdbcTemplate jt;
	   
	
	
	private static String extension =".xlsx" ;
	
    private static final String FILE_NAME = "Test_File"+extension;
    
    private  String file_name ="Test_File" ;
    
    ArrayList<Object> list_values = new ArrayList<Object>();
	
	ArrayList<Object> list_header_excel = new ArrayList<Object>();
	
	int number_lignes ;
    
    
    public ReadExcel() {
	
    	// this.read_excel_file(String filename);
    
    }
    
    
    
    // mysql test functions 
    
    // test mysql 
    
    
	
    public List<String> getNames_from_db() {
    	List<String> list = new ArrayList<String>();
    	list.addAll( jt.queryForList("select title from test ;",String.class) ) ;
    	return list ;
    }

    

    // methode 2 : jdbctemplate . execute 
    
    
    
    public void test_jdbc2() {
    	jt.execute("CREATE TABLE amine111(" +
                "id11 SERIAL, name22 VARCHAR(255), price33 NUMERIC(15, 2))");

    }
    
    /*
    
    // methode 3 : with parmeters 
    
    
    public void test_jdbc3(String nom_table) {
    	jt.execute("CREATE TABLE "+nom_table+"(" +
                "id11 SERIAL, name22 VARCHAR(255), price33 NUMERIC(15, 2))");

    }
    
    

       */
    
    	public void create_table(String nom_table) {
    
    		String sql_create_table =" CREATE TABLE "+nom_table+ "( id INT PRIMARY KEY NOT NULL ) " ;
    		jt.execute(sql_create_table);
    	}
    	
    	
    	public void add_colonnes(String nom_colonne , String nom_table) {
    		
    		String sql_add_colonne = "ALTER TABLE "+nom_table+" ADD "+nom_colonne+" VARCHAR(255)" ;
    		
    		jt.execute(sql_add_colonne) ;
    		
    	}

     
  
    
    
    // function to read excel file 
    
    public void read_excel_file(String filename) {


    	// mod
    	list_header_excel.clear();
    	
    	list_values.clear();

    	 String extension =".xlsx" ;
    			
        try {

            FileInputStream excelFile = new FileInputStream(new File(filename+extension));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int id = 0 ;

            while (iterator.hasNext()) {
  

            	id++ ;

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                
                

                while (cellIterator.hasNext()) {
                	

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                        
                        
                        // add header excel file values 
                        
                        if( id == 1  ) {
                        	list_header_excel.add( currentCell.getStringCellValue() );
                        }
                        
                        else {
                        	
                        	list_values.add( currentCell.getStringCellValue() )  ;
                            
                        }
                        
                        
                        
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                        
                        
                        
                        // add header excel file 
                        
                        if( id ==1 ) {
                        	list_header_excel.add( currentCell.getNumericCellValue() );
                        }
                        
                        else {
                        
                        	// add the excel file values 
                        	
                        	list_values.add( currentCell.getNumericCellValue() ) ;
                        	
                        }
                        		
                    }

                 
                    
                }
                
                
                // System.out.println("id == " + String.valueOf(id) ) ;
             
                System.out.println("-----------------");

                number_lignes = id ;
            }
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(filename + " not found ") ;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Input/Output inputStream excel file problem ....");
        }

    
    	
    }
    
    // get header of excel file 
    
    public ArrayList<Object> get_excel_file_header(String filename){
    	
    	for(int j=0;j<list_header_excel.size();j++) {
    		
    		 add_colonnes( list_header_excel.get(j).toString() , filename );
    		
    	}
    	
    	
    	return list_header_excel ; 
    	

    }
    
    // get values of excel file 
    
    public ArrayList<Object> get_excel_file_values(){
    
    	return list_values ; 
    }
    
    // get list of object of excel file .
    
    ArrayList<Object> list ;
    
    public void values_extract() {
    	
    
    	  
    	int header_number = list_header_excel.size() ;
    	
    	int k  ;
    	int i = 0 ;
    	
    	System.out.println(" number of header = " + String.valueOf(header_number) ) ;
    	
    	System.out.println("size of list_value = " + String.valueOf(list_values.size()) ) ;
    	
    	
    		
    		for (k=0+i ; k < list_values.size() ; k++) {
    			
    			String[] list ;
    		
    			while(k < header_number+i ) {
    				System.out.println( " value " + k + "  ==> " + list_values.get(k) );
    				k+=1;
    				
    				
    			}
    			
    			i+= header_number ;
    	
    			System.out.println("****************");
    			
    		}
    	
    	
    }
    
    
    public List<String> testttt() {
    	return getNames_from_db();
    }
    
    public void test_create_table(String nom_table) {
    	create_table(nom_table);
    }
  
    
    // last part : function to insert all data from excel .
    
public String insert_from_excel_to_database(String filename_tablename){
    	

	  
	int header_number = list_header_excel.size() ;
	
	int k  ;
	int i = 0 ;
	
	System.out.println(" number of header = " + String.valueOf(header_number) ) ;
	
	System.out.println("size of list_value = " + String.valueOf(list_values.size()) ) ;
	
	int id = 1 ;
	
	String sql = "insert into "+filename_tablename+" values (' "+id+" ',' ";
	
	
		
		for (k=0+i ; k < list_values.size() ; k++) {
			
		
			System.out.println(" k k == " + k);
			
		
			while(k < header_number+i ) {
				System.out.println( " value " + k + "  ==> " + list_values.get(k) );
				
				
				
				if( k != ( (header_number+i)-1 )  ) {
					sql+=list_values.get(k)+" ',' " ;
				}
				
				if( k == ( (header_number+i)-1 ) ) {
					sql+=list_values.get(k) ; 
				}
				
				
				// System.out.println(sql);
				
				k+=1;
				
				
			}
			
			id++ ;
			
			
			sql+= ( "'),(' "+id+" ',' " );
			
			i+= header_number ;
			
			k-- ;
			
			
			System.out.println("****************");
	
			
		}
		
		String new_sql = sql.substring(0, sql.length()- 10);
		
		System.out.println("req finale => " + new_sql ) ;
		
		// now ,execute the insert sql request .
		
		 jt.execute(new_sql);
		
		return new_sql ;
	
    }
 
   
}
