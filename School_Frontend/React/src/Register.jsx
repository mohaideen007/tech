import { useState } from "react";
import axios from 'axios';
import { Link,useNavigate } from "react-router-dom";
function Register(){
const[username,setUsername]=useState("");
const[password,setPassword]=useState("");


const navigate=useNavigate();


function handleRegister(e){
    e.preventDefault();
    if (username && password) {
        axios.post("http://localhost:9000/user/adduser", {
            username: username,
            password: password
        })
        .then(r => {
            console.log(r.data);
            navigate("/login");
            alert("Registered Successfully")
        })
        .catch(err => {
            alert("Registration failed");
            console.error(err);
        });
    } else {
        alert("Enter Username and Password");
    }
}


    return(
        <>
        <div className="register-container">
  <form className="register-form" onSubmit={handleRegister}>
    <h2>Create Account</h2>

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

    <button type="submit">Register</button>

    <Link to="/login">
      <button type="button">Or Login</button>
    </Link>
  </form>
</div>

        </>
    )
}

export default Register;