const express = require('express');
const bodyParser = require('body-parser');
const app = express();

app.use(bodyParser.urlencoded( {extended: true}));


app.get('/home.html', (req, res) => {
    res.sendFile(__dirname + '/home.html');
});

app.get('/faqs.html', (req, res) => {
    res.sendFile(__dirname + '/faqs.html');
});

app.get('/about.html', (req, res) => {
    res.sendFile(__dirname + '/about.html');
});

app.get('/myProfile.html', (req, res) => {
    res.sendFile(__dirname + '/myProfile.html');
});

app.get('/settings.html', (req, res) => {
    res.sendFile(__dirname + '/settings.html');
});

app.get('/account.html', (req, res) => {
    res.sendFile(__dirname + '/account.html');
});

app.get('/help.html', (req, res) => {
    res.sendFile(__dirname + '/help.html');
});

app.get('/login.html', (req, res) => {
    res.sendFile(__dirname + '/login.html');
});

app.get('/selectTags.html', (req, res) => {
    res.sendFile(__dirname + '/selectTags.html');
});

app.get('coin shop.html', (req, res) => {
    res.sendFile(__dirname + '/coin shop.html');
});

app.get('/home.html/:id', (req, res) => {
    console.log(req.body);
});


const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listening on port ${port}...`));
