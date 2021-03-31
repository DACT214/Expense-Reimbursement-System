

var url = 'http://localhost:8080/ERSServer/userView'


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
        } else {
          
        }
    }
}