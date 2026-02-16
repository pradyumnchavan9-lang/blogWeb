import { useState } from "react";
import api from "../api/api"
import "./Login.css";

function Login() {

    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");

    const [error,setError] = useState("")

    async function handleSubmit(e){
        e.preventDefault();
        setError("");

        try{
            const response = await api.post("/auth/login",{
                username,
                password
            });

            const token = response.data.token;

            localStorage.setItem("token",token);

            alert("Login Successful");

        }catch(error){
            setError("Invalid username or password");
        }

    }

    return(
        <div className = "login-container">
            <div className = "login-card">
                <h2>Login</h2>
                {error && <div className = "login-error">{error}</div>}
                <form onSubmit={handleSubmit}>
                    <input
                        type = "text"
                        placeholder = "Username"
                        value = {username}
                        onChange = {(e) => setUsername(e.target.value)}
                    />

                    <br /><br />

                    <input
                        type = "password"
                        placeholder = "Password"
                        value = {password}
                        onChange = {(e) => setPassword(e.target.value)}
                    />

                    <br /><br />

                    <button type ="submit">Login</button>
                </form>
            </div>
         </div>
    );

}

export default Login;
