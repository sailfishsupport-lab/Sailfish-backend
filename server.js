const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');

const app = express();

// Middleware
app.use(express.json());
app.use(cors());

// MongoDB Connection
const dbURI = process.env.DB_URL;

mongoose.connect(dbURI)
    .then(() => console.log('Database Connected Successfully'))
    .catch(err => console.error('DB Connection Error:', err));

// Basic Route to check if server is live
app.get('/', (req, res) => {
    res.send('Sailfish Backend Engine is running perfectly.');
});

// Port configuration
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
