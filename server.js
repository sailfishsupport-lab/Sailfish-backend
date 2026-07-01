const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const app = express();

app.use(express.json());
app.use(cors()); // Render frontend se request allow karne ke liye

// Cloud MongoDB Connection String (Isme password badal lena baad mein)
const MONGO_URI = process.env.MONGO_URI || "mongodb+srv://admin:sailfish2026@cluster0.mongodb.net/sailfish";

mongoose.connect(MONGO_URI)
    .then(() => console.log("Sailfish Cloud Database Connected Successfully!"))
    .catch((err) => console.error("Database Connection Refused:", err));

// Database Model Schema
const User = mongoose.model('User', new mongoose.Schema({
    username: { type: String, required: true, unique: true },
    password: { type: String, required: true },
    role: { type: String, enum: ['user', 'vendor', 'partner'], default: 'user' },
    phone: { type: String, default: "9219914526" }
}));

// Main Login Endpoint
app.post('/api/auth/login', async (req, res) => {
    try {
        const { username, password } = req.body;
        if (!username || !password) {
            return res.status(400).json({ message: "All security fields are required." });
        }

        const cleanUsername = username.trim().replace(/['"`;()]/g, "");
        const user = await User.findOne({ username: cleanUsername });

        if (!user || password !== user.password) {
            return res.status(401).json({ message: "Identity code or security phrase mismatch." });
        }

        // Return successful data with user role
        return res.status(200).json({
            message: "Success",
            user: { phone: user.phone, role: user.role }
        });
    } catch (err) {
        return res.status(500).json({ message: "Internal Auth Engine Collapse." });
    }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
