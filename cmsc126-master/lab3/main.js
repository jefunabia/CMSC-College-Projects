// Initialize Firebase
var config = {
    apiKey: "AIzaSyDi9hKvpviI3mqBUP-hSdmBh_1vPzNGAWc",
    authDomain: "cmsc126-dances.firebaseapp.com",
    databaseURL: "https://cmsc126-dances.firebaseio.com",
    projectId: "cmsc126-dances",
    storageBucket: "cmsc126-dances.appspot.com",
    messagingSenderId: "786758937792"
  };
  firebase.initializeApp(config);

// reference messages collection
var messagesRef = firebase.database().ref('messages');

// listen for form submit
document.getElementById('contactForm').addEventListener('submit', submitForm);

// Submit form
function submitForm(e){
    e.preventDefault();

    // get values
    var email = getInputVal('email');
    var name = getInputVal('name');
    var message = getInputVal('message');

    // save message
    saveMessage(name, email, message);

    // show alert
    document.querySelector('.alert').style.display = 'block';

    // hide alert after 3 seconds
    setTimeout(function(){
        document.querySelector('.alert').style.display = 'none';
    }, 3000);

    // to clear form after submission data in the fields
    document.getElementById('contactForm').reset();
}

// function to get form values
function getInputVal(id){
    return document.getElementById(id).value;
}

// save message to firebase
function saveMessage(name, email, message){
    var newMessageRef = messagesRef.push();
    newMessageRef.set({
        name: name,
        email: email,
        message: message
    })
}