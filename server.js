const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');
const bcrypt = require('bcrypt');

const app = express();
const saltRounds = 10;

app.use(cors());
app.use(express.json());

// Database connection
const MONGO_URI = "mongodb+srv://sailfishsupport_db_user:BuBbTU4fJimLidxj@sailfishdb.v3bvyte.mongodb.net/?appName=SailfishDB"; 
mongoose.connect(MONGO_URI)
    .then(() => console.log("Database connected"))
    .catch(err => console.log(err));

// User Schema
const userSchema = new mongoose.Schema({
    username: { type: String, required: true, unique: true },
    password: { type: String, required: true }
});
const User = mongoose.model('User', userSchema);

// Signup Endpoint
app.post('/signup', async (req, res) => {
    try {
        const { username, password } = req.body;
        const hashedPassword = await bcrypt.hash(password, saltRounds);
        
        const newUser = new User({ username, password: hashedPassword });
        await newUser.save();
        res.json({ status: "success", message: "Account ban gaya!" });
    } catch (err) {
        res.json({ status: "error", message: "Signup failed" });
    }
});

// Login Endpoint
app.post('/login', async (req, res) => {
    const { username, password } = req.body;
    const user = await User.findOne({ username });
    
    if (user) {
        const match = await bcrypt.compare(password, user.password);
        if (match) {
            res.json({ status: "success" });
        } else {
            res.json({ status: "error", message: "Galat password" });
        }
    } else {
        res.json({ status: "error", message: "User nahi mila" });
    }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));

