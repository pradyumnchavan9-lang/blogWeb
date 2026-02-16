import { useState } from "react";
import api from "../api/api";
import "./Register.css";

function Register() {

    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");
    const [email,setEmail] = useState("");

    const [success,setSuccess] = useState("")
    const [error,setError] = useState("")

    async function handleSubmit(e){
        e.preventDefault();

        setSuccess("")
        setError("")

        try{
            const response = await api.post("/auth/register",{
                username,
                email,
                password
            });

            setUsername("")
            setEmail("")
            setPassword("")

            setSuccess("Registered successfully! You can Login Now");

        }catch(error){

            setError(error.response?.data.message || "Something went wrong");
        }
    }

    return (
        <div className = "register-container">
            <div className = "register-card">
                {success && <div className = "register-success">{success}</div>}
                {error && <div className = "register-error">{error}</div>}
                <form onSubmit = {handleSubmit}>
                    <input
                        type = "text"
                        placeholder = "Username"
                        value = {username}
                        onChange = {(e) => setUsername(e.target.value)}
                    />

                    <br /><br />

                    <input
                         type = "email"
                         placeholder = "Email"
                         value = {email}
                         onChange = {(e) => setEmail(e.target.value)}
                    />

                    <br /><br />

                    <input
                        type = "password"
                        placeholder = "Password"
                        value = {password}
                        onChange = {(e) => setPassword(e.target.value)}
                    />

                    <br /><br />



                    <button type ="submit">Register</button>
                </form>
            </div>
        </div>
    );

}

export default Register;
