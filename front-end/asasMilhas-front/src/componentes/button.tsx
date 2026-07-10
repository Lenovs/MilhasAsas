// src/componentes/Button.js
import React from "react";

const Button = ({ label, active, onClick }) => {
  return (
    <button
      className={active ? "btn active" : "btn"}
      onClick={onClick}
    >
      {label}
    </button>
  );
};

export default Button;
