function goBack(){
		location.replace("http://localhost:8080/ERSServer/login")
}

var url = 'http://localhost:8080/ERSServer/update';
function update() {
    var fName = document.getElementById("fname").value;
    var lName = document.getElementById("lname").value;
    var username = document.getElementById("usrName").value;
    
    
    var xhttp = new XMLHttpRequest();

    
    xhttp.onreadystatechange = receiveData;
    xhttp.open('GET', url); 
    xhttp.setRequestHeader("fName", fName)
    xhttp.setRequestHeader("lName", lName)
    xhttp.setRequestHeader("username", username)
   
   
    xhttp.send();
    
    function receiveData() {
   
       
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
              
                var response = xhttp.responseText;
                console.log(response);
                location.replace("http://localhost:8080/ERSServer/")
                
            } else {
              
                dataSection.innerHTML = "Error reciving data"
            }
        } else {}
    }
    
    
}