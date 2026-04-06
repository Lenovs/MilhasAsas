import React from 'react'

const Header: React.Rf =()=>{
    return (
    <header className="header">
        <div className = "nav-superior">
            <div className="logo">
              <img className="logoIMG" src="/img/logoAsas.png" alt='logo'/>
            </div>

            <nav className="list">
               <ul>
                  <li> Login</li>
                  <li>Cadastrar </li>
                  <li> Ajuda</li>
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