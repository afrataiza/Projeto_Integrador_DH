import { useState } from "react"; 
import Input from '../../components/Input'
import Button from '../../components/Button'


const Cadastro = () => { 
    const [email, setEmail] = useState(""); 
    const [senha, setSenha] = useState("");
   return(
      <section>
       <h1>Criar sua conta</h1>
       <label htmlFor="text">Nome</label>
       <Input type="text" placeholder="Digite seu nome" required ></Input>
       <Input type="text" placeholder="Digite seu sobrenome" required ></Input>
       <Input type="email" value={email} onChange={(e) => {setEmail(e.target.value), setError("")}} placeholder="Digite seu e-mail" required ></Input>
       <Input type="email" value={email} onChange={(e) => {setEmail(e.target.value), setError("")}} placeholder="Repita seu e-mail" required ></Input>
       <Input type="password" value={senha} onChange={(e) => {setSenha(e.target.value), setError("")}} placeholder="Digite sua senha" required ></Input>
      </section>
   )
}

export default Cadastro; 