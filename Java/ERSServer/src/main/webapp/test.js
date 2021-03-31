

var url = 'http://localhost:8080/ERSServer/managerView'


function getData() {
  
    var xhttp = new XMLHttpRequest();

    
    xhttp.onreadystatechange = receiveData;
    
    xhttp.open('GET', url); 
   
   
    xhttp.send();

    
    function receiveData() {
   
        var dataSection = document.getElementById('data');
        dataSection.innerHTML = '';
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
              
                var response = xhttp.responseText;
                console.log(response);
                document.getElementById('data').innerHTML = response;
                
            } else {
              
                dataSection.innerHTML = "Error reciving data"
            }
        } else {
          
        }
    }
}

function getPending() {
  
    var xhttp = new XMLHttpRequest();

    
    xhttp.onreadystatechange = receiveData;
    xhttp.open('GET', url); 
    xhttp.setRequestHeader('status', 'pending');
   
   
    xhttp.send();

    
    function receiveData() {
   
        var dataSection = document.getElementById('data');
        dataSection.innerHTML = '';
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
              
                var response = xhttp.responseText;
                console.log(response);
                document.getElementById('data').innerHTML = response;
                
            } else {
              
                dataSection.innerHTML = "Error reciving data"
            }
        } else {
          
        }
    }
}

function getAccepted() {
  
    var xhttp = new XMLHttpRequest();

    
    xhttp.onreadystatechange = receiveData;
    xhttp.open('GET', url); 
    xhttp.setRequestHeader('status', 'accepted');
   
   
    xhttp.send();

    
    function receiveData() {
   
        var dataSection = document.getElementById('data');
        dataSection.innerHTML = '';
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
              
                var response = xhttp.responseText;
                console.log(response);
                document.getElementById('data').innerHTML = response;
                
            } else {
              
                dataSection.innerHTML = "Error reciving data"
            }
        } else {}
    }
}

var urls= 'http://localhost:8080/ERSServer/search'


function getSerched() {
    var fName = document.getElementById("first").value;
    var lName = document.getElementById("last").value;
    
    var xhttp = new XMLHttpRequest();

    
    xhttp.onreadystatechange = receiveData;
    xhttp.open('GET', urls); 
    xhttp.setRequestHeader('fName', fName);
    xhttp.setRequestHeader('lName', lName);
   
   
    xhttp.send();

    
    function receiveData() {
   
        var dataSection = document.getElementById('data');
        dataSection.innerHTML = '';
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
              
                var response = xhttp.responseText;
                console.log(response);
                document.getElementById('data').innerHTML = response;
                
            } else {
              
                dataSection.innerHTML = "Error reciving data"
            }
        } else {
          
        }
    }
}

var urlr= 'http://localhost:8080/ERSServer/resolve'

function accpetReq() {
    var reqNum = document.getElementById("reqID").value;
    
    
    var xhttp = new XMLHttpRequest();

    
    xhttp.onreadystatechange = receiveData;
    xhttp.open('POST', urlr); 
    xhttp.setRequestHeader('reqNum', reqNum);
    xhttp.setRequestHeader('status', 'accepted');
   
    xhttp.send();

    function receiveData() {
   
       
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
              
                var response = xhttp.responseText;
                console.log(response);
                location.replace("http://localhost:8080/ERSServer/login")
                
            } else {
              
                dataSection.innerHTML = "Error reciving data"
            }
        } else {}
    }
}



function denyReq() {
    var reqNum = document.getElementById("reqID").value;
    
    
    var xhttp = new XMLHttpRequest();

    
    xhttp.onreadystatechange = receiveData;
    xhttp.open('POST', urlr); 
    xhttp.setRequestHeader('reqNum', reqNum);
    xhttp.setRequestHeader('status', 'deny');
   
   
    xhttp.send();
    
    function receiveData() {
   
       
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
              
                var response = xhttp.responseText;
                console.log(response);
                location.replace("http://localhost:8080/ERSServer/login")
                
            } else {
              
                dataSection.innerHTML = "Error reciving data"
            }
        } else {}
    }
    
    
}