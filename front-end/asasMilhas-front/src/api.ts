/ src/api.js
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080", // ajuste para sua API
});

export default api;
