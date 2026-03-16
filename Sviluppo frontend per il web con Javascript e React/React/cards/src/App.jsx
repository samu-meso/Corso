import Card from "./components/Card";

function App() {
  return (
    <div>
      {/* Piero Experimento, panini experimentali */}
      <h1>Il nostro primo componente funzionale in React!</h1>
      <Card sottotitolo="Aaa" paragrafoUno="Titolo prima immagine" paragrafoDue="Descrizione prima immagine"/>
      <Card sottotitolo="Bbb" paragrafoUno="Titolo seconda immagine" paragrafoDue="Descrizione seconda immagine"/>
      <Card sottotitolo="Ccc" paragrafoUno="Titolo terza immagine" paragrafoDue="Descrizione terza immagine"/>
    </div>
  )
}

export default App;