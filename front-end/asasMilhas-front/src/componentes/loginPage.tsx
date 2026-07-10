import React from 'react'

const LoginPage:React.RF = ()=>{
    return(
        <nav className="pageLogin">
            <ul>
              <li className="email" >
                  <input type="text" placeholder=" email" className="email-input"/>
              </li>

               <li className="password" >
                   <input type="text" placeholder="password" className="password-input"/>
              </li>
            </ul>

        </nav>
        );
    };