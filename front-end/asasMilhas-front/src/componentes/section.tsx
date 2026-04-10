import React from 'react'
import Card from './cardOferta'

const SectionOfertas: React.RF = ()=>{
    return(
          <div className="sectionOferta">
            <div className="ofertasCard">
            <p className="tituloOferta">Passsagem Aéreas </p>

             <div className="ofertas">
               <Card/>
               <Card/>
               <Card/>
               <Card/>
             </div>

            </div>

          </div>
        );
    };
export default SectionOfertas;