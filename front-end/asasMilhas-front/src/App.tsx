import { useState } from 'react'
import Header from './componentes/header'
import Pesquisa from './componentes/pesquisa'
import Banner1 from './componentes/banner1'
import SectionOfertas from './componentes/section'
import BannerFinal from './componentes/bannerFinal'
import Footer from './componentes/footer'
import '/src/pesquisa.css'
import '/src/header.css'
import '/src/banner1.css'
import '/src/section.css'
import '/src/bannerFinal.css'
import '/src/footer.css'
function App() {
  const [count, setCount] = useState(0)

  return (
      <div>
      <Header/>
      <Pesquisa/>
      <Banner1/>
      <SectionOfertas/>
      <SectionOfertas/>
      <BannerFinal/>
      <Footer/>
      </div>
      );
};

export default App;
