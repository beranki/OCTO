var electron = require("electron");

function sendForm(event) {
    event.preventDefault(); // stop the form from submitting
    let firstname = document.getElementById("firstname").value;
    electron.ipcRenderer.send('form-submission', firstname)
}