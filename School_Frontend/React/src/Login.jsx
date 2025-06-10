import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import './Login.css';

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate(); // <-- for redirection

  function handleLogin(e) {
    e.preventDefault(); // prevent form from submitting normally

    if (!username || !password) {
      alert("Enter both username and password.");
      return;
    }

    axios.post("http://localhost:9000/user/login", {
      username: username,
      password: password
    })
    .then(response => {
      const token = response.data.token || response.data;
      if (token) {
        localStorage.setItem("token", token);
        console.log("Token stored:", token);
        alert("Login successful!");
        navigate("/home"); // <-- redirect only after success
      } else {
        alert("Login failed: No token received");
      }
    })
    .catch(err => {
      alert("Login failed: Invalid credentials");
      console.error(err);
    });
  }

  return (
    <div className="register-container">
      <form className="register-form" onSubmit={handleLogin}>
        <h2>Login</h2>

        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button type="submit">Login</button>

        <Link to="/">
          <button type="button">Or Register</button>
        </Link>
      </form>
    </div>
  );
}

export default Login;
