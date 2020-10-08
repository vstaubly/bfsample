//
// Code to call REST API for air traffic control queue management
//
function loadTable()
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var listObj = JSON.parse(this.responseText);
            var tableContents = "<tr><th>Name</th><th>Type</th><th>Size</th></tr>";
            if ((listObj) && (Array.isArray(listObj))) {
                for (var i = 0; i < listObj.length; i++) {
                    tableContents += "<tr><td>" + listObj[i].name + "</td><td>" + listObj[i].type +
                        "</td><td>" + listObj[i].size + "</td></tr>";
                }
            }
            document.getElementById("queueTable").innerHTML = tableContents;
        }
    };
    xhttp.open("GET", "/", true);
    xhttp.send();
}

function addToQueue(obj)
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("status").innerHTML = this.responseText;
            loadTable();
        }
    };
    xhttp.open("POST", "/", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(obj));
}

function removeFromQueue()
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var contents = "empty queue";
            if (this.responseText != "") {
                var planeObj = JSON.parse(this.responseText);
                if (planeObj) {
                    contents = "Name: " + planeObj.name + "<br/>Type: " + planeObj.type + "<br/>Size: " + planeObj.size;
                }
            }
            document.getElementById("next").innerHTML = contents;
            loadTable();
        }
    };
    xhttp.open("DELETE", "/", true);
    xhttp.send();
}

function addPlane()
{
    var pName = document.getElementById("name").value;
    var pType = document.getElementById("type").value;
    var pSize = document.getElementById("size").value;
    var obj = { name: pName, type: pType, size: pSize };
    addToQueue(obj);
}

function getNextPlane()
{
    document.getElementById("status").innerHTML = "Getting next plane";
    removeFromQueue();
}
