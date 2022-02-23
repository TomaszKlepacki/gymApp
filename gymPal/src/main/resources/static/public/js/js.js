// hide and show toggle password
var state= false;
function toggle(){
   if (state){
       document.getElementById("password").setAttribute("type","password");
       document.getElementById("eye").style.color='#7a797e';
       state = false;
   } 

   
  
   else{
    document.getElementById("password").setAttribute("type","text");
    document.getElementById("eye").style.color='#5887ef';
    state = true;
   }

 
   
}
function toggle_2(){
    if (state){
        document.getElementById("password-2").setAttribute("type","password-2");
        document.getElementById("eye-2").style.color='#7a797e';
        state = false;
    } 
 
    
   
    else{
     document.getElementById("password-2").setAttribute("type","text");
     document.getElementById("eye-2").style.color='#5887ef';
     state = true;
    }

    
 }

 // strong password

 const indicator = document.querySelector(".indicator");
 const input = document.querySelector("input");
 const weak = document.querySelector(".weak");
 const medium = document.querySelector(".medium");
 const strong = document.querySelector(".strong");
 const text = document.querySelector(".text");

 function trigger(){
    if(input.value != ""){
      indicator.style.display = "block";
      indicator.style.display = "flex";
    }else{

    }
         
    
 }
