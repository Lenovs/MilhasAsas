import { useState } from 'react'
import Header from './componentes/header'
import Pesquisa from './componentes/pesquisa'
import Banner1 from './componentes/banner1'
import SectionOfertas from './componentes/section'
import '/src/pesquisa.css'
import '/src/header.css'
import '/src/banner1.css'
function App() {
  const [count, setCount] = useState(0)

  return (
      <div>
      <Header/>
      <Pesquisa/>
      <Banner1/>
      <SectionOfertas/>
      </div>
      );
};

export default App;
