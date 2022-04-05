const mongoose = require('mongoose');
// const User = require('./models/user');
const Joi = require('joi');
const express = require('express');
const app = express();
const users = require('./routes/user');
const auth = require('./routes/auth')
//const uri = 'mongodb+srv://bajunkinizer:Buzziscool1@128project-rc3bu.mongodb.net/test?retryWrites=true';
const uri = 'mongodb://bajunkinizer:Buzziscool1@128project-shard-00-00-rc3bu.mongodb.net:27017,128project-shard-00-01-rc3bu.mongodb.net:27017,128project-shard-00-02-rc3bu.mongodb.net:27017/test?ssl=true&replicaSet=128project-shard-0&authSource=admin&retryWrites=true';

mongoose.connect(uri)
.then( () => console.log('Connected to DB'))
.catch( err => console.error('Error connecting', err));

app.use(express.json());
app.use('/api/users', users);
app.use('/api/auth', auth);
app.get('/', function (req, res) {
    res.send('hello world')
  })



const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listening on port ${port}...`));

