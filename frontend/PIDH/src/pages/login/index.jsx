import { useState } from "react"; 
import Input from '../../components/Input'
import Button from '../../components/Button'

const Login = () => { 
  const [email, setEmail] = useState(""); 
  const [senha, setSenha] = useState("");
  const [error, setError] = useState(""); 
  return(
    <div>
     <h1>Iniciar sessão</h1>
     <div>
      <label htmlFor="text">Email</label>
      <Input type="email" value={email} onChange={(e) => {setEmail(e.target.value), setError("")}} placeholder="Digite seu e-mail" required ></Input>

      <label htmlFor="text">Senha</label>
      <Input type="password" value={senha} onChange={(e) => {setSenha(e.target.value), setError("")}} placeholder="Digite sua senha" required ></Input>
      <label htmlFor="text">{error}</label>
      <Button text="Entrar"></Button>
      <p>Ainda não tem conta <a href="">Registre-se</a></p>
     </div>
  

    </div>
  )
}


export default Login;