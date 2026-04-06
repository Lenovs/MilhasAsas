import { useState } from 'react'
import Header from './componentes/header'
import Pesquisa from './componentes/pesquisa'
import '/src/pesquisa.css'
import '/src/header.css'
function App() {
  const [count, setCount] = useState(0)

  return (
      <div>
      <Header/>
      <Pesquisa/>
      </div>
      );
};

export default App;
