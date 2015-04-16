package simplifiedeliza;
/* 
   ------------------------------------------------------------------
   '########:'##:::::::'####:'########::::'###::::
   ##.....:: ##:::::::. ##::..... ##::::'## ##:::
   ##::::::: ##:::::::: ##:::::: ##::::'##:. ##::
   ######::: ##:::::::: ##::::: ##::::'##:::. ##:
   ##...:::: ##:::::::: ##:::: ##::::: #########:
   ##::::::: ##:::::::: ##::: ##:::::: ##.... ##:
   ########: ########:'####: ########: ##:::: ##:
   ........::........::....::........::..:::::..::

   eliza.java - a simplified version of Joseph Weizenbaum's Eliza

   -written by Akshat Singhal 
   at Oberlin College, in the Spring of  2007, for CSCI 364 
   ------------------------------------------------------------------
*/

//edited by Rezky Amelia into indonesian version one

import java.util.*;

public class eliza{

    /*-----------------*/
    /* main() */
    /*-----------------*/

    public static void main(String args[]){
	/* Initialization */
	boolean endloop=false;
	Scanner darkly  = new Scanner(System.in);
	Scanner currentline;
	/* -end init- */

	/* Print welcome message */
	System.out.println("Selamat Datang.");
	/* -end printing- */

	/* Run a loop for I/O */ 
	while (!endloop){
	    System.out.print(" - ");
	    currentline=new Scanner(darkly.nextLine().toLowerCase());
	    if (currentline.findInLine("bye")==null)
		System.out.println(respond(currentline));//print a response
	    else {//Exit program if 'bye' was typed
		System.out.println("Ok, sampai jumpa lagi");				   
		endloop=true;
	    }
	}/* -end I/O loop- */
    }/* -end main() - */


    /*-----------------*/
    /* respond() - returns a response that Eliza would return, given an input
       string from the user */
    /*-----------------*/

    private static String respond(Scanner s){

	/* Init a HashMap of keyword-response pairs */
	HashMap<String,String[]> responses = new HashMap<String,String[]>();
	String[] temp0={"Jadi apa yang ingin anda sampaikan?",
			"Saya mengerti.",
			"Saya tidak yakin bahwa saya mengerti apa yang anda katakan",
			"Bisa diperjelas lagi?",
			"Menarik sekali"			
	};
	responses.put("NOTFOUND", temp0);

	String[] temp1={"Bisa diperjelas lagi?"};    
	responses.put("selalu", temp1);

	String[] temp2={"Benarkah itu alasannya?"};
	responses.put("karena", temp2);

	String[] temp3={"Oh tidak apa-apa kok."};
	responses.put("maaf", temp3);

	String[] temp4={"Kelihatannya anda tidak yakin..."};
	responses.put("mungkin", temp4);

	String[] temp5={"Benarkah anda berpikir demikian?"};
	responses.put("saya merasa", temp5);
        responses.put("aku merasa", temp5); 

	String[] temp6={"Kita sedangkan mendiskusikan diri anda, bukan diri saya."};
	responses.put("kamu", temp6);
        responses.put("anda", temp6);

	String[] temp7={"Anda terlihat yakin sekali."};
	responses.put("ya", temp7);

	String[] temp8={"Kenapa tidak?",
			"Anda yakin"};
	responses.put("tidak", temp8);

	String[] temp9={"Berapa lama anda telah merasa *?",
			"Apakah anda percaya bahwa itu normal untuk *?"
	};
	responses.put("saya", temp9);
	responses.put("aku", temp9);

	String[] temp10={"Ceritakan lebih banyak mengenai hal yang anda rasakan",
			 "Apakah anda sering merasa *?",
			 "Apakah anda menikmati perasaan *?",
			 "Mengapa anda merasakan hal seperti itu?"
	};
	responses.put("saya merasa", temp10);

	String[] temp11={"Ceritakan lebih banyak tentang keluarga anda.",
			 "Seberapa dekatkah anda dengan keluarga?",
			 "Apakah keluarga penting bagi anda?"
	};
	responses.put("keluarga", temp11);
	responses.put("ibu", temp11);
	responses.put("ayah", temp11);
	responses.put("mama", temp11);
	responses.put("papa", temp11);
	responses.put("adik", temp11);
	responses.put("kakak", temp11);
	responses.put("istri", temp11);
	responses.put("suami", temp11);

	String[] temp12={"Apakah anda sering memimpikan hal tersebut?",
			 "Siapakah orang yang anda temui di mimpi anda?",
			 "Apakah anda merasa terganggu dengan mimpi anda?"		 
	};
	responses.put("mimpi", temp12);
       	
		      
	String[] keywords={"selalu","karena","maaf","mungkin","saya pikir",
                            "ku pikir","kamu","ya","tidak","saya","aku","saya merasa","aku merasa","keluarga",
			   "kamu","anda","ibu","mama","papa","ayah","kakak",
			   "adik","suami","istri","mimpi"};
	/* -end hashmap init- */
	
	/* initialize variables */
	String response="";
	String[] response_array={""};
	boolean found=false;
	String currentkeyword="";
	/* - end init - */

	/* Loop through keywords */
	for(int i=0; i<keywords.length;i++){
	    if ((s.findInLine(currentkeyword=(String)keywords[i])!=null) 
		&& (responses.get(currentkeyword)!=null)){
		/*If a keyword is found in the current input, get a response
		  from HashMap and return it*/
		found=true;
		response_array=(String[])responses.get(currentkeyword);
		response=response_array[(int)((response_array.length-1)*
					     Math.random())];
		/* If response has a *, replace it with the remainder of 
		   input string _with the last character removed if it is
		   a punctuation character_ */
		if (response.indexOf('*')!=-1){
		    String remaining_input;
		    if (s.hasNext() && 
			(remaining_input=s.nextLine().trim())!=null){
			response = response.substring(0,response.indexOf('*'))+
			    remaining_input
			    .substring(0,remaining_input.length()-1)
			    + remaining_input
			    .substring(remaining_input.length()-1,
				       remaining_input.length())
			    .replaceAll("[^A-Za-z]", "") +
			    response.substring(response.indexOf('*')+1, 
					       response.length());
			response=response.trim();
		    }
		    else
			response=response.replaceAll("[*]","");
		}
	    }
	}
	
	/*respond with a default message if no keywords were found in the 
	  input string */
	if (!found){
	    response_array=(String[])responses.get("NOTFOUND");
	    response=response_array[(int)((response_array.length-1)*
					 Math.random())];
	}
	return response;
    }/*- end respond() - */    
}
