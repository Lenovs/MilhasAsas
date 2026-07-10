import React , {useState}from 'react';
import Button from "./Button";

const Header: React.Rf =()=>{
    const [active, setActive] = useState('login');
    return (
    <header className="header">
        <div className = "nav-superior">
            <div className="logo">
              <img className="logoIMG" src="/img/logoAsas.png" alt='logo'/>
            </div>

            <nav className="list">
               <ul>
                  <li>
                   <Button  label="Login" active={active === "login"}
                  onClick={()=> setActive("login")}
                  />
                  </li>

                   <li>
                   <Button  label="Cadastrar" active={active === "cadastro"}
                   onClick={()=> setActive("cadastro")}
                    />
                    </li>

                    <li>
                    <Button  label="Ajuda" active={active === "ajuda"}
                    onClick={()=> setActive("ajuda")}
                     />
                     </li>
               </ul>
            <input type="text" placeholder=" Pesquisar" className="search-input"/>
            </nav>

        </div>
        <div className="linha"></div>
        <div className="nav-inferior">
             <ul>
                <li>Passagens Aéreas</li>
                <li>Hóteis</li>
                <li>Pacotes</li>
                <li>Trocar Milhas</li>
                <li>Comprar Milhas</li>
                <li>Empresas</li>
             </ul>
        </div>
    </header>
         );
      };
  export default Header;