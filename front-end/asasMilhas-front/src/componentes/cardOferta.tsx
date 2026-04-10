import React from 'react'

const Card : React.RF = ()=>{
    return(
         <div className="card">
              <img className="imgSection" src="/img/paisagem-01.jpg" alt="img" />
                  <p className="destino">São Paulo</p>
                  <p className="valor">A parti de R$ 300,00</p>
                  <p className="milhasNecessarias"> Use acima de 5.000 Milhas</p>
         </div>
        );
    };
export default Card;