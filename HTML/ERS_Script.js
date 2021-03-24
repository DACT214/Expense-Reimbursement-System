function myFun(){
    var po = document.getElementById("posit").value;
    var usrn = document.getElementById("ur").value;
    var passw= document.getElementById("pass").value;
    
    // document.getElementById("home").innerHTML= po;

    console.log(po);

    
}

// var url = 'https://ron-swanson-quotes.herokuapp.com/v2/quotes'
// var xhrbtn = document.querySelector("#xhr");
// var fetchbtn = document.querySelector("#fetch");
// var axiosbtn = document.querySelector("#axios");
// var display = document.querySelector("#quote");

// fetchbtn.addEventListener("click", function(){
//     fetch(url)
//     .then(function(res){
//       res.json().then(function(data){
//         display.innerText = data[0];
//       })
//     })
//     .catch(function(){
//       alert("ERROR!");
//     })
//   });